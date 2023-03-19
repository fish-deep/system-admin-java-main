package com.fanchen.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JwtUtil
 */
@Data
@Component
@ConfigurationProperties(prefix = "system.jwt")//yml文件中
public class JwtUtil {

    private String key;
    private long expire;//3600
    private String header;


    public String createToken(String username){
        Date date = new Date();
        System.out.println(date);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(username)
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + 1000 * expire)) //令牌过期时间，当前时间+1小时
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public Claims parserToken(String jwt){
        try{
            return Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwt)
                    .getBody();
        }catch (Exception e){
            return null;
        }
    }

    public boolean isExpire(Claims claims){
        return claims.getExpiration().before(new Date());
    }

}
