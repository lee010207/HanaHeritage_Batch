package com.heeha.domain.account.JCS.dto;

import com.heeha.domain.account.JCS.entity.AccountFix;
import com.heeha.domain.customer.entity.Customer;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class DepositAccountCreateDto extends AccountCreateDto {

    private final String DepositAccountCode = "02";

    public DepositAccountCreateDto(@NotEmpty String accountName,
                                   @NotEmpty String accountPassword,
                                   @NotEmpty Long balance) {
        super(accountName, accountPassword, balance);
    }

    @Override
    public AccountFix toEntity(Long accountNumber, Customer customer) {
        return AccountFix.builder().accountNumber(accountNumber)
                .name(super.getAccountName())
                .balance(super.getBalance())
                .password(super.getAccountPassword())
                .customer(customer)
                .build();
    }

    @Override
    public String getAccountCode() {
        return DepositAccountCode;
    }
}
