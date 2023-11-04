package com.qk.chat.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.inspur.plugins.common.util.TextUtil;
import com.qk.chat.common.constant.Constant;
import com.qk.chat.common.constant.ConstantError;

import com.qk.chat.server.common.exception.Asserts;
import com.qk.chat.server.dao.UserAuditInfoDao;
import com.qk.chat.server.dao.UserRelationInfoDao;
import com.qk.chat.server.domain.entity.UserRelationInfo;
import com.qk.chat.server.domain.param.AuditApplyParam;
import com.qk.chat.server.domain.param.DeleteFriendParam;
import com.qk.chat.server.domain.param.FriendAddParam;
import com.qk.chat.server.mapper.UserAuditInfoMapper;
import com.qk.chat.server.mapper.ImUserInfoMapper;
import com.qk.chat.server.domain.entity.UserAuditInfo;
import com.qk.chat.server.domain.entity.ImUserInfo;
import com.qk.chat.server.domain.param.FindUserSecretParam;
import com.qk.chat.server.domain.vo.UserFriendApplyVO;
import com.qk.chat.server.mapper.UserRelationInfoMapper;
import com.qk.chat.server.service.UserAuditInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author young
 * @since 2023年09月05日
 */
@Service
public class UserAuditInfoServiceImpl extends ServiceImpl<UserAuditInfoMapper, UserAuditInfo> implements UserAuditInfoService {

    @Autowired
    ImUserInfoMapper userInfoMapper;
    
    @Autowired
    UserAuditInfoMapper userAuditInfoMapper;
    
    @Autowired
    UserRelationInfoDao userRelationInfoDao;
    
    @Autowired
    UserAuditInfoDao userAuditInfoDao;
    
    @Autowired
    UserRelationInfoMapper userRelationInfoMapper;
    
    @Override
    public UserFriendApplyVO findFriendService(FindUserSecretParam findUserSecretParam) {
        ImUserInfo imUserInfo = userInfoMapper.getUserBaseInfo(findUserSecretParam.getUserIdentify());
        Asserts.isTrue(TextUtil.isNull(imUserInfo), ConstantError.FIND_USER_IS_EXISTS);
        return UserFriendApplyVO.builder()
                .userId(imUserInfo.getUserId())
                .nickName(imUserInfo.getNickName())
                .userSecretIdentify(imUserInfo.getEmail())
                .avatar(imUserInfo.getAvatar())
                .build();
    }

    @Override
    public boolean applyFriendService(String userId, FriendAddParam friendAddParam) {
//        Asserts.isTrue(userId.equals(friendAddParam.getAuditId()),ConstantError.NOT_ADD_ONESELF_FRIEND);
//        Asserts.isTrue(userRelationInfoDao.checkExistsFriendRelation(friendAddParam.getAuditId(),userId).size() > 0, ConstantError.FIND_EXIST_USER_RELA);
//        Asserts.isTrue(userAuditInfoDao.checkExistsFriendRelation(friendAddParam.getAuditId(),userId).size() > 0,ConstantError.NOT_IS_REPEAT_APPLY);
//        UserAuditInfo userAuditInfo = UserAuditInfo.builder()
//                .id(UUID.randomUUID().toString())
//                .userId(userId)
//                .businessType(friendAddParam.getBusinessType())
//                .businessId(friendAddParam.getAuditId())
//                .applyTime(new Date())
//                .auditUserId(friendAddParam.getAuditId())
//                .applyReason(friendAddParam.getApplyReason())
//                .auditStatus(0)
//                .build();
//        userAuditInfoMapper.insert(userAuditInfo);
        return true;
    }

    @Override
    public List<UserAuditInfo> friendListService(String userId,Integer pageNum,Integer pageSize,String keyword) {
        PageHelper.startPage(pageNum,pageSize);
        return userAuditInfoMapper.getUserAuditInfoList(userId, keyword);
    }

    @Override
    public boolean auditApplyService(String userId,AuditApplyParam auditApplyParam) {
        Asserts.isTrue(userRelationInfoDao.checkExistsFriendRelation(userId,auditApplyParam.getFriendUserId()).size() > 0,ConstantError.FIND_EXIST_USER_RELA);
        if (Constant.IS_NO.equals(auditApplyParam.getAuditDetail())){
            //如果是拒绝 修改状态审核是按
            userAuditInfoMapper.editTurnAuditStatus(2,userId,new Date(),auditApplyParam.getAuditReason());
            return true;
        }
        userAuditInfoMapper.editTurnAuditStatus(1,userId,new Date(),auditApplyParam.getAuditReason());
        this.doCreateRelationInfo(userId,auditApplyParam);
        return true;
    }

    @Override
    public boolean deleteFriendService(String userId, DeleteFriendParam deleteFriendParam) {
        //判断是否存在好友关系
        Asserts.isTrue(userRelationInfoDao.checkExistsFriendRelation(userId,deleteFriendParam.getFriendUserId()).size() < 1, ConstantError.NOT_EXIST_FRIEND_RELA);
        if (userRelationInfoMapper.deleteFriend(userId,deleteFriendParam.getFriendUserId()) 
                && userRelationInfoMapper.deleteFriend(deleteFriendParam.getFriendUserId(),userId)){
            return true;
        }
        return false;
    }

    public void doCreateRelationInfo(String userId,AuditApplyParam auditApplyParam){
        UserRelationInfo userRelationInfo1 = UserRelationInfo.builder()
                .id(UUID.randomUUID().toString())
                .userId(userId)
                .friendId(auditApplyParam.getFriendUserId())
                .auditTime(new Date())
                .applyTime(new Date())
                .build();
        UserRelationInfo userRelationInfo2 = UserRelationInfo.builder()
                .id(UUID.randomUUID().toString())
                .userId(auditApplyParam.getFriendUserId())
                .friendId(userId)
                .auditTime(new Date())
                .applyTime(new Date())
                .build();
        userRelationInfoMapper.insert(userRelationInfo1);
        userRelationInfoMapper.insert(userRelationInfo2);
    }
}
