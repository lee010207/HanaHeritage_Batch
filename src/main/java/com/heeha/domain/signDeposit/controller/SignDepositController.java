package com.heeha.domain.signDeposit.controller;

import com.heeha.domain.signDeposit.dto.SignDepositRequest;
import com.heeha.domain.signDeposit.service.SignDepositService;
import com.heeha.domain.signSaving.dto.SavingJoinRequestDto;
import com.heeha.domain.signSaving.service.SignSavingService;
import com.heeha.global.config.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/signDeposit")
public class SignDepositController {
    private final SignDepositService signDepositService;

    @Operation(summary = "적금 상품 가입")
    @ApiResponses({
            @ApiResponse(responseCode = "1000", description = "상품 가입 성공", content = @Content(schema = @Schema(implementation = BaseResponse.SuccessResult.class)))
    })
    @PostMapping("/create")
    public void createDepositAccount(Long customerId, SignDepositRequest request) {
        signDepositService.joinDepositAccount(customerId, request);
    }
}
