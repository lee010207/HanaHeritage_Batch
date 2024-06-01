package com.heeha.domain.account.yma.repository;

import com.heeha.domain.signDeposit.entity.SignDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountInfoRepository extends JpaRepository<SignDeposit, Long> {
    @Query("select s from sign_deposit s join fetch s.account join fetch s.depositsProduct where s.account.id = :accountId ")
    SignDeposit findByAccountId(@Param(value = "accountId") Long accountId);
}

