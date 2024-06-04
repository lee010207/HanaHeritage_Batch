package com.heeha.domain.auth.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CertificationDto {
    @NotEmpty
    private String phoneNumber;
    @NotEmpty
    private String certificationCode;
}
