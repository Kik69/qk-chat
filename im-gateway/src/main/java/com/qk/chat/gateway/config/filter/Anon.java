package com.qk.chat.gateway.config.filter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * {@code @ClassName} Anon
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/9/4 14:11
 */
@Component
@ConfigurationProperties(prefix = "anon")
public class Anon {
    private List<String> url;

    public List<String> getUrl() {
        return url;
    }
    
    public void setUrl(List<String> url) {
        this.url = url;
    }
}
