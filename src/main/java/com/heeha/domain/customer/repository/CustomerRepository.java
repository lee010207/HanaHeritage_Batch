package com.heeha.domain.customer.repository;

import com.heeha.domain.consulting.dto.GetConsultingDto;
import com.heeha.domain.customer.dto.CustomerContactDto;
import com.heeha.domain.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByPhoneNumber(String phoneNumber);

    @Query("SELECT new com.heeha.domain.customer.dto.CustomerContactDto(id, name, phoneNumber) " +
            "FROM customer " +
            "WHERE role = com.heeha.domain.customer.entity.RoleType.USER")
    List<CustomerContactDto> findCustomerContact();
}
