package com.qk.chat.server.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * qun_member
 * </p>
 *
 * @author young
 * @since 2023年09月30日
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("qun_member")
public class QunMember implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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
    private LocalDate applyTime;

    /**
     * 审核时间
     */
    @TableField("audit_time")
    private LocalDate auditTime;


}
