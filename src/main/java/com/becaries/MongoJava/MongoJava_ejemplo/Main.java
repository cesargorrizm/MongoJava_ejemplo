package com.becaries.MongoJava.MongoJava_ejemplo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

import com.becaries.MongoJava.Equipos;
import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.DB;

import com.mongodb.DBObject;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Main {

	public static void main(String args[]) {

		// PASO 1: Conexión al Server de MongoDB Pasandole el host y el puerto
		// MongoClient mongoClient1 = new MongoClient("localhost", 27017);

		ConnectionString connectionString = new ConnectionString(
				"mongodb+srv://Becarios:Admin1234@cluster0.aq3pk.mongodb.net/test?retryWrites=true&w=majority");
		MongoClientSettings settings = MongoClientSettings.builder()
				.applyConnectionString(connectionString)
				.build();
		MongoClient mongoClient = MongoClients.create(settings);
		MongoDatabase database = mongoClient.getDatabase("Disney");
		database.createCollection("Princesa");
		database.createCollection("Principe");
		MongoCollection<Document> pelicula = database.getCollection("Pelicula");
		MongoCollection<Document> villano = database.getCollection("Villano");
		MongoCollection<Document> princesa = database.getCollection("Princesa");
		MongoCollection<Document> principe = database.getCollection("Principe");
		MongoCollection<Document> mascota = database.getCollection("Mascota");
		MongoCollection<Document> personajeSec = database.getCollection("Personaje_Secundario");

		// PASO 4.2.2: "READ Peliculas"
		System.out.println("\nPeliculas en bd");
		MongoCursor<Document> resultDocument = pelicula.find().iterator();

		while (resultDocument.hasNext()) {
			Document a = resultDocument.next();
			System.out.println(a.getString("name") + " "+ a.getObjectId("_id"));
		}
		
		mongoClient.close();

	}

}
