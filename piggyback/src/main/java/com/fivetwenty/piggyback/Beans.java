package com.fivetwenty.piggyback;


import com.fivetwenty.piggyback.model.Constants;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.Document;
import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.storage.InMemoryStorageProvider;
import org.jobrunr.storage.StorageProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Beans configuration for Storage Provider, MongoDB, and Map
 */
@Configuration
public class Beans {

    @Bean
    public StorageProvider storageProvider(JobMapper jobMapper) {
        InMemoryStorageProvider storageProvider = new InMemoryStorageProvider();
        storageProvider.setJobMapper(jobMapper);
        return storageProvider;
    }

    @Bean("mongo")
    public MongoClient mongoClient(){
        ConnectionString connectionString = new ConnectionString("mongodb+srv://rex1346:Romil%401995@cluster0.4nkebes.mongodb.net/test");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean("routesMap")
    public Map<String, Map<String, List<List<String>>>> routesMap(){
        Map<String, Map<String, List<List<String>>>> routesMap=new HashMap<>();

        MongoCollection<Document> routesCollection = mongoClient().getDatabase(Constants.dbName).getCollection(Constants.routesCollection);
        FindIterable<Document> routeIt = routesCollection.find();
        try (MongoCursor<Document> routeCursor = routeIt.iterator()) {
            while (routeCursor.hasNext()) {
                Document sourcePath = routeCursor.next();
                List<Document> destinations = (List<Document>) sourcePath.get("mappings");
                Map<String, List<List<String>>> pathMaps = new HashMap<>();
                for (Document destination : destinations) {
                    List<List<String>> paths = (List<List<String>>) destination.get("paths");
                    pathMaps.put((String) destination.get("dest"), paths);
                }
                routesMap.put((String) sourcePath.get("source"), pathMaps);
            }
        }
        return routesMap;
    }

}
