package com.daoren.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Demo1 {
    public static void main(String[] args) {
        final MongoClient client = new MongoClient("localhost", 27017);
        final MongoDatabase database = client.getDatabase("test1");
        final MongoCredential credential = MongoCredential.createCredential("admin", "test1", "admin".toCharArray());
        System.out.println("Coonected to the database successfully.");
        System.out.println(database);
        System.out.println("credential");
        System.out.println(credential);
//        database.createCollection("tutorial");
        System.out.println("Create collection tutorial successfully!");
        System.out.println("Select collection 'user'...");
        final MongoCollection<Document> user = database.getCollection("user");
        final Document doc1 = new Document()
                .append("age", 23)
                .append("name", "tom");
        user.insertOne(doc1);
        System.out.println("insert document!");

    }
}
