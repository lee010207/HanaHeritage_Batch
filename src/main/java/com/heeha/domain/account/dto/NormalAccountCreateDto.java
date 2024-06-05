package com.heeha.domain.account.dto;

import com.heeha.domain.account.entity.Account;
import com.heeha.domain.customer.entity.Customer;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class NormalAccountCreateDto extends AccountCreateDto {

    private final String normalAccountCode = "03";

    public NormalAccountCreateDto(@NotEmpty String accountName,
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
        return normalAccountCode;
    }
}
