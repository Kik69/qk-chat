package com.qk.chat.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qk.chat.server.domain.entity.ImUserInfo;
import org.apache.ibatis.annotations.Mapper;

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
}
