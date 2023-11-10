package com.qk.chat.server.service.impl;


import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.inspur.plugins.common.util.TextUtil;
import com.qk.chat.common.constant.ConstantError;
import com.qk.chat.server.common.exception.Asserts;
import com.qk.chat.server.domain.entity.ImFriendshipGroup;
import com.qk.chat.server.domain.param.FriendGroupParam;
import com.qk.chat.server.mapper.ImFriendshipGroupMapper;
import com.qk.chat.server.service.ImFriendshipGroupService;
import com.qk.chat.web.context.ThreadContext;
import org.checkerframework.checker.units.qual.A;
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
 * @author codehd
 * @since 2023年11月10日
 */
@Service
public class ImFriendshipGroupServiceImpl extends ServiceImpl<ImFriendshipGroupMapper, ImFriendshipGroup> implements ImFriendshipGroupService {

    @Autowired
    ImFriendshipGroupMapper imFriendshipGroupMapper;
    
    @Override
    public boolean createGroupService(FriendGroupParam friendGroupParam) {
        String userId = ThreadContext.getLoginToken().getUserId();
        List<String> allByGroupNameList = imFriendshipGroupMapper.getAllByGroupNameList(userId);
        Asserts.isTrue(TextUtil.isNotNull(allByGroupNameList) && allByGroupNameList.contains(friendGroupParam.getGroupName()), ConstantError.EXISTS_GROUP);
        this.doCreateGroup(friendGroupParam,userId);
        return true;
    }
    
    public void doCreateGroup(FriendGroupParam friendGroupParam,String userId){
        ImFriendshipGroup imFriendshipGroup = new ImFriendshipGroup();
        imFriendshipGroup.setGroupId(UUID.randomUUID().toString());
        imFriendshipGroup.setAppId(0);
        imFriendshipGroup.setFromId(userId);
        imFriendshipGroup.setGroupName(friendGroupParam.getGroupName());
        imFriendshipGroup.setCreateTime(new DateTime());
        imFriendshipGroupMapper.insert(imFriendshipGroup);
    }
}
