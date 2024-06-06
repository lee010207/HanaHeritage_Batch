package com.heeha.domain.statistics.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "statistics_settlement")
@Table(name = "statistics_settlement")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@ToString
@Getter
@Setter
public class StatisticsSettlement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private LocalDate date;

    private Long depositAmount;
    private Long withdrawalAmount;
    private Long depositCount;
    private Long withdrawalCount;
}
