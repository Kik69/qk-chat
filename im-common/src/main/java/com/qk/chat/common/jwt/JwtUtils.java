package com.inspur.app.main.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * {@code @ClassName} JwtUtils
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/5/26 16:02
 */
public class JwtUtils {

    private static String secret = "hc5ihoQIXkDzkfLgyxwvyC8cgk5E2SxwU8DAYypsEMRlzhkSaqGTb0pQR2lylz21SEaoRyEVIDZDBZ4zTrBTrQoZww0qCVlBabv";

    public static String generateToken(String target, Date expireDate) {
        return Jwts.builder().setHeaderParam("type", "JWT").setSubject(target).setIssuedAt(expireDate)
                .setExpiration(expireDate).signWith(SignatureAlgorithm.HS512, secret).compact();
    }


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
