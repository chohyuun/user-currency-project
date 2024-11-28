package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.ExchangeRequestDto;
import com.sparta.currency_user.dto.ExchangeResponseDataDto;
import com.sparta.currency_user.dto.ExchangeStatusRequestDto;
import com.sparta.currency_user.entity.UserCurrency;
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
        UserCurrency userCurrency = new UserCurrency(exchangeRequestDto.getAmountInKrw(), exchangeRequestDto.getStatus());

        userCurrency.setUser(userRepository.findById(exchangeRequestDto.getUserId()));
        userCurrency.setCurrency(currencyRepository.findById(exchangeRequestDto.getCurrencyId()));

        BigDecimal krw = new BigDecimal(exchangeRequestDto.getAmountInKrw());
        BigDecimal exchange = krw.divide(userCurrency.getCurrency().getExchangeRate(), 2, RoundingMode.HALF_UP);

        userCurrency.setAmountAfterExchange(exchange);
        exchangeRepository.save(userCurrency);

        return new ExchangeResponseDataDto(userCurrency);
    }

    public List<ExchangeResponseDataDto> getUserExchange(Long userId) {
        List<UserCurrency> userCurrencyList = exchangeRepository.findAllByUserId(userId);

        return userCurrencyList.stream().map(userCurrency ->
                new ExchangeResponseDataDto(
                        userCurrency.getId(),
                        userCurrency.getUser().getId(),
                        userCurrency.getCurrency().getId(),
                        userCurrency.getAmountInKwr(),
                        userCurrency.getAmountAfterExchange(),
                        userCurrency.getStatus(),
                        userCurrency.getCreatedAt(),
                        userCurrency.getModifiedAt()
                )
        ).toList();
    }

    @Transactional
    public ExchangeResponseDataDto updateExchange(Long id, ExchangeStatusRequestDto exchangeStatusRequestDto) {
        UserCurrency userCurrency = exchangeRepository.findByIdOrThrow(id);

        userCurrency.update(exchangeStatusRequestDto.getStatus());

        return new ExchangeResponseDataDto(userCurrency);
    }
}
