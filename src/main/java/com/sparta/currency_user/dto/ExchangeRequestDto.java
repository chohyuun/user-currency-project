package com.sparta.currency_user.dto;

import lombok.Getter;

@Getter
public class ExchangeRequestDto {
    private final long userId;
    private final long currencyId;
    private final int amountInKrw;

    public ExchangeRequestDto(long userId, long currencyId, int amountInKrw) {
        this.userId = userId;
        this.currencyId = currencyId;
        this.amountInKrw = amountInKrw;
    }
}
