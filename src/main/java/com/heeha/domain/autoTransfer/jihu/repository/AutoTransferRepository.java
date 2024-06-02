package com.heeha.domain.autoTransfer.jihu.repository;


import com.heeha.domain.autoTransfer.entity.AutoTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoTransferRepository extends JpaRepository<AutoTransfer, Long> {

}