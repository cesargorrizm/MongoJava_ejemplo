package com.becaries.MongoJava.Clases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bson.conversions.Bson;

class Main {

	public static void main(String args[]) {

		// PASO 1: Conexion al Server de MongoDB Pasandole el host y el puerto
		// MongoClient mongoClient1 = new MongoClient("localhost", 27017);
		MongoDatabase database = Conexion.conexionMongoDB();

		// PASO 2: Crear colecciones necesarias para el desarrollo de la aplicacion
		// Logica.crearColecciones(database);

		// PASO 3: Meter datos de los csv a la base de datos para aligerar tiempo

		// PASO 4: obtener las colecciones para poder trabajar con ellas
		Logica.obtenerColecciones(database);

		// PASO 5: DESARROLLO DE LA APLICACIÓN

		// COMIENZA LA APLICACION
		// Mostrar un menu diferente por cada opcion
		// Pedir nombre y contra antes de inciar caulquier cosa
		// Logica.inicioSesion();

		{
			List<Bson> filters = new ArrayList<>();
			Bson match = new Document("$match",
					new Document("Princesa.idPelicula", true));

			Bson lookup = new Document("$lookup",
					new Document("from", "Pelicula")
							.append("localField", "idPrincesa")
							.append("foreignField", "_id")
							.append("as", "look_coll"));

			filters.add(lookup);
			filters.add(match);

			MongoCursor<Document> it = database.getCollection("Princesa").aggregate(filters).iterator();
			

			while (it.hasNext()) {
				 Document next = it.next();
				System.out.println(next.toString());
			}

			// var coll = database.getCollection("pelicula");

			// // create the pipeline operations, first with the $match
			// DBObject match = new BasicDBObject("$match",
			// new BasicDBObject("_id", ""));

			// // build the $lookup operations
			// DBObject lookupFields = new BasicDBObject("from", "company");
			// lookupFields.put("localField", "companyId");
			// lookupFields.put("foreignField", "_id");
			// lookupFields.put("as", "company");
			// //DBObject lookup = new BasicDBObject("$lookup", lookupFields);

			// // build the $project operations
			// DBObject projectFields = new BasicDBObject("name", 1);
			// projectFields.put("lastName", 1);
			// projectFields.put("companyId", 1);
			// projectFields.put("companyName", "$company.companyName");
			// DBObject project = new BasicDBObject("$project", projectFields);

			// List<DBObject> pipeline = Arrays.asList(match, lookup, project);

			// AggregationOutput output = coll.aggregate(pipeline);

			// for (DBObject result : output.results()) {
			// System.out.println(result);
			// }
		}

	}

}
