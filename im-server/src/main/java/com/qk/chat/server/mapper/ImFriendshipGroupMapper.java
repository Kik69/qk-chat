package com.qk.chat.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qk.chat.server.domain.entity.ImFriendshipGroup;
import com.qk.chat.server.domain.entity.ImGroupMemberEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author codehd
 * @since 2023年11月10日
 */
@Mapper
public interface ImFriendshipGroupMapper extends BaseMapper<ImFriendshipGroup> {
    List<String> getAllByGroupNameList(String fromId);
    
    int addGroupMemberData(@Param("groupId") String groupId,@Param("toIds") String toIds);

    List<ImGroupMemberEntity> getGroupMemberInfo(@Param("groupId") String groupId, @Param("toId") String toId);
}
