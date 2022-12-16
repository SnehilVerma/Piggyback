package com.fivetwenty.piggyback.repository;

import com.fivetwenty.piggyback.model.DriverRequest;
import com.fivetwenty.piggyback.model.RideRequestWaiting;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Interface for Ride Requests Repository
 */
public interface RideRequestWaitingRepository extends MongoRepository<RideRequestWaiting,String>{

}
