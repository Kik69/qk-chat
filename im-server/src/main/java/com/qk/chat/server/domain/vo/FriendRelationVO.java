package com.qk.chat.server.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * {@code @ClassName} FriendRelationVO
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/11/9 15:45
 */
@Data
public class FriendRelationVO {
    private String fromId;
    
    private String toId;
    
    private Integer status;
}
