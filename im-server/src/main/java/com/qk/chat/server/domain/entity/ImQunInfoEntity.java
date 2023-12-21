package com.qk.chat.server.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * qun
 * </p>
 *
 * @author <a href="https://fengwenyi.com?fs=mpcg">code</a>
 * @since 2023-12-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("im_qun_info")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImQunInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 群名称
     */
    @TableField("name")
    private String name;

    /**
     * 群公告
     */
    @TableField("announcement")
    private String announcement;

    /**
     * 国籍id
     */
    @TableField("nationality_id")
    private String nationalityId;

    /**
     * 机构ID
     */
    @TableField("organization_id")
    private String organizationId;

    /**
     * 群主
     */
    @TableField("owner_id")
    private String ownerId;

    /**
     * 所属类别
     */
    @TableField("category_id")
    private String categoryId;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * STATUS
     */
    @TableField("status")
    private Integer status;

    /**
     * 创建人ID
     */
    @TableField("create_user_id")
    private String createUserId;

    /**
     * 创建时间
     */
    @TableField("gmt_create")
    private Date gmtCreate;

    /**
     * 更新人ID
     */
    @TableField("modified_user_id")
    private String modifiedUserId;

    /**
     * 更新时间
     */
    @TableField("gmt_modified")
    private Date gmtModified;

    /**
     * 创建人
     */
    @TableField("create_user_name")
    private String createUserName;

    /**
     * 更新人
     */
    @TableField("modified_user_name")
    private String modifiedUserName;
}
