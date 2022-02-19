package com.becaries.MongoJava.Clases;

import java.time.LocalDateTime;
import java.util.HashMap;

import com.becaries.MongoJava.modelos.Director;
import com.becaries.MongoJava.modelos.Princesa;
import com.becaries.MongoJava.modelos.Principe;
import com.becaries.MongoJava.modelos.Villano;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bson.types.ObjectId;

class Main {

	public static boolean BOOLEAN = false;
	public static MongoCollection<Document> pelicula = null;
	public static MongoCollection<Document> villano;
	public static MongoCollection<Document> princesa;
	public static MongoCollection<Document> principe;
	public static MongoCollection<Document> director;
	public static MongoCollection<Document> usuario;
	public static ecriptar en = new ecriptar();

	public static void main(String args[]) {

		// PASO 1: Conexion al Server de MongoDB Pasandole el host y el puerto
		// MongoClient mongoClient1 = new MongoClient("localhost", 27017);
		MongoDatabase database = Conecion.conexionMongoDB();

		// PASO 2: Crear colecciones necesarias para el desarrollo de la aplicacion
		// crearColecciones(database);

		// PASO 3: Meter datos de los csv a la base de datos para aligerar tiempo

		// PASO 4: obtener las colecciones para poder trabajar con ellas
		Logica.obtenerColecciones(database);

		// PASO 5: DESARROLLO DE LA APLICACIÓN

		// COMIENZA LA APLICACION
		// Mostrar un menu diferente por cada opcion
		// Pedir nombre y contra antes de inciar caulquier cosa
		Logica.inicioSesion();

	}

	public static void editarDirector(String id, Director p) {
		Document findDocument2 = new Document("_id", new ObjectId(id));

		Document updateDocument2 = new Document("$set",
				new Document()
						.append("Director", p.getDirector())
						.append("Edad", p.getEdad())
						.append("Ciudad_nacimiento", p.getCiudadNacimiento())
						.append("Vehiculo", p.getVehiculo())
						.append("Email", p.getEmail())
						.append("Genero", p.getGenero())
						.append("Ip_movil", p.getIpMovil())
						.append("ip_address", p.getIpAddress())
						.append("Empresa", p.getEmpresa())
						.append("Titulacion", p.getTitulacion()));
		director.updateMany(findDocument2, updateDocument2);
		System.out.println("Update ejecutado");
	}

	public static void editarPricensa(String id, Princesa p) {
		Document findDocument2 = new Document("_id", new ObjectId(id));

		Document updateDocument2 = new Document("$set",
				new Document()
						.append("Nombre", p.getNombre())
						.append("Edad", p.getEdad())
						.append("Ciudad", p.getCiudad())
						.append("Vehiculo", p.getVehiculo())
						.append("Email", p.getEmail())
						.append("Genero", p.getGenero())
						.append("Creacion", p.getCreacion())
						.append("ip_address", p.getIpAddress())
						.append("Universidad", p.getUniversidad())
						.append("Titulacion", p.getTitulacion()));
		princesa.updateMany(findDocument2, updateDocument2);
		System.out.println("Update ejecutado");
	}

	public static void editarVillano(String id, Villano p) {
		Document findDocument2 = new Document("_id", new ObjectId(id));

		Document updateDocument2 = new Document("$set",
				new Document()
						.append("Nombre", p.getNombre())
						.append("Edad", p.getEdad())
						.append("Ciudad", p.getCiudad())
						.append("Vehiculo", p.getVehiculo())
						.append("Email", p.getEmail())
						.append("Genero", p.getGenero())
						.append("Creacion", p.getCreacion())
						.append("ip_address", p.getIpAddress())
						.append("Universidad", p.getUniversidad())
						.append("Titulacion", p.getTitulacion()));
		villano.updateMany(findDocument2, updateDocument2);
		System.out.println("Update ejecutado");
	}

	public static void editarPrincipe(String id, Principe p) {
		Document findDocument2 = new Document("_id", new ObjectId(id));

		Document updateDocument2 = new Document("$set",
				new Document()
						.append("Nombre", p.getNombre())
						.append("Edad", p.getEdad())
						.append("Ciudad", p.getCiudad())
						.append("Vehiculo", p.getVehiculo())
						.append("Email", p.getEmail())
						.append("Genero", p.getGenero())
						.append("Creacion", p.getCreacion())
						.append("ip_address", p.getIpAddress())
						.append("Universidad", p.getUniversidad())
						.append("Titulacion", p.getTitulacion()));
		principe.updateMany(findDocument2, updateDocument2);
		System.out.println("Update ejecutado");
	}

}
