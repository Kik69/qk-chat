package com.qk.chat.server.controller;

import com.qk.chat.common.result.CommonResult;
import com.qk.chat.server.domain.param.NewQunParam;
import com.qk.chat.server.service.ImQunInfoService;
import com.qk.chat.web.context.LoginUserInfo;
import com.qk.chat.web.context.ThreadContext;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * qun 前端控制器
 * </p>
 *
 * @author <a href="https://fengwenyi.com?fs=mpcg">code</a>
 * @since 2023-12-21
 */
@RestController
@RequestMapping("/server/qun-info")
public class ImQunInfoController {

    @Autowired
    ImQunInfoService imQunInfoService;

    @ApiOperation("新建群聊")
    @RequestMapping(value = "/new-qun",method = RequestMethod.POST)
    public CommonResult<Boolean> newQunInfo(@Valid @RequestBody NewQunParam newQunParam){
        return CommonResult.success(imQunInfoService.newQunInfoService(newQunParam));
    }
}
