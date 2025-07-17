package com.pontoeletronico.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Exceptions extends RuntimeException{
    private HttpStatus status;
    private String message;
}
