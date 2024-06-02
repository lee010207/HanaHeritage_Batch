package com.heeha.domain.signDeposit.service;

import com.heeha.domain.signDeposit.dto.AccountInfoResponse;
import com.heeha.domain.signDeposit.entity.SignDeposit;
import com.heeha.domain.signDeposit.repository.SignDepositRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignDepositService {

    private final SignDepositRepository signDepositRepository;

    public AccountInfoResponse getAccountInfo(Long accountId) {
        SignDeposit signDeposit = signDepositRepository.findByAccountId(accountId);

        return AccountInfoResponse.todto(signDeposit);
    }
}
