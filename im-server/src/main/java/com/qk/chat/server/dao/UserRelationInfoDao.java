package com.qk.chat.server.dao;

import com.qk.chat.server.domain.entity.UserRelationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * {@code @ClassName} UserRelationInfoDao
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/9/8 10:31
 */
@Component
public class UserRelationInfoDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public List<UserRelationInfo> checkExistsFriendRelation(String userId, String friendId){
        return jdbcTemplate.query("SELECT * FROM user_relation_info WHERE user_id = ? AND friend_id = ?", new BeanPropertyRowMapper<>(UserRelationInfo.class), userId, friendId);
    }
}
