package com.qk.chat.server.controller;


import com.qk.chat.common.result.CommonResult;
import com.qk.chat.server.common.pager.CommonPage;
import com.qk.chat.server.domain.entity.ImExamineInfo;
import com.qk.chat.server.domain.entity.UserAuditInfo;
import com.qk.chat.server.service.impl.ImExamineInfoServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/server/examine")
public class ImExamineInfoController {
    
    @Autowired
    ImExamineInfoServiceImpl imExamineInfoService;
    
    @ApiOperation("申请列表")
    @RequestMapping(value = "/friend-list", method = RequestMethod.POST)
    public CommonResult<CommonPage<ImExamineInfo>> friendList(@NotBlank(message = "用户ID不为空") String userId,
                                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                              @RequestParam(required = false) String keyword) {
        return CommonResult.success(CommonPage.restPage(imExamineInfoService.friendListService(userId, pageNum, pageSize, keyword)));
    }
}

