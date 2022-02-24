package com.becaries.MongoJava.Clases;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import java.util.logging.Level;
import java.util.logging.Logger;

// Clase que se encarga de establecer conexion con mongoDB
public class Conexion {

    // Metodo para establecer conexion
    public static MongoDatabase conexionMongoDB() {
        // Establecemos que los errores que imprima el logger sean solo los severos
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        // Le pasamos la url
        ConnectionString connectionString = new ConnectionString(
                "mongodb+srv://Becarios:Admin1234@cluster0.aq3pk.mongodb.net/test?retryWrites=true&w=majority");
        // Le pasamos la configuracion
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        // Creamos un cliente
        MongoClient mongoClient = MongoClients.create(settings);
        // Y accedemos a la base de datos
        MongoDatabase database = mongoClient.getDatabase("Disney");
        return database;
    }
}
