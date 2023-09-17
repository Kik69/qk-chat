package com.qk.chat.server.common.event.listener;

import com.qk.chat.common.constant.Constant;
import com.qk.chat.common.constant.ConstantError;
import com.qk.chat.common.exception.BusinessException;
import com.qk.chat.common.number.VerifyCodeUtil;
import com.qk.chat.server.common.config.redis.RedisToolsUtil;
import com.qk.chat.server.common.event.LoginSendCodeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;

/**
 * {@code @ClassName} LoginSendCodeListener
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/9/11 9:54
 */
@Service
public class LoginSendCodeListener implements ApplicationListener<LoginSendCodeEvent> {

    @Autowired
    RedisToolsUtil redisToolsUtil;

    @Resource
    JavaMailSender javaMailSender;
    
    @Override
    public void onApplicationEvent(LoginSendCodeEvent loginSendCodeEvent) {
        this.sendEmail(loginSendCodeEvent);
    }
    
    public void sendEmail(LoginSendCodeEvent loginSendCodeEvent){
        String emailText = loginSendCodeEvent.getEmailText();
        String code = VerifyCodeUtil.generateVerifyCode(6);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("im即时通讯验证码");
        mailMessage.setText("尊敬的用户您好!\n\n感谢您注册im即时通讯。\n\n尊敬的: " + emailText + "您的校验验证码为: " + code + ",有效期5分钟，请不要把验证码信息泄露给其他人,如非本人请勿操作");
        mailMessage.setTo(emailText);
        try {
            mailMessage.setFrom(new InternetAddress(MimeUtility.encodeText("im官方") + "<2901603085@qq.com>").toString());
            javaMailSender.send(mailMessage);
            //存入redis
            redisToolsUtil.set(Constant.PREFIX_KEY_EMAIL + emailText,code,600);
        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException(ConstantError.SYSTEM_ERROR);
        }
    }
    
}
