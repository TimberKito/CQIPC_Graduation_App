package com.timberkito.server.config.security.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Timber.Wang
 * @date 2021/12/10 22:05
 */
@Component
public class JwtTokenUtil {

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * @param userDetails
     * @return java.lang.String
     * @author Timber.Wang
     * @describe: 根据用户信息生成 token
     * @date 2021/12/15 21:19
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * @param token
     * @return java.lang.String
     * @author Timber.Wang
     * @describe: 从 token 中获取登陆用户名
     * @date 2021/12/15 21:19
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFormToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * @param token
     * @param userDetails
     * @return boolean
     * @author Timber.Wang
     * @describe: 验证 token 是否有效
     * @date 2021/12/15 21:19
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * @param token
     * @return boolean
     * @author Timber.Wang
     * @describe: 判断 token 是否可以被刷新
     * @date 2021/12/15 21:20
     */
    public boolean canRefresh(String token) {
        return isTokenExpired(token);
    }

    /**
     * @param token
     * @return java.lang.String
     * @author Timber.Wang
     * @describe: 刷新 token
     * @date 2021/12/15 21:20
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFormToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * @param token
     * @return boolean
     * @author Timber.Wang
     * @describe: 判断 token 是否失效
     * @date 2021/12/15 21:21
     */
    private boolean isTokenExpired(String token) {
        Date expireDate = getExpiredDateFromToken(token);
        return expireDate.before(new Date());
    }

    /**
     * @param token
     * @return java.util.Date
     * @author Timber.Wang
     * @describe: 从 token 中获取过期时间
     * @date 2021/12/15 21:21
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFormToken(token);
        return claims.getExpiration();
    }

    /**
     * @param token
     * @return io.jsonwebtoken.Claims
     * @author Timber.Wang
     * @describe: 从 token 中获取载荷
     * @date 2021/12/15 21:21
     */
    private Claims getClaimsFormToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    /**
     * @param claims
     * @return java.lang.String
     * @author Timber.Wang
     * @describe: 根据载荷生成 jwt token
     * @date 2021/12/15 21:22
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * @param
     * @return java.util.Date
     * @author Timber.Wang
     * @describe: 生成 token 失效时间
     * @date 2021/12/15 21:22
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }
}
