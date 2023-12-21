package com.qk.chat.server.domain.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * {@code @ClassName} FriendGroupEditParam
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/11/12 16:23
 */
@Data
public class FriendGroupEditParam {
    @NotBlank(message = "主键ID不为空")
    private String id;

    @NotBlank(message = "分组名称不为空")
    private String groupName;
}
