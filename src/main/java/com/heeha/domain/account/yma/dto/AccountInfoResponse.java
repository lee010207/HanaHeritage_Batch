package com.heeha.domain.account.yma.dto;

import com.heeha.domain.signDeposit.entity.InstallmentMethodType;
import com.heeha.domain.signDeposit.entity.SignDeposit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class AccountInfoResponse {

    private Long accountNumber; // 계좌번호

    private String finPrditNm; //상품명

    private String type; //과목명

    private long balance; //잔액

    private LocalDateTime creatdAt; //계약일시

    private Integer contractYears; //계약햇수

    private InstallmentMethodType installmentMethod; // 적립 방법

    private Double interestRate; // 금리

    public static AccountInfoResponse todto(SignDeposit signDeposit) {
        return AccountInfoResponse.builder()
                .accountNumber(signDeposit.getAccount().getAccount_number())
                .finPrditNm(signDeposit.getDepositsProduct().getFinPrdtNm())
                .type(String.valueOf(signDeposit.getDepositsProduct().getType()))
                .balance(signDeposit.getAccount().getBalance())
                .creatdAt(signDeposit.getCreated_at())
                .contractYears(signDeposit.getContractYears())
                .installmentMethod(signDeposit.getInstallmentMethod())
                .interestRate(signDeposit.getInterestRate())
                .build();
    }
}
