package com.qk.chat.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.chat.server.domain.entity.ImFriendshipGroup;
import com.qk.chat.server.domain.param.AddMemberParam;
import com.qk.chat.server.domain.param.DelMemberParam;
import com.qk.chat.server.domain.param.FriendGroupAddParam;
import com.qk.chat.server.domain.param.FriendGroupEditParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author codehd
 * @since 2023年11月10日
 */
public interface ImFriendshipGroupService extends IService<ImFriendshipGroup> {
    boolean createGroupService(FriendGroupAddParam friendGroupAddParam);

    boolean editGroupService(FriendGroupEditParam friendGroupEditParam);

    boolean addMemberService(AddMemberParam addMemberParam);

    boolean delMemberService(DelMemberParam delMemberParam);
}
