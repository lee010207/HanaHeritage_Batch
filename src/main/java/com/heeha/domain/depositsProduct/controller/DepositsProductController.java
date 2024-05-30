package com.heeha.domain.depositsProduct.controller;


import com.heeha.domain.depositsProduct.dto.GetDetailDepositsProductResponse;
import com.heeha.domain.depositsProduct.service.DepositsProductService;
import com.heeha.global.config.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/api/v1/deposits-product")
public class DepositsProductController {
    private final DepositsProductService depositsProductService;

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
}
