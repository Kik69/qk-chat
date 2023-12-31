package com.qk.chat.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qk.chat.server.domain.entity.ImFriendshipInfo;
import com.qk.chat.server.domain.param.CheckFriendParam;
import com.qk.chat.server.domain.vo.FriendRelationVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author codehd
 * @since 2023年11月02日
 */
@Mapper
public interface ImFriendshipMapper extends BaseMapper<ImFriendshipInfo> {
    ImFriendshipInfo getFriendOneInfo(@Param("fromId") String fromId,@Param("toId") String toId);
    
    int insertFriendInfo(@Param("friendList") List<ImFriendshipInfo> friendList);

    int editFriendStatus(@Param("fromId") String fromId,@Param("toId") String toId);
    
    List<String> getFriendToIds(String fromId);

    List<FriendRelationVO> getOneWayCheckList(@Param("checkFriendParam") CheckFriendParam checkFriendParam, @Param("fromId") String fromId);

    List<FriendRelationVO> getBothWayCheckList(@Param("checkFriendParam") CheckFriendParam checkFriendParam, @Param("fromId") String fromId);


}
