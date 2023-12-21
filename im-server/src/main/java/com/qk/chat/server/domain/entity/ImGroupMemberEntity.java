package com.qk.chat.server.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author <a href="https://fengwenyi.com?fs=mpcg">codehd</a>
 * @since 2023-12-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("im_group_member")
public class ImGroupMemberEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分组ID
     */
    @TableField("group_id")
    private String groupId;

    /**
     * 成员ID
     */
    @TableField("to_id")
    private String toId;
}
