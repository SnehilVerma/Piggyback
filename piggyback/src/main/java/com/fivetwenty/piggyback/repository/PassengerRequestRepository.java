package com.fivetwenty.piggyback.repository;

import com.fivetwenty.piggyback.model.PassengerRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Interface for Passenger Requests Repository
 */
public interface PassengerRequestRepository extends MongoRepository<PassengerRequest,String> {
}
