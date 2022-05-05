package com.gallo.dom.analytics_server_dev.model.requests;

public class AppUserRegisterRequest {
    String emailAddress;
    String domainBase;
    String password;
    String passwordConfirm;

    public AppUserRegisterRequest(){}

    public AppUserRegisterRequest(String emailAddress, String domainBase, String password, String passwordConfirm) {
        this.emailAddress = emailAddress;
        this.domainBase = domainBase;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
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

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @Override
    public String toString() {
        return "AppUserRegisterRequest{" +
                "emailAddress='" + emailAddress + '\'' +
                ", domainBase='" + domainBase + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                '}';
    }
}
