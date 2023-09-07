package com.qk.chat.server.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * <p>
 * im_user_info
 * </p>
 *
 * @author young
 * @since 2023年08月27日
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("user_base_info")
public class UserBaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id")
    private String userId;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * E-MAIL
     */
    @TableField("email")
    private String email;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 性别
     */
    @TableField("gender")
    private Integer gender;

    /**
     * 头象
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 签名
     */
    @TableField("personal_signature")
    private String personalSignature;

    /**
     * 生日
     */
    @TableField("birthday")
    private LocalDate birthday;

    /**
     * 手机号码
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 最近登录时间
     */
    @TableField("last_login_time")
    private Date lastLoginTime;

    /**
     * 是否激活
     */
    @TableField("activate")
    private String activate;

    /**
     * 激活时间
     */
    @TableField("activate_time")
    private Date activateTime;

    /**
     * 渠道来源
     */
    @TableField("channel")
    private String channel;

    /**
     * SECRET MOBILE
     */
    @TableField("secret_mobile")
    private String secretMobile;

    /**
     * 设备
     */
    @TableField("device")
    private String device;

    /**
     * 设备id
     */
    @TableField("device_id")
    private String deviceId;

    /**
     * 设备模型
     */
    @TableField("device_model")
    private String deviceModel;

    /**
     * ip
     */
    @TableField("ip")
    private String ip;

    /**
     * STATUS
     */
    @TableField("status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField("gmt_create")
    private Date gmtCreate;

    /**
     * 更新时间
     */
    @TableField("gmt_modified")
    private Date gmtModified;


}
