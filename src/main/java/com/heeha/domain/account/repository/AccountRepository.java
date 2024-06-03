package com.heeha.domain.account.repository;

import com.heeha.domain.account.entity.Account;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountNumber(Long accountNumber);

    Boolean existsAccountByAccountNumber(Long accountNumber);
    @Query("SELECT a FROM account a join fetch a.customer where a.customer.id = :customerId")
    List<Account> findAccountFixByCustomerId(Long customerId);
}
