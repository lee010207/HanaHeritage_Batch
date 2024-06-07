package com.heeha.domain.postBeneficiary.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostBeneficiaryRegisterDto {
    private String name;
    private String phoneNumber;
    private String address;
    private LocalDateTime birthdate;
    private String relation;
    private String ratio;
}
