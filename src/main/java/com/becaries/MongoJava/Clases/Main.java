package com.becaries.MongoJava.Clases;

import com.mongodb.client.MongoDatabase;

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
		Logica.inicioSesion();

	}

}
