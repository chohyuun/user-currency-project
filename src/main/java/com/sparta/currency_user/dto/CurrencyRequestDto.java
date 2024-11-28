package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.Currency;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CurrencyRequestDto {
    @NotNull(message="환율 이름을 입력해 주세요.")
    private String currencyName;

    @NotNull(message="환율을 입력해 주세요.")
    @DecimalMin(value = "0.01", message = "0.01보다 작을 수는 없습니다.")
    private BigDecimal exchangeRate;

    @NotNull(message="심볼을 입력해 주세요.")
    private String symbol;

    public Currency toEntity() {
        return new Currency(
                this.currencyName,
                this.exchangeRate,
                this.symbol
        );
    }
}
