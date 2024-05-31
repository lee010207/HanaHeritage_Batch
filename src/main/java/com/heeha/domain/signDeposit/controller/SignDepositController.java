package com.heeha.domain.signDeposit.controller;

import com.heeha.domain.signDeposit.dto.DepositResponse;
import com.heeha.domain.signDeposit.service.SignDepositService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accountinfo")
@RequiredArgsConstructor
public class SignDepositController {
    private final SignDepositService signDepositService;

    @GetMapping("/{accountId}")
    public ResponseEntity<DepositResponse> getAccountInfo(@PathVariable Long accountId) {
        DepositResponse response = signDepositService.getAccountInfo(accountId);
        return ResponseEntity.ok(response);
    }
}