package com.heeha.domain.depositProduct.yma.repository;

import com.heeha.domain.depositProduct.entity.DepositProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositsProductRepository extends JpaRepository<DepositProduct, Long> {
}
