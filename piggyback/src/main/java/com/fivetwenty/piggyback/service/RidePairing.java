package com.fivetwenty.piggyback.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.jobrunr.jobs.annotations.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RidePairing {

    /**
     * Job which looks for newly added requests and creates matches and stores them in Matches table.
     */

    @Autowired
    MongoClient mongoClient;

    @Job(name = "Ride Pairing Job",retries = 2)
    public void executeRidePairing(){
        System.out.println("pairing started");
        MongoDatabase database = mongoClient.getDatabase("PiggyData");
        MongoCollection<Document> collection = database.getCollection("Routes");
        System.out.println(collection.countDocuments());

    }



}
