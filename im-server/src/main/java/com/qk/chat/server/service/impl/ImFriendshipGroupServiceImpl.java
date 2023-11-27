package com.qk.chat.server.service.impl;


import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qk.chat.common.constant.ConstantError;
import com.qk.chat.common.enmu.RegisterTypeEnum;
import com.qk.chat.common.utils.TextUtil;
import com.qk.chat.server.common.exception.Asserts;
import com.qk.chat.server.domain.entity.ImFriendshipGroup;
import com.qk.chat.server.domain.param.FriendGroupAddParam;
import com.qk.chat.server.domain.param.FriendGroupEditParam;
import com.qk.chat.server.mapper.ImFriendshipGroupMapper;
import com.qk.chat.server.service.ImFriendshipGroupService;
import com.qk.chat.web.context.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public boolean createGroupService(FriendGroupAddParam friendGroupAddParam) {
        String userId = ThreadContext.getLoginToken().getUserId();
        List<String> allByGroupNameList = imFriendshipGroupMapper.getAllByGroupNameList(userId);
        Asserts.isTrue(TextUtil.isNotNull(allByGroupNameList) && allByGroupNameList.contains(friendGroupAddParam.getGroupName()), ConstantError.EXISTS_GROUP);
        this.addGroupMember(this.doCreateGroup(friendGroupAddParam,userId), friendGroupAddParam.getToIds());
        return true;
    }

    @Override
    public boolean editGroupService(FriendGroupEditParam friendGroupEditParam) {
        ImFriendshipGroup imFriendshipGroup = imFriendshipGroupMapper.selectById(friendGroupEditParam.getGroupId());
        Asserts.isTrue(TextUtil.isNull(imFriendshipGroup),ConstantError.NOT_EXISTS_GROUP);
        if (friendGroupEditParam.getIsType() == RegisterTypeEnum.IM_ZERO.getCode()){
            //修改分组信息 删除分组成员或者修改名称
            ImFriendshipGroup friendshipGroup = new ImFriendshipGroup();
            friendshipGroup.setGroupId(friendGroupEditParam.getGroupId());
            if (TextUtil.isNotNull(friendGroupEditParam.getGroupName())) friendshipGroup.setGroupName(friendshipGroup.getGroupName());
            friendshipGroup.setUpdateTime(new DateTime());
            imFriendshipGroupMapper.update(friendshipGroup,null);
        }
        if (friendGroupEditParam.getIsType() == RegisterTypeEnum.IM_ONE.getCode()){
            // 向分组内添加新的成员
        }
        return false;
    }

    public String doCreateGroup(FriendGroupAddParam friendGroupAddParam, String userId){
        String groupId = UUID.randomUUID().toString();
        ImFriendshipGroup imFriendshipGroup = new ImFriendshipGroup();
        imFriendshipGroup.setGroupId(groupId);
        imFriendshipGroup.setAppId(0);
        imFriendshipGroup.setFromId(userId);
        imFriendshipGroup.setGroupName(friendGroupAddParam.getGroupName());
        imFriendshipGroup.setCreateTime(new DateTime());
        imFriendshipGroupMapper.insert(imFriendshipGroup);
        return groupId;
    }
    
    public void addGroupMember(String groupId,List<String> toIds){
        toIds.stream().forEach(toId -> imFriendshipGroupMapper.addGroupMemberData(groupId,toId));
    }
}
