package com.heeha.domain.property.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PropertyRegisterDto {
    private String propertyType;
    private Long amount;
    private String location;
    private Integer quantity;
}
