package com.heeha.domain.signDeposit.entity;

import com.heeha.domain.consulting.entity.WorkType;
import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum AutoCancelType {
    DEFAULT("만기일"),
    DEFERRED("이연만기일");

    private static final Map<String, String> TITLE_MAP = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(AutoCancelType::getTitle, AutoCancelType::name))
    );

    public static AutoCancelType of(String title) {
        return AutoCancelType.valueOf(TITLE_MAP.get(title));
    }

    @Getter
    private String title;

    AutoCancelType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }


}