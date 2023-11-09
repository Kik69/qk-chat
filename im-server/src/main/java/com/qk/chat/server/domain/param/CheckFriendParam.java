package com.qk.chat.server.domain.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * {@code @ClassName} CheckFriendParam
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/11/9 15:42
 */
@Data
public class CheckFriendParam {
    @NotEmpty(message = "toIds不为空")
    private List<String> toIds;
    
    @NotNull(message = "验证类型不为空")
    private Integer checkType;
}
