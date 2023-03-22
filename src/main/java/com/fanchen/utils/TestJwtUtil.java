package com.fanchen.utils;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.xml.ws.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestJwtUtil {
    //设置jwt密钥
    private static final String SECRET = "dawdakjh@#";

    /**
     * 生成Token
     * @param acct
     * @return
     */
    public static String gerateToken(String acct){
        Map<String,Object> claims = new HashMap<String,Object>();
        claims.put("acct",acct);
        String token = Jwts.builder()
                //签发算法，设置密钥
                .signWith(SignatureAlgorithm.HS256, SECRET)
                //body数据，要唯一，自行设置
                .addClaims(claims)
                //设置签发时间
                .setIssuedAt(new Date())
                //设置过期时间，当前系统时间后过 一天
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                //生成token
                .compact();
        return token;
    }

    /**
     * 解析Token
     * @param token
     */

    public static Map<String,Object> parseToken(String token){

        Map<String,Object> map = (Map<String,Object>)Jwts.parser()
                //设置解析需要的密钥
                .setSigningKey(SECRET)
                .parseClaimsJws(token)

                .getBody();
        return map;
    }

    public static void main(String[] args) {
        String token = gerateToken("admin");
        System.out.println(token);
        Map<String, Object> map = parseToken(token);
        String jwt = (String) map.get("acct");
        System.out.println(jwt);
    }
}
