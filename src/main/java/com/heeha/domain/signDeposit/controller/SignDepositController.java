package com.heeha.domain.signDeposit.controller;

import com.heeha.domain.account.yma.dto.AccountInfoResponse;
import com.heeha.domain.signDeposit.dto.SignDepositRequest;
import com.heeha.domain.signDeposit.dto.SignDepositResponse;
import com.heeha.domain.signDeposit.service.SignDepositService;
import com.heeha.domain.signSaving.dto.SavingJoinRequestDto;
import com.heeha.domain.signSaving.service.SignSavingService;
import com.heeha.global.config.BaseException;
import com.heeha.global.config.BaseResponse;
import com.heeha.global.config.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public BaseResponse.SuccessResult<SignDepositResponse> createDepositAccount(Long customerId,
                                                                                SignDepositRequest request) {

        return BaseResponse.success(signDepositService.joinDepositAccount(customerId, request));
    }
    @Operation(summary = "적금 계좌 정보 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "1000", description = "조회 완료", content = @Content(schema = @Schema(implementation = BaseResponse.SuccessResult.class))),
            @ApiResponse(responseCode = "3200", description = "계좌 아이디 없음", content = @Content(schema = @Schema(implementation = BaseResponse.ErrorResult.class))),
    })
    @GetMapping("/{accountId}")
    public BaseResponse.SuccessResult<AccountInfoResponse> getAccountInfo(@PathVariable Long accountId) {
        try {
            AccountInfoResponse response = signDepositService.getAccountInfo(accountId);
            return BaseResponse.success(response);
        } catch (NullPointerException e) {
            throw new BaseException(BaseResponseStatus.ACCOUNTS_EMPTY_ACCOUNT_ID);
        }
    }
}
