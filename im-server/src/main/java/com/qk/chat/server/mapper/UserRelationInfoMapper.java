package com.qk.chat.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qk.chat.server.domain.entity.UserRelationInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author young
 * @since 2023年09月05日
 */
@Mapper
public interface UserRelationInfoMapper extends BaseMapper<UserRelationInfo> {
    boolean deleteFriend(@Param("userId") String userId,@Param("friendId") String friendId);
}
