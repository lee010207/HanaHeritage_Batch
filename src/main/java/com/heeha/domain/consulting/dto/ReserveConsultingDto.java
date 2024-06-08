package com.heeha.domain.consulting.dto;

import com.heeha.domain.consulting.entity.Consulting;
import com.heeha.domain.consulting.entity.WorkType;
import com.heeha.domain.customer.entity.Customer;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ReserveConsultingDto {
    private String workTypeName;
    private String phoneNumber;
    private String reservationDate;

    public Consulting toEntity(Long customerId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return Consulting.builder()
                .customer(Customer.builder().id(customerId).build())
                .workType(WorkType.of(workTypeName))
                .phoneNumber(phoneNumber)
                .reservationDate(LocalDate.parse(reservationDate, formatter))
                .isCompleted(false)
                .build();
    }
}
