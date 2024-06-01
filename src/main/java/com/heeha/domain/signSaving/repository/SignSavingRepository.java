package com.heeha.domain.signSaving.repository;

import com.heeha.domain.signSaving.entity.SignSaving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignSavingRepository extends JpaRepository<SignSaving, Long> {
}
