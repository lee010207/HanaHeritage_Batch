package com.heeha.domain.account.yma.service;

import com.heeha.domain.account.yma.dto.AccountInfoResponse;
import com.heeha.domain.account.yma.repository.AccountInfoRepository;
import com.heeha.domain.signDeposit.entity.SignDeposit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountInfoService {
    private final AccountInfoRepository accountInfoRepository;

    public AccountInfoResponse getAccountInfo(Long accountId) {
        SignDeposit signDeposit = accountInfoRepository.findByAccountId(accountId);

        return AccountInfoResponse.todto(signDeposit);
    }
}