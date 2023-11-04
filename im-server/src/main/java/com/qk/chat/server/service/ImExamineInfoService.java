package com.qk.chat.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.chat.server.domain.entity.ImExamineInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author codehd
 * @since 2023年11月02日
 */
public interface ImExamineInfoService extends IService<ImExamineInfo> {
    List<ImExamineInfo> friendListService(String userId,Integer pageNum,Integer pageSize,String keyword);
}
