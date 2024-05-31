package com.heeha.domain.account.service;

import com.heeha.domain.account.dto.AccountCheckResponse;
import com.heeha.domain.account.dto.AccountCreateDto;
import com.heeha.domain.account.entity.Account;
import com.heeha.domain.account.repository.AccountRepository;
import com.heeha.domain.customer.entity.Customer;
import com.heeha.domain.customer.repository.CustomerRepository;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private static final Logger log = LoggerFactory.getLogger(AccountService.class);
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final int LOW_BOUND = 10_000_000;
    private final int MAX_BOUND = 90_000_000;

    public Long createAccount(Long customerId, AccountCreateDto accountCreateDto) {
        Long accountNumber;
        do {
            accountNumber = generateAccountNumber(accountCreateDto.getBranchCode(), accountCreateDto.getAccountCode());
        } while (accountRepository.existsAccountByAccountNumber(accountNumber));

        Customer customer = customerRepository.findById(customerId).get();
        Account account = accountCreateDto.toEntity(accountNumber, customer);

        return accountRepository.save(account).getId();
    }

    public List<AccountCheckResponse> myAccounts(Long customerId) {
        List<Account> accounts = accountRepository.findAccountByCustomerId(customerId);
        return accounts.stream().map(AccountCheckResponse::new).toList();
    }

    private Long generateAccountNumber(String branchCode, String accountCode) {
        StringBuilder sb = new StringBuilder(branchCode).append(accountCode).append(makeRandomNumber());
        return Long.parseLong(sb.toString());
    }

    private String makeRandomNumber() {
        Random random = new Random();
        return String.valueOf(LOW_BOUND + random.nextInt(MAX_BOUND));
    }
}
