package com.maxlikarenko.mailscheduler.models;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {

    private final HttpStatus code;

    public AppException(String message) {
        super(message);
        this.code = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public AppException(HttpStatus code, String message) {
        super(message);
        this.code = code;
    }

    public HttpStatus getCode() {
        return code;
    }
}
