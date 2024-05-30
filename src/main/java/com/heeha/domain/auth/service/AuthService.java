package com.heeha.domain.auth.service;

import com.heeha.domain.auth.dto.CustomerUserInfoDto;
import com.heeha.domain.auth.dto.JwtToken;
import com.heeha.domain.auth.jwt.JwtTokenProvider;
import com.heeha.domain.customer.entity.Customer;
import com.heeha.domain.customer.repository.CustomerRepository;
import com.heeha.global.config.BaseException;
import com.heeha.global.config.BaseResponseStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final CustomerRepository customerRepository;
    private final JwtTokenProvider jwtTokenProvider;


    public Customer loadUserByPhoneNumber(CustomerUserInfoDto customerUserInfoDto) {
        // 해당하는 유저를 찾을 수 없는 경우
        return customerRepository.findByPhoneNumber(customerUserInfoDto.getPhoneNumber()).orElseThrow(
                () -> new BaseException(BaseResponseStatus.USERS_EMPTY_USER_ID)
        );
    }

    public JwtToken generateJwtToken(Customer customer) {
        // JWT 토큰 생성
        String accessToken = jwtTokenProvider.createAccessToken(customer.getId(), customer.getRoleType());
        String refreshToken = jwtTokenProvider.createRefreshToken(customer.getId(), customer.getRoleType());
        return new JwtToken(accessToken, refreshToken);
    }
}
//
//
//@Service
//@RequiredArgsConstructor
//public class AuthService {
//    private final CustomerRepository customerRepository;
//    private final JwtTokenProvider jwtTokenProvider;
//
//    public JwtToken loadUserByPhoneNumber(@NotEmpty(message = "전화번호는 필수 입력값입니다.") CustomerUserInfoDto customerUserInfoDto) {
//        //해당하는 유저를 찾을 수 없는 경우
//        Customer customer = customerRepository.findByPhoneNumber(customerUserInfoDto.getPhoneNumber()).orElseThrow(
//                () -> new BaseException(BaseResponseStatus.USERS_EMPTY_USER_ID)
//        );
//        if(!customer.getPassword().equals(customerUserInfoDto.getPassword())) {
//            // 고쳐야 함
//            throw new BaseException(BaseResponseStatus.INVALID_JWT);
//        }
//
//        return new JwtToken(jwtTokenProvider.createAccessToken(customer.getId(),customer.getRoleType()), jwtTokenProvider.createRefreshToken(customer.getId(), customer.getRoleType()));
//    }
//}