package com.qk.chat.server.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * {@code @ClassName} LoginUser
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/8/27 22:17
 */
@Data
@Builder
public class LoginUser{
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 头象
     */
    private String avatar;
    /**
     * 设备id
     */
    private String deviceId;

    private Date days;

    private Long expireAt;

    private Map<String, Object> extensions = new LinkedHashMap<>();
}
