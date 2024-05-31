package com.heeha.domain.depositsProduct.controller;


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

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final DepositsProductService depositProductService;

    @Operation(summary = "예적금 상품 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "1000", description = "조회 완료", content = @Content(schema = @Schema(implementation = BaseResponse.SuccessResult.class))),
            @ApiResponse(responseCode = "5000", description = "조회 불가", content = @Content(schema = @Schema(implementation = BaseResponse.ErrorResult.class))),
    })
    @GetMapping("/save")
    public boolean saveall() {
        depositProductService.save();
        return true;
    }
}
