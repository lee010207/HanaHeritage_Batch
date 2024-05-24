package com.heeha.domain.postBeneficiary.repository;

import com.heeha.domain.postBeneficiary.entity.PostBeneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostBeneficiaryRepository extends JpaRepository<PostBeneficiary, Long> {
}
