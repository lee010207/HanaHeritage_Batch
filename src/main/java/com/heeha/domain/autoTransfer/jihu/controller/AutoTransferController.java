package com.heeha.domain.autoTransfer.jihu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/autoTransfer")
public class AutoTransferController {

//    @Operation(summary = "계좌 조회")
//    @ApiResponses({
//            @ApiResponse(responseCode = "1000", description = "계좌조회 성공", content = @Content(schema = @Schema(implementation = SuccessResult.class))),
//            @ApiResponse(responseCode = "5000", description = "오류", content = @Content(schema = @Schema(implementation = ErrorResult.class))),
//    })
//    @PostMapping("/get")
//    public SuccessResult<List<AccountCheckResponse>> getAccount(@RequestBody Long customerId) {
//        log.info("내 계좌 조회 : {}", customerId);
//        return BaseResponse.success(accountService.findByCustomerId(customerId));
//    }
//
//    @Operation(summary = "계좌 이체")
//    @ApiResponses({
//            @ApiResponse(responseCode = "1000", description = "계좌이체 성공", content = @Content(schema = @Schema(implementation = SuccessResult.class))),
//            @ApiResponse(responseCode = "3300", description = "계좌이체 실패", content = @Content(schema = @Schema(implementation = ErrorResult.class))),
//    })
//    @PostMapping("/simple")
//    public void simpleTransfer(@RequestBody HistoryRequest historyRequest) {
//            log.info("계좌이체 시도 : {}", historyRequest.toString());
//            accountService.transfer(historyRequest);
//    }
}
