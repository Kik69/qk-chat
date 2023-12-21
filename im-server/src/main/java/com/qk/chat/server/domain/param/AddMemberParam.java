package com.qk.chat.server.domain.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class AddMemberParam {
    @NotBlank(message = "分组ID不为空")
    private String groupId;

    @NotEmpty(message = "成员不为空")
    private List<String> toIds;
}
