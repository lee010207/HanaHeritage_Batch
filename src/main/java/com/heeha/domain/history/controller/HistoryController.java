package com.heeha.domain.history.controller;

import com.heeha.domain.account.JCS.dto.AccountCheckResponse;
import com.heeha.domain.history.dto.HistoryDto;
import com.heeha.domain.history.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/history")
@RequiredArgsConstructor
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping("/api/v1/history/account/{accountNumber}")
    public List<HistoryDto> getHistoryByAccountNumber(@PathVariable Long accountNumber) {
        return historyService.getHistoryByAccountNumber(accountNumber);
    };
}
