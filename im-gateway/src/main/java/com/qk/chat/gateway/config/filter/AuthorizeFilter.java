package com.qk.chat.gateway.config;

import com.inspur.plugins.common.util.TextUtil;
import com.qk.chat.common.exception.BusinessException;
import com.qk.chat.common.jwt.JwtUtils;
import com.qk.chat.server.common.redis.RedisToolsUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;

/**
 * {@code @ClassName} AuthorizeFilter
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/9/3 8:54
 */
@Component
public class AuthorizeFilter implements GlobalFilter, Order {
    
    @Autowired
    RedisToolsUtil redisToolsUtil;

    private static final String AUTHORIZE_TOKEN = "token";

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        if (request.getURI().getPath().contains("/server/user/email/login")){
            return chain.filter(exchange);
        }
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst(AUTHORIZE_TOKEN);
        if (TextUtil.isNull(token)){
            throw new BusinessException("请登录！");
        }
        try {
            Claims claimByToken = JwtUtils.getClaimByToken(token);
            String emailUid = claimByToken.get("uid") + "";
            
        }
    }

    @Override
    public int value() {
        return 0;
    }
}
