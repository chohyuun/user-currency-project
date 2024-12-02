package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.repository.CurrencyRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ErrorLog {
    private final CurrencyRepository currencyRepository;

    @PostConstruct
    public void init() {
        List<Currency> currency = currencyRepository.findAll();

        for(Currency currencyData : currency) {
            if(currencyData.getExchangeRate().compareTo(BigDecimal.ZERO) <= 0){
                log.info("환율이 0이하인 데이터가 있습니다. 해당 데이터 번호 : " + currencyData.getId().toString());
            }
        }


    }
}
