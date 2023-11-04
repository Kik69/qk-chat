package com.qk.chat.server.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author codehd
 * @since 2023年11月02日
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("im_examine_info")
public class ImExamineInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * app_id
     */
    @TableField("app_id")
    private Integer appId;

    /**
     * from_id
     */
    @TableField("from_id")
    private String fromId;

    /**
     * to_id
     */
    @TableField("to_id")
    private String toId;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 是否已读 1已读
     */
    @TableField("read_status")
    private Integer readStatus;

    /**
     * 好友来源
     */
    @TableField("add_source")
    private String addSource;

    /**
     * 好友验证信息
     */
    @TableField("add_wording")
    private String addWording;

    /**
     * 审批状态 1同意 2拒绝
     */
    @TableField("approve_status")
    private Integer approveStatus;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @TableField("sequence")
    private Long sequence;


}
