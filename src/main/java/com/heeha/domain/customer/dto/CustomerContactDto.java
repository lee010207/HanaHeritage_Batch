package com.heeha.domain.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomerContactDto {
    private Long id;
    private String name;
    private String phoneNumber;
}
