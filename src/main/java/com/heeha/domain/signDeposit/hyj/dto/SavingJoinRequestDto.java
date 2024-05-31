package com.heeha.domain.signDeposit.hyj.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SavingJoinRequestDto {

    private Long savingProductId;

    private Long withdrawAccountId;
    private Long depositAmount;
    private String accountPassword;

    private String installmentMethod;
    private String maturityClass;
    private String autoCancel;

    private Integer contractYears;
    private Double interestRate;
    private Boolean snsNotice;
}
