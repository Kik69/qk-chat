package com.qk.chat.server.service;

import com.qk.chat.server.domain.entity.ImQunInfoEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.chat.server.domain.param.NewQunParam;

/**
 * <p>
 * qun 服务类
 * </p>
 *
 * @author <a href="https://fengwenyi.com?fs=mpcg">code</a>
 * @since 2023-12-21
 */
public interface ImQunInfoService extends IService<ImQunInfoEntity> {
    boolean newQunInfoService(NewQunParam newQunParam);
}
