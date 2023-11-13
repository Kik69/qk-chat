package com.qk.chat.server.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author codehd
 * @since 2023年11月10日
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("im_friendship_group")
public class ImFriendshipGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(value = "group_id")
    private String groupId;

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

    @TableField("group_name")
    private String groupName;

    @TableField("sequence")
    private Long sequence;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @TableField("del_flag")
    private Integer delFlag;


}
