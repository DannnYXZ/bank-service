package com.dannnyxz.bank.exception;

import com.dannnyxz.bank.dto.ApiError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ApiError> handleApiException(ApiException e) {
        return ResponseEntity.status(e.getCode()).body(ApiError.builder().message(e.getMessage()).build());
    }
}
