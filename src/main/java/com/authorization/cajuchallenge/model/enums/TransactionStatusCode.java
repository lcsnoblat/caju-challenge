package com.authorization.cajuchallenge.model.enums;

import lombok.Getter;

@Getter
public enum TransactionStatusCode {
    SUCCESS("00"),
    ERROR("07"),
    INSUFFICIENT_FUNDS("51");

    private final String code;

    TransactionStatusCode(String code) {
        this.code = code;
    }

}