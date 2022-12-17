package com.fivetwenty.piggyback.service;

import com.fivetwenty.piggyback.model.Constants;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.jobrunr.jobs.annotations.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * The Mongo client.
 * Module to flush all the old entries
 * When a driver/passenger requests for a ride, the job retries for 2 times
 * After which the request would be flushed out and the user would have
 * to try to find a match again after some time
 */
@Component
public class FlushOldEntries {
    @Autowired
    MongoClient mongoClient;


    /**
     * Flush old passenger entries.
     */
    @Job(name = "Flush old entries for Passenger", retries = 2)
    public void flushOldPassengerEntries() {
        MongoDatabase database = mongoClient.getDatabase("PiggyData");

        while (true) {
            try {
                MongoCollection<Document> passengerRequestsCollection = database.getCollection(Constants.passengerRequestsCollection);

                MongoCursor<Document> pcursor = passengerRequestsCollection.find().iterator();
                try {
                    while (pcursor.hasNext()) {
                        Document passenger = pcursor.next();
                        String userId = (String) passenger.get("userId");
                        Date requestTime = (Date) passenger.get("date");

                        Date timeNOw = new Date();
                        long timeDiff = timeNOw.getTime() - requestTime.getTime();

                        if (timeDiff >= Constants.passengerRequestTimeoutConstant) {
                            passengerRequestsCollection.deleteOne(new Document("userId", userId));
                        }
                    }
                } finally {
                    pcursor.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Flush old driver entries.
     */
    @Job(name = "Flush old entries for Driver", retries = 2)
    public void flushOldDriverEntries() {
        MongoDatabase database = mongoClient.getDatabase("PiggyData");

        while (true) {
            try {
                MongoCollection<Document> riderRequestsCollection = database.getCollection(Constants.riderRequestsCollection);
                MongoCursor<Document> rcursor = riderRequestsCollection.find().iterator();

                try {
                    while (rcursor.hasNext()) {
                        Document driver = rcursor.next();
                        String userId = (String) driver.get("userId");
                        Date requestTime = (Date) driver.get("date");

                        Date timeNOw = new Date();
                        long timeDiff = timeNOw.getTime() - requestTime.getTime();

                        if (timeDiff >= Constants.driverRequestTimeoutConstant) {
                            riderRequestsCollection.deleteOne(new Document("userId", userId));
                        }
                    }
                } finally {
                    rcursor.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

