package com.fivetwenty.piggyback.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("DriverRequest")
public class DriverRequest extends CustomerRequest{
    @Id
    String id = null;

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    LocalDateTime localDateTime;

    public DriverRequest(String userId, String src, String dst){
        this.setUserId(userId);
        this.setSrc(src);
        this.setDst(dst);
        this.setType("Driver");
    }
}
