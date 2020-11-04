package com.delivery.util;


import com.delivery.entity.Customer;
import com.delivery.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * JWT工具类
 */
public class JwtUtils {

    public static final String SUBJECT = "yvan";

    /**
     * 过期时间，毫秒，一天
     */
    public static final long EXPIRE = 1000 * 60 * 60 * 24 * 1;

    /**
     * 过期时间 一分钟
     */
    public static final long EXPIREMINUTE = 1000 * 60 * 1;

    /**
     * 秘钥
     */
    public static final String APPSECRET = "yvna1314";

    /**
     * 生成JWT
     *
     * @param user 用户
     * @return token
     */
    public static String geneJsonWebToken(User user) {
        if (user == null || user.getUserName() == null || user.getUserNumber() == null) {
            return null;
        }
        return Jwts.builder().setSubject(SUBJECT)
                .claim("name", user.getUserName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, APPSECRET).compact();
    }

    /**
     * 生成JWT
     *
     * @param customer 用户
     * @return token
     */
    public static String geneJsonWebToken(Customer customer) {
        if (customer == null || customer.getName() == null || customer.getCustomerEmail() == null) {
            return null;
        }
        return Jwts.builder().setSubject(SUBJECT)
                .claim("name", customer.getName())
                .claim("customerEmail", customer.getCustomerEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, APPSECRET).compact();
    }

    public static String geneJsonWebTokeEmail(String email) {
        if (!TypeUtil.isValidString(email)) {
            return null;
        }
        return Jwts.builder().setSubject(SUBJECT)
                .claim("email", email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIREMINUTE * 5))
                .signWith(SignatureAlgorithm.HS256, APPSECRET).compact();
    }


    public static Claims checkJWT(String token) throws Exception {
        try {
            final Claims claims;
            claims = Jwts.parser().setSigningKey(APPSECRET)
                    .parseClaimsJws(token).getBody();
            return claims;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
