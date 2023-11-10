package com.qk.chat.server.domain.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * {@code @ClassName} FriendGroupParam
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/11/10 11:05
 */
@Data
public class FriendGroupParam {
    private String appId;
    
    @NotBlank(message = "组名称不为空")
    private String groupName;
    
    private Date createTime;
}
