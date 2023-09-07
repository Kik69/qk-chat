package com.qk.chat.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * {@code @ClassName} ServerApplication
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/8/28 17:57
 */
@SpringBootApplication(scanBasePackages = "com.qk.chat")
public class ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class,args);
    }
}
