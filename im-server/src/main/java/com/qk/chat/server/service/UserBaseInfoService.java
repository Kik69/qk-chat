package com.qk.chat.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.chat.server.domain.dto.LoginUser;
import com.qk.chat.server.domain.entity.UserBaseInfo;
import com.qk.chat.server.domain.param.CheckLoginParam;
import com.qk.chat.server.domain.param.EmailRegisterParam;

/**
 * <p>
 * im_user_info 服务类
 * </p>
 *
 * @author young
 * @since 2023年08月27日
 */
public interface UserBaseInfoService extends IService<UserBaseInfo> {
    String emailRegisterService(EmailRegisterParam emailRegisterParam);
    
    String sendEmailService(String emailText);
    
    LoginUser checkLoginService(CheckLoginParam checkLoginParam);
}
