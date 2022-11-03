package com.fivetwenty.piggyback.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("Users")
public class User implements IUser {

    @Id
    String userId = null;
    String userName = null;
    Float userRating = null;

    UserType userType = UserType.DEFAULT;


    public User(String userId, String userName, Float userRating,UserType userType) {
//        this.userId = userId;
        this.userName = userName;
        this.userRating = userRating;
        this.userType = userType;
    }

    @Override
    public String getName() {
        return userName;
    }

    @Override
    public Float getRating() {
        return userRating;
    }

    @Override
    public UserType getUserType() {
        return userType;
    }
}
