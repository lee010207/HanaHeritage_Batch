package com.heeha.domain.auth.jwt;

import com.heeha.global.config.BaseException;
import com.heeha.global.config.BaseResponseStatus;
import jakarta.servlet.http.HttpServletRequest;

public class JwtTokenExtractor {
    private static final String SCHEMA = "Bearer ";
    private static final String TOKEN_HEADER = "Authorization";

    private JwtTokenExtractor() {
    }

    public static String extractJwt(HttpServletRequest req) {
        String token = req.getHeader(TOKEN_HEADER);

        if (token != null && token.startsWith(SCHEMA)) {
            return token.replace(SCHEMA,"");
        }
        throw new BaseException(BaseResponseStatus.INVALID_JWT);
    }
}