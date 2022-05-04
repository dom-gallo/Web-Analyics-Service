package com.gallo.dom.analytics_server_dev.model.requests;

public class AppUserRegisterRequest {
    String emailAddress;
    String domainBase;
    String password;

    public AppUserRegisterRequest(){}

    public AppUserRegisterRequest(String emailAddress, String domainBase, String password) {
        this.emailAddress = emailAddress;
        this.domainBase = domainBase;
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDomainBase() {
        return domainBase;
    }

    public void setDomainBase(String domainBase) {
        this.domainBase = domainBase;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AppUserRegisterRequest{" +
                "emailAddress='" + emailAddress + '\'' +
                ", domainBase='" + domainBase + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
