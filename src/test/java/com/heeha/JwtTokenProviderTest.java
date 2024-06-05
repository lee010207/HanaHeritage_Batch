package com.heeha;

import com.heeha.domain.auth.jwt.JwtTokenProvider;
import com.heeha.domain.customer.entity.RoleType;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JwtTokenProviderTest {
    private final String secret = "0mUet36qbTmKpG6lQPJJbij815G75KB2zwUim2U6Hjc=";
    private final String issue = "quswjdgma83@naver.com";

    @InjectMocks
    private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validateValidToken() {
        String secret = this.secret;
        String issue = this.issue;
        jwtTokenProvider = new JwtTokenProvider(secret, issue);

        String token = jwtTokenProvider.createAccessToken(1L, RoleType.USER);
        assertTrue(jwtTokenProvider.validateToken(token));
    }

    @Test
    void validateInvalidToken() {
        String secret = this.secret;
        String issue = this.issue;
        jwtTokenProvider = new JwtTokenProvider(secret, issue);

        String token = "invalid.token.here";
        assertFalse(jwtTokenProvider.validateToken(token));
    }

    @Test
    void validateExpiredToken() {
        String secret = this.secret;
        String issue = this.issue;
        jwtTokenProvider = new JwtTokenProvider(secret, issue);
        Map<String, Object> payload = new HashMap<>();
        payload.put("id",  1);
        payload.put("role", RoleType.USER);
        // Create a token with a short expiration time
        String token = jwtTokenProvider.createToken(1L, payload); // Token expired 1 second ago
        assertFalse(jwtTokenProvider.validateToken(token));
    }
}