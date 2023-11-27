package com.qk.chat.server.service.impl;


import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.qk.chat.common.constant.ConstantError;
import com.qk.chat.common.enmu.RegisterTypeEnum;
import com.qk.chat.common.utils.TextUtil;
import com.qk.chat.server.common.exception.Asserts;
import com.qk.chat.server.domain.entity.ImExamineInfo;
import com.qk.chat.server.domain.entity.ImFriendshipInfo;
import com.qk.chat.server.domain.entity.ImUserInfo;
import com.qk.chat.server.domain.param.*;
import com.qk.chat.server.domain.vo.FriendRelationVO;
import com.qk.chat.server.domain.vo.UserFriendApplyVO;
import com.qk.chat.server.domain.vo.UserFriendListVO;
import com.qk.chat.server.mapper.ImExamineInfoMapper;
import com.qk.chat.server.mapper.ImFriendshipMapper;
import com.qk.chat.server.mapper.ImUserInfoMapper;
import com.qk.chat.server.service.ImFriendshipService;
import com.qk.chat.web.context.LoginUserInfo;
import com.qk.chat.web.context.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author codehd
 * @since 2023年11月02日
 */
@Service
public class ImFriendshipServiceImpl extends ServiceImpl<ImFriendshipMapper, ImFriendshipInfo> implements ImFriendshipService {

    @Autowired
    ImFriendshipMapper imFriendshipMapper;
    
    @Autowired
    ImUserInfoMapper imUserInfoMapper;
    
    @Autowired
    ImExamineInfoMapper imExamineInfoMapper;
    
    @Override
    public boolean addFriendService(FriendAddParam friendAddParam) {
        LoginUserInfo loginToken = ThreadContext.getLoginToken();
        ImFriendshipInfo friendOneInfo = imFriendshipMapper.getFriendOneInfo(loginToken.getUserId(), friendAddParam.getToId());
        ImUserInfo userInfo = imUserInfoMapper.getUserInfoById(friendAddParam.getToId());
        Asserts.isTrue(!TextUtil.isNull(friendOneInfo) && friendOneInfo.getStatus() == RegisterTypeEnum.IM_ONE.getCode(), ConstantError.FIND_EXIST_USER_RELA);
        if (userInfo.getFriendAllowType() == RegisterTypeEnum.IM_ONE.getCode()){
            if (TextUtil.isNull(friendOneInfo)){
                this.doCreateFriendRecord(friendAddParam,loginToken.getUserId());
            }
            if (!TextUtil.isNull(friendOneInfo) && friendOneInfo.getStatus() == RegisterTypeEnum.IM_TWO.getCode()){
                //修改原来的状态为正常
                ImFriendshipInfo imFriendshipInfo = new ImFriendshipInfo();
                imFriendshipInfo.setId(friendOneInfo.getId());
                imFriendshipInfo.setStatus(RegisterTypeEnum.IM_ONE.getCode());
                imFriendshipMapper.updateById(imFriendshipInfo);
            }
            return true;
        }
        //增加审核环节
        Asserts.isTrue(!TextUtil.isNull(imExamineInfoMapper.getExamineInfo(loginToken.getUserId(),friendAddParam.getToId())),ConstantError.NOT_IS_REPEAT_APPLY);
        this.doCreateApplyRecord(friendAddParam,loginToken.getUserId());
        return true;
    }

    @Transactional
    @Override
    public boolean applyFriendService(FriendApplyParam friendApplyParam) {
        LoginUserInfo loginToken = ThreadContext.getLoginToken();
        ImExamineInfo examineInfo = imExamineInfoMapper.getExamineInfo(friendApplyParam.getFromId(), loginToken.getUserId());
        Asserts.isTrue(TextUtil.isNull(examineInfo) || examineInfo.getApproveStatus() != RegisterTypeEnum.IM_THREE.getCode(),ConstantError.DATA_ERROR);
        ImExamineInfo imExamineInfo = new ImExamineInfo();
        imExamineInfo.setId(examineInfo.getId());
        imExamineInfo.setReadStatus(RegisterTypeEnum.IM_ONE.getCode());
        imExamineInfo.setApproveStatus(friendApplyParam.getApproveStatus());
        imExamineInfo.setUpdateTime(new DateTime());
        imExamineInfoMapper.updateById(imExamineInfo);
        //添加好友关系
        FriendAddParam friendAddParam = new FriendAddParam();
        friendAddParam.setToId(loginToken.getUserId());
        friendAddParam.setRemark(friendApplyParam.getRemark());
        friendAddParam.setAddSource(examineInfo.getAddSource());
        this.doCreateFriendRecord(friendAddParam, friendApplyParam.getFromId());
        return true;
    }

    @Override
    public UserFriendApplyVO findFriendService(FindUserSecretParam findUserSecretParam) {
        ImUserInfo imUserInfo = imUserInfoMapper.getUserBaseInfo(findUserSecretParam.getUserIdentify());
        Asserts.isTrue(TextUtil.isNull(imUserInfo), ConstantError.FIND_USER_IS_EXISTS);
        return UserFriendApplyVO.builder()
                .userId(imUserInfo.getUserId())
                .nickName(imUserInfo.getNickName())
                .userSecretIdentify(imUserInfo.getEmail())
                .avatar(imUserInfo.getAvatar())
                .build();
    }

    @Override
    public boolean deleteFriendService(FriendDeleteParam deleteParam) {
        LoginUserInfo loginToken = ThreadContext.getLoginToken();
        ImFriendshipInfo friendOneInfo = imFriendshipMapper.getFriendOneInfo(loginToken.getUserId(), deleteParam.getToId());
        Asserts.isTrue(TextUtil.isNull(friendOneInfo),ConstantError.DATA_ERROR);
        imFriendshipMapper.editFriendStatus(loginToken.getUserId(), deleteParam.getToId());
        return true;
    }

    @Override
    public List<UserFriendListVO> listFriendService(Integer pageNum, Integer pageSize, String keyword) {
        PageHelper.startPage(pageNum,pageSize);
        LoginUserInfo loginToken = ThreadContext.getLoginToken();
        List<String> friendToIds = imFriendshipMapper.getFriendToIds(loginToken.getUserId());
        if (TextUtil.isNull(friendToIds)){
            return null;
        }
        return imUserInfoMapper.getUserFriendList(friendToIds,keyword);
    }

    public List<FriendRelationVO> verifyFriendRelation(CheckFriendParam param) {
        String userId = ThreadContext.getLoginToken().getUserId();
        if (param.getCheckType() == RegisterTypeEnum.IM_ONE.getCode()){
            // 单向验证
            return this.addExistsFriend(param.getToIds(),this.getFriendRelationsByType(param,userId,true),userId);
        }
        if (param.getCheckType() == RegisterTypeEnum.IM_TWO.getCode()){
            // 双向验证
            return this.addExistsFriend(param.getToIds(),this.getFriendRelationsByType(param,userId,false),userId);
        }
        
        return Collections.emptyList();
    }

    @Override
    public boolean blackFriendService(FriendBlackParam friendBlackParam) {
        String userId = ThreadContext.getLoginToken().getUserId();
        ImFriendshipInfo friendOneInfo = imFriendshipMapper.getFriendOneInfo(userId, friendBlackParam.getToId());
        Asserts.isTrue(userId.equals(friendBlackParam.getToId()),ConstantError.NOT_BLACK_SELF);
        Asserts.isTrue(TextUtil.isNull(friendOneInfo),ConstantError.NOT_EXIST_USER_RELA);
        Asserts.isTrue(friendOneInfo.getBlack() == RegisterTypeEnum.IM_TWO.getCode(),ConstantError.ALREADY_BLACK_EXISTS);
        ImFriendshipInfo imFriendshipInfo = new ImFriendshipInfo();
        imFriendshipInfo.setId(friendOneInfo.getId());
        imFriendshipInfo.setBlack(RegisterTypeEnum.IM_TWO.getCode());
        imFriendshipMapper.updateById(imFriendshipInfo);
        return true;
    }

    public List<FriendRelationVO> addExistsFriend(List<String> toIds,List<FriendRelationVO> params,String userId){
        List<FriendRelationVO> newParams = toIds.stream()
                .filter(s -> !params.stream().map(FriendRelationVO::getToId).collect(Collectors.toList()).contains(s))
                .map(s -> {
                    FriendRelationVO relationVO = new FriendRelationVO();
                    relationVO.setFromId(userId);
                    relationVO.setToId(s);
                    relationVO.setStatus(0);
                    return relationVO;
                })
                .collect(Collectors.toList());
        params.addAll(newParams);
        return params;

    }

    /**
     * 根据类型获取好友关系列表
     * @param param 验证好友参数
     * @param userId 用户ID
     * @param isOneWayCheck 是否单向检测
     * @return 好友关系视图对象列表
     */
    private List<FriendRelationVO> getFriendRelationsByType(CheckFriendParam param, String userId, boolean isOneWayCheck){
        return isOneWayCheck ? imFriendshipMapper.getOneWayCheckList(param, userId)
                : imFriendshipMapper.getBothWayCheckList(param, userId);
    }

    public void doCreateFriendRecord(FriendAddParam friendAddParam,String userId){
        List<ImFriendshipInfo> friendList = new ArrayList<>();
        ImFriendshipInfo imFriendshipInfo = new ImFriendshipInfo();
        ImFriendshipInfo imFriendTargetInfo = new ImFriendshipInfo();
        imFriendshipInfo.setId(UUID.randomUUID().toString());
        imFriendshipInfo.setFromId(userId);
        imFriendshipInfo.setToId(friendAddParam.getToId());
        imFriendshipInfo.setRemark(friendAddParam.getRemark());
        imFriendshipInfo.setStatus(RegisterTypeEnum.IM_ONE.getCode());
        imFriendshipInfo.setBlack(RegisterTypeEnum.IM_ONE.getCode());
        imFriendshipInfo.setCreateTime(new DateTime());
        imFriendshipInfo.setAddSource(friendAddParam.getAddSource());

        imFriendTargetInfo.setId(UUID.randomUUID().toString());
        imFriendTargetInfo.setFromId(friendAddParam.getToId());
        imFriendTargetInfo.setToId(userId);
        imFriendTargetInfo.setRemark(friendAddParam.getRemark());
        imFriendTargetInfo.setStatus(RegisterTypeEnum.IM_ONE.getCode());
        imFriendTargetInfo.setBlack(RegisterTypeEnum.IM_ONE.getCode());
        imFriendTargetInfo.setCreateTime(new DateTime());
        imFriendTargetInfo.setAddSource(friendAddParam.getAddSource());
        friendList.add(imFriendshipInfo);
        friendList.add(imFriendTargetInfo);
        friendList.stream().forEach(friend -> imFriendshipMapper.insert(friend));
    }
    
    public void doCreateApplyRecord(FriendAddParam friendAddParam,String userId){
        ImExamineInfo imExamineInfo = new ImExamineInfo();
        imExamineInfo.setId(UUID.randomUUID().toString());
        imExamineInfo.setAppId(1);
        imExamineInfo.setFromId(userId);
        imExamineInfo.setToId(friendAddParam.getToId());
        imExamineInfo.setRemark(friendAddParam.getRemark());
        imExamineInfo.setReadStatus(RegisterTypeEnum.IM_THREE.getCode());
        imExamineInfo.setAddSource(friendAddParam.getAddSource());
        imExamineInfo.setApproveStatus(RegisterTypeEnum.IM_THREE.getCode());
        imExamineInfoMapper.insert(imExamineInfo);
    }
}
