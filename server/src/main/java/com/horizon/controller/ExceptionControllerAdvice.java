package com.horizon.controller;

import com.horizon.exception.RestApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Amjad
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<Object> handleApiErrorException(RestApiException exception) {
        return ResponseEntity.status(exception.getHttpStatus()).body(exception);
    }

}
