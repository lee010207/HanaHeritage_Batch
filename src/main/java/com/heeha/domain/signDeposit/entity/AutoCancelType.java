package com.heeha.domain.signDeposit.entity;

import lombok.Getter;

public enum AutoCancelType {
    DEFAULT("만기일"),
    DEFERRED("이연만기일");

    @Getter
    private String title;

    AutoCancelType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}