package com.heeha.domain.account.jihu.dto;


import com.heeha.domain.customer.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class AccountResponse {
    private Long accountNumber;
    private Long balance;
    private Customer customer;
    private String password;

}