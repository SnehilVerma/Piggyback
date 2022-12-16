package com.fivetwenty.piggyback.repository;

import com.fivetwenty.piggyback.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Interface for User Requests Repository
 */
public interface UserRepository extends MongoRepository<User,String> {
    @Query("{userName:'?0'}")
    User findUserByName(String id);
}
