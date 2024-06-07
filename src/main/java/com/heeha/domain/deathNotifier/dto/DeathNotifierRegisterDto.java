package com.heeha.domain.deathNotifier.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeathNotifierRegisterDto {
    private String name;
    private String phoneNumber;
    private String address;
    private LocalDateTime birthdate;
    private String relation;
}
