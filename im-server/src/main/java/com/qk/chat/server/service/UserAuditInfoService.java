package com.qk.chat.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.chat.common.exception.BusinessException;
import com.qk.chat.server.domain.entity.UserAuditInfo;
import com.qk.chat.server.domain.param.AuditApplyParam;
import com.qk.chat.server.domain.param.FindUserSecretParam;
import com.qk.chat.server.domain.param.FriendApplyParam;
import com.qk.chat.server.domain.vo.UserFriendApplyVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author young
 * @since 2023年09月05日
 */
public interface UserAuditInfoService extends IService<UserAuditInfo> {
    /**
     * 查询好友
     * @param findUserSecretParam
     * @return
     */
    UserFriendApplyVO findFriendService(FindUserSecretParam findUserSecretParam) throws BusinessException;
    
    String applyFriendService(FriendApplyParam friendApplyParam);

    List<UserAuditInfo> friendListService(String userId,Integer pageNum,Integer pageSize,String keyword);
    
    String auditApplyService(AuditApplyParam auditApplyParam);
}
