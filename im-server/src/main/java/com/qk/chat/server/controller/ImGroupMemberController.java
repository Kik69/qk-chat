package com.qk.chat.server.controller;


import com.qk.chat.common.result.CommonResult;
import com.qk.chat.server.domain.param.AddMemberParam;
import com.qk.chat.server.domain.param.DelMemberParam;
import com.qk.chat.server.domain.param.FriendGroupAddParam;
import com.qk.chat.server.domain.param.FriendGroupEditParam;
import com.qk.chat.server.service.ImFriendshipGroupService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author codehd
 * @since 2023年11月10日
 */
@RestController
@RequestMapping("/server/group")
@Validated
public class ImGroupMemberController {
    
    @Autowired
    ImFriendshipGroupService imFriendshipGroupService;
    
    @ApiOperation("创建分组")
    @RequestMapping(value = "/create-group",method = RequestMethod.POST)
    public CommonResult<Boolean> createGroup(@Valid @RequestBody FriendGroupAddParam friendGroupAddParam){
        return CommonResult.success(imFriendshipGroupService.createGroupService(friendGroupAddParam));
    }
    
    @ApiOperation("修改分组")
    @RequestMapping(value = "/edit-group",method = RequestMethod.POST)
    public CommonResult<Boolean> editGroup(@Valid @RequestBody FriendGroupEditParam friendGroupEditParam){
        return CommonResult.success(imFriendshipGroupService.editGroupService(friendGroupEditParam));
    }

    @ApiOperation("添加成员")
    @RequestMapping(value = "/add-member",method = RequestMethod.POST)
    public CommonResult<Boolean> addMember(@Valid @RequestBody AddMemberParam addMemberParam){
        return CommonResult.success(imFriendshipGroupService.addMemberService(addMemberParam));
    }

    @ApiOperation("删除成员")
    @RequestMapping(value = "/del-member",method = RequestMethod.POST)
    public CommonResult<Boolean> delMember(@Valid @RequestBody DelMemberParam delMemberParam){
        return CommonResult.success(imFriendshipGroupService.delMemberService(delMemberParam));
    }
}

