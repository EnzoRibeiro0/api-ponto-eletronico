package com.pontoeletronico.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exceptions.class)
    public ResponseEntity<String> handleApiException(Exceptions ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

}
