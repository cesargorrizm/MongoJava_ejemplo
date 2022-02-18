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

		ArrayList<Futbolista> futbolistas = new ArrayList<Futbolista>();
		ArrayList<Equipos> equipos = new ArrayList<Equipos>();

		futbolistas.add(new Futbolista("Iker", "Casillas", 33, new ArrayList<String>(Arrays.asList("Portero")), true));
		futbolistas.add(new Futbolista("Carles", "Puyol", 36,
				new ArrayList<String>(Arrays.asList("Central", "Lateral")), true));
		futbolistas.add(new Futbolista("Sergio", "Ramos", 28,
				new ArrayList<String>(Arrays.asList("Lateral", "Central")), true));
		futbolistas.add(new Futbolista("Andrés", "Iniesta", 30,
				new ArrayList<String>(Arrays.asList("Centrocampista", "Delantero")), true));
		futbolistas
				.add(new Futbolista("Fernando", "Torres", 30, new ArrayList<String>(Arrays.asList("Delantero")), true));
		futbolistas
				.add(new Futbolista("Leo", " Baptistao", 22, new ArrayList<String>(Arrays.asList("Delantero")), false));

		// PASO 1: Conexión al Server de MongoDB Pasandole el host y el puerto
		// MongoClient mongoClient1 = new MongoClient("localhost", 27017);

		ConnectionString connectionString = new ConnectionString(
				"mongodb+srv://Becarios:Admin1234@cluster0.aq3pk.mongodb.net/test?retryWrites=true&w=majority");
		MongoClientSettings settings = MongoClientSettings.builder()
				.applyConnectionString(connectionString)
				.build();
		MongoClient mongoClient = MongoClients.create(settings);
		MongoDatabase database = mongoClient.getDatabase("Futbol");

		// PASO 2: Conexión a la base de datos
		// DB db = mongoClient.getDB("Futbol");

		// PASO 3: Obtenemos una coleccion para trabajar con ella
		MongoCollection<Document> collection = database.getCollection("Futbolistas");

		MongoCollection<Document> equipo = database.getCollection("Equipos");

		// PASO 4: CRUD (Create-Read-Update-Delete)

		// PASO 4.1: "CREATE" -> Metemos los objetos futbolistas (o documentos en Mongo)
		// en la coleccion Futbolista

		for (Futbolista fut : futbolistas) {
			collection.insertOne(new Document()
					.append("_id", new ObjectId())
					.append("nombre", fut.getNombre())
					.append("genres", Arrays.asList("Documentary", "Comedy")));

		}

		// PASO 4.2.2: "READ" -> Hacemos una Query con condiciones (Buscar Futbolistas
		// que sean delanteros) y lo pasamos a un objeto Java
		System.out.println("\nFutbolistas que juegan en la posición de Delantero");
		Document queryResult = collection.find(Filters.eq("nombre", "Sergio")).first();
		System.out.println(queryResult.toJson());

		// PASO 4.2.1: "READ" -> Leemos todos los documentos de la base de datos
		int numDocumentos = (int) collection.count();
		System.out.println("Número de documentos en la colección Futbolistas: " + numDocumentos + "\n");

		// Busco todos los documentos de la colección y los imprimo
		// FindIterable<Document> cursor = collection.find();
		// try {
		// while (cursor.iterator() != null) {
		// System.out.println(cursor.toString());
		// }
		// } finally {

		// }

		// Select the "people" collection
		MongoCollection<Document> collection1 = database.getCollection("Futbolistas");

		// Create the document to specify find criteria
		Document findDocument = new Document("nombre", "Sergio");

		// Document to store query results
		MongoCursor<Document> resultDocument = collection1.find(findDocument).iterator();
		//MongoCursor<Document> resultDocument = collection1.find().iterator();

		System.out.println("*** Listing People By Sergio ****");

		// Iterate over the results printing each document
		while (resultDocument.hasNext()) {
			System.out.println(resultDocument.next().getString("nombre"));
		}

		// PASO 4.3: "UPDATE" -> Actualizamos la edad de los jugadores. Sumamos 100 años
		// a los jugadores que tengan mas de 30 años
		// DBObject find = new BasicDBObject("edad", new BasicDBObject("$gt", 30));
		// DBObject updated = new BasicDBObject().append("$inc", new
		// BasicDBObject().append("edad", 100));
		// collection.updateMany(find, updated, false, true);
		// Select the "people" collection
		MongoCollection<Document> collection3 = database.getCollection("Futbolistas");

		// Create the document to specify find criteria
		Document findDocument2 = new Document("nombre", "Sergio");

		// Create the document to specify the update
		Document updateDocument2 = new Document("$set",
				new Document("nombre", "Adolfo"));

		// Find one person and delete
		// collection3.findOneAndUpdate(findDocument2, updateDocument2);

		// FInd many and update
		collection3.updateMany(findDocument2, updateDocument2);
		System.out.println("Update ejecutado");

		// PASO 4.4: "DELETE" -> Borramos todos los futbolistas que sean
		// internacionales (internacional = true)
		// DBObject findDoc = new BasicDBObject("internacional", true);
		// collection.remove(findDoc);


			// Create the document to specify find criteria
			Document findDocument5 = new Document("nombre", "Adolfo");
		
			// Find one person and delete
			//collection.findOneAndDelete(findDocument5);
			//Find very person and delete
			collection.deleteMany(findDocument5);
			System.out.println("eLEMINACION EJECUTADA");

		// PASO FINAL: Cerrar la conexion
		mongoClient.close();

	}

}
