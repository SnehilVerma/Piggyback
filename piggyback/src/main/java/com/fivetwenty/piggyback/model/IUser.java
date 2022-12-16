package com.fivetwenty.piggyback.model;

/**
 * User Interface to handle username, rating, and user type (driver/passenger)
 */
public interface IUser {

    /**
     * Enumerations over User Types - Driver and Passenger
     */
    public static enum UserType {
        DEFAULT,
        DRIVER,
        PASSENGER
    }
    public abstract String getName();
    public abstract Float getRating();

    //public abstract UserType getUserType();



}
