package com.qk.chat.server.controller;


import com.qk.chat.common.result.CommonResult;
import com.qk.chat.server.common.pager.CommonPage;
import com.qk.chat.server.domain.entity.UserAuditInfo;
import com.qk.chat.server.domain.param.FriendAddParam;
import com.qk.chat.server.domain.param.FriendApplyParam;
import com.qk.chat.server.service.impl.ImFriendshipServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author codehd
 * @since 2023年11月02日
 */
@RestController
@RequestMapping("/server/friend")
@Validated
public class ImFriendshipController {
    
    @Autowired
    ImFriendshipServiceImpl imFriendshipService;
    
    @ApiOperation("添加好友")
    @RequestMapping(value = "add-friend",method = RequestMethod.POST)
    public CommonResult<Boolean> addFriend(@Valid @RequestBody FriendAddParam friendAddParam) {
        return CommonResult.success(imFriendshipService.addFriendService(friendAddParam));
    }

    @ApiOperation("审核好友")
    @RequestMapping(value = "apply-friend",method = RequestMethod.POST)
    public CommonResult<Boolean> applyFriend(@Valid @RequestBody FriendApplyParam friendApplyParam) {
        return CommonResult.success(imFriendshipService.applyFriendService(friendApplyParam));
    }
}

