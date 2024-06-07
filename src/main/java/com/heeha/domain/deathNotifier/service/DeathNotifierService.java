package com.heeha.domain.deathNotifier.service;

import com.heeha.domain.deathNotifier.dto.DeathNotifierRegisterDto;
import com.heeha.domain.deathNotifier.entity.DeathNotifier;
import com.heeha.domain.deathNotifier.repository.DeathNotifierRepository;
import com.heeha.domain.livingTrust.entity.LivingTrust;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeathNotifierService {
    private final DeathNotifierRepository deathNotifierRepository;

    public Long save(DeathNotifierRegisterDto registerDto, LivingTrust livingTrust) {
        DeathNotifier deathNotifier = DeathNotifier.builder()
                .name(registerDto.getName())
                .phoneNumber(registerDto.getPhoneNumber())
                .address(registerDto.getAddress())
                .birthdate(registerDto.getBirthdate())
                .relation(registerDto.getRelation())
                .livingTrust(livingTrust).build();
        return deathNotifierRepository.save(deathNotifier).getId();
    }
}
