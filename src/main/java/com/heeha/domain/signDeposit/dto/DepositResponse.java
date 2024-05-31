package com.heeha.domain.signDeposit.dto;

import com.heeha.domain.account.entity.Account;
import com.heeha.domain.depositsProduct.entity.DepositsProduct;
import com.heeha.domain.signDeposit.entity.InstallmentMethodType;
import com.heeha.domain.signDeposit.entity.SignDeposit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class DepositResponse {

    private Long accountNumber; // 계좌번호

    private String finPrditNm; //상품명

    private String type; //과목명

    private long balance; //잔액

    private LocalDateTime creatdAt; //계약일시

    private Integer contractYears; //계약햇수

    private InstallmentMethodType installmentMethod; // 적립 방법

    private Double interestRate; // 금리


}
