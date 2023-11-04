package com.qk.chat.server.domain.param;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * {@code @ClassName} FriendAddParam
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/9/5 17:13
 */
@Data
public class FriendAddParam {
    @NotBlank(message = "好友ID不为空")
    private String toId;
    
    private String remark;
    
    @NotBlank(message = "申请来源不为空")
    private String addSource;
}
