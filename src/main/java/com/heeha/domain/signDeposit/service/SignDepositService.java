package com.heeha.domain.signDeposit.service;

import com.heeha.domain.signDeposit.dto.DepositResponse;
import com.heeha.domain.signDeposit.entity.SignDeposit;
import com.heeha.domain.signDeposit.repository.SignDepositRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignDepositService {
    private final SignDepositRepository signDepositRepository;

    public DepositResponse getAccountInfo(Long accountId) {
        SignDeposit signDeposit = signDepositRepository.findByAccountId(accountId);

        return DepositResponse.todto(signDeposit);
    }
}