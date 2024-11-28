package com.sparta.currency_user.dto;

import lombok.Getter;

@Getter
public class ExchangeStatusRequestDto {
    private final String status;

    public ExchangeStatusRequestDto(String status) {
        this.status = status;
    }
}
