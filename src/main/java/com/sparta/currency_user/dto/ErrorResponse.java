package com.sparta.currency_user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private final String status;
    private final int statusCode;
    private final ErrorResponseData error;

    public ErrorResponse(String status, int statusCode, ErrorResponseData error) {
        this.status = status;
        this.statusCode = statusCode;
        this.error = error;
    }
}
