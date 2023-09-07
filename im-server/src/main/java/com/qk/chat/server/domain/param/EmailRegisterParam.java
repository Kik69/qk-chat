package com.qk.chat.server.domain.vo.param;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * {@code @ClassName} EmailRegisterParam
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/8/27 22:19
 */
@Data
public class EmailRegisterParam {
    @Email(message = "邮箱格式错误")
    private String email;
    @NotBlank(message = "用户名不能为空")
    private String userName;
    @NotBlank(message = "密码不能为空")
    @Size(min = 5,max = 16,message = "密码长度在5-16位数之间")
    private String password;
    @NotBlank(message = "确认密码不能为空")
    private String confirmPassword;
    @NotBlank(message = "验证码不能为空")
    private String captcha;
    private String introducer;
}
