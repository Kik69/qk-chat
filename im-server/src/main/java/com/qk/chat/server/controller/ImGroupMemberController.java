package com.qk.chat.server.controller;


import com.qk.chat.common.result.CommonResult;
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
}

