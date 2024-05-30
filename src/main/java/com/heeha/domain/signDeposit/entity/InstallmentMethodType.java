package com.heeha.domain.signDeposit.entity;

import lombok.Getter;

public enum InstallmentMethodType {
    FIXED("정액적립식"),
    FREE("자유적립식");

    @Getter
    private String title;

    InstallmentMethodType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}