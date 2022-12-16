package com.fivetwenty.piggyback;

import static org.junit.Assert.assertEquals;
import static  com.fivetwenty.piggyback.controller.PiggyController.*;

import com.fivetwenty.piggyback.controller.PiggyController;
import com.fivetwenty.piggyback.model.Constants;
import com.fivetwenty.piggyback.model.User;
import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mongodb.client.model.Filters;
import org.springframework.http.ResponseEntity;

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
        //Document document = new Document("userName", "testuser").append("password", "testpassword");
        String userId = user.getName();
        Document document = new Document();
        document.put("userId", "testUser");
        document.put("userName", "testpassword");
        document.put("userRating", 5f);
        document.put("password", "aaaa");

        FindIterable iterable = mock(FindIterable.class);
        MongoCursor cursor = mock(MongoCursor.class);

        when(mongoClient.getDatabase("PiggyData")).thenReturn(database);
        when(database.getCollection(Constants.usersCollection)).thenReturn(userCollection);
        when(userCollection.find(Filters.eq("userId", userId))).thenReturn(iterable);
        when(iterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true).thenReturn(false);
        when(cursor.next()).thenReturn(document);

        ResponseEntity<String> result = loginController.loginRequest(user);
        assertEquals("Login Successful", result.getBody());
    }

    @Test
    public void testloginInvalidUser() {
        User user = new User("InvalidUser", "InvalidPassword", 5f, "cccc");
        //Document document = new Document("userName", "testuser").append("password", "testpassword");
        String userId = user.getName();
        Document document = new Document();
        document.put("userId", "testUser");
        document.put("userName", "testpassword");
        document.put("userRating", 5f);
        document.put("password", "aaaa");

        FindIterable iterable = mock(FindIterable.class);
        MongoCursor cursor = mock(MongoCursor.class);

        when(mongoClient.getDatabase("PiggyData")).thenReturn(database);
        when(database.getCollection(Constants.usersCollection)).thenReturn(userCollection);
        when(userCollection.find(Filters.eq("userId", userId))).thenReturn(iterable);
        when(iterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true).thenReturn(false);
        when(cursor.next()).thenReturn(document);

        ResponseEntity<String> result = loginController.loginRequest(user);
        assertEquals("No user found", result.getBody());
    }

    public void testloginFailed() {
        User user = new User("testuser", "testpassword", 5f, "bbbb");
        //Document document = new Document("userName", "testuser").append("password", "testpassword");
        String userId = user.getName();
        Document document = new Document();
        document.put("userId", "testUser");
        document.put("userName", "testpassword");
        document.put("userRating", 5f);
        document.put("password", "aaaa");

        FindIterable iterable = mock(FindIterable.class);
        MongoCursor cursor = mock(MongoCursor.class);

        when(mongoClient.getDatabase("PiggyData")).thenReturn(database);
        when(database.getCollection(Constants.usersCollection)).thenReturn(userCollection);
        when(userCollection.find(Filters.eq("userId", userId))).thenReturn(iterable);
        when(iterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true).thenReturn(false);
        when(cursor.next()).thenReturn(document);

        ResponseEntity<String> result = loginController.loginRequest(user);
        assertEquals("Wrong Password", result.getBody());
    }


}
