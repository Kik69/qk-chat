package com.qk.chat.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.chat.server.domain.entity.UserAuditInfo;
import com.qk.chat.server.domain.param.AuditApplyParam;
import com.qk.chat.server.domain.param.DeleteFriendParam;
import com.qk.chat.server.domain.param.FindUserSecretParam;
import com.qk.chat.server.domain.param.FriendAddParam;
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
    UserFriendApplyVO findFriendService(FindUserSecretParam findUserSecretParam);
    
    boolean applyFriendService(String userId, FriendAddParam friendAddParam);

    List<UserAuditInfo> friendListService(String userId,Integer pageNum,Integer pageSize,String keyword);
    
    boolean auditApplyService(String userId,AuditApplyParam auditApplyParam);
    
    boolean deleteFriendService(String userId, DeleteFriendParam deleteFriendParam);
}
