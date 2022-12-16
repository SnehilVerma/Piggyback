package com.fivetwenty.piggyback;

import static org.junit.Assert.assertEquals;
import static  com.fivetwenty.piggyback.controller.PiggyController.*;

import com.fivetwenty.piggyback.controller.PiggyController;
import com.fivetwenty.piggyback.model.Constants;
import com.fivetwenty.piggyback.model.User;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.print.Doc;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PiggyControllerTests {

    @InjectMocks
    private PiggyController loginController;

    @Mock
    private MongoClient mongoClient;

    @Mock
    private MongoDatabase database;

    @Mock
    private MongoCollection<Document> userCollection;

    @Test
    public void testloginSuccessful() {
        User user = new User("testuser", "testpassword", 5f, "aaaa");
        //Document entry = new Document("userName", "testuser").append("password", "testpassword");
        BasicDBObject document = new BasicDBObject();
        document.put("userId", "testUser");
        document.put("userName", "testpassword");
        document.put("userRating", 5f);
        document.put("password", "aaaa");
        when(mongoClient.getDatabase("PiggyData")).thenReturn(database);
        when(database.getCollection(Constants.usersCollection)).thenReturn(userCollection);
        when(userCollection.find(Filters.eq("userName", "testuser")).first()).thenReturn((Document) document);

        String result = loginController.loginRequest(user);
        assertEquals("Login Successful", result);
    }


}
