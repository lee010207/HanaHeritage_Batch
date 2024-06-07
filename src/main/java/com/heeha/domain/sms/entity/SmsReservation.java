package com.heeha.domain.sms.entity;

import com.heeha.domain.base.entity.BaseEntity;
import com.heeha.domain.customer.entity.Customer;
import com.heeha.domain.property.entity.Property;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "sms_reservation")
@Table(name = "sms_reservation")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class SmsReservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String content;
    private LocalDate sendingDate;
    private Boolean isSent;
}
