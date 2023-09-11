package com.qk.chat.server.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.inspur.plugins.common.util.TextUtil;
import com.qk.chat.common.constant.Constant;
import com.qk.chat.common.exception.BusinessException;
import com.qk.chat.common.jwt.JwtUtils;
import com.qk.chat.common.number.VerifyCodeUtil;
import com.qk.chat.server.common.config.redis.RedisToolsUtil;
import com.qk.chat.server.common.event.LoginSendCodeEvent;
import com.qk.chat.server.mapper.UserBaseInfoMapper;
import com.qk.chat.server.domain.dto.LoginUser;
import com.qk.chat.server.domain.param.CheckLoginParam;
import com.qk.chat.server.domain.param.EmailRegisterParam;
import com.qk.chat.server.service.UserBaseInfoService;
import com.qk.chat.server.domain.entity.UserBaseInfo;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * im_user_info 服务实现类
 * </p>
 *
 * @author ZYL
 * @since 2023年08月27日
 */
@Service
public class UserBaseInfoServiceImpl extends ServiceImpl<UserBaseInfoMapper, UserBaseInfo> implements UserBaseInfoService {
    
    @Autowired
    RedisToolsUtil redisToolsUtil;
    
    @Resource
    JavaMailSender javaMailSender;
    
    @Autowired
    UserBaseInfoMapper userInfoMapper;
    
    @Autowired
    StringEncryptor stringEncryptor;
    
    @Autowired
    ApplicationEventPublisher applicationEventPublisher;
    

    /**
     * 用户注册
     * @param emailRegisterParam
     * @return
     */
    @Override
    public String emailRegisterService(EmailRegisterParam emailRegisterParam) {
        Assert.isTrue(!this.checkMailExist(emailRegisterParam.getEmail()),"邮箱已经注册");
        Assert.isTrue(emailRegisterParam.getPassword().equals(emailRegisterParam.getConfirmPassword()),"确认密码错误");
        Assert.isTrue(this.checkEmailCode(emailRegisterParam.getEmail(),emailRegisterParam.getCaptcha()),"验证码错误");
        this.doRegister(emailRegisterParam);
        return "注册成功";
    }

    /**
     * 发送验证码
     * @param emailText
     * @return
     */
    @Override
    public String sendEmailService(String emailText) {
        String verifyCode = String.valueOf(redisToolsUtil.get(Constant.PREFIX_KEY_EMAIL + emailText));
        if (TextUtil.isNull(verifyCode)){
            applicationEventPublisher.publishEvent(new LoginSendCodeEvent(this,emailText));
            return "邮件发送成功";
        }
        return "验证码已发送至您的邮箱，请注意查收";
    }

    /**
     * 用户登录
     * @param checkLoginParam
     * @return
     */
    @Override
    public LoginUser checkLoginService(CheckLoginParam checkLoginParam) {
        UserBaseInfo userInfo = this.getUserInfo(checkLoginParam.getEmail());
        Assert.isTrue(this.checkMailExist(checkLoginParam.getEmail()),"用户名或密码错误");
        Assert.isTrue(stringEncryptor.decrypt(userInfo.getPassword()).equals(checkLoginParam.getPassword()),"用户名或密码错误");
        String token = JwtUtils.generateToken(Constant.PREFIX_UID + checkLoginParam.getEmail());
        //存入redis 设置过期时间
        redisToolsUtil.set(Constant.PREFIX_UID + checkLoginParam.getEmail(),token,30,TimeUnit.MINUTES);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("token",token);
        return LoginUser.builder()
                .userName(userInfo.getUserName())
                .userId(userInfo.getUserId())
                .nickName(userInfo.getNickName())
                .days(new Date())
                .extensions(hashMap)
                .build();
    }


    public UserBaseInfo getUserInfo(String emailText){
        QueryWrapper<UserBaseInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",emailText);
        return userInfoMapper.selectOne(queryWrapper);
    }

    /**
     * 验证code是否正确
     */
    public boolean checkEmailCode(String emailText,String code){
        return code.equals(String.valueOf(redisToolsUtil.get(Constant.PREFIX_KEY_EMAIL + emailText)));
    }

    /**
     * 判断邮箱是否存在
     */
    public boolean checkMailExist(String emailText){
        QueryWrapper<UserBaseInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",emailText);
        List<UserBaseInfo> userInfos = userInfoMapper.selectList(queryWrapper);
        if (TextUtil.isNotNull(userInfos)){
            return true;
        }
        return false;
    }

    /**
     * 用户注册
     */
    public void doRegister(EmailRegisterParam registerParam){
        try {
            UserBaseInfo userInfo = new UserBaseInfo();
            userInfo.setUserId(UUID.randomUUID().toString());
            userInfo.setUserName(registerParam.getUserName());
            userInfo.setPassword(stringEncryptor.encrypt(registerParam.getPassword()));
            userInfo.setEmail(registerParam.getEmail());
            userInfo.setActivate(Constant.IS_YES);
            userInfo.setActivateTime(new DateTime());
            userInfo.setIp(InetAddress.getLocalHost().getHostAddress());
            userInfo.setStatus(1);
            userInfo.setGmtCreate(new DateTime());
            userInfoMapper.insert(userInfo);
        }catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("系统内部异常，请联系管理员处理");
        }
    }
}
