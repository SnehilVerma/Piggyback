package com.fivetwenty.piggyback.model;

/**
 * Module to handle all the customer requests
 * Contains getter and setter methods for userId, source, destination
 */
public class CustomerRequest {
    private  String userId;
    private  String src;
    private  String dst;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

}
