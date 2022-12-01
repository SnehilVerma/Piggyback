package com.fivetwenty.piggyback.service;

import com.fivetwenty.piggyback.model.Constants;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.jobrunr.jobs.annotations.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.util.*;

import static com.mongodb.client.model.Filters.eq;

@Component
public class RidePairing {

    /**
     * Job which looks for newly added requests and creates matches and stores them in Matches table.
     */

    @Autowired
    MongoClient mongoClient;


    private static MongoCollection<Document> routesCollection;
    //logic:
    //every 5 seconds, get data from RiderRequests collection and PassengerRequests collection.
    //Form mappings in a way that a ride can accomodate passengers. Mapping should be of the form
    //( p1 -> r1,r2 ) = passenger 1 can get a lift from rider 1 or rider 2.
    // keep adding/updating the possible combinations in the matches collection.
    @Job(name = "Ride Pairing Job",retries = 2)
    public void executeRidePairing(){
        System.out.println("pairing started");
        MongoDatabase database = mongoClient.getDatabase("PiggyData");

        //get the routes data and let the object be stored in memory.
        routesCollection = database.getCollection("Routes");

        while(true) {
            try {
                System.out.println("matching riders with passengers");
                MongoCollection<Document> riderRequestsCollection = database.getCollection(Constants.riderRequestsCollection);
                MongoCollection<Document> passengerRequestsCollection = database.getCollection(Constants.passengerRequestsCollection);

                //get existing mappings, in the form.
                Map<String, Set<String>> mappings = new HashMap<>();
                MongoCollection<Document> existingMatches = database.getCollection(Constants.matchesCollection);
                //TODO: put existing matches into mappings hashmap.

                FindIterable<Document> pi = passengerRequestsCollection.find();
                FindIterable<Document> ri = riderRequestsCollection.find();
                MongoCursor<Document> pcursor = pi.iterator();
                MongoCursor<Document> rcursor = ri.iterator();

                try {
                    while(pcursor.hasNext()) {
                        Document passenger = pcursor.next();
                        String pid = (String)passenger.get("userId");
                        while(rcursor.hasNext()){
                            Document rider = rcursor.next();
                            String rid = (String)rider.get("userId");
                            //IMPORTANT TODO : try not to recheck same passenger/rider pair again. aim for efficiency.

                            if(mappings.get(pid)!=null && mappings.get(pid).contains(rid)){
                                continue;
                            }else{
                                //two possibilities, new rider, need to recheck.
                                //TODO : old rider but didn't match earlier - this can be avoided.
                                //check if this rider can take the passenger, and if yes, add it to matches results.
                                if(overlappingPathsExist(rider,passenger)){
                                    if(mappings.get(pid)==null){
                                        mappings.put(pid,new HashSet<String>());
                                    }
                                    mappings.get(pid).add(rid);
                                }
                            }
                        }
                    }
                } finally {
                    pcursor.close();
                    rcursor.close();
                }

                //store mappings.
                for(Map.Entry<String,Set<String>> entry : mappings.entrySet()){
                    Document d = existingMatches.find(Filters.eq("userId",entry.getKey())).first();
                    if(d==null){
                        existingMatches.insertOne(new Document().append("userId",entry.getKey()).append("matches",entry.getValue()));
                    }else {
                        existingMatches.updateOne(Filters.eq("userId", entry.getKey()), Updates.set("matches", entry.getValue()));
                    }
                }

                Thread.sleep(5000);

            }catch (Exception e){
                System.out.println(e);
            }
        }


    }

    private boolean overlappingPathsExist(Document rider,Document passenger){
        if(routesCollection==null){
            return false;
        }
        String rsrc = (String)rider.get("src");
        String rdst = (String)rider.get("dst");

        Document route = routesCollection.find(eq("source",rsrc)).first();
        List<Document> destinationMaps = (List<Document>) route.get("mappings");
        for(Document d : destinationMaps){
            /*System.out.println(d.get("dest"));*/
            if(d.get("dest").equals(rdst)){
                //found the FIRST MATCHING SHORTEST PATH , which has overlapping properties.
                boolean foundMatch = isOverlapping((List<List<String>>) d.get("paths"), (String) passenger.get("src"), (String) passenger.get("dst"));
                if(foundMatch){
                    return true;
                }
            }
        }
        return false;
    }


    private boolean isOverlapping(List<List<String>> paths,String psource,String pdestination){
        for(List<String> path : paths){
            //check if in this path, the passenger's source destination fits.
            //psource -> pdestination.
            int sourceIndex = path.indexOf(psource);
            int destIndex = path.indexOf(pdestination);
            if(sourceIndex>0 && destIndex>0 && destIndex > sourceIndex){
                //both indices found and source comes before destination.
                return true;
            }
        }
        return false;   //not found.
    }




}
