package com.qk.chat.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qk.chat.server.domain.entity.ImFriendshipGroup;
import org.apache.ibatis.annotations.Mapper;

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
}
