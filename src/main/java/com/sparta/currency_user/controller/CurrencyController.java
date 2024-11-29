package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.CurrencyRequestDto;
import com.sparta.currency_user.dto.StatusResponse;
import com.sparta.currency_user.service.CurrencyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currencies")
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService currencyService;

    private final StatusResponse statusResponse = new StatusResponse("success", 200, null);

    /**
     * 환율 조회
     */
    @GetMapping
    public ResponseEntity<StatusResponse> findCurrencies() {
        statusResponse.setStatusCode(200);
        statusResponse.setData(currencyService.findAll());

        return new ResponseEntity<>(statusResponse, HttpStatus.OK);
    }

    /**
     * 환율 단건 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<StatusResponse> findCurrency(@PathVariable Long id) {
        statusResponse.setStatusCode(200);
        statusResponse.setData(currencyService.findById(id));

        return new ResponseEntity<>(statusResponse, HttpStatus.OK);
    }

    /**
     * 환율 등록
     */
    @PostMapping
    public ResponseEntity<StatusResponse> createCurrency(@RequestBody @Valid CurrencyRequestDto currencyRequestDto) {
        statusResponse.setStatusCode(201);
        statusResponse.setData(currencyService.save(currencyRequestDto));

        int compareTo = currencyRequestDto.getExchangeRate().compareTo(BigDecimal.ZERO);

        if(compareTo <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "환율은 0이하가 될 수 없습니다.");
        }

        return new ResponseEntity<>(statusResponse, HttpStatus.CREATED);

    }
}
