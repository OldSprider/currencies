package com.scand.currencies.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
class CustomizedResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<ApiError> constraintViolationException(ConstraintViolationException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "validation error", errors);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
