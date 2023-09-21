package com.qk.chat.server.controller;

import com.qk.chat.common.result.CommonResult;
import com.qk.chat.server.domain.dto.LoginUser;
import com.qk.chat.server.domain.param.CheckLoginParam;
import com.qk.chat.server.domain.param.EmailRegisterParam;
import com.qk.chat.server.service.UserBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


/**
 * {@code @ClassName} UserController
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/8/27 19:11
 */
@RestController
@RequestMapping("/server/user")
@Validated
public class UserBaseInfoController {

    @Autowired
    UserBaseInfoService userBaseInfoService;

    @PostMapping("/email-register")
    public CommonResult<Boolean> emailRegister(@Valid @RequestBody EmailRegisterParam emailRegisterParam) {
        return CommonResult.success(userBaseInfoService.emailRegisterService(emailRegisterParam));
    }

    @PostMapping("/email-send")
    public CommonResult<String> sendEmail(@Email(message = "邮箱格式不正确") @NotBlank(message = "邮箱不能为空") String emailText) {
        return CommonResult.success(userBaseInfoService.sendEmailService(emailText));
    }

    @PostMapping("/email-login")
    public CommonResult<LoginUser> checkLogin(@Valid @RequestBody CheckLoginParam checkLoginParam){
        return CommonResult.success(userBaseInfoService.checkLoginService(checkLoginParam),"登录成功！");
    }
    
    @GetMapping("/test")
    public CommonResult<String> test(){
        return CommonResult.success("test");
    }
}
