package com.heeha.domain.signSaving.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SavingJoinRequestDto {

    private Long savingProductId;

    private Long withdrawAccountId;
    private Long depositAmount;
    private String accountPassword;

    private Integer contractYears;
    private Double interestRate;
    private Boolean snsNotice;
}
