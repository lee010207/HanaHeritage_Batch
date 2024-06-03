package com.heeha.domain.account.jihu.controller;

import com.heeha.domain.account.JCS.service.AccountService;
import com.heeha.domain.account.jihu.dto.AccountCheckResponse;

import com.heeha.global.config.BaseResponse;
import com.heeha.global.config.BaseResponse.SuccessResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/account")
@CrossOrigin
public class AccountController {
    private final AccountService accountService;


    @Operation(summary = "계좌 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "1000", description = "계좌조회 성공", content = @Content(schema = @Schema(implementation = SuccessResult.class))),
    })
    @GetMapping("/my")
    public SuccessResult<List<AccountCheckResponse>> getMyAccounts() {
        return BaseResponse.success(accountService.myAccounts(1L));
    }


}