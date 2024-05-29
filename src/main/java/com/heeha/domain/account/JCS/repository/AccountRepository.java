package com.heeha.domain.account.JCS.repository;

import com.heeha.domain.account.JCS.entity.AccountFix;
import com.heeha.domain.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountFix, Long> {

    Boolean existsAccountByAccountNumber(Long accountNumber);
}
