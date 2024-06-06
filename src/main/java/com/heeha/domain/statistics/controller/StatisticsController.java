package com.heeha.domain.statistics.controller;

import com.heeha.domain.statistics.entity.StatisticsSettlement;
import com.heeha.domain.statistics.service.StatisticsSettlementService;
import com.heeha.global.config.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsSettlementService statisticsSettlementService;

    @Operation(summary = "일주일 정산 집계 데이터 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "1000", description = "정산 데이터 조회 성공", content = @Content(schema = @Schema(implementation = BaseResponse.SuccessResult.class)))
    })
    @GetMapping("/settlement")
    public BaseResponse.SuccessResult<List<StatisticsSettlement>> getSettlement(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate) {
        List<StatisticsSettlement> response = statisticsSettlementService.getAllByDateBetween(startDate, endDate);
        return BaseResponse.success(response);
    }
}
