package com.becaries.MongoJava.MongoJava_ejemplo;

import java.util.ArrayList;

import com.becaries.MongoJava.modelos.Pelicula;
import com.becaries.MongoJava.modelos.Princesa;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

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

		// editarPelicula("6204183901e6db753d0bd2f7", "Blancanieves");
		// RELACIONES PRINCESAS
		// relacionPelicula("6204183901e6db753d0bd2f7", "6206999001e6db753d584343");//1
		// relacionPelicula("6204183901e6db753d0bd2f8", "6206999001e6db753d58432e");//2
		// relacionPelicula("6204183901e6db753d0bd2f9", "6206999001e6db753d584335");//3
		// relacionPelicula("6204183901e6db753d0bd2fa", "6206999001e6db753d58433a");//4
		// relacionPelicula("6204183901e6db753d0bd2fb", "6206b251ec50498c1ff2fd15");//5
		// relacionPelicula("6204183901e6db753d0bd2fc", "6206999001e6db753d58432d");//6
		// relacionPelicula("6204183901e6db753d0bd2fd", "6206999001e6db753d58432d");//7
		// relacionPelicula("6204183901e6db753d0bd2fe", "6206999001e6db753d58432f");//8
		// relacionPelicula("6204183901e6db753d0bd2ff", "6206999001e6db753d584342");//9
		// relacionPelicula("6204183901e6db753d0bd300", "6206999001e6db753d584344");//10
		// relacionPelicula("6204183901e6db753d0bd301", "6206999001e6db753d584348");//11
		// relacionPelicula("6204183901e6db753d0bd302", "6206b251ec50498c1ff2fd13");//12
		// relacionPelicula("6204183901e6db753d0bd303", "6206999001e6db753d584330");//13
		// relacionPelicula("6204183901e6db753d0bd304", "6206999001e6db753d584331");//14
		// relacionPelicula("6204183901e6db753d0bd304", "6206999001e6db753d584332");//14
		// relacionPelicula("6204183901e6db753d0bd305", "6206999001e6db753d58433c");//15
		// relacionPelicula("6204183901e6db753d0bd306", "6206999001e6db753d584340");//16
		// relacionPelicula("6204183901e6db753d0bd307", "6206999001e6db753d584333");//17
		// relacionPelicula("6204183901e6db753d0bd308", "6206999001e6db753d584336");//18
		// relacionPelicula("6204183901e6db753d0bd309", "6206999001e6db753d584330");//19
		// relacionPelicula("6204183901e6db753d0bd30a", "6206b251ec50498c1ff2fd14");//20
		// relacionPelicula("6204183901e6db753d0bd30b", "6206999001e6db753d584337");//21
		// relacionPelicula("6204183901e6db753d0bd30c", "6206999001e6db753d58433f");//22
		// relacionPelicula("6204183901e6db753d0bd30d", "6206999001e6db753d584338");//23
		// relacionPelicula("6204183901e6db753d0bd30e", "6206999001e6db753d584346");//24
		// relacionPelicula("6204183901e6db753d0bd30f", "6206999001e6db753d584345");//25
		// relacionPelicula("6204183901e6db753d0bd310", "6206999001e6db753d584345");//26
		// relacionPelicula("6204183901e6db753d0bd310", "6206999001e6db753d584331");//26
		// relacionPelicula("6204183901e6db753d0bd311", "6206999001e6db753d584341");//27
		// relacionPelicula("6204183901e6db753d0bd312", "6206999001e6db753d58433b");//28
		
		// RELACIONES VILLANOS
		relacionVillano("6204183901e6db753d0bd2f7", "6206b3c9ec50498c1ff2fd33");//1
		relacionVillano("6204183901e6db753d0bd2f8", "6206b3c9ec50498c1ff2fd20");//2
		relacionVillano("6204183901e6db753d0bd2f9", "6206b3c9ec50498c1ff2fd35");//3
		relacionVillano("6204183901e6db753d0bd2fa", "6206b3c9ec50498c1ff2fd32");//4
		relacionVillano("6204183901e6db753d0bd2fb", "6206b3c9ec50498c1ff2fd1e");//5
		relacionVillano("6204183901e6db753d0bd2fc", "6206b3c9ec50498c1ff2fd1a");//6
		relacionVillano("6204183901e6db753d0bd2fd", "6206b3c9ec50498c1ff2fd1a");//7
		relacionVillano("6204183901e6db753d0bd2fe", "6206b3c9ec50498c1ff2fd21");//8
		relacionVillano("6204183901e6db753d0bd2ff", "6206b3c9ec50498c1ff2fd1f");//9
		relacionVillano("6204183901e6db753d0bd300", "6206b3c9ec50498c1ff2fd27");//10
		relacionVillano("6204183901e6db753d0bd301", "6206b3c9ec50498c1ff2fd29");//11
		relacionVillano("6204183901e6db753d0bd302", "6206b3c9ec50498c1ff2fd28");//12
		relacionVillano("6204183901e6db753d0bd303", "6206999001e6db753d584330");//13
		relacionVillano("6204183901e6db753d0bd304", "6206b3c9ec50498c1ff2fd30");//14
		relacionVillano("6204183901e6db753d0bd304", "6206b3c9ec50498c1ff2fd20");//14
		relacionVillano("6204183901e6db753d0bd305", "6206b3c9ec50498c1ff2fd2c");//15
		relacionVillano("6204183901e6db753d0bd306", "6206b3c9ec50498c1ff2fd23");//16
		relacionVillano("6204183901e6db753d0bd307", "6206b3c9ec50498c1ff2fd19");//17
		relacionVillano("6204183901e6db753d0bd308", "6206b3c9ec50498c1ff2fd32");//18
		relacionVillano("6204183901e6db753d0bd309", "6206b3c9ec50498c1ff2fd30");//19
		relacionVillano("6204183901e6db753d0bd30a", "6206b3c9ec50498c1ff2fd34");//20
		relacionVillano("6204183901e6db753d0bd30b", "6206b3c9ec50498c1ff2fd2d");//21
		relacionVillano("6204183901e6db753d0bd30c", "6206b3c9ec50498c1ff2fd31");//22
		relacionVillano("6204183901e6db753d0bd30d", "6206b3c9ec50498c1ff2fd36");//23
		relacionVillano("6204183901e6db753d0bd30e", "6206b3c9ec50498c1ff2fd2e");//24
		relacionVillano("6204183901e6db753d0bd30f", "6206b3c9ec50498c1ff2fd1c");//25
		relacionVillano("6204183901e6db753d0bd310", "6206b3c9ec50498c1ff2fd22");//26
		relacionVillano("6204183901e6db753d0bd310", "6206b3c9ec50498c1ff2fd2b");//26
		relacionVillano("6204183901e6db753d0bd311", "6206b3c9ec50498c1ff2fd2a");//27
		relacionVillano("6204183901e6db753d0bd312", "6206b3c9ec50498c1ff2fd1b");//28
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
	private static void relacionVillano(String idPelicula, String idVillano) {
		Document findDocument2 = new Document("_id", new ObjectId(idVillano));
		Document updateDocument2 = new Document("$set",
				new Document("idPelicula", new ObjectId(idPelicula)));
		villano.updateMany(findDocument2, updateDocument2);
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
