package com.heeha.domain.signDeposit.repository;

import com.heeha.domain.signDeposit.entity.SignDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SignDepositRepository extends JpaRepository<SignDeposit, Long> {
    @Query("select s from sign_deposit s join fetch s.account join fetch s.depositsProduct where s.account.id = :accountId ")
    SignDeposit findByAccountId(@Param(value = "accountId") Long accountId);
}
