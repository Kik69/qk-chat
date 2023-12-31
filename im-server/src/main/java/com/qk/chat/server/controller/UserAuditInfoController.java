package com.qk.chat.server.controller;

import com.qk.chat.common.result.CommonResult;
import com.qk.chat.server.common.pager.CommonPage;
import com.qk.chat.server.domain.entity.UserAuditInfo;
import com.qk.chat.server.domain.param.AuditApplyParam;
import com.qk.chat.server.domain.param.DeleteFriendParam;
import com.qk.chat.server.domain.param.FindUserSecretParam;
import com.qk.chat.server.domain.param.FriendAddParam;
import com.qk.chat.server.domain.vo.UserFriendApplyVO;
import com.qk.chat.server.service.UserAuditInfoService;
import com.qk.chat.web.context.LoginUserInfo;
import com.qk.chat.web.context.ThreadContext;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * {@code @ClassName} UserAuditInfoController
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/9/5 14:33
 */
@RestController
@RequestMapping("/server/audit")
@Validated
public class UserAuditInfoController {
    @Autowired
    UserAuditInfoService userAuditInfoService;

    @ApiOperation("查找好友")
    @RequestMapping(value = "/find-friend", method = RequestMethod.POST)
    public CommonResult<UserFriendApplyVO> findFriend(@Valid @RequestBody FindUserSecretParam findUserSecretParam) {
        return CommonResult.success(userAuditInfoService.findFriendService(findUserSecretParam));
    }

    @ApiOperation("申请好友")
    @RequestMapping(value = "/apply-friend", method = RequestMethod.POST)
    public CommonResult<Boolean> applyFriend(@Valid @RequestBody FriendAddParam friendAddParam) {
        LoginUserInfo loginToken = ThreadContext.getLoginToken();
        return CommonResult.success(userAuditInfoService.applyFriendService(loginToken.getUserId(), friendAddParam));
    }

    @ApiOperation("申请列表")
    @RequestMapping(value = "/friend-list", method = RequestMethod.POST)
    public CommonResult<CommonPage<UserAuditInfo>> friendList(@NotBlank(message = "用户ID不为空") String userId,
                                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                              @RequestParam(required = false) String keyword) {
        return CommonResult.success(CommonPage.restPage(userAuditInfoService.friendListService(userId, pageNum, pageSize, keyword)));
    }

    @ApiOperation("审核申请")
    @RequestMapping(value = "/audit-apply", method = RequestMethod.POST)
    public CommonResult<Boolean> auditApply(@Valid @RequestBody AuditApplyParam auditApplyParam) {
        LoginUserInfo loginToken = ThreadContext.getLoginToken();
        return CommonResult.success(userAuditInfoService.auditApplyService(loginToken.getUserId(), auditApplyParam));
    }

    @ApiOperation("删除好友")
    @RequestMapping(value = "/delete-friend", method = RequestMethod.POST)
    public CommonResult<Boolean> deleteFriend(@Valid @RequestBody DeleteFriendParam deleteFriendParam){
        return CommonResult.success(userAuditInfoService.deleteFriendService(ThreadContext.getLoginToken().getUserId(),deleteFriendParam));
    }
}
