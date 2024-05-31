package com.heeha.domain.account.yma.controller;

import com.heeha.domain.account.yma.dto.AccountInfoResponse;
import com.heeha.domain.account.yma.service.AccountInfoService;
import com.heeha.global.config.BaseException;
import com.heeha.global.config.BaseResponse;
import com.heeha.global.config.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.heeha.global.config.BaseResponse.SuccessResult;
@RestController
@RequestMapping("/api/v1/accountinfo")
@RequiredArgsConstructor
public class AccountInfoController {
    private final AccountInfoService accountInfoService;

    @Operation(summary = "계좌 정보 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "1000", description = "조회 완료", content = @Content(schema = @Schema(implementation = BaseResponse.SuccessResult.class))),
            @ApiResponse(responseCode = "3200", description = "계좌 아이디 없음", content = @Content(schema = @Schema(implementation = BaseResponse.ErrorResult.class))),
    })
    @GetMapping("/{accountId}")
    public SuccessResult<AccountInfoResponse> getAccountInfo(@PathVariable Long accountId) {
        try {
            AccountInfoResponse response = accountInfoService.getAccountInfo(accountId);
            return BaseResponse.success(response);
        } catch (NullPointerException e){
            throw new BaseException(BaseResponseStatus.ACCOUNTS_EMPTY_ACCOUNT_ID);
        }
    }
}