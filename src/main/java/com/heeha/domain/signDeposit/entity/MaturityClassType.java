package com.heeha.domain.signDeposit.entity;

import lombok.Getter;

public enum MaturityClassType {
    SELF("직접 해지"),
    AUTO("만기 시 자동해지");

    @Getter
    private String title;

    MaturityClassType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}