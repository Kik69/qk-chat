package com.qk.chat.server.domain.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * {@code @ClassName} CheckApplyParam
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/9/13 15:35
 */
@Data
public class AuditApplyParam {
    @NotBlank(message = "审核结果不为空")
    private String auditDetail;
    
    @NotBlank(message = "申请人ID")
    private String friendUserId;
    
    private String auditReason;
}
