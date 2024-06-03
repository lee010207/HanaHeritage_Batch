package com.heeha.domain.history.repository;

import com.heeha.domain.account.entity.Account;
import com.heeha.domain.history.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findByAccount_Id(Long accountNumber);
}
