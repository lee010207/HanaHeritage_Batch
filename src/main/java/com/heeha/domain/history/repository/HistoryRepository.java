package com.heeha.domain.history.repository;

import com.heeha.domain.history.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    @Query("SELECT h FROM history h WHERE h.account.id IS NOT NULL AND h.account.id = :accountId")
    List<History> findByAccountId(Long accountId);
}
