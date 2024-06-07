package com.heeha.domain.property.service;

import com.heeha.domain.livingTrust.entity.LivingTrust;
import com.heeha.domain.property.dto.PropertyRegisterDto;
import com.heeha.domain.property.entity.Property;
import com.heeha.domain.property.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PropertyService {
    private final PropertyRepository propertyRepository;

    public Long save(PropertyRegisterDto propertyDto, LivingTrust livingTrust) {
        Property property = Property.builder()
                .livingTrust(livingTrust)
                .type(propertyDto.getPropertyType())
                .amount(propertyDto.getAmount())
                .location(propertyDto.getLocation())
                .quantity(propertyDto.getQuantity())
                .build();

        return propertyRepository.save(property).getId();
    }
}
