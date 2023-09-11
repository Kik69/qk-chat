package com.qk.chat.server.controller;

import com.qk.chat.common.result.CommonResult;
import com.qk.chat.server.domain.param.FindUserSecretParam;
import com.qk.chat.server.domain.param.FriendApplyParam;
import com.qk.chat.server.domain.vo.UserFriendApplyVO;
import com.qk.chat.server.service.UserAuditInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
    @RequestMapping(value = "/find-friend",method = RequestMethod.POST)
    public CommonResult<UserFriendApplyVO> findFriend(@Valid @RequestBody FindUserSecretParam findUserSecretParam){
        return CommonResult.success(userAuditInfoService.findFriendService(findUserSecretParam));
    }
    
    @RequestMapping(value = "/apply-friend",method = RequestMethod.POST)
    @ApiOperation("申请好友")
    public CommonResult<String> applyFriend(@Valid @RequestBody FriendApplyParam friendApplyParam){
        return CommonResult.success(userAuditInfoService.applyFriendService(friendApplyParam));
    }
}
