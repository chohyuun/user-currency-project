package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.ExchangeRequestDto;
import com.sparta.currency_user.dto.ExchangeResponseDataDto;
import com.sparta.currency_user.entity.UserCurrency;
import com.sparta.currency_user.enums.ExchangeStatus;
import com.sparta.currency_user.repository.CurrencyRepository;
import com.sparta.currency_user.repository.ExchangeRepository;
import com.sparta.currency_user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeService {
    private final ExchangeRepository exchangeRepository;
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;

    public ExchangeResponseDataDto createExchange(ExchangeRequestDto exchangeRequestDto) {
        UserCurrency userCurrency = new UserCurrency(exchangeRequestDto.getAmountInKrw(), ExchangeStatus.NORMAL);

        userCurrency.setUser(userRepository.findByIdOrElseThrow(exchangeRequestDto.getUserId()));
        userCurrency.setCurrency(currencyRepository.findByIdOrElseThrow(exchangeRequestDto.getCurrencyId()));

        BigDecimal krw = new BigDecimal(exchangeRequestDto.getAmountInKrw());
        BigDecimal exchange = krw.divide(userCurrency.getCurrency().getExchangeRate(), 2, RoundingMode.HALF_UP);

        userCurrency.setAmountAfterExchange(exchange);
        exchangeRepository.save(userCurrency);

        return new ExchangeResponseDataDto(userCurrency);
    }

    public List<ExchangeResponseDataDto> getUserExchange(Long userId) {
        List<UserCurrency> userCurrencyList = exchangeRepository.findAllByUserId(userId);

        return userCurrencyList.stream().map(ExchangeResponseDataDto::new).toList();
    }

    @Transactional
    public ExchangeResponseDataDto updateExchange(Long id) {
        UserCurrency userCurrency = exchangeRepository.findByIdOrElseThrow(id);
        userCurrency.update(ExchangeStatus.CANCELLED);

        return new ExchangeResponseDataDto(userCurrency);
    }
}
