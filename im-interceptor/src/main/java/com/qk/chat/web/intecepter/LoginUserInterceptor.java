package com.qk.chat.web.intecepter;

import com.qk.chat.common.jwt.JwtUtils;
import com.qk.chat.common.utils.TextUtil;
import com.qk.chat.web.context.LoginUserInfo;
import com.qk.chat.web.context.ThreadContext;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * {@code @ClassName} LoginUserInterceptor
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/9/20 14:23
 */
@Component
public class LoginUserInterceptor implements HandlerInterceptor {
    
    private static final String AUTHORIZE_TOKEN = "Authorization";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(AUTHORIZE_TOKEN);
        if (TextUtil.isNotNull(token)){
            Claims claimByToken = JwtUtils.getClaimByToken(token.substring("Bearer ".length()));
            LoginUserInfo loginUserInfo = new LoginUserInfo();
            loginUserInfo.setUserId(claimByToken.get("userId") + "");
            ThreadContext.bindLoginToken(loginUserInfo);
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadContext.clearToken();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
