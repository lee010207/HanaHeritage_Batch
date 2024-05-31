package com.heeha.domain.auth.controller;

import com.heeha.domain.auth.dto.CustomerUserInfoDto;
import com.heeha.domain.auth.dto.JwtToken;
import com.heeha.domain.auth.jwt.JwtTokenExtractor;
import com.heeha.domain.auth.service.AuthService;
import com.heeha.domain.customer.entity.Customer;
import com.heeha.global.config.BaseException;
import com.heeha.global.config.BaseResponse;
import com.heeha.global.config.BaseResponseStatus;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    public BaseResponse.SuccessResult<JwtToken> login(@RequestBody CustomerUserInfoDto loginRequest) {
        // 사용자 정보 로드
        Customer userDetails = authService.loadUserByPhoneNumber(loginRequest);

        // 비밀번호 검증
        if (!userDetails.getPassword().equals(loginRequest.getPassword())) {
            throw new BaseException(BaseResponseStatus.INVALID_USER_JWT);
        }

        // JWT 토큰 생성
        return BaseResponse.success(authService.generateJwtToken(userDetails));
    }

    @PostMapping("/reissue")
    public BaseResponse.SuccessResult<JwtToken> reissue(HttpServletRequest request) {
        // refresh 토큰 추출
        String refreshToken = JwtTokenExtractor.extractRefresh(request);
        String accessToken = JwtTokenExtractor.extractJwt(request);
        return BaseResponse.success(authService.reissue(refreshToken,accessToken));
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        String accessToken = JwtTokenExtractor.extractJwt(request);
        authService.logout(accessToken);
    }
}
