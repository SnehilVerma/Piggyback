package com.fivetwenty.piggyback.controller;

import com.fivetwenty.piggyback.model.*;
import com.fivetwenty.piggyback.repository.DriverRequestRepository;
import com.fivetwenty.piggyback.repository.PassengerRequestRepository;
import com.fivetwenty.piggyback.repository.UserRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.apache.logging.log4j.message.Message;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.print.Doc;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Filter;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class PiggyController {

    //TODO: need to check which concrete object to assign to ExecutorService interface.
    private final ExecutorService executor = Executors.newSingleThreadExecutor();


    @Autowired
    PassengerRequestRepository passengerRequestRepository;

    @Autowired
    DriverRequestRepository driverRequestRepository;

    @Autowired
    UserRepository userRepository;


    @Autowired
    MongoClient mongoClient;

    @GetMapping("/")
    public String index(){
        return "Application OK";
    }

    @PostMapping("/queryRoute")
    public String getRoutes(@RequestBody PickupDrop pickupDrop){
        System.out.println(pickupDrop.getPickup() + " " + pickupDrop.getDrop());

        return pickupDrop.toString();
    }

    public void requestDump(CustomerRequest customerRequest){
        String userId = customerRequest.getUserId();
        String src = customerRequest.getSrc();
        String dst = customerRequest.getDst();
        Date date = new Date();

        System.out.println(customerRequest.getType());
        if (customerRequest.getType().equals("Driver")){
            DriverRequest driverRequest = new DriverRequest(userId, src, dst);
            driverRequest.setDate(date);
            driverRequestRepository.save(driverRequest);
        } else {
            PassengerRequest passengerRequest = new PassengerRequest(userId, src, dst);
            passengerRequest.setDate(date);
            passengerRequestRepository.save(passengerRequest);
        }
    }
    @PostMapping("/requestMatch")
    public String requestMatching(@RequestBody CustomerRequest customerRequest){

        requestDump(customerRequest);
        return "Request entered in the Database";
    }

    @PostMapping("/registration")
    public String registrationRequest(@RequestBody User user){
        try{
            userRepository.save(user);
        } catch (Exception e){
            System.out.println(e);
            return "User already exist";
        }
        return "Registration Completed";
    }

    @PostMapping("/login")
    public String loginRequest(@RequestBody User user){
        MongoDatabase database = mongoClient.getDatabase("PiggyData");
        String userId = user.getName();

        try{
            MongoCollection<Document> userCollection = database.getCollection(Constants.usersCollection);
            Document entry = userCollection.find(Filters.eq("userName", userId)).first();
            if (entry == null){
                return "No user found";
            }
            String password = (String) entry.get("password");
             if (password.equals(user.getPassword())){
                 return "Login Successful";
             }
        }catch (Exception e){
            System.out.println(e);
            return "Login Failed";
        }
        return "Failed";
    }


//    @GetMapping("/requestRide")
//    /**
//     * <p>
//     *     Sample endpoint to test Server side events.
//     *     What it's supposed to do - When the client calls this endpoint, the server sets up a
//     *     unidirectional channel with the client. The server can send updates to this connected client,
//     *     and when it's done it will close the connection.
//     *     Important thing to note : Unlike WebSockets which are bidirectional, here the connection is
//     *     unidirection from server -> client ( which called this endpoint ).
//     * </p>
//     */
//    public SseEmitter requestRide(){
//        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
//        sseEmitter.onCompletion(()->System.out.println("SseEmitted completed"));
//        sseEmitter.onTimeout(()->System.out.println("SseEmitted completed"));
//        sseEmitter.onError((ex)->System.out.println("error " + ex));
//        executor.execute(()->{
//            for(int i=0;i<15;i++){
//                try{
//                    sseEmitter.send(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")));
//                    Thread.sleep(1000);    //sleep for 1 sec.
//                }catch (Exception ex){
//                    ex.printStackTrace();
//                    sseEmitter.completeWithError(ex);
//                }
//            }
//            sseEmitter.complete();
//        });
//        System.out.println("exiting controller");
//        return sseEmitter;
//
//    }


    //logic to keep checking the 'matches' collection and keep pushing to client,
    // if any new entry is added to this passenger.
    @GetMapping("/matches/{userId}")
    public SseEmitter matches(@PathVariable String userId){
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        sseEmitter.onCompletion(()->System.out.println("SseEmitted completed"));
        sseEmitter.onTimeout(()->System.out.println("SseEmitted completed"));
        sseEmitter.onError((ex)->System.out.println("error " + ex));

        executor.execute(()->{

            try {
                for(int i=0;i<Constants.matchPushTimeoutConstant;i++) {
                    MongoCollection<Document> matchesCollection = mongoClient.getDatabase(Constants.dbName).
                            getCollection(Constants.matchesCollection);

                    MongoCollection<Document> riderRequestsCollection = mongoClient.getDatabase(Constants.dbName).
                            getCollection(Constants.riderRequestsCollection);


                    Document matchArray = matchesCollection.find(Filters.eq("userId", userId)).first();


                    List<Document> resultMatches = new ArrayList<>();
                    List<String> matches = (List<String>) matchArray.get("matches");
                    for(String riderMatch : matches){
                        Document d =new Document();
                        Document currRider = riderRequestsCollection.find(Filters.eq("userId", riderMatch)).first();
                        d.put("userId",riderMatch);
                        d.put("src",currRider.get("src"));
                        d.put("dst",currRider.get("dst"));
                        resultMatches.add(d);
                    }
                    sseEmitter.send(resultMatches);
                    Thread.sleep(1000);
                }
            }catch(Exception ex){
                ex.printStackTrace();
                sseEmitter.completeWithError(ex);
            }
            sseEmitter.complete();
        });

        System.out.println("exiting matches sse");
        return sseEmitter;
    }

}
