package com.qk.chat.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qk.chat.server.domain.entity.UserBaseInfo;
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
public interface UserBaseInfoMapper extends BaseMapper<UserBaseInfo> {
    UserBaseInfo getUserBaseInfo(String email);
}
