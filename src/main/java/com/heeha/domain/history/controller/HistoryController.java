package com.heeha.domain.history.controller;

import com.heeha.domain.history.dto.HistoryDto;
import com.heeha.domain.history.service.HistoryService;
import com.heeha.global.config.BaseResponse;
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

    @GetMapping("/account/{accountId}")
    public BaseResponse.SuccessResult<List<HistoryDto>> getHistoryByAccountId(@PathVariable Long accountId) {
        return BaseResponse.success(historyService.getHistoryByAccountId(accountId));
    };
}
