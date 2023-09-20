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
import com.qk.chat.server.mapper.UserAuditInfoMapper;
import com.qk.chat.server.mapper.UserBaseInfoMapper;
import com.qk.chat.server.domain.entity.UserAuditInfo;
import com.qk.chat.server.domain.entity.UserBaseInfo;
import com.qk.chat.server.domain.param.FindUserSecretParam;
import com.qk.chat.server.domain.param.FriendApplyParam;
import com.qk.chat.server.domain.vo.UserFriendApplyVO;
import com.qk.chat.server.mapper.UserRelationInfoMapper;
import com.qk.chat.server.service.UserAuditInfoService;
import com.qk.chat.web.context.LoginUserInfo;
import com.qk.chat.web.context.ThreadContext;
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
    UserBaseInfoMapper userInfoMapper;
    
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
        UserBaseInfo userBaseInfo = userInfoMapper.getUserBaseInfo(findUserSecretParam.getUserIdentify());
        Asserts.isTrue(TextUtil.isNull(userBaseInfo), ConstantError.FIND_USER_IS_EXISTS);
        return UserFriendApplyVO.builder()
                .userId(userBaseInfo.getUserId())
                .nickName(userBaseInfo.getNickName())
                .userSecretIdentify(userBaseInfo.getEmail())
                .avatar(userBaseInfo.getAvatar())
                .build();
    }

    @Override
    public String applyFriendService(FriendApplyParam friendApplyParam) {
        //查看是否有好友关系
        Asserts.isTrue(userRelationInfoDao.checkExistsFriendRelation(friendApplyParam.getApplyId(),friendApplyParam.getAuditId()).size() > 0, ConstantError.FIND_EXIST_USER_RELA);
        Asserts.isTrue(userAuditInfoDao.checkExistsFriendRelation(friendApplyParam.getApplyId(),friendApplyParam.getAuditId()).size() > 0,ConstantError.NOT_IS_REPEAT_APPLY);
        UserAuditInfo userAuditInfo = UserAuditInfo.builder()
                .id(UUID.randomUUID().toString())
                .userId(friendApplyParam.getApplyId())
                .businessType(friendApplyParam.getBusinessType())
                .businessId(friendApplyParam.getAuditId())
                .applyTime(new Date())
                .auditUserId(friendApplyParam.getAuditId())
                .applyReason(friendApplyParam.getApplyReason())
                .auditStatus(0)
                .build();
        userAuditInfoMapper.insert(userAuditInfo);
        return "申请成功";
    }

    @Override
    public List<UserAuditInfo> friendListService(String userId,Integer pageNum,Integer pageSize,String keyword) {
        PageHelper.startPage(pageNum,pageSize);
        return userAuditInfoMapper.getUserAuditInfoList(userId, keyword);
    }

    @Override
    public String auditApplyService(AuditApplyParam auditApplyParam) {
        LoginUserInfo loginToken = ThreadContext.getLoginToken();
        Asserts.isTrue(userRelationInfoDao.checkExistsFriendRelation(loginToken.getUserId(),auditApplyParam.getFriendUserId()).size() > 0,ConstantError.FIND_EXIST_USER_RELA);
        String auditDetail = auditApplyParam.getAuditDetail();
        try {
            if (Constant.IS_NO.equals(auditDetail)){
                //如果是拒绝 修改状态审核是按
                userAuditInfoMapper.editTurnAuditStatus(loginToken.getUserId(),new Date(),auditApplyParam.getAuditReason());
            }else {
                userAuditInfoMapper.editPassAuditStatus(loginToken.getUserId(),new Date(),auditApplyParam.getAuditReason());
                //增加好友关系
                this.doCreateRelationInfo(auditApplyParam);
            }
            return "审核成功";
        }catch (Exception e){
            e.printStackTrace();
            return "审核失败";
        }
    }
    
    public void doCreateRelationInfo(AuditApplyParam auditApplyParam){
        UserRelationInfo userRelationInfo = UserRelationInfo.builder().id(UUID.randomUUID().toString())
                .userId(ThreadContext.getLoginToken().getUserId())
                .friendId(auditApplyParam.getFriendUserId())
                .auditTime(new Date())
                .applyTime(new Date())
                .build();
        userRelationInfoMapper.insert(userRelationInfo);
    }
}
