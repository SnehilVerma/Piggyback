package com.fivetwenty.piggyback.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Module to handle cases when user has requested a ride and is waiting
 * Updates the status of the ride once a match is found
 */
@Document("RideRequestWaiting")
public class RideRequestWaiting {
    private  String passengerId;
    private  String src;
    private  String dst;
    private  String driverId;
    private  boolean status;
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
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

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

}
