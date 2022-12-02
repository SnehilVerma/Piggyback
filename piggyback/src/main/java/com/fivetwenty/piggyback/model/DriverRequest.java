package com.fivetwenty.piggyback.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("DriverRequest")
public class DriverRequest extends CustomerRequest{
    @Id
    String id = null;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    Date date;

    public DriverRequest(String userId, String src, String dst){
        this.setUserId(userId);
        this.setSrc(src);
        this.setDst(dst);
        this.setType("Driver");
    }
}
