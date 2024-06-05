package com.heeha.domain.signSaving.controller;

import com.heeha.domain.auth.Auth;
import com.heeha.domain.signSaving.dto.SavingJoinRequestDto;
import com.heeha.domain.signSaving.dto.SavingJoinResponseDto;
import com.heeha.domain.signSaving.service.SignSavingService;
import com.heeha.global.config.BaseResponse;
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
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/signSaving")
public class SignSavingController {
    private final SignSavingService signSavingService;

    @Operation(summary = "예금 상품 가입")
    @ApiResponses({
            @ApiResponse(responseCode = "1000", description = "상품 가입 성공", content = @Content(schema = @Schema(implementation = SuccessResult.class)))
    })
    @PostMapping("/create")
    public BaseResponse.SuccessResult<SavingJoinResponseDto> createSavingAccount(@Auth Long customerId,
                                                                                 @RequestBody SavingJoinRequestDto request) {

        return BaseResponse.success(signSavingService.joinSavingAccount(customerId, request));
    }
}
