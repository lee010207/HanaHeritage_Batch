package com.heeha.domain.consulting.controller;

import com.heeha.domain.consulting.dto.ConsultingReservationRequest;
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
            @ApiResponse(responseCode = "1000", description = "회원가입 성공", content = @Content(schema = @Schema(implementation = SuccessResult.class))),
            @ApiResponse(responseCode = "5000", description = "이미 가입된 휴대폰 또는 주민등록번호", content = @Content(schema = @Schema(implementation = ErrorResult.class))),
    })
    @PostMapping("/reservation")
    public SuccessResult<Long> signUp(@RequestBody ConsultingReservationRequest request) {
        log.info("상담 예약 시도 : {}", request);
        return BaseResponse.success(consultingService.save(request));
    }
}
