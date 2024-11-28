package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.ExchangeRequestDto;
import com.sparta.currency_user.dto.ExchangeResponseDataDto;
import com.sparta.currency_user.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/exchanges")
@RequiredArgsConstructor
public class ExchangeController {
    private final ExchangeService exchangeService;

    /**
     * 환전 정보 저장
     */
    @PostMapping
    public ResponseEntity<ExchangeResponseDataDto> createExchange(
            @RequestBody ExchangeRequestDto exchangeRequestDto
    ) {
        ExchangeResponseDataDto exchangeResponseDataDto = exchangeService.createExchange(exchangeRequestDto);

        return new ResponseEntity<>(exchangeResponseDataDto, HttpStatus.CREATED);
    }

    /**
     * 선택 유저 환전 조회
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<ExchangeResponseDataDto>> getExchange(
            @PathVariable Long userId
    ) {
        if(userId == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "검색할 유저를 입력해 주세요.");
        }

        List<ExchangeResponseDataDto> exchangeResponseDataDtoList = exchangeService.getUserExchange(userId);

        return new ResponseEntity<>(exchangeResponseDataDtoList, HttpStatus.OK);
    }

    /**
     * 환전 정보 업데이트
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ExchangeResponseDataDto> update(
            @PathVariable Long id
    ) {
        if(id == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "취소 처리할 환전 정보를 입력해 주세요.");
        }
        ExchangeResponseDataDto exchangeResponseDataDto = exchangeService.updateExchange(id);

        return new ResponseEntity<>(exchangeResponseDataDto, HttpStatus.OK);
    }

}
