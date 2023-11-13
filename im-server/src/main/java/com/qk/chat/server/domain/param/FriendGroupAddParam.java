package com.qk.chat.server.domain.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * {@code @ClassName} FriendGroupAddParam
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/11/10 11:05
 */
@Data
public class FriendGroupAddParam {
    private String appId;
    
    @NotBlank(message = "组名称不为空")
    private String groupName;
    
    private List<String> toIds;
}
