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
public class ConsultingReservationRequest {
    private String workTypeName;
    private String phoneNumber;
    private String reservationDatetime;

    public Consulting toEntity(Long customerId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return Consulting.builder()
                .customer(Customer.builder().id(customerId).build())
                .workType(WorkType.of(workTypeName))
                .phoneNumber(phoneNumber)
                .reservationDatetime(LocalDate.parse(reservationDatetime, formatter))
                .build();
    }
}
