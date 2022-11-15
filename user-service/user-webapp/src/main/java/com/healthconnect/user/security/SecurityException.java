package com.healthconnect.user.security;

import org.springframework.http.HttpStatus;

public class SecurityException extends RuntimeException {

    private int status;
    private String message;

    public SecurityException(HttpStatus status, String message) {
        super(message);
        this.status = status.value();
        this.message = message;
    }

    public SecurityException(int statusCode, String message) {
        super(message);
        this.status = statusCode;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}