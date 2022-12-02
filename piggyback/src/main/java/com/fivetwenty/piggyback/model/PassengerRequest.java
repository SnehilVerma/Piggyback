package com.fivetwenty.piggyback.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.crypto.Data;
import java.util.Date;

@Document("PassengerRequest")
public class PassengerRequest extends CustomerRequest {

    @Id
    String id = null;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    Date date;

    public PassengerRequest(String userId, String src, String dst){
        this.setUserId(userId);
        this.setSrc(src);
        this.setDst(dst);
        this.setType("Passenger");
    }

}
