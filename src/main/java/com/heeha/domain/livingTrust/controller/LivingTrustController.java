package com.heeha.domain.livingTrust.controller;

import com.heeha.domain.auth.Auth;
import com.heeha.domain.livingTrust.dto.LivingTrustCreateDto;
import com.heeha.domain.livingTrust.dto.LivingTrustDoneDto;
import com.heeha.domain.livingTrust.service.LivingTrustService;
import com.heeha.global.config.BaseResponse;
import com.heeha.global.config.BaseResponse.SuccessResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/living-trust")
public class LivingTrustController {
    private final LivingTrustService livingTrustService;

    @PostMapping("/contract")
    public SuccessResult<LivingTrustDoneDto> makeContract(@Auth Long customerId,
                                                          @RequestBody LivingTrustCreateDto livingTrustCreateDto) {
        log.info("make LivingTrust Contract for Customer : {}", customerId);
        return BaseResponse.success(livingTrustService.makeContract(customerId, livingTrustCreateDto));
    }

    @GetMapping("/my")
    public SuccessResult<LivingTrustDoneDto> getMyLivingTrust(@Auth Long customerId) {
        log.info("getMyLivingTrust for Customer : {}", customerId);
        return BaseResponse.success(livingTrustService.getMyLivingTrust(customerId));
    }
}
