package com.qk.chat.server.dao;

import com.qk.chat.server.domain.entity.UserAuditInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * {@code @ClassName} UserAuditInfoDao
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/9/8 10:31
 */
@Component
public class UserAuditInfoDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<UserAuditInfo> checkExistsFriendRelation(String userId, String friendId){
        return jdbcTemplate.query("SELECT * FROM user_audit_info WHERE user_id = ? AND audit_user_id = ?",new BeanPropertyRowMapper<>(UserAuditInfo.class),userId,friendId);
    }
}
