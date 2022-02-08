package com.example.demo.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerExeptions {

    @ExceptionHandler
    public ResponseEntity<AllExeption> exeption(ExeptionMessage message) {
        AllExeption allExeption = new AllExeption();
        allExeption.setMessage(message.getMessage());
        return new ResponseEntity<>(allExeption, HttpStatus.BAD_REQUEST);
    }
}
