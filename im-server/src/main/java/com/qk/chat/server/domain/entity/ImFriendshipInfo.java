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
@TableName("im_friendship_info")
public class ImFriendshipInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * app_id
     */
    @TableId(value = "id")
    private String id;

    /**
     * from_id
     */
    private String fromId;

    /**
     * to_id
     */
    private String toId;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 状态 1正常 2删除
     */
    @TableField("status")
    private Integer status;

    /**
     * 1正常 2拉黑
     */
    @TableField("black")
    private Integer black;

    @TableField("create_time")
    private Date createTime;

    @TableField("friend_sequence")
    private Long friendSequence;

    @TableField("black_sequence")
    private Long blackSequence;

    /**
     * 来源
     */
    @TableField("add_source")
    private String addSource;

    /**
     * 来源
     */
    @TableField("extra")
    private String extra;


}
