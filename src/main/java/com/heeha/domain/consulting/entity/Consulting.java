package com.heeha.domain.consulting.entity;

import com.heeha.domain.base.entity.BaseEntity;
import com.heeha.domain.consulting.dto.GetConsultingDto;
import com.heeha.domain.customer.entity.Customer;
import com.heeha.domain.history.dto.HistoryDto;
import com.heeha.domain.history.entity.History;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "consulting")
@Table(name = "consulting")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Consulting extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private WorkType workType;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(updatable = false)
    private LocalDate reservationDate;

    @Column(nullable = false)
    private Boolean isCompleted = Boolean.FALSE;
}
