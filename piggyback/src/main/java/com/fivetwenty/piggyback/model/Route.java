package com.fivetwenty.piggyback.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("Routes")
public class Route {

    String source;
    List<Destination> destinationList;

    public class Destination{
        String dest;
        List<List<String>> paths;
    }

}
