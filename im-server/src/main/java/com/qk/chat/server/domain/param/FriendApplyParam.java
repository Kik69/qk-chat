package com.qk.chat.server.domain.param;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * {@code @ClassName} FriendApplyParam
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/9/5 17:13
 */
@Data
@Builder
public class FriendApplyParam {
    @NotBlank(message = "申请人不为空")
    private String applyId;
    @NotBlank(message = "审核人不为空")
    private String auditId;
    @NotBlank(message = "业务类型不为空")
    private String businessType;
    @NotBlank(message = "业务ID不为空")
    private String businessId;
    private Date applyTime;
    @NotBlank(message = "申请理由不为空")
    private String applyReason;
}
