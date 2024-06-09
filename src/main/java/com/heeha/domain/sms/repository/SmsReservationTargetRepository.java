package com.heeha.domain.sms.repository;

import com.heeha.domain.sms.dto.SmsPhoneNumberDto;
import com.heeha.domain.sms.entity.SmsReservationTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmsReservationTargetRepository extends JpaRepository<SmsReservationTarget, Long> {
    @Query("""
            SELECT c.phoneNumber
            FROM sms_reservation_target srt
            JOIN customer c
            ON srt.customer.id = c.id
            WHERE srt.smsReservation.id = :smsReservationId
            """)
    List<String> findPhoneNumberBySmsReservationId(@Param(value = "smsReservationId")Long smsReservationId);

}
