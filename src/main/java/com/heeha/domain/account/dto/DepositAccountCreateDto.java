package com.heeha.domain.account.dto;

import com.heeha.domain.account.entity.Account;
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
    public Account toEntity(Long accountNumber, Customer customer) {
        return Account.builder().accountNumber(accountNumber)
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
