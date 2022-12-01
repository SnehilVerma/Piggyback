package com.fivetwenty.piggyback.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("PassengerRequest")
public class PassengerRequest extends CustomerRequest {

    @Id
    String id = null;

    public PassengerRequest(String userId, String src, String dst){
        this.setUserId(userId);
        this.setSrc(src);
        this.setDst(dst);
        this.setType("Passenger");
    }

}
