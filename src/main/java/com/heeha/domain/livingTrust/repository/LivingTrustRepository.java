package com.heeha.domain.livingTrust.repository;

import com.heeha.domain.livingTrust.entity.LivingTrust;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivingTrustRepository extends JpaRepository<LivingTrust, Long> {
}
