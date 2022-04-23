package com.gallo.dom.analytics_server_dev.model;

import java.time.LocalDateTime;

public class PageViewRequest {
    private String domainBase;
    private String path;
    private LocalDateTime timeViewed;

    public PageViewRequest(String domainBase, String path) {
        this.domainBase = domainBase;
        this.path = path;
        this.timeViewed = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "PageViewRequest{" +
                "domainBase='" + domainBase + '\'' +
                ", path='" + path + '\'' +
                ", timeViewed=" + timeViewed +
                '}';
    }

    public String getDomainBase() {
        return domainBase;
    }

    public void setDomainBase(String domainBase) {
        this.domainBase = domainBase;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public LocalDateTime getTimeViewed() {
        return timeViewed;
    }

    public void setTimeViewed(LocalDateTime timeViewed) {
        this.timeViewed = timeViewed;
    }
}
