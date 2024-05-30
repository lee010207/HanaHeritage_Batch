package com.heeha.domain.depositsProduct.entity;

import lombok.Getter;

@Getter
public enum DepositsType {
    DEPOSIT("적금"),
    SAVING("예금");

    private final String type;

    DepositsType(String title) {
        this.type = title;
    }

}