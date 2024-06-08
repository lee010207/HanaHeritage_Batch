package com.heeha.domain.consulting.repository;

import com.heeha.domain.consulting.dto.GetConsultingDto;
import com.heeha.domain.consulting.entity.Consulting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConsultingRepository extends JpaRepository<Consulting, Long> {

    @Query("SELECT new com.heeha.domain.consulting.dto.GetConsultingDto(id, customer.name, phoneNumber, workType, isCompleted) " +
            "FROM consulting " +
            "WHERE reservationDate = reservationDate")
    List<GetConsultingDto> findAllByReservationDate(LocalDate reservationDate);
}

