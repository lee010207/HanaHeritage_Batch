package com.heeha.domain.signDeposit.entity;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum InstallmentMethodType {
    FIXED("정액적립식"),
    FREE("자유적립식");
    private static final Map<String, String> TITLE_MAP = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(InstallmentMethodType::getTitle, InstallmentMethodType::name))
    );

    public static InstallmentMethodType of(String title) {
        return InstallmentMethodType.valueOf(TITLE_MAP.get(title));
    }

    @Getter
    private String title;

    InstallmentMethodType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}