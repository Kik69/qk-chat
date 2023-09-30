package com.qk.chat.server.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qk.chat.server.domain.entity.QunInfo;
import com.qk.chat.server.domain.param.NewQunParam;
import com.qk.chat.server.mapper.QunInfoMapper;
import com.qk.chat.server.service.QunInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * <p>
 * qun 服务实现类
 * </p>
 *
 * @author young
 * @since 2023年09月30日
 */
@Service
public class QunInfoServiceImpl extends ServiceImpl<QunInfoMapper, QunInfo> implements QunInfoService {
    
    @Autowired
    QunInfoMapper qunInfoMapper;

    @Override
    public boolean newQunInfoService(NewQunParam newQunParam,String userId) {
        this.doCreateQun(newQunParam,userId);
        return true;
    }
    
    public void doCreateQun(NewQunParam newQunParam,String userId){
        QunInfo qunInfo = QunInfo.builder()
                .id(UUID.randomUUID().toString())
                .name(newQunParam.getName())
                .announcement(newQunParam.getAnnouncement())
                .nationalityId(newQunParam.getNationalityId())
                .organizationId(newQunParam.getOrganizationId())
                .ownerId(userId)
                .categoryId(newQunParam.getCategoryId())
                .remark(newQunParam.getRemark())
                .status(0)
                .createUserId(userId)
                .gmtCreate(new Date())
                .createUserName(userId)
                .build();
        qunInfoMapper.insert(qunInfo);
    }
}
