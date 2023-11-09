package com.qk.chat.server.controller;


import com.qk.chat.common.result.CommonResult;
import com.qk.chat.server.domain.param.*;
import com.qk.chat.server.domain.vo.FriendRelationVO;
import com.qk.chat.server.domain.vo.UserFriendApplyVO;
import com.qk.chat.server.domain.vo.UserFriendListVO;
import com.qk.chat.server.service.impl.ImFriendshipServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

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

    @ApiOperation("查找好友")
    @RequestMapping(value = "/find-friend", method = RequestMethod.POST)
    public CommonResult<UserFriendApplyVO> findFriend(@Valid @RequestBody FindUserSecretParam findUserSecretParam) {
        return CommonResult.success(imFriendshipService.findFriendService(findUserSecretParam));
    }

    @ApiOperation("删除好友")
    @RequestMapping(value = "/delete-friend", method = RequestMethod.POST)
    public CommonResult<Boolean> deleteFriend(@Valid @RequestBody FriendDeleteParam friendDeleteParam) {
        return CommonResult.success(imFriendshipService.deleteFriendService(friendDeleteParam));
    }

    @ApiOperation("好友列表")
    @RequestMapping(value = "/list-friend", method = RequestMethod.POST)
    public CommonResult<List<UserFriendListVO>> listFriend(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                           @RequestParam(required = false) String keyword) {
        return CommonResult.success(imFriendshipService.listFriendService(pageNum,pageSize,keyword));
    }

    @ApiOperation("校验好友")
    @RequestMapping(value = "/check-friend", method = RequestMethod.POST)
    public CommonResult<List<FriendRelationVO>> checkFriend(@Valid @RequestBody CheckFriendParam checkFriendParam){
        return CommonResult.success(imFriendshipService.verifyFriendRelation(checkFriendParam));
    }
}

