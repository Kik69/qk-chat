package com.qk.chat.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.chat.server.domain.entity.ImExamineInfo;
import com.qk.chat.server.domain.entity.ImFriendshipInfo;
import com.qk.chat.server.domain.param.FriendAddParam;
import com.qk.chat.server.domain.param.FriendApplyParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author codehd
 * @since 2023年11月02日
 */
public interface ImFriendshipService extends IService<ImFriendshipInfo> {
    boolean addFriendService(FriendAddParam friendAddParam);

    boolean applyFriendService(FriendApplyParam friendApplyParam);
}
