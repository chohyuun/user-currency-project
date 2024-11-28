package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.UserCurrency;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class ExchangeResponseDataDto {
    private final long id;
    private final long userId;
    private final long currencyId;
    private final int amountInKrw;
    private final BigDecimal amountAfterExchange;
    private final String status;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public ExchangeResponseDataDto(UserCurrency userCurrency) {
        this.id = userCurrency.getId();
        this.userId = userCurrency.getUser().getId();
        this.currencyId = userCurrency.getCurrency().getId();
        this.amountInKrw = userCurrency.getAmountInKwr();
        this.amountAfterExchange = userCurrency.getAmountAfterExchange();
        this.status = userCurrency.getStatus();
        this.createdAt = userCurrency.getCreatedAt();
        this.modifiedAt = userCurrency.getModifiedAt();
    }

    public ExchangeResponseDataDto(long id, Long userId, Long currencyId, int amountInKwr, BigDecimal amountAfterExchange, String status, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.userId = userId;
        this.currencyId = currencyId;
        this.amountInKrw = amountInKwr;
        this.amountAfterExchange = amountAfterExchange;
        this.status = status;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
