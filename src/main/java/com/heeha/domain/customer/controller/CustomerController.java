package com.heeha.domain.customer.controller;

import com.heeha.domain.customer.dto.SignUpRequest;
import com.heeha.domain.customer.service.CustomerService;
import com.heeha.global.config.BaseException;
import com.heeha.global.config.BaseResponse;
import com.heeha.global.config.BaseResponse.ErrorResult;
import com.heeha.global.config.BaseResponse.SuccessResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Operation(summary = "회원 가입")
    @ApiResponses({
            @ApiResponse(responseCode = "1000", description = "회원가입 성공", content = @Content(schema = @Schema(implementation = SuccessResult.class))),
            @ApiResponse(responseCode = "5000", description = "이미 가입된 휴대폰 또는 주민등록번호", content = @Content(schema = @Schema(implementation = ErrorResult.class))),
    })
    @PostMapping("/signup")
    public SuccessResult<Long> signUp(@RequestBody SignUpRequest signUpRequest) {
        log.info("회원가입 시도 : {}", signUpRequest);
        return BaseResponse.success(customerService.save(signUpRequest));
    }
}
