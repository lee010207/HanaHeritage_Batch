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

        return mapToDepositResponse(signDeposit);
    }

    private DepositResponse mapToDepositResponse(SignDeposit signDeposit) {
        return new DepositResponse(
                signDeposit.getAccount().getAccount_number(),
                signDeposit.getDepositsProduct().getFinPrdtNm(),
                signDeposit.getDepositsProduct().getType().getTitle(),
                signDeposit.getAccount().getBalance(),
                signDeposit.getCreated_at(),
                signDeposit.getContractYears(),
                signDeposit.getInstallmentMethod(),
                signDeposit.getInterestRate()
        );
    }
}