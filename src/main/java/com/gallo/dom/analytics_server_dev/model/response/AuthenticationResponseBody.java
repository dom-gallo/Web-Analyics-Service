package com.gallo.dom.analytics_server_dev.model.response;

import java.time.LocalDateTime;

public class AuthenticationResponseBody {
    private String token;
    private String message;
    private LocalDateTime generatedAt;

    public AuthenticationResponseBody(String token, String message) {
        this.token = token;
        this.message = message;
        this.generatedAt = LocalDateTime.now();
    }

    public AuthenticationResponseBody() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AuthenticationResponseBody{" +
                "token='" + token + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
