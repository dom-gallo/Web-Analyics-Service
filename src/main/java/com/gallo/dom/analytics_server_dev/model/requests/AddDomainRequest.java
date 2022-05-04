package com.gallo.dom.analytics_server_dev.model.requests;

public class AddDomainRequest {
    private String domainBase;
    private String ownerEmailAddress;

    public AddDomainRequest() {
    }

    public AddDomainRequest(String domainBase) {
        this.domainBase = domainBase;
    }

    public String getDomainBase() {
        return domainBase;
    }

    public void setDomainBase(String domainBase) {
        this.domainBase = domainBase;
    }

    public String getOwnerEmailAddress() {
        return ownerEmailAddress;
    }

    public void setOwnerEmailAddress(String ownerEmailAddress) {
        this.ownerEmailAddress = ownerEmailAddress;
    }

    @Override
    public String toString() {
        return "AddDomainRequest{" +
                "domainBase='" + domainBase + '\'' +
                '}';
    }
}
