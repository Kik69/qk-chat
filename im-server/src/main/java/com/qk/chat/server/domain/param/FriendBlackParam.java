package com.qk.chat.server.domain.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * {@code @ClassName} FriendBlackParam
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/11/10 10:11
 */
@Data
public class FriendBlackParam {
    @NotBlank(message = "好友ID不为空")
    private String toId;
}
