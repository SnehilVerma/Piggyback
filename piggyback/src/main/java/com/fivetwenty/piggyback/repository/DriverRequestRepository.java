package com.fivetwenty.piggyback.repository;

import com.fivetwenty.piggyback.model.DriverRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Interface for Driver Requests Repository
 */
public interface DriverRequestRepository extends MongoRepository<DriverRequest, String> {
}
