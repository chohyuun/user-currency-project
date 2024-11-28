package com.sparta.currency_user.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponseData {
    private final String code;
    private final String message;
    private final String details;
    private final LocalDateTime timestamp;

    public ErrorResponseData(String code, String message, String details, LocalDateTime timestamp) {
        this.code = code;
        this.message = message;
        this.details = details;
        this.timestamp = timestamp;
    }

}
