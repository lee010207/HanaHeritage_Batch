package com.heeha.domain.sms.repository;

import com.heeha.domain.sms.entity.SmsReservation;
import com.heeha.domain.sms.entity.SmsReservationTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsReservationTargetRepository extends JpaRepository<SmsReservationTarget, Long> {
}
