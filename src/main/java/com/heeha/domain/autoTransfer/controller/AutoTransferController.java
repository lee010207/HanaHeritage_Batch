package com.heeha.domain.autoTransfer.controller;

import com.heeha.domain.autoTransfer.dto.CreateAutoTransferDto;
import com.heeha.domain.autoTransfer.service.AutoTransferService;
import com.heeha.global.config.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.heeha.global.config.BaseResponse.ErrorResult;
import com.heeha.global.config.BaseResponse.SuccessResult;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/autoTransfer")
public class AutoTransferController {

    private final AutoTransferService  autoTransferService;

    @Operation(summary = "자동이체 등록")
    @ApiResponses({
            @ApiResponse(responseCode = "1000", description = "자동이체 등록 성공", content = @Content(schema = @Schema(implementation = SuccessResult.class))),
            @ApiResponse(responseCode = "3500", description = "자동이체 등록 실패", content = @Content(schema = @Schema(implementation = ErrorResult.class))),
    })
    @PostMapping("/register")
    public SuccessResult<Long> getAccount(@RequestBody CreateAutoTransferDto createAutoTransferDto) {
        log.info("자동이체 등록 : {}", createAutoTransferDto.toString());
        return BaseResponse.success(autoTransferService.createAutoTransfer(createAutoTransferDto));
    }

}
