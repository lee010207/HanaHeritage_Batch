package com.heeha.domain.consulting.controller;

import com.heeha.domain.consulting.dto.ConsultingReservationRequest;
import com.heeha.domain.consulting.service.ConsultingService;
import com.heeha.global.config.BaseResponse;
import com.heeha.global.config.BaseResponse.ErrorResult;
import com.heeha.global.config.BaseResponse.SuccessResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/consulting")
public class ConsultingController {
    private final ConsultingService consultingService;

    @Operation(summary = "상담 예약하기")
    @ApiResponses({
            @ApiResponse(responseCode = "1000", description = "상담 예약 성공", content = @Content(schema = @Schema(implementation = SuccessResult.class))),
            @ApiResponse(responseCode = "2010", description = "잘못된 유저 아이디값", content = @Content(schema = @Schema(implementation = ErrorResult.class))),
            @ApiResponse(responseCode = "15001", description = "잘못된 상담 유형 (가능 : 예·적금, 대출, 외환, 펀드·보험·연금·일임형ISA, 폰·모바일·인터넷뱅킹, 기업뱅킹, 퇴직연금)", content = @Content(schema = @Schema(implementation = ErrorResult.class))),
    })
    @PostMapping("/reservation")
    public SuccessResult<Long> signUp(@RequestBody ConsultingReservationRequest request) {
        log.info("상담 예약 시도 : {}", request);
        Long customerId = 1L; // TODO : token에서 가져오도록 수정
        return BaseResponse.success(consultingService.save(customerId, request));
    }
}
