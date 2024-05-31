package com.heeha.domain.depositsProduct.repository;

import com.heeha.domain.depositsProduct.entity.DepositsProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositsProductRepository extends JpaRepository<DepositsProduct, Long> {
}
