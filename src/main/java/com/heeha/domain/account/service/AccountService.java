package com.heeha.domain.account.service;

import com.heeha.domain.account.dto.AccountCheckResponse;
import com.heeha.domain.account.dto.AccountCreateDto;
import com.heeha.domain.account.dto.AccountValidationRequest;
import com.heeha.domain.account.repository.AccountRepository;
import com.heeha.domain.account.entity.Account;
import com.heeha.domain.customer.entity.Customer;
import com.heeha.domain.customer.repository.CustomerRepository;
import com.heeha.global.config.BaseException;
import com.heeha.global.config.BaseResponseStatus;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private static final Logger log = LoggerFactory.getLogger(AccountService.class);
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final int LOW_BOUND = 10_000_000;
    private final int MAX_BOUND = 90_000_000;

    @Transactional
    public Account createAccount(Long customerId, AccountCreateDto accountCreateDto) {
        Long accountNumber;
        do {
            accountNumber = generateAccountNumber(accountCreateDto.getBranchCode(), accountCreateDto.getAccountCode());
        } while (accountRepository.existsAccountByAccountNumber(accountNumber));

        Customer customer = customerRepository.findById(customerId).get();
        Account account = accountCreateDto.toEntity(accountNumber, customer);

        return accountRepository.save(account);
    }

    public List<AccountCheckResponse> myAccounts(Long customerId) {
        List<Account> accountFixes = accountRepository.findAccountFixByCustomerId(customerId);
        return accountFixes.stream().map(AccountCheckResponse::new).toList();
    }

    public Boolean validateAccount(AccountValidationRequest validationRequest) {
        Account account = accountRepository.findById(validationRequest.getAccountId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.SYSTEM_ERROR));

        if (!account.getPassword().equals(validationRequest.getAccountPassword())) {
            throw new BaseException(BaseResponseStatus.INVALID_ACCOUNT_PASSWORD);
        }
        return Boolean.TRUE;
    }

    public Account getAccount(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(
                () -> new BaseException(BaseResponseStatus.SYSTEM_ERROR)
        );
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
