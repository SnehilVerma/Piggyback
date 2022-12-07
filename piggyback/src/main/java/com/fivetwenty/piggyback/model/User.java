package com.fivetwenty.piggyback.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("Users")
public class User implements IUser {

    @Id
    String userId = null;
    String userName = null;
    Float userRating = null;

    //UserType userType = UserType.DEFAULT;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String password = null;


    public User(String userId, String userName, Float userRating, String password) {
//        this.userId = userId;
        this.userName = userName;
        this.userRating = userRating;
        this.password = password;
    }

    @Override
    public String getName() {
        return userName;
    }

    @Override
    public Float getRating() {
        return userRating;
    }

}
