package com.heeha.domain.livingTrust.dto;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class GetLivingTrustSummaryDto {
    private Long id;
    private String customerName;
    private LocalDateTime created_at;
    private Boolean isApproved;
}
