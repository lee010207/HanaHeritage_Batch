package com.heeha.domain.property.dto;

import com.heeha.domain.livingTrust.entity.LivingTrust;
import com.heeha.domain.property.entity.Property;
import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class PropertyResponse {

    private String name;
    private String type;
    private Long amount;
    private String location;
    private Integer quantity;

    public PropertyResponse(Property property) {
        this.name = property.getName();
        this.type = property.getType();
        this.amount = property.getAmount();
        this.location = property.getLocation();
        this.quantity = property.getQuantity();
    }
}
