package com.qk.chat.server.controller;


import com.qk.chat.common.result.CommonResult;
import com.qk.chat.server.domain.param.FriendGroupParam;
import com.qk.chat.server.service.ImFriendshipGroupService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public CommonResult<Boolean> createGroup(@Valid @RequestBody FriendGroupParam friendGroupParam){
        return CommonResult.success(imFriendshipGroupService.createGroupService(friendGroupParam));
    }
}

