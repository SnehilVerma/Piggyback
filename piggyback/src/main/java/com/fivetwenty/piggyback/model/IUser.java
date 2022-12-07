package com.fivetwenty.piggyback.model;

public interface IUser {

    public static enum UserType {
        DEFAULT,
        DRIVER,
        PASSENGER
    }
    public abstract String getName();
    public abstract Float getRating();

    //public abstract UserType getUserType();



}
