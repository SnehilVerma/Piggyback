package com.fivetwenty.piggyback.repository;

import com.fivetwenty.piggyback.model.Route;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoutesRepository extends MongoRepository<Route, String> {

}
