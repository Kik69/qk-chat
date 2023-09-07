package com.qk.chat.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * {@code @ClassName} GatewayApplication
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/9/2 19:58
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.qk.chat")
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class,args);
    }
}
