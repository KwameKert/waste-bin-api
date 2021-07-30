package com.springbootbroilerstarter.demo.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class UnexpectedFailure extends RuntimeException {

    public UnexpectedFailure(String message) {
        super(message);
    }
}