package com.qk.chat.server.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * {@code @ClassName} UserFriendListVO
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/11/8 15:52
 */
@Data
public class UserFriendListVO {
    private String userName;
    
    private String nickName;
    
    private String email;
    
    private Integer gender;
    
    private String avatar;
    
    private String personalSignature;
    
    private Date birthday;
    
    private String mobile;
    
    private String lastLoginTime;
}
