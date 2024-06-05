package com.heeha.domain.account.dto;

import com.heeha.domain.account.entity.Account;
import com.heeha.domain.customer.entity.Customer;
import jakarta.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public abstract class AccountCreateDto {
    private final String branchCode = "111";
    @NotEmpty
    private String accountName;
    @NotEmpty
    private String accountPassword;
    @NotEmpty
    private Long balance;

    public abstract Account toEntity(Long accountNumber, Customer customer);
    public abstract String getAccountCode();
}
