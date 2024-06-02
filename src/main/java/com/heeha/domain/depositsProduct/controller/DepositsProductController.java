package com.heeha.domain.depositsProduct.controller;


import com.heeha.domain.depositsProduct.dto.GetListDepositsProductResponse;
import com.heeha.domain.depositsProduct.dto.GetDetailDepositsProductResponse;
import com.heeha.domain.depositsProduct.service.DepositsProductService;
import com.heeha.global.config.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameter;
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
    @Operation(summary = "예적금 상품 상세 조회")
    @Parameter(name = "id", description = "조회하고 싶은 deposits product id 입력")
    @ApiResponses({
            @ApiResponse(responseCode = "1000", description = "회원가입 성공", content = @Content(schema = @Schema(implementation = BaseResponse.SuccessResult.class))),
            @ApiResponse(responseCode = "7600", description = "존재하지 않는 deposit product id", content = @Content(schema = @Schema(implementation = BaseResponse.ErrorResult.class))),
    })
    @GetMapping("/detail")
    public BaseResponse.SuccessResult<GetDetailDepositsProductResponse> signUp(@RequestParam("id") Long id) {
        GetDetailDepositsProductResponse response = depositsProductService.getDetail(id);
        return BaseResponse.success(response);
    }

    @GetMapping("/save")
    @Operation(summary = "예적금 상품 저장")
    public BaseResponse.SuccessResult<Boolean> saveProduct() {
        depositsProductService.saveSavingProduct();
        depositsProductService.saveDepositProduct();
        return BaseResponse.success(true);
    }
}

