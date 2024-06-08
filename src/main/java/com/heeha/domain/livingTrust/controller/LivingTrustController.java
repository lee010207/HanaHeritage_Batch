package com.heeha.domain.livingTrust.controller;


import com.heeha.domain.auth.Auth;
import com.heeha.domain.livingTrust.dto.LivingTrustCreateDto;
import com.heeha.domain.livingTrust.dto.LivingTrustDoneDto;
import com.heeha.global.config.BaseResponse;
import com.heeha.global.config.BaseResponse.SuccessResult;
import com.heeha.domain.livingTrust.dto.GetLivingTrustSummaryDto;
import com.heeha.domain.livingTrust.service.LivingTrustService;
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
@RequestMapping("/api/v1/living-trust")
public class LivingTrustController {
    private final LivingTrustService livingTrustService;


    @Operation(summary = "유언 대용 신탁 계약 생성")
    @ApiResponses({
            @ApiResponse(responseCode = "1000", description = "계약 생성 성공", content = @Content(schema = @Schema(implementation = BaseResponse.SuccessResult.class))),
    })
    @PostMapping("/contract")
    public SuccessResult<LivingTrustDoneDto> makeContract(@Auth Long customerId,
                                                          @RequestBody LivingTrustCreateDto livingTrustCreateDto) {
        log.info("make LivingTrust Contract for Customer : {}", customerId);
        return BaseResponse.success(livingTrustService.makeContract(customerId, livingTrustCreateDto));
    }

    @Operation(summary = "나의 유언 대용 신탁 계약 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "1000", description = "계약 조회 성공", content = @Content(schema = @Schema(implementation = BaseResponse.SuccessResult.class))),
    })
    @GetMapping("/my")
    public SuccessResult<LivingTrustDoneDto> getMyLivingTrust(@Auth Long customerId) {
        log.info("getMyLivingTrust for Customer : {}", customerId);
        return BaseResponse.success(livingTrustService.getMyLivingTrust(customerId));
    }

    @Operation(summary = "[😈Admin] 상속 계약 대기 목록 조회하기")
    @ApiResponses({
            @ApiResponse(responseCode = "1000", description = "계약 대기 목록 조회 성공", content = @Content(schema = @Schema(implementation = BaseResponse.SuccessResult.class))),
    })
    @GetMapping("/contract/list")
    public BaseResponse.SuccessResult<List<GetLivingTrustSummaryDto>> getLivingTrustList() {
        return BaseResponse.success(livingTrustService.getAllSummary());
    }

    @Operation(summary = "[😈Admin] 상속 계약 승인 처리하기")
    @ApiResponses({
            @ApiResponse(responseCode = "1000", description = "승인 처리 성공", content = @Content(schema = @Schema(implementation = BaseResponse.SuccessResult.class))),
    })
    @PutMapping("/contract/complete")
    public BaseResponse.SuccessResult<Boolean> editConsultingStatus(@RequestParam("id") Long id) {
        return BaseResponse.success(livingTrustService.setComplete(id));
    }
}
