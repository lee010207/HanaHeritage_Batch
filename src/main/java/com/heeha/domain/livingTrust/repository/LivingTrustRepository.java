package com.heeha.domain.livingTrust.repository;

import com.heeha.domain.livingTrust.entity.LivingTrust;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LivingTrustRepository extends JpaRepository<LivingTrust, Long> {
    @Query("select l from living_trust l "
            + "join fetch l.properties "
            + "where l.customer.id = :customerId")
    Optional<LivingTrust> findLivingTrustByCustomerId(@Param("customerId") Long customerId);

}
