package com.qk.chat.server.domain.param;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * {@code @ClassName} FindUserSecretParam
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/9/5 16:19
 */
@Data
public class FindUserSecretParam {
    @Email(message = "邮箱格式错误")
    @NotBlank(message = "邮箱不能为空")
    private String userIdentify;
}
