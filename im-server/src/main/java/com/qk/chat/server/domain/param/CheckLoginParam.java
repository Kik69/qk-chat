package com.qk.chat.server.domain.param;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * {@code @ClassName} CheckLoginParam
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/8/30 10:52
 */
@Data
public class CheckLoginParam {
    @Email(message = "邮箱格式错误")
    private String email;
    @NotBlank(message = "密码不能为空")
    @Size(min = 5,max = 16,message = "密码长度在5-16位数之间")
    private String password;
}
