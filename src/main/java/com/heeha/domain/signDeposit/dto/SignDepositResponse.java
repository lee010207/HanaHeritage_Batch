package com.heeha.domain.signDeposit.dto;

import com.heeha.domain.signDeposit.entity.SignDeposit;
import com.heeha.domain.signSaving.entity.SignSaving;
import lombok.Getter;

@Getter
public class SignDepositResponse {
    private Long id;
    private Long accountNumber;
    private String name;
    private long balance;
    private Integer contractYears; // 계약 햇수
    private Boolean snsNotice; // SNS 만기 알림
    private Double interestRate; // 금리

    public SignDepositResponse(SignDeposit deposit) {
        this.id = deposit.getId();
        this.accountNumber = deposit.getAccount().getAccountNumber();
        this.name = deposit.getAccount().getName();
        this.balance = deposit.getAccount().getBalance();
        this.contractYears = deposit.getContractYears();
        this.snsNotice = deposit.getSnsNotice();
        this.interestRate = deposit.getInterestRate();
    }
}
