package com.qk.chat.web.config;

import com.qk.chat.web.intecepter.LoginUserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * {@code @ClassName} WebMvcConfig
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/9/20 14:21
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    LoginUserInterceptor loginUserInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginUserInterceptor);
    }
}
