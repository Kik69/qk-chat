package com.qk.chat.server.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.qk.chat.server.domain.entity.ImExamineInfo;
import com.qk.chat.server.mapper.ImExamineInfoMapper;
import com.qk.chat.server.service.ImExamineInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author codehd
 * @since 2023年11月02日
 */
@Service
public class ImExamineInfoServiceImpl extends ServiceImpl<ImExamineInfoMapper, ImExamineInfo> implements ImExamineInfoService {

    @Autowired
    ImExamineInfoMapper imExamineInfoMapper;
    
    @Override
    public List<ImExamineInfo> friendListService(String userId, Integer pageNum, Integer pageSize, String keyword) {
        PageHelper.startPage(pageNum,pageSize);
        return imExamineInfoMapper.getImExamineInfoList(userId, keyword);
    }
}
