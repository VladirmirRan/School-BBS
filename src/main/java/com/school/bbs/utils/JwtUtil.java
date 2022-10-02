package com.school.bbs.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * @author lu.xin
 * @version 1.0.0
 * @description JWT工具类
 * @createDate 2022/10/2 11:25
 * @since 1.0.0
 */
public class JwtUtil {
    /**
     * 有效期为 60 * 60 *1000 一个小时
     */
    public static final Long JWT_TTL = 60 * 60 * 1000L;

    /**
     * 设置秘钥明文
     */
    public static final String JWT_KEY = "schoolbbs";

    /**
     * 获取uuid
     *
     * @return String token
     */
    public static String getUUID() {
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        return token;
    }

    /**
     * 生成jwt
     *
     * @param subject String token中要存放的数据（json格式）
     * @return
     */
    public static String createJWT(String subject) {
        // 设置过期时间
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());
        return builder.compact();
    }

    /**
     * 生成jwt
     *
     * @param subject   String token中要存放的数据（json格式）
     * @param ttlMillis Long token超时时间
     * @return String
     */
    public static String createJWT(String subject, Long ttlMillis) {
        // 设置 过期时间
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());
        return builder.compact();
    }

    /**
     * 生成jwt
     *
     * @param subject   String token中要存放的数据（json格式）
     * @param ttlMillis Long token超时时间
     * @param uuid      String uuid
     * @return JwtBuilder
     */
    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                //唯一的ID
                .setId(uuid)
                // 主题 可以是JSON数据
                .setSubject(subject)
                // 签发者
                .setIssuer("lux")
                // 签发时间
                .setIssuedAt(now)
                //使用HS256对称加密算法签 名, 第二个参数为秘钥
                .signWith(signatureAlgorithm, secretKey)
                .setExpiration(expDate);
    }

    /**
     * 创建token
     *
     * @param id        String id
     * @param subject   String token中要存放的数据（json格式）
     * @param ttlMillis Long token超时时间
     * @return String
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        // 设置过期时间
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);
        return builder.compact();
    }

    /**
     * 测试
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjMjIwNzRkMzk4NGU0OGEzODgxOGI5MWU2YTVkNGI5NSIsInN1YiI6IjEwIiwiaXNzIjoic2ciLCJpYXQiOjE2NDc5MTUzOTQsImV4cCI6MTY0NzkxODk5NH0.xltF4-3j2Sw_bspyHOYv1UeK532k-l9L-DcOnBTDiAk";
        Claims claims = parseJWT(token);
        String subject = claims.getSubject();
        System.out.println("id:" + subject);
//        System.out.println(claims);
    }

    /**
     * 生成加密后的秘钥
     *
     * @return SecretKey
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 解析
     *
     * @param jwt String token
     * @return Claims
     * @throws Exception 异常
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
