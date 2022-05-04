package com.gallo.dom.analytics_server_dev.model;

import java.io.Serializable;

public class TestObject implements Serializable {
    private String sub;
    private String TestClaim;
    private Long exp;

    public TestObject(String sub, String testClaim, Long exp) {
        this.sub = sub;
        TestClaim = testClaim;
        this.exp = exp;
    }

    public TestObject(String sub) {
        this.sub = sub;
    }

    @Override
    public String toString() {
        return "TestObject{" +
                "sub='" + sub + '\'' +
                ", TestClaim='" + TestClaim + '\'' +
                ", exp=" + exp +
                '}';
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getTestClaim() {
        return TestClaim;
    }

    public void setTestClaim(String testClaim) {
        TestClaim = testClaim;
    }

    public Long getExp() {
        return exp;
    }

    public void setExp(Long exp) {
        this.exp = exp;
    }
}
