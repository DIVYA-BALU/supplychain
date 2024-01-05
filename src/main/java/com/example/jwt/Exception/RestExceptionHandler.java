package com.example.jwt.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = NoOrderFoundException.class)
    public ResponseEntity<?> handleNoOrderFoundException(){
        return new ResponseEntity<>("No orders found",HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(){
        return new ResponseEntity<>("No User found",HttpStatus.NOT_FOUND);

    }
}
