package com.qk.chat.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qk.chat.common.constant.Constant;
import com.qk.chat.common.exception.BusinessException;
import com.qk.chat.server.dao.UserAuditInfoMapper;
import com.qk.chat.server.dao.UserBaseInfoMapper;
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
    
    @Override
    public UserFriendApplyVO findFriendService(FindUserSecretParam findUserSecretParam) {
        QueryWrapper<UserBaseInfo> userFriendApplyWrapper = new QueryWrapper<>();
        userFriendApplyWrapper.eq("email",findUserSecretParam.getUserIdentify());
        UserBaseInfo userInfo = userInfoMapper.selectOne(userFriendApplyWrapper);
        return UserFriendApplyVO.builder()
                .userId(userInfo.getUserId())
                .nickName(userInfo.getNickName())
                .userSecretIdentify(userInfo.getEmail())
                .avatar(userInfo.getAvatar())
                .build();
    }

    @Override
    public String applyFriendService(FriendApplyParam friendApplyParam) {
        try {
            UserAuditInfo userAuditInfo = UserAuditInfo.builder()
                    .id(UUID.randomUUID().toString())
                    .userId(friendApplyParam.getApplyId())
                    .businessType(Constant.BUSINESS_TYPE)
                    .businessId(friendApplyParam.getAuditId())
                    .applyTime(new Date())
                    .auditUserId(friendApplyParam.getAuditId())
                    .applyReason(friendApplyParam.getApplyReason())
                    .auditStatus(0)
                    .build();
            userAuditInfoMapper.insert(userAuditInfo);
            return "申请成功";
        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException("申请失败,系统内部异常");
        }
    }
}
