package com.heeha.domain.consulting.controller;

import com.heeha.domain.auth.Auth;
import com.heeha.domain.consulting.dto.GetConsultingDto;
import com.heeha.domain.consulting.dto.ReserveConsultingDto;
import com.heeha.domain.consulting.entity.Consulting;
import com.heeha.domain.consulting.service.ConsultingService;
import com.heeha.global.config.BaseResponse;
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

import java.time.LocalDate;
import java.util.List;

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
    public SuccessResult<Long> reserveConsulting(@Auth Long customerId, @RequestBody ReserveConsultingDto request) {
        return BaseResponse.success(consultingService.save(customerId, request));
    }

    @Operation(summary = "상담 목록 조회하기")
    @ApiResponses({
            @ApiResponse(responseCode = "1000", description = "상담 목록 조회 성공", content = @Content(schema = @Schema(implementation = SuccessResult.class))),
    })
    @GetMapping("/reservation")
    public SuccessResult<List<GetConsultingDto>> getConsultingList(@RequestParam("reservationDate") LocalDate reservationDate) {
        return BaseResponse.success(consultingService.getAllByReservationDate(reservationDate));
    }
}
