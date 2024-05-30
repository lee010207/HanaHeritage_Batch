package com.heeha.domain.auth.jwt;

import com.heeha.domain.auth.dto.RoleType;
import com.heeha.global.config.BaseException;
import com.heeha.global.config.BaseResponseStatus;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtTokenProvider {

    public static final String USER_TOKEN_NAME = "user_refresh_token";
    public static final String MASTER_TOKEN_NAME = "master_refresh_token";
    private final SecretKey key;
    private final long accessExpireTime = 30 * 60 * 1000L;
    private final long refreshExpireTime = 24 * 60 * 60 * 1000L;
    private final JwtParser jwtParser;
    private final String ISSUE;

    public JwtTokenProvider(@Value("${jwt.secret}") String secret,
                            @Value("${jwt.issuer}") String issue) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
        this.ISSUE = issue;
    }

    public String createAccessToken(Long id, RoleType roleType) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", id);
        payload.put("role", roleType.name());
        return createToken(accessExpireTime, payload);
    }

    public String createRefreshToken(Long id, RoleType roleType) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", id);
        payload.put("role", roleType.name());
        return createToken(refreshExpireTime, payload);
    }

    private String createToken(long expireTime, Map<String, Object> payload) {
        Date now = new Date();
        return Jwts.builder()
                .setIssuer(ISSUE)
                .setClaims(payload)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expireTime))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public RoleType getRoleFromToken(String token) {
        Claims claims = getValidClaim(token);
        return RoleType.valueOf(claims.get("role").toString());
    }

    public Long getCustomerIdFromToken(String token) {
        Claims claims = getValidClaim(token);
        return Long.parseLong(claims.get("id").toString());
    }

    private Claims getValidClaim(String token) {
        try {
            return parseClaim(token);
        } catch (ExpiredJwtException e) {
            throw new BaseException(BaseResponseStatus.EXPIRED_TOKEN);
        } catch (MalformedJwtException | IllegalArgumentException e) {
            throw new BaseException(BaseResponseStatus.INVALID_JWT);
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.SYSTEM_ERROR);
        }
    }

    private Claims parseClaim(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public String isTokenValid(String token) {
        try {
            Claims claims = getValidClaim(token);
            return "PASS";
        } catch (BaseException e) {
            if (e.getStatus().getCode() == BaseResponseStatus.INVALID_JWT.getCode()) {
                return "INVALID_JWT";
            } else if (e.getStatus().getCode() == BaseResponseStatus.EXPIRED_TOKEN.getCode()){
                return "EXPIRED_TOKEN";
            } else {
                return "SYSTEM_ERROR";
            }
        }
    }
}