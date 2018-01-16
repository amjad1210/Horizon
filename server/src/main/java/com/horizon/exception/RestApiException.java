package com.horizon.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Amjad
 */
@JsonIgnoreProperties({"cause", "stackTrace", "localizedMessage","message" ,"suppressed"})
@Getter @AllArgsConstructor
public class RestApiException extends Exception {

    private HttpStatus httpStatus;
    private String errorMessage;

    public RestApiException(String errorMessage) {
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errorMessage = errorMessage;
    }

}
