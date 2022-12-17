package com.fivetwenty.piggyback;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fivetwenty.piggyback.model.User;
import com.fivetwenty.piggyback.repository.RoutesRepository;
import com.fivetwenty.piggyback.repository.UserRepository;
import com.fivetwenty.piggyback.service.FlushOldEntries;
import com.fivetwenty.piggyback.service.RidePairing;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Main Application for Piggyback
 */
@SpringBootApplication
public class PiggybackApplication implements CommandLineRunner {

    //Test mongo connections.
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoutesRepository routesRepository;


    @Autowired
    JobScheduler jobScheduler;

    @Autowired
    private RidePairing ridePairing;

    @Autowired
    private FlushOldEntries flushOldEntries;

    public static void main(String[] args) {
        SpringApplication.run(PiggybackApplication.class, args);
        System.out.println("Starting Application");
    }


    @PostConstruct
    public void init() {
        jobScheduler.enqueue(() -> ridePairing.executeRidePairing());
        jobScheduler.enqueue(() -> flushOldEntries.flushOldDriverEntries());
        jobScheduler.enqueue(() -> flushOldEntries.flushOldPassengerEntries());
    }

    @Override
    public void run(String... args) throws Exception {

        //SAMPLE DATA INSERTION/QUERY - done using mongo repositories.
        //UserDumper();


//		RoutesDataDumper(); 	//ONLY uncomment if you need to insert data again.

    }

    //TODO: Sample code 1
    public void UserDumper() {
        User user1 = new User("", "Snehil Verma", 0.0f, "aaaa");
        User user2 = new User("", "Piusha G", 0.0f, "bbbbb");
        userRepository.save(user1);
        userRepository.save(user2);
        System.out.println("Users saved");
    }


    //TODO: Sample code 1
    //Helper method to insert all routes data to mongo db collection : Routes.
    //This one is done using Mongo client object - another handy way to insert data.
    public void RoutesDataDumper() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String localDir = System.getProperty("user.dir");
        File jsonFile = new File(localDir);
        jsonFile = new File(jsonFile.getParentFile(), "data/amherst_routes_dump.json");
        Map<String, Object> map
                = objectMapper.readValue(jsonFile, new TypeReference<Map<String, Object>>() {
        });

        List<Document> routes = new ArrayList<Document>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Document doc = new Document();
            doc.put("source", entry.getKey());
            doc.put("mappings", entry.getValue());
            routes.add(doc);
        }
        try (MongoClient mongoClient = MongoClients.create("mongodb+srv://rex1346:Romil%401995@cluster0.4nkebes.mongodb.net/test")) {
            MongoDatabase database = mongoClient.getDatabase("PiggyData");
            MongoCollection<Document> collection = database.getCollection("Routes");
            collection.insertMany(routes);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Completed dumping data");
    }
}
