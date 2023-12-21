package com.qk.chat.server.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * qun_member
 * </p>
 *
 * @author <a href="https://fengwenyi.com?fs=mpcg">code</a>
 * @since 2023-12-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("im_qun_member")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImQunMemberEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 群ID
     */
    @TableField("qun_id")
    private String qunId;

    /**
     * 群成员ID
     */
    @TableField("member_id")
    private String memberId;

    /**
     * 申请时间
     */
    @TableField("apply_time")
    private Date applyTime;

    /**
     * 审核时间
     */
    @TableField("audit_time")
    private Date auditTime;
}
