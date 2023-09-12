package com.qk.chat.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qk.chat.server.domain.entity.UserAuditInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author young
 * @since 2023年09月05日
 */
@Mapper
public interface UserAuditInfoMapper extends BaseMapper<UserAuditInfo> {
    List<UserAuditInfo> getUserAuditInfoList(@Param("userId") String userId,@Param("keyword") String keyword);
}
