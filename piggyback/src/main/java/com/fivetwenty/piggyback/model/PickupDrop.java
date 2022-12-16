package com.fivetwenty.piggyback.model;

/**
 * Module to process the pick and drop requests
 */
public class PickupDrop {
    private String pickup;
    private String drop;

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    @Override
    public String toString() {
        return "PickupDrop{" +
                "pickup='" + pickup + '\'' +
                ", drop='" + drop + '\'' +
                '}';
    }

    public String getDrop() {
        return drop;
    }

    public void setDrop(String drop) {
        this.drop = drop;
    }
}
