package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.ErrorResponse;
import com.sparta.currency_user.dto.ErrorResponseData;
 import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentExceptionNotFound(
            IllegalArgumentException exception
    ) {
        ErrorResponseData errorResponseData = new ErrorResponseData(
                "NOT_FOUND",
                exception.getMessage(),
                exception.getLocalizedMessage(),
                LocalDateTime.now()
        );

        ErrorResponse errorResponse = new ErrorResponse("error", 404, errorResponseData);

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerMethodArgumentNotValidExceptionBadRequest(
            MethodArgumentNotValidException exception
    ) {
        ErrorResponseData errorResponseData = new ErrorResponseData(
                "BAD_REQUEST",
                Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage(),
                Objects.requireNonNull(exception.getBindingResult().getFieldError()).getField(),
                LocalDateTime.now()
        );

        ErrorResponse errorResponse = new ErrorResponse("error", 400, errorResponseData);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            Exception exception
    ) {

        ErrorResponseData errorResponseData = new ErrorResponseData(
                "NOT_FOUND",
                exception.getMessage(),
                "잘못된 입력값입니다.",
                LocalDateTime.now()
        );


        ErrorResponse errorResponse = new ErrorResponse("error", 404, errorResponseData);

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
