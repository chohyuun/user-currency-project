package com.sparta.currency_user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class StatusResponse {
    private final String status;

    @Setter
    private int statusCode;

    @Setter
    private Object data;

    public StatusResponse(String status, int statusCode, Object data) {
        this.status = status;
        this.statusCode = statusCode;
        this.data = data;
    }
}
