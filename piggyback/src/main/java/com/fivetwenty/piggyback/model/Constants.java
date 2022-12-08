package com.fivetwenty.piggyback.model;

public class Constants {

    public static String dbName = "PiggyData";

    public static String routesCollection = "Routes";

    public static String usersCollection = "Users";

    public static String matchesCollection = "Matches";

    public static String riderRequestsCollection = "DriverRequest";

    public static String passengerRequestsCollection = "PassengerRequest";

    public static Integer matchPushTimeoutConstant = 60;    //will send updates to rider for a minute.
    //in live client this timeout should be a high value, and when the server stops sending result to client,
    //the client should show 'No Riders in your area' msg.
}
