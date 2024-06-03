package com.heeha.domain.auth.service;

import com.heeha.domain.auth.dto.CustomerUserInfoDto;
import com.heeha.domain.auth.dto.JwtToken;
import com.heeha.domain.auth.jwt.JwtTokenProvider;
import com.heeha.domain.customer.entity.Customer;
import com.heeha.domain.customer.repository.CustomerRepository;
import com.heeha.global.config.BaseException;
import com.heeha.global.config.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final CustomerRepository customerRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, Object> redisTemplate;

    public Customer loadUserByPhoneNumber(CustomerUserInfoDto customerUserInfoDto) {
        // 해당하는 유저를 찾을 수 없는 경우
        return customerRepository.findByPhoneNumber(customerUserInfoDto.getPhoneNumber()).orElseThrow(
                () -> new BaseException(BaseResponseStatus.USERS_EMPTY_USER_ID)
        );
    }

    public JwtToken generateJwtToken(Customer customer) {
        // JWT 토큰 생성
        String accessToken = jwtTokenProvider.createAccessToken(customer.getId(), customer.getRole());
        String refreshToken = jwtTokenProvider.createRefreshToken(customer.getId(), customer.getRole());

        // Redis에 Refresh Token 저장 (만료 시간 설정을 통해 자동 삭제 처리)
        redisTemplate.opsForValue()
                .set("RT:" + customer.getId(), refreshToken, jwtTokenProvider.getRefreshExpireTime(), TimeUnit.MILLISECONDS);
        return new JwtToken(accessToken, refreshToken);
    }

    public JwtToken reissue(String refreshToken) {
        // Refresh Token 검증
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new BaseException(BaseResponseStatus.EXPIRED_TOKEN);
        }

        // refreshToken에서 User ID를 가져옵니다.
        Long userId = jwtTokenProvider.getCustomerIdFromToken(refreshToken);

        // Redis에서 저장된 Refresh Token 값을 가져옵니다.
        String storedRefreshToken = (String) redisTemplate.opsForValue().get("RT:" + userId);

        // 저장된 Refresh Token이 없거나 일치하지 않는 경우
        if (ObjectUtils.isEmpty(storedRefreshToken) || !storedRefreshToken.equals(refreshToken)) {
            throw new BaseException(BaseResponseStatus.INVALID_REFRESH_TOKEN);
        }

        // 새로운 토큰 생성
        return generateJwtToken(customerRepository.findById(userId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.USERS_EMPTY_USER_ID)));


    }

    public void logout(String accessToken) {
        // Access Token 검증
        if (!jwtTokenProvider.validateToken(accessToken)) {
            throw new BaseException(BaseResponseStatus.SYSTEM_ERROR);
        }

        // Access Token에서 User ID를 가져옵니다.
        Long userId = jwtTokenProvider.getCustomerIdFromToken(accessToken);
        System.out.println(userId);
        // Redis에서 해당 User ID로 저장된 Refresh Token이 있는지 확인 후 있을 경우 삭제합니다.
        if (redisTemplate.opsForValue().get("RT:" + userId) != null) {
            redisTemplate.delete("RT:" + userId);
        }

        // Access Token 유효 시간 가져와서 Blacklist로 저장
        Long expiration = jwtTokenProvider.getAccessExpireTime();
        redisTemplate.opsForValue().set(accessToken, "logout", expiration, TimeUnit.MILLISECONDS);
    }


}