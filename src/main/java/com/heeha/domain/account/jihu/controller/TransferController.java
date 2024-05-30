package com.heeha.domain.account.jihu.controller;

import com.heeha.domain.account.entity.Account;
import com.heeha.domain.account.jihu.dto.AccountCheckResponse;
import com.heeha.domain.account.jihu.service.AccountService;
import com.heeha.domain.history.dto.HistoryRequest;
import com.heeha.domain.history.service.HistoryService;
import com.heeha.global.config.BaseException;
import com.heeha.global.config.BaseResponse;
import com.heeha.global.config.BaseResponse.ErrorResult;
import com.heeha.global.config.BaseResponse.SuccessResult;
import com.heeha.global.config.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/transfer")
@CrossOrigin(origins = "http://localhost:3000")
public class TransferController {
    private final AccountService accountService;

    @Operation(summary = "계좌 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "1000", description = "계좌조회 성공", content = @Content(schema = @Schema(implementation = SuccessResult.class))),
            @ApiResponse(responseCode = "5000", description = "오류", content = @Content(schema = @Schema(implementation = ErrorResult.class))),
    })
    @PostMapping("/get")
    public SuccessResult<List<AccountCheckResponse>> getAccount(@RequestBody Long customerId) {
        log.info("내 계좌 조회 : {}", customerId);
        return BaseResponse.success(accountService.findByCustomerId(customerId));
    }

    @Operation(summary = "계좌 이체")
    @ApiResponses({
            @ApiResponse(responseCode = "1000", description = "계좌이체 성공", content = @Content(schema = @Schema(implementation = SuccessResult.class))),
            @ApiResponse(responseCode = "3300", description = "계좌이체 실패", content = @Content(schema = @Schema(implementation = ErrorResult.class))),
    })
    @PostMapping("/simple")
    public void simpleTransfer(@RequestBody HistoryRequest historyRequest) {
            log.info("계좌이체 시도 : {}", historyRequest.toString());
            accountService.transfer(historyRequest);
    }
}
