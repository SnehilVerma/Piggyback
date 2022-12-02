package com.fivetwenty.piggyback.service;

import com.fivetwenty.piggyback.model.Constants;
import com.fivetwenty.piggyback.model.DriverRequest;
import com.fivetwenty.piggyback.model.PassengerRequest;
import com.fivetwenty.piggyback.repository.DriverRequestRepository;
import com.fivetwenty.piggyback.repository.PassengerRequestRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.jobrunr.jobs.annotations.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FlushOldEntries {
    @Autowired
    MongoClient mongoClient;

//    @Autowired
//    PassengerRequestRepository passengerRequestRepository;
//
//    @Autowired
//    DriverRequestRepository driverRequestRepository;

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
//                        String src = (String) passenger.get("src");
//                        String dst = (String) passenger.get("dst");
                        Date requestTime = (Date) passenger.get("date");

                        Date timeNOw = new Date();
                        long timeDiff = timeNOw.getTime() - requestTime.getTime();

//                        PassengerRequest passengerRequest = new PassengerRequest(userId, src, dst);
//                        passengerRequest.setDate(requestTime);
//                        passengerRequest.setType("Passenger");

                        if (timeDiff >= 5) {
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
//                        String src = (String) driver.get("src");
//                        String dst = (String) driver.get("dst");
                        Date requestTime = (Date) driver.get("date");

                        Date timeNOw = new Date();
                        long timeDiff = timeNOw.getTime() - requestTime.getTime();

//                        DriverRequest driverRequest = new DriverRequest(userId, src, dst);
//                        driverRequest.setDate(requestTime);
//                        driverRequest.setType("Passenger");

                        if (timeDiff >= 10) {
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

