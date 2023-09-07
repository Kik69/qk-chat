package com.qk.chat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author young
 * @since 2023年09月05日
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("user_audit_info")
public class UserAuditInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 申请人ID
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 业务类型（群或者好友ID）
     */
    @TableField("business_type")
    private String businessType;

    /**
     * 业务ID （如果是群对应群ID，如果是好友对应好友ID
     */
    @TableField("business_id")
    private Integer businessId;

    /**
     * 申请时间
     */
    @TableField("apply_time")
    private LocalDateTime applyTime;

    /**
     * 审核时间
     */
    @TableField("audit_time")
    private LocalDateTime auditTime;

    /**
     * 审核人ID
     */
    @TableField("audit_user_id")
    private Integer auditUserId;

    /**
     * 申请理由
     */
    @TableField("apply_reason")
    private String applyReason;

    /**
     * 审核理由
     */
    @TableField("audit_reason")
    private String auditReason;

    /**
     * 审核状态（0：未审核，1：审核通过，2：审核未通过）
     */
    @TableField("audit_status")
    private Integer auditStatus;


}
