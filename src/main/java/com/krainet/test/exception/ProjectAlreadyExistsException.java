package com.krainet.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProjectAlreadyExistsException extends RuntimeException {
    private String message;

    public ProjectAlreadyExistsException(String message) {
        super(message);
    }
}
