package com.qk.chat.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qk.chat.server.domain.entity.ImExamineInfo;
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
public interface ImExamineInfoMapper extends BaseMapper<ImExamineInfo> {
    ImExamineInfo getExamineInfo(@Param("fromId") String fromId,@Param("toId") String toId);
    
    List<ImExamineInfo> getImExamineInfoList(@Param("userId") String userId,@Param("keyword") String keyword);
}
