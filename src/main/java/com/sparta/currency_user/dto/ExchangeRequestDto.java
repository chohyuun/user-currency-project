package com.sparta.currency_user.dto;

import lombok.Getter;

@Getter
public class ExchangeRequestDto {
    private final long userId;
    private final long currencyId;
    private final int amountInKrw;
    private final String status;

    public ExchangeRequestDto(long userId, long currencyId, int amountInKrw, String status) {
        this.userId = userId;
        this.currencyId = currencyId;
        this.amountInKrw = amountInKrw;
        this.status = status;
    }
}
