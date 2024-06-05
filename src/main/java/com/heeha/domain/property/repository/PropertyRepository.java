package com.heeha.domain.property.repository;

import com.heeha.domain.property.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findAllByLivingTrust_IdAndType(Long livingTrustId, String type);
    //금전(cash), 부동산(reality), 유가증권(security), 금전채권(bond)
}
