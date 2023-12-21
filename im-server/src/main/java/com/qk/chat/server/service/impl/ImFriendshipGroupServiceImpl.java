package com.qk.chat.server.service.impl;


import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qk.chat.common.constant.ConstantError;
import com.qk.chat.common.utils.TextUtil;
import com.qk.chat.server.common.exception.Asserts;
import com.qk.chat.server.domain.entity.ImFriendshipGroup;
import com.qk.chat.server.domain.entity.ImGroupMemberEntity;
import com.qk.chat.server.domain.param.AddMemberParam;
import com.qk.chat.server.domain.param.DelMemberParam;
import com.qk.chat.server.domain.param.FriendGroupAddParam;
import com.qk.chat.server.domain.param.FriendGroupEditParam;
import com.qk.chat.server.mapper.ImFriendshipGroupMapper;
import com.qk.chat.server.mapper.ImGroupMemberMapper;
import com.qk.chat.server.service.ImFriendshipGroupService;
import com.qk.chat.web.context.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Autowired
    ImGroupMemberMapper imGroupMemberMapper;
    
    @Override
    public boolean createGroupService(FriendGroupAddParam friendGroupAddParam) {
        String userId = ThreadContext.getLoginToken().getUserId();
        List<String> allByGroupNameList = imFriendshipGroupMapper.getAllByGroupNameList(userId);
        Asserts.isTrue(TextUtil.isNotNull(allByGroupNameList) && allByGroupNameList.contains(friendGroupAddParam.getGroupName()), ConstantError.EXISTS_GROUP);
        this.doCreateGroup(friendGroupAddParam,userId);
        return true;
    }

    @Override
    public boolean editGroupService(FriendGroupEditParam friendGroupEditParam) {
        ImFriendshipGroup imFriendshipGroup = imFriendshipGroupMapper.selectById(friendGroupEditParam.getId());
        Asserts.isTrue(TextUtil.isNull(imFriendshipGroup),ConstantError.NOT_EXISTS_GROUP);
        ImFriendshipGroup friendshipGroup = new ImFriendshipGroup();
        //修改分组信息
        friendshipGroup.setId(friendGroupEditParam.getId());
        if (TextUtil.isNotNull(friendGroupEditParam.getGroupName())) {
            friendshipGroup.setGroupName(friendGroupEditParam.getGroupName());
        }
        friendshipGroup.setUpdateTime(new DateTime());
        imFriendshipGroupMapper.update(friendshipGroup,null);
        return true;
    }

    @Override
    public boolean addMemberService(AddMemberParam addMemberParam) {
        this.addGroupMember(addMemberParam.getGroupId(),addMemberParam.getToIds());
        return true;
    }

    @Override
    public boolean delMemberService(DelMemberParam delMemberParam) {
        String groupId = delMemberParam.getGroupId();
        String toId = delMemberParam.getToId();
        LambdaQueryWrapper<ImFriendshipGroup> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .eq(ImFriendshipGroup::getId,groupId)
                .eq(ImFriendshipGroup::getFromId,ThreadContext.getLoginToken().getUserId());
        List<ImFriendshipGroup> friendshipGroupList = imFriendshipGroupMapper.selectList(lambdaQueryWrapper);
        Asserts.isTrue(friendshipGroupList.size() != 1,ConstantError.NOT_EXISTS_GROUP);
        LambdaQueryWrapper<ImGroupMemberEntity> delWrapper = new LambdaQueryWrapper();
        delWrapper.eq(ImGroupMemberEntity::getGroupId,groupId)
                .eq(ImGroupMemberEntity::getToId,toId);
        imGroupMemberMapper.delete(delWrapper);
        return true;
    }

    public void doCreateGroup(FriendGroupAddParam friendGroupAddParam, String userId){
        String groupId = UUID.randomUUID().toString();
        ImFriendshipGroup imFriendshipGroup = new ImFriendshipGroup();
        imFriendshipGroup.setId(groupId);
        imFriendshipGroup.setAppId(0);
        imFriendshipGroup.setFromId(userId);
        imFriendshipGroup.setGroupName(friendGroupAddParam.getGroupName());
        imFriendshipGroup.setCreateTime(new DateTime());
        imFriendshipGroupMapper.insert(imFriendshipGroup);
    }
    
    public void addGroupMember(String groupId,List<String> toIds){
        List<String> allToId = toIds.stream().
                filter(toId -> TextUtil.isNull(imFriendshipGroupMapper.getGroupMemberInfo(groupId, toId)))
                .collect(Collectors.toList());
        allToId.forEach(toId -> imFriendshipGroupMapper.addGroupMemberData(groupId,toId));
    }


}
