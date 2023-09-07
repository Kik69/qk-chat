package com.qk.chat.server.domain.vo;

import lombok.Builder;
import lombok.Data;

/**
 * {@code @ClassName} UserFriendApplyVO
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/9/5 16:23
 */
@Data
@Builder
public class UserFriendApplyVO {
    private String userId; 
    private String userSecretIdentify;
    private String avatar;
    private String nickName;
}
