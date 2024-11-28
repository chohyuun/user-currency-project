package com.sparta.currency_user.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ExchangeStatus {
    NORMAL("NORMAL"),
    CANCELLED("CANCELLED");

    private final String status;

    ExchangeStatus(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }

    @JsonCreator
    public static ExchangeStatus fromStatus(String status) {
        for (ExchangeStatus exchangeStatus : ExchangeStatus.values()) {
            if (exchangeStatus.status.equals(status)) {
                return exchangeStatus;
            }
        }
        throw new IllegalArgumentException("상태값을 확인해 주세요(normal, cancelled)");
    }
}
