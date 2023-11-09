package com.qk.chat.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qk.chat.server.domain.entity.ImUserInfo;
import com.qk.chat.server.domain.param.CheckFriendParam;
import com.qk.chat.server.domain.vo.FriendRelationVO;
import com.qk.chat.server.domain.vo.UserFriendListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * im_user_info Mapper 接口
 * </p>
 *
 * @author ZYL
 * @since 2023年08月27日
 */
@Mapper
public interface ImUserInfoMapper extends BaseMapper<ImUserInfo> {
    ImUserInfo getUserBaseInfo(String email);
    
    ImUserInfo getUserInfoById(String userId);
    
    List<UserFriendListVO> getUserFriendList(@Param("userIds") List<String> userIds,@Param("keyword") String keyword);
}
