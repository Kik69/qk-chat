package com.qk.chat.server.domain.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * {@code @ClassName} NewQunParam
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/9/30 11:37
 */
@Data
public class NewQunParam {
    @NotBlank(message = "群名称不为空")
    private String name;//群名称
    private String announcement;//群公告
    @NotBlank(message = "国籍不为空")
    private String nationalityId;//所在国籍
    private String organizationId;//所在组织
    private String remark;//备注
    @NotBlank(message = "群主不为空")
    private String ownerId;//群主
    private String categoryId;//类型
}
