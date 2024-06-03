package com.heeha.domain.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AccountValidationRequest {
    private Long accountId;
    private String accountPassword;

}
