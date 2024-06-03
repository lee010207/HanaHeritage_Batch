package com.heeha.domain.signDeposit.entity;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum MaturityClassType {
    SELF("직접 해지"),
    AUTO("만기 시 자동해지");

    private static final Map<String, String> TITLE_MAP = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(MaturityClassType::getTitle, MaturityClassType::name))
    );

    public static MaturityClassType of(String title) {
        return MaturityClassType.valueOf(TITLE_MAP.get(title));
    }


    @Getter
    private String title;

    MaturityClassType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}