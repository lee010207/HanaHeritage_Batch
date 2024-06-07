package com.heeha.domain.sms.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class SmsPhoneNumberDto {
    private Long smsReservationTargetId;
    private String phoneNumber;
}
