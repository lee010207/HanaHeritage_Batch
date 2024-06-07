package com.heeha.domain.sms.dto;

import com.heeha.domain.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class ReserveSmsDto {
    private String content;
    private LocalDate sendingDate;
    private List<Long> customerIdList;
}
