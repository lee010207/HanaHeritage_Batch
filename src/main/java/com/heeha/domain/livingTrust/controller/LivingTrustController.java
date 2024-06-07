package com.heeha.domain.livingTrust.controller;

import com.heeha.domain.consulting.dto.GetConsultingDto;
import com.heeha.domain.consulting.service.ConsultingService;
import com.heeha.domain.livingTrust.entity.LivingTrust;
import com.heeha.domain.livingTrust.service.LivingTrustService;
import com.heeha.global.config.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/living-trust")
public class LivingTrustController {
    private final LivingTrustService livingTrustService;

    @Operation(summary = "[😈Admin] 상속 계약 대기 목록 조회하기")
    @ApiResponses({
            @ApiResponse(responseCode = "1000", description = "계약 대기 목록 조회 성공", content = @Content(schema = @Schema(implementation = BaseResponse.SuccessResult.class))),
    })
    @GetMapping("/contract/list")
    public BaseResponse.SuccessResult<List<LivingTrust>> getConsultingList() {
        return BaseResponse.success(livingTrustService.getAll());
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
