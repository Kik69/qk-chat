package com.qk.chat.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.chat.server.domain.entity.QunInfo;
import com.qk.chat.server.domain.param.NewQunParam;

/**
 * <p>
 * qun 服务类
 * </p>
 *
 * @author young
 * @since 2023年09月30日
 */
public interface QunInfoService extends IService<QunInfo> {
    boolean newQunInfoService(NewQunParam newQunParam,String userId);
}
