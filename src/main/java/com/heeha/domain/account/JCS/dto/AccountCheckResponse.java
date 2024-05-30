package com.heeha.domain.account.JCS.dto;

import com.heeha.domain.account.JCS.entity.AccountFix;
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

    public AccountCheckResponse(AccountFix account) {
        this.accountNumber = account.getAccountNumber();
        this.name = account.getName();
        this.balance = account.getBalance();
        this.createdAt = account.getCreated_at();
    }
}
