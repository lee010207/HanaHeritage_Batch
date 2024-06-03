package com.heeha.domain.autoTransfer.repository;

import com.heeha.domain.autoTransfer.entity.AutoTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface AutoTransferRepository extends JpaRepository<AutoTransfer, Long> {
    List<AutoTransfer> findByAutoTransferDay(int day);
}
