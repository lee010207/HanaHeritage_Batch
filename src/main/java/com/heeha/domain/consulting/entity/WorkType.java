package com.heeha.domain.consulting.entity;

import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum WorkType {
    DEPOSITS("예·적금"),
    LOAN("대출"),
    FOREX("외환"),
    FUND("펀드·보험·연금·일임형ISA"),
    MOBILE("폰·모바일·인터넷뱅킹"),
    CORPORATE_BANKING("기업뱅킹"),
    RETIREMENT_PENSION("퇴직연금");

    private static final Map<String, String> TITLE_MAP = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(WorkType::getTitle, WorkType::name))
    );

    @Getter
    private String title;

    WorkType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static WorkType of(String title) {
        return WorkType.valueOf(TITLE_MAP.get(title));
    }
}