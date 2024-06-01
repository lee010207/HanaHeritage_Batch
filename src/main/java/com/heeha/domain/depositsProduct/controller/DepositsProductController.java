package com.heeha.domain.depositsProduct.controller;


import com.heeha.domain.depositsProduct.dto.GetListDepositsProductResponse;
import com.heeha.domain.depositsProduct.service.DepositsProductService;
import com.heeha.global.config.BaseResponse;
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
@RequestMapping("/api/v1/deposits-products")
public class DepositsProductController {
    private final DepositsProductService depositsProductService;

    @Operation(summary = "예적금 상품 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "1000", description = "조회 완료", content = @Content(schema = @Schema(implementation = BaseResponse.SuccessResult.class))),
    })
    @GetMapping("/list")
    public BaseResponse.SuccessResult<List<GetListDepositsProductResponse>> getDepositsProducts() {
        List<GetListDepositsProductResponse> response = depositsProductService.getList();
        return BaseResponse.success(response);
    }

    @Operation(summary = "예적금 상품 검색")
    @ApiResponses({
            @ApiResponse(responseCode = "1000", description = "검색 완료", content = @Content(schema = @Schema(implementation = BaseResponse.SuccessResult.class))),
    })
    @GetMapping("/search")
    public BaseResponse.SuccessResult<List<GetListDepositsProductResponse>> searchDepositsProducts(@RequestParam String searchword) {
        List<GetListDepositsProductResponse> response = depositsProductService.searchList(searchword);
        return BaseResponse.success(response);
    }
}

