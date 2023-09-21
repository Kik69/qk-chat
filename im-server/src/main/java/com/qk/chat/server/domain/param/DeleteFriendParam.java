package com.qk.chat.server.domain.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * {@code @ClassName} DeleteFriendParam
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/9/21 14:30
 */
@Data
public class DeleteFriendParam {
    @NotBlank(message = "删除ID不能为空")
    private String friendUserId;
}
