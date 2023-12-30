package com.example.jwt.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = NoOrderFoundException.class)
    public ResponseEntity handleNoOrderFoundException(){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
    
}
