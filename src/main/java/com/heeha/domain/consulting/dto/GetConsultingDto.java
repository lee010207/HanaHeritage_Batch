package com.heeha.domain.consulting.dto;

import com.heeha.domain.consulting.entity.WorkType;
import lombok.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class GetConsultingDto {
    private Long id;
    private String customerName;
    private String phoneNumber;
    private String workType;
    private Boolean isCompleted;

    GetConsultingDto(Long id, String customerName, String phoneNumber, WorkType workType, Boolean isCompleted) {
        this.id = id;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.workType = workType.getTitle();
        this.isCompleted = isCompleted;
    }
}
