package com.heeha.domain.property.repository;

import com.heeha.domain.property.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
