package com.fivetwenty.piggyback;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fivetwenty.piggyback.repository.RoutesRepository;
import com.fivetwenty.piggyback.repository.UserRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class PiggybackApplication implements CommandLineRunner {

	//Test mongo connections.
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoutesRepository routesRepository;

	public static void main(String[] args) {
		SpringApplication.run(PiggybackApplication.class, args);
		System.out.println("Starting Application");


	}


	@Override
	public void run(String... args) throws Exception {

		//SAMPLE DATA INSERTION/QUERY - done using mongo repositories.
//		User user1 = new User("","Snehil Verma",0.0f, IUser.UserType.DRIVER);
//		User user2 = new User("","Piusha G",0.0f, IUser.UserType.PASSENGER);
//		userRepository.save(user1);
//		userRepository.save(user2);
//		System.out.println("Users saved");

		//there are many ways to do this and only for TEST we are doing this.
//		System.out.println(userRepository.findUserByName("Snehil Verma").getUserType());
//		System.out.println(userRepository.findUserByName("Piusha G").getUserType());



		//RoutesDataDumper(); 	//ONLY uncomment if you need to insert data again.


	}

	//Helper method to insert all routes data to mongo db collection : Routes.
	//This one is done using Mongo client object - another handy way to insert data.
	public void RoutesDataDumper() throws Exception{
		ObjectMapper objectMapper=new ObjectMapper();
		String localDir = System.getProperty("user.dir");
		File jsonFile = new File(localDir);
		jsonFile = new File(jsonFile.getParentFile(),"data/amherst_routes_dump.json");
		Map<String, Object> map
				= objectMapper.readValue(jsonFile, new TypeReference<Map<String,Object>>(){});

		List<Document> routes=new ArrayList<Document>();
		for(Map.Entry<String,Object> entry : map.entrySet()){
			Document doc=new Document();
			doc.put("source",entry.getKey());
			doc.put("mappings",entry.getValue());
			routes.add(doc);
		}
		try(MongoClient mongoClient = MongoClients.create("mongodb+srv://rex1346:Romil%401995@cluster0.4nkebes.mongodb.net/test")) {
			MongoDatabase database = mongoClient.getDatabase("PiggyData");
			MongoCollection<Document> collection = database.getCollection("Routes");
			collection.insertMany(routes);
		}catch (Exception e){
			System.out.println(e);
		}
	}
}
