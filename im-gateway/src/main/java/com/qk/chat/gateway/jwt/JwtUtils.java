package com.qk.chat.gateway.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * {@code @ClassName} JwtUtils
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/5/26 16:02
 */
@Component
public class JwtUtils {

    private static String secret = "hc5ihoQIXkDzkfLgyxwvyC8cgk5E2SxwU8DAYypsEMRlzhkSaqGTb0pQR2lylz21SEaoRyEVIDZDBZ4zTrBTrQoZww0qCVlBabv";

    /**
     * 生成token
     * @param uid
     * @return
     */
    public static String generateToken(String uid) {
        return Jwts.builder().setHeaderParam("type", "JWT").setSubject("im-chat").setIssuedAt(new Date()).claim("uid",uid)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }


    /**
     * 检查token是否过期
     * @param expiration
     * @return
     */
    public static boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }



    public static Claims getClaimByToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            claims = e.getClaims();
        }
        return claims;
    }
}
