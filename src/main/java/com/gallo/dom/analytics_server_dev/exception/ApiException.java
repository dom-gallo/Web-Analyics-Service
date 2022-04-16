package com.gallo.dom.analytics_server_dev.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiException {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;
    private final LocalDateTime localDateTime;

    public ApiException(String message, Throwable throwable, HttpStatus httpStatus, LocalDateTime localDateTime) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
        this.localDateTime = localDateTime;
    }

    public String getMessage() {
        return message;
    }
    @JsonIgnore
    public Throwable getThrowable() {
        return throwable;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    @Override
    public String toString() {
        return "ApiException{" +
                "message='" + message + '\'' +
                ", throwable=" + throwable +
                ", httpStatus=" + httpStatus +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
