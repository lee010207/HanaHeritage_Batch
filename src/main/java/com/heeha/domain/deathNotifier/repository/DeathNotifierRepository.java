package com.heeha.domain.deathNotifier.repository;

import com.heeha.domain.deathNotifier.entity.DeathNotifier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeathNotifierRepository extends JpaRepository<DeathNotifier, Long> {
}
