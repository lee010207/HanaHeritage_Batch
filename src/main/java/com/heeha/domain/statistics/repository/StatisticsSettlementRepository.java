package com.heeha.domain.statistics.repository;

import com.heeha.domain.statistics.entity.StatisticsSettlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StatisticsSettlementRepository extends JpaRepository<StatisticsSettlement, Long> {
    List<StatisticsSettlement> findByDate(LocalDate date);
}
