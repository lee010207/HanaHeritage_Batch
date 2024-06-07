package com.heeha.domain.sms.entity;

import com.heeha.domain.base.entity.BaseEntity;
import com.heeha.domain.customer.entity.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "sms_reservation")
@Table(name = "sms_reservation")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class SmsReservationTarget extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sms_reservation_id")
    private SmsReservation smsReservation;
}
