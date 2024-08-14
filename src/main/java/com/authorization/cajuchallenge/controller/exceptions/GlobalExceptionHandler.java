package com.authorization.cajuchallenge.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ex.getBindingResult().getFieldErrors().forEach(error ->
                System.err.println("Validation error on field '" + error.getField() + "': " + error.getDefaultMessage())
        );

        Map<String, String> response = new HashMap<>();
        response.put("code", "07");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleAllExceptions(Exception ex) {
        System.err.println("Exception: " + ex.getMessage());

        Map<String, String> response = new HashMap<>();
        response.put("code", "07");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}