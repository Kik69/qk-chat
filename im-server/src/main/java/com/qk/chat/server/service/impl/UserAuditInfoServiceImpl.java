package com.qk.chat.server.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.inspur.plugins.common.util.TextUtil;
import com.qk.chat.common.exception.BusinessException;
import com.qk.chat.server.dao.UserAuditInfoDao;
import com.qk.chat.server.dao.UserRelationInfoDao;
import com.qk.chat.server.mapper.UserAuditInfoMapper;
import com.qk.chat.server.mapper.UserBaseInfoMapper;
import com.qk.chat.server.domain.entity.UserAuditInfo;
import com.qk.chat.server.domain.entity.UserBaseInfo;
import com.qk.chat.server.domain.param.FindUserSecretParam;
import com.qk.chat.server.domain.param.FriendApplyParam;
import com.qk.chat.server.domain.vo.UserFriendApplyVO;
import com.qk.chat.server.service.UserAuditInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    
    @Override
    public UserFriendApplyVO findFriendService(FindUserSecretParam findUserSecretParam) {
        UserBaseInfo userBaseInfo = userInfoMapper.getUserBaseInfo(findUserSecretParam.getUserIdentify());
        
        if (TextUtil.isNull(userBaseInfo)){
            throw new BusinessException("查无此人，请确认邮箱是否正确！");
        }
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
        Assert.isTrue(userAuditInfoDao.checkExistsFriendRelation(friendApplyParam.getApplyId(),friendApplyParam.getAuditId()).size() < 0,"已经是好友关系！");
        Assert.isTrue(userAuditInfoDao.checkExistsFriendRelation(friendApplyParam.getApplyId(),friendApplyParam.getAuditId()).size() < 0,"您已发起申请！");
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
}
