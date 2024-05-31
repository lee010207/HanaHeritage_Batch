package com.heeha.domain.account.dto;

import com.heeha.domain.account.JCS.entity.AccountFix;
import com.heeha.domain.account.entity.Account;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AccountCheckResponse {
    private Long accountNumber;
    private String name;
    private Long balance;
    private LocalDateTime createdAt;

    public AccountCheckResponse(Account account) {
        this.accountNumber = account.getAccountNumber();
        this.name = account.getName();
        this.balance = account.getBalance();
        this.createdAt = account.getCreated_at();
    }
}
