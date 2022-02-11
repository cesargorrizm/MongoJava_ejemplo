package com.becaries.MongoJava.MongoJava_ejemplo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

import com.becaries.MongoJava.Equipos;
import com.becaries.MongoJava.modelos.Pelicula;
import com.becaries.MongoJava.modelos.Princesa;
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

	public static MongoCollection<Document> pelicula = null;
	public static MongoCollection<Document> villano;
	public static MongoCollection<Document> princesa;
	public static MongoCollection<Document> principe;
	public static MongoCollection<Document> mascota;
	public static MongoCollection<Document> personajeSec;

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
		// database.createCollection("Princesa");
		// database.createCollection("Principe");
		pelicula = database.getCollection("Pelicula");
		princesa = database.getCollection("Princesa");
		villano = database.getCollection("Villano");
		principe = database.getCollection("Principe");
		mascota = database.getCollection("Mascota");
		personajeSec = database.getCollection("Personaje_Secundario");

		// leerPelicula();
		// Pelicula pelicula = new Pelicula("PruebaInsert", "Muerte y destruccion",
		// "2001",
		// "Es una prueba que estamos haciendo de vs", "1", "100", "1", "12554232");
		// insertarPelicula(pelicula);

		// eliminarPelicula("PruebaInsert");
		// mongoClient.close();

		//editarPelicula("6204183901e6db753d0bd2f7", "Blancanieves");
		relacionPelicula("6204183901e6db753d0bd2f7", "6206999001e6db753d584343");

		// ArrayList<Princesa> f = leerPrincesa();
		// for (Princesa princesa : f) {
		// System.out.println(princesa);
		// }
	}

	private static void relacionPelicula(String idPelicula, String idPrincesa) {
		Document findDocument2 = new Document("_id", new ObjectId(idPrincesa));
		Document updateDocument2 = new Document("$set",
				new Document("idPelicula", new ObjectId(idPelicula)));
		princesa.updateMany(findDocument2, updateDocument2);
		System.out.println("Update ejecutado");
	}

	private static ArrayList<Princesa> leerPrincesa() {
		ArrayList<Princesa> listaprincesas = new ArrayList<Princesa>();
		MongoCursor<Document> resultDocument = princesa.find().iterator();

		while (resultDocument.hasNext()) {
			Document a = resultDocument.next();

			System.out.println(a.getString("name") + " " + a.get("_id"));
			Princesa princesa = new Princesa(a.getObjectId("_id").toString(), a.getString("name"),
					a.getString("personality"),
					a.getString("appearance"), a.getString("nationality"), a.getString("occupation"),
					a.getString("likes"),
					a.getString("dislikes"), a.getString("powers"), a.getString("famousquotes"),
					a.getString("zodiacsign"),
					a.getString("category"), a.getString("image"), a.getString("idPelicula"));
			listaprincesas.add(princesa);
		}

		return listaprincesas;
	}

	private static void leerPelicula() {
		MongoCursor<Document> resultDocument = pelicula.find().iterator();

		while (resultDocument.hasNext()) {
			Document a = resultDocument.next();
			System.out.println(a.getString("name") + " " + a.getObjectId("_id"));

		}
	}

	public static void insertarPelicula(Pelicula p) {
		pelicula.insertOne(new Document()
				.append("_id", new ObjectId())
				.append("name", p.getName())
				.append("Theme", p.getTheme())
				.append("YearOpening", p.getYearOpening())
				.append("Description", p.getDescription())
				.append("Puntuation", p.getPuntuation())
				.append("YearsMin", p.getYearsMin())
				.append("dislikes", p.getDislikes())
				.append("duration", p.getDuration()));
	}

	public static void eliminarPelicula(String idPelicula) {
		Document findDocument = new Document("name", idPelicula);
		pelicula.deleteMany(findDocument);
	}

	public static void editarPelicula(String dt, String dt2) {
		Document findDocument2 = new Document("_id", new ObjectId(dt));
		Document updateDocument2 = new Document("$set",
				new Document("name", dt2));
		pelicula.updateMany(findDocument2, updateDocument2);
		System.out.println("Update ejecutado");
	}
}
