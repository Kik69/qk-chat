package com.qk.chat.server.domain.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * {@code @ClassName} FriendApplyParam
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/11/2 16:51
 */
@Data
public class FriendApplyParam {
    @NotBlank(message = "申请人不为空")
    private String fromId;
    
    @NotBlank(message = "好友验证信息不为空")
    private String addWording;
    
    @NotNull(message = "审核结果不为空")
    private Integer approveStatus;
    
    private String remark;
    
    private Date updateTime;
}
