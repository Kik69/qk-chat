package com.qk.chat.server.common.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * {@code @ClassName} LoginSendCodeEvent
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/9/11 9:52
 */
@Getter
public class LoginSendCodeEvent extends ApplicationEvent {
    
    private String emailText;
    
    public LoginSendCodeEvent(Object source,String emailText) {
        super(source);
        this.emailText = emailText;
    }
}
