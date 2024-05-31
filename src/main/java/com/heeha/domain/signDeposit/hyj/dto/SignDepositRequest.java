package com.heeha.domain.signDeposit.hyj.dto;

import com.heeha.domain.account.entity.Account;
import com.heeha.domain.autoTransfer.entity.AutoTransfer;
import com.heeha.domain.consulting.entity.WorkType;
import com.heeha.domain.customer.entity.Customer;
import com.heeha.domain.depositsProduct.entity.DepositsProduct;
import com.heeha.domain.signDeposit.entity.AutoCancelType;
import com.heeha.domain.signDeposit.entity.InstallmentMethodType;
import com.heeha.domain.signDeposit.entity.MaturityClassType;
import com.heeha.domain.signDeposit.entity.SignDeposit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SignDepositRequest {
    private String installmentMethod;
    private String maturityClass;
    private String autoCancel;
    private Integer monthlyAmount;

    private Integer contractYears;
    private Boolean snsNotice;
    private Double interestRate;

    public SignDeposit toEntity(Long account_id, Long deposits_product_id, Long auto_transfer_id) {
        return SignDeposit.builder()
                .account(Account.builder().id(account_id).build())
                .depositsProduct(DepositsProduct.builder().id(deposits_product_id).build())
                .autoTransfer(AutoTransfer.builder().id(auto_transfer_id).build())
                .installmentMethod(InstallmentMethodType.of(installmentMethod))
                .maturityClass(MaturityClassType.of(maturityClass))
                .autoCancel(AutoCancelType.of(autoCancel))
                .monthlyAmount(monthlyAmount)
                .contractYears(contractYears)
                .snsNotice(snsNotice)
                .interestRate(interestRate)
                .build();

    }
}
