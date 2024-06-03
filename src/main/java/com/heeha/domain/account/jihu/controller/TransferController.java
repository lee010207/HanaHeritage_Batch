package com.heeha.domain.account.jihu.controller;

import com.heeha.domain.account.jihu.service.TransferService;
import com.heeha.domain.history.dto.TransferHistoryDto;
import com.heeha.global.config.BaseResponse.ErrorResult;
import com.heeha.global.config.BaseResponse.SuccessResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/transfer")
@CrossOrigin(origins = "http://localhost:3000")
public class TransferController {
    private final TransferService transferService;

    @Operation(summary = "계좌 이체")
    @ApiResponses({
            @ApiResponse(responseCode = "1000", description = "계좌이체 성공", content = @Content(schema = @Schema(implementation = SuccessResult.class))),
            @ApiResponse(responseCode = "3300", description = "계좌이체 실패", content = @Content(schema = @Schema(implementation = ErrorResult.class))),
    })
    @PostMapping("/simple")
    public void simpleTransfer(@RequestBody TransferHistoryDto createHistoryDto) {
            log.info("계좌이체 시도 : {}", createHistoryDto.toString());
            transferService.transfer(createHistoryDto);
    }
}
