package com.heeha.domain.consulting.dto;

import com.heeha.domain.consulting.entity.Consulting;
import com.heeha.domain.consulting.entity.WorkType;
import com.heeha.domain.customer.entity.Customer;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ConsultingReservationRequest {
    private String workTypeName;
    private String phoneNumber;
    private LocalDateTime reservationDatetime;

    public Consulting toEntity(Long customerId) {
        return Consulting.builder()
                .customer(Customer.builder().id(customerId).build())
                .workType(WorkType.of(workTypeName))
                .phoneNumber(phoneNumber)
                .reservationDatetime(reservationDatetime)
                .build();
    }
}
