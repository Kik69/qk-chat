package com.qk.chat.server.domain.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * {@code @ClassName} FriendDeleteParam
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/11/8 14:41
 */
@Data
public class FriendDeleteParam {
    @NotBlank(message = "好友ID不为空")
    private String toId;
}
