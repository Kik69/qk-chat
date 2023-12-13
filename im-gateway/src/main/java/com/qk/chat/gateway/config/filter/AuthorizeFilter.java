package com.qk.chat.gateway.config.filter;

import com.alibaba.fastjson.JSON;
import com.qk.chat.common.utils.TextUtil;
import com.qk.chat.gateway.config.redis.RedisToolsUtil;
import com.qk.chat.common.jwt.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * {@code @ClassName} AuthorizeFilter
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/9/3 8:54
 */
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {

    @Autowired
    RedisToolsUtil redisToolsUtil;

    @Autowired
    Anon anon;

    private static final String AUTHORIZE_TOKEN = "Authorization";


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        if (anon.getUrl().contains(request.getPath().toString())){
            return chain.filter(exchange);
        }
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst(AUTHORIZE_TOKEN);
        if (TextUtil.isNull(token)){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.writeWith(Mono.just(this.convertMsgData(response,"请登录")));
        }
        try {
            String subToken = token.substring("Bearer ".length());
            Claims claimByToken = JwtUtils.getClaimByToken(subToken);
            String emailUid = claimByToken.get("uid") + "";
            String redisToken = redisToolsUtil.get(emailUid) + "";
            if (TextUtil.isNotNull(redisToken) && subToken.equals(redisToken)){
                //获取过期时间
                if (redisToolsUtil.getExpire(emailUid) < 600){
                    redisToolsUtil.expire(emailUid,30, TimeUnit.MINUTES);
                }
            }else {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.writeWith(Mono.just(this.convertMsgData(response,"token已过期！")));
            }
        }catch (Exception e){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.writeWith(Mono.just(this.convertMsgData(response,"token校验失败！")));
        }
        return chain.filter(exchange);
    }


    @Override
    public int getOrder() {
        return 0;
    }
    
    
    public DataBuffer convertMsgData(ServerHttpResponse response,String message){
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("code",500);
        resultMap.put("message",message);
        resultMap.put("data",false);
        byte[] bytes = JSON.toJSONString(resultMap).getBytes(StandardCharsets.UTF_8);
        return response.bufferFactory().wrap(bytes);
    }
}
