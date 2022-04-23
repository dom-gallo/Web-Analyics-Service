package com.gallo.dom.analytics_server_dev.model;

public class AppUserSecurityContext {
    String username;

    public AppUserSecurityContext(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "AppUserSecurityContext{" +
                "username='" + username + '\'' +
                '}';
    }
}
