package com.qk.chat.server.service.impl;

import com.qk.chat.server.domain.entity.ImQunInfoEntity;
import com.qk.chat.server.domain.entity.ImQunMemberEntity;
import com.qk.chat.server.domain.param.NewQunParam;
import com.qk.chat.server.mapper.ImQunInfoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qk.chat.server.service.ImQunInfoService;
import com.qk.chat.web.context.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * <p>
 * qun 服务实现类
 * </p>
 *
 * @author <a href="https://fengwenyi.com?fs=mpcg">code</a>
 * @since 2023-12-21
 */
@Service
public class ImQunInfoServiceImpl extends ServiceImpl<ImQunInfoMapper, ImQunInfoEntity> implements ImQunInfoService {

    @Autowired
    ImQunInfoMapper imQunInfoMapper;

    @Override
    public boolean newQunInfoService(NewQunParam newQunParam) {
        String userId = ThreadContext.getLoginToken().getUserId();
        this.doCreateQun(newQunParam,userId);
        return true;
    }

    public void doCreateQun(NewQunParam newQunParam,String userId){
        ImQunInfoEntity qunInfo = ImQunInfoEntity.builder()
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
        imQunInfoMapper.insert(qunInfo);
    }
}
