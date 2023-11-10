package com.qk.chat.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.chat.server.domain.entity.ImFriendshipGroup;
import com.qk.chat.server.domain.param.FriendGroupParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author codehd
 * @since 2023年11月10日
 */
public interface ImFriendshipGroupService extends IService<ImFriendshipGroup> {
    boolean createGroupService(FriendGroupParam friendGroupParam);
}
