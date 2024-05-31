package com.heeha.domain.signDeposit.hyj.repository;

import com.heeha.domain.account.entity.Account;
import com.heeha.domain.autoTransfer.entity.AutoTransfer;
import com.heeha.domain.depositsProduct.entity.DepositsProduct;
import com.heeha.domain.signDeposit.entity.SignDeposit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignDepositRepository extends JpaRepository<SignDeposit, Long> {
}
