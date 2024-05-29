package com.heeha.domain.account.JCS.dto;

import com.heeha.domain.account.JCS.entity.AccountFix;
import com.heeha.domain.customer.entity.Customer;
import jakarta.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class AccountCreateDto {
    private final String branchCode = "111";
    @NotEmpty
    private String accountName;
    @NotEmpty
    private String accountPassword;
    @NotEmpty
    private Long balance;

    public abstract AccountFix toEntity(Long accountNumber, Customer customer);
    public abstract String getAccountCode();
}
