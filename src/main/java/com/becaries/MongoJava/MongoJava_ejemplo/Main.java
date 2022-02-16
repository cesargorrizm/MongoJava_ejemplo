package com.becaries.MongoJava.MongoJava_ejemplo;

import java.util.ArrayList;

import com.becaries.MongoJava.modelos.Director;
import com.becaries.MongoJava.modelos.Pelicula;
import com.becaries.MongoJava.modelos.Princesa;
import com.becaries.MongoJava.modelos.Principe;
import com.becaries.MongoJava.modelos.Villano;
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
	public static MongoCollection<Document> director;

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
		// database.createCollection("Villano");

		pelicula = database.getCollection("Pelicula");
		princesa = database.getCollection("Princesa");
		villano = database.getCollection("Villano");
		principe = database.getCollection("Principe");
		director = database.getCollection("Director");

		System.out.println("MOSTRAR DATOS");
		System.out.println("	1. Mostrar peliculas");
		System.out.println("	2. Mostrar princesas");
		System.out.println("	3. Mostrar directores");
		System.out.println("	4. Mostrar villanos");
		System.out.println("	5. Mostrar director");
		System.out.println("EDITAR DATOS");
		System.out.println("	6. Editar peliculas");
		System.out.println("	7. Editar princesas");
		System.out.println("	8. Editar directores");
		System.out.println("	9. Editar villanos");
		System.out.println("	10. Editar director");
		System.out.println("BORRAR DATOS");
		System.out.println("	11. Borrar peliculas");
		System.out.println("	12. Borrar princesas");
		System.out.println("	13. Borrar directores");
		System.out.println("	14. Borrar villanos");
		System.out.println("	15. Borrar director");
		System.out.println("ACTUALIZAR DATOS");
		System.out.println("	16. Actualilzar peliculas");
		System.out.println("	17. Actualilzar princesas");
		System.out.println("	18. Actualilzar directores");
		System.out.println("	19. Actualilzar villanos");
		System.out.println("	20. Actualilzar director");
		System.out.println("VER TRAILER");
		System.out.println("	21. Ver trailer");

		// switch (key) {
		// case value:

		// break;

		// default:
		// break;
		// }
		// Mostrar Princesas
		// ArrayList<Princesa> princesas = leerPrincesa();
		// for (Princesa princesa : princesas) {
		// System.out.println(princesa);
		// }

		// MostrarPelicula
		// ArrayList<Pelicula> peliculas = leerPelicula();
		// for (Pelicula pelicula : peliculas) {
		// 	System.out.println(pelicula);
		// }

		//MostrarVillano
		// ArrayList<Villano> villanos = leerVillanos();
		// for (Villano villano : villanos) {
		// 	System.out.println(villano);
		// }

		// MostrarDirector
		// ArrayList<Director> directores = leerDirector();
		// for (Director d : directores) {
		// 	System.out.println(d);
		// }
		//MostrarPrincipe
		// ArrayList<Principe> principes = leerPrincipes();
		// for (Principe p : principes) {
		// 	System.out.println(p);
		// }

		// eliminar pelicula
		// eliminarPelicula("620d55f1d32ca8575431eff9");
		
	//System.out.println("*******************************************************INSERTAR*******************************************************************");
	Pelicula pelicula =new Pelicula("null", "titulo", "titulo", "ano",
		"duron", "p", "guon", "mua", "foafia", "reo", "s",
		"trer");
	editarPelicula("620d5799a4e64776d3058b13", pelicula);
	// insertarPrincesa(princesa);
	// Pelicula pelicula =new Pelicula("null", "titulo", "tituloOriginal", "ano",
	// 	"duracion", "pais", "guion", "musica", "fotografia", "reparto", "sinopsis",
	// 	"trailer");

	// 	insertarPelicula(pelicula);
	// Principe principe = new Principe("id", "nombre", "edad", "ciudad", "vehiculo", "email", "genero", "creacion", "ipAddress", "universidad", "titulacion");
	// 	insertarPrincipe(principe);
	// 	Villano villano = new Villano("id", "nombre", "edad", "ciudad", "vehiculo", "email", "genero", "creacion", "ipAddress", "universidad", "titulacion");
	// 	insertarVillano(villano);
	// 	Director director = new Director("id", "nombre", 2, "ciudad", "vehiculo", "email", "genero", "creacion", "ipAddress", "universidad", "titulacion");
	// 	insertarDirector(director);

		// leerPelicula();
		// Pelicula pelicula = new Pelicula("PruebaInsert", "Muerte y destruccion",
		// "2001",
		// "Es una prueba que estamos haciendo de vs", "1", "100", "1", "12554232");
				// insertarPelicula(pelicula);

		// eliminarPelicula("PruebaInsert");
		// mongoClient.close();

		// editarPelicula("6204183901e6db753d0bd2f7", "Blancanieves");

		// ArrayList<Princesa> f = leerPrincesa();
		// for (Princesa princesa : f) {
		// System.out.println(princesa);
		// }
	}

	

	private static void relacionPrincesa(String idPelicula, String idPrincesa) {
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

	private static void relacionPrincipe(String idPelicula, String idPrincipe) {
		Document findDocument = new Document("_id", new ObjectId(idPrincipe));
		Document updateDocument = new Document("$set",
				new Document("idPelicula", new ObjectId(idPelicula)));
		principe.updateMany(findDocument, updateDocument);
		System.out.println("Update ejecutado");
	}

	private static void relacionDirector(String idPelicula, String idDirector) {
		Document findDocument = new Document("_id", new ObjectId(idDirector));
		Document updateDocument = new Document("$set",
				new Document("idPelicula", new ObjectId(idPelicula)));
		director.updateMany(findDocument, updateDocument);
		System.out.println("Update ejecutado");
	}

	private static ArrayList<Princesa> leerPrincesa() {
		ArrayList<Princesa> listaprincesas = new ArrayList<Princesa>();
		MongoCursor<Document> resultDocument = princesa.find().iterator();

		while (resultDocument.hasNext()) {
			Document p = resultDocument.next();
			Princesa princesa = new Princesa(p.getObjectId("_id").toString(), p.getString("Nombre"),
					p.getString("Edad"), p.getString("Ciudad"), p.getString("Vehiculo"), p.getString("Email"),
					p.getString("Genero"), p.getString("Creacion"), p.getString("ip_address"),
					p.getString("Universidad"), p.getString("Titulacion"));
			listaprincesas.add(princesa);
		}

		return listaprincesas;
	}

	private static ArrayList<Pelicula> leerPelicula() {
		ArrayList<Pelicula> listapPeliculas = new ArrayList<Pelicula>();
		MongoCursor<Document> resultDocument = pelicula.find().iterator();

		while (resultDocument.hasNext()) {
			Document p = resultDocument.next();
			Pelicula pelicula = new Pelicula(p.getObjectId("_id").toString(), p.getString("Titulo"),
					p.getString("Titulo original"), p.getString("Año"), p.getString("Duración"), p.getString("País"),
					p.getString("Guion"), p.getString("Música"), p.getString("Fotografía"), p.getString("Reparto"),
					p.getString("Sinopsis"), p.getString("Trailer"));
			listapPeliculas.add(pelicula);
		}
		return listapPeliculas;

	}

	private static ArrayList<Principe> leerPrincipes() {
		ArrayList<Principe> listaPrincipe = new ArrayList<Principe>();
		MongoCursor<Document> resultDocument = principe.find().iterator();

		while (resultDocument.hasNext()) {
			Document p = resultDocument.next();
			Principe principe = new Principe(p.getObjectId("_id").toString(), p.getString("Nombre"),
					p.getString("Edad"), p.getString("Ciudad"), p.getString("Vehiculo"), p.getString("Email"),
					p.getString("Genero"), p.getString("Creacion"), p.getString("ip_address"),
					p.getString("Universidad"), p.getString("Titulacion"));
			listaPrincipe.add(principe);
		}

		return listaPrincipe;
	}

	private static ArrayList<Villano> leerVillanos() {
		ArrayList<Villano> listaVillano = new ArrayList<Villano>();
		MongoCursor<Document> resultDocument = villano.find().iterator();

		while (resultDocument.hasNext()) {
			Document p = resultDocument.next();
			Villano villano = new Villano(p.getObjectId("_id").toString(), p.getString("Nombre"),
					p.getString("Edad"), p.getString("Ciudad"), p.getString("Vehiculo"), p.getString("Email"),
					p.getString("Genero"), p.getString("Creacion"), p.getString("ip_address"),
					p.getString("Universidad"), p.getString("Titulacion"));
			listaVillano.add(villano);
		}

		return listaVillano;
	}

	private static ArrayList<Director> leerDirector() {
		ArrayList<Director> listaDirector = new ArrayList<Director>();
		MongoCursor<Document> resultDocument = director.find().iterator();

		while (resultDocument.hasNext()) {
			Document p = resultDocument.next();
			Director director = new Director(p.getObjectId("_id").toString(), p.getString("Director"),
					p.getInteger("Edad"), p.getString("Ciudad_nacimiento"), p.getString("Vehiculo"),
					p.getString("Email"),
					p.getString("Genero"), p.getString("Ip_movil"), p.getString("ip_address"),
					p.getString("Empresa"), p.getString("Titulacion"));
			listaDirector.add(director);
		}

		return listaDirector;
	}

	public static void insertarPrincesa(Princesa p) {
		princesa.insertOne(new Document()
				.append("_id", new ObjectId())
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
	}
	public static void insertarDirector(Director p) {
		director.insertOne(new Document()
				.append("_id", new ObjectId())
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
	}
	public static void insertarVillano(Villano p) {
		villano.insertOne(new Document()
				.append("_id", new ObjectId())
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
	}
	public static void insertarPrincipe(Principe p) {
		principe.insertOne(new Document()
				.append("_id", new ObjectId())
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
	}

	public static void insertarPelicula(Pelicula p) {
		pelicula.insertOne(new Document()
				.append("_id", new ObjectId())
				.append("Titulo", p.getTitulo())
				.append("Titulo original", p.getTituloOriginal())
				.append("Año", p.getAno())
				.append("Duración", p.getDuracion())
				.append("País", p.getPais())
				.append("Guion", p.getGuion())
				.append("Música", p.getMusica())
				.append("Fotografía", p.getFotografia())
				.append("Reparto", p.getReparto())
				.append("Sinopsis", p.getSinopsis())
				.append("Trailer", p.getTrailer()));
	}

	public static void eliminarPelicula(String id) {
		Document findDocument = new Document("_id", new ObjectId(id));
		pelicula.deleteMany(findDocument);
	}
	public static void eliminarDirector(String id) {
		Document findDocument = new Document("_id", new ObjectId(id));
		director.deleteMany(findDocument);
	}
	public static void elimnarPrincipe(String id) {
		Document findDocument = new Document("_id", new ObjectId(id));
		principe.deleteMany(findDocument);
	}
	
	public static void eliminarPrincesa(String id) {
		Document findDocument = new Document("_id", new ObjectId(id));
		princesa.deleteMany(findDocument);
	}
	
	public static void eliminarVillano(String id) {
		Document findDocument = new Document("_id", new ObjectId(id));
		villano.deleteMany(findDocument);
	}

	public static void editarPelicula(String id, Pelicula p) {
		Document findDocument2 = new Document("_id", new ObjectId(id));

		Document updateDocument2 = new Document("$set",
				new Document()
				.append("Titulo", p.getTitulo())
				.append("Titulo original", p.getTituloOriginal())
				.append("Año", p.getAno())
				.append("Duración", p.getDuracion())
				.append("País", p.getPais())
				.append("Guion", p.getGuion())
				.append("Música", p.getMusica())
				.append("Fotografía", p.getFotografia())
				.append("Reparto", p.getReparto())
				.append("Sinopsis", p.getSinopsis())
				.append("Trailer", p.getTrailer()));
		pelicula.updateMany(findDocument2, updateDocument2);
		System.out.println("Update ejecutado");
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
	private static void establecerRelaciones() {
		// RELACIONES PRINCESAS
		relacionPrincesa("620d3e6e41d00a35312038e3", "620d3e1241d00a353120388b");// 1
		relacionPrincesa("620d3e6e41d00a35312038e4", "620d3e1241d00a353120388c");// 2
		relacionPrincesa("620d3e6e41d00a35312038e5", "620d3e1241d00a353120388d");// 3
		relacionPrincesa("620d3e6e41d00a35312038e6", "620d3e1241d00a353120388e");// 4
		relacionPrincesa("620d3e6e41d00a35312038e7", "620d3e1241d00a353120388f");// 5
		relacionPrincesa("620d3e6e41d00a35312038e8", "620d3e1241d00a3531203890");// 6
		relacionPrincesa("620d3e6e41d00a35312038e9", "620d3e1241d00a3531203891");// 7
		relacionPrincesa("620d3e6e41d00a35312038ea", "620d3e1241d00a3531203892");// 8
		relacionPrincesa("620d3e6e41d00a35312038eb", "620d3e1241d00a3531203893");// 9
		relacionPrincesa("620d3e6e41d00a35312038ec", "620d3e1241d00a3531203894");// 10
		relacionPrincesa("620d3e6e41d00a35312038ed", "620d3e1241d00a3531203895");// 11
		relacionPrincesa("620d3e6e41d00a35312038ee", "620d3e1241d00a3531203896");// 12
		relacionPrincesa("620d3e6e41d00a35312038ef", "620d3e1241d00a3531203897");// 13
		relacionPrincesa("620d3e6e41d00a35312038f0", "620d3e1241d00a3531203898");// 14
		relacionPrincesa("620d3e6e41d00a35312038f1", "620d3e1241d00a3531203899");// 15
		relacionPrincesa("620d3e6e41d00a35312038f2", "620d3e1241d00a353120389a");// 16
		relacionPrincesa("620d3e6e41d00a35312038f3", "620d3e1241d00a353120389b");// 17
		relacionPrincesa("620d3e6e41d00a35312038f4", "620d3e1241d00a353120389c");// 18
		relacionPrincesa("620d3e6e41d00a35312038f5", "620d3e1241d00a353120389d");// 19
		relacionPrincesa("620d3e6e41d00a35312038f6", "620d3e1241d00a353120389e");// 20
		relacionPrincesa("620d3e6e41d00a35312038f7", "620d3e1241d00a353120389f");// 21
		relacionPrincesa("620d3e6e41d00a35312038f8", "620d3e1241d00a35312038a0");// 22
		relacionPrincesa("620d3e6e41d00a35312038f9", "620d3e1241d00a35312038a1");// 23
		relacionPrincesa("620d3e6e41d00a35312038fa", "620d3e1241d00a35312038a2");// 24
		relacionPrincesa("620d3e6e41d00a35312038fb", "620d3e1241d00a35312038a3");// 25
		relacionPrincesa("620d3e6e41d00a35312038fc", "620d3e1241d00a35312038a4");// 26

		// RELACIONES VILLANOS
		relacionVillano("620d3e6e41d00a35312038e3", "620d3e3841d00a35312038c3");// 1
		relacionVillano("620d3e6e41d00a35312038e4", "620d3e3841d00a35312038c4");// 2
		relacionVillano("620d3e6e41d00a35312038e5", "620d3e3841d00a35312038c5");// 3
		relacionVillano("620d3e6e41d00a35312038e6", "620d3e3841d00a35312038c6");// 4
		relacionVillano("620d3e6e41d00a35312038e7", "620d3e3841d00a35312038c7");// 5
		relacionVillano("620d3e6e41d00a35312038e8", "620d3e3841d00a35312038c8");// 6
		relacionVillano("620d3e6e41d00a35312038e9", "620d3e3841d00a35312038c9");// 7
		relacionVillano("620d3e6e41d00a35312038ea", "620d3e3841d00a35312038ca");// 8
		relacionVillano("620d3e6e41d00a35312038eb", "620d3e3841d00a35312038cb");// 9
		relacionVillano("620d3e6e41d00a35312038ec", "620d3e3841d00a35312038cc");// 10
		relacionVillano("620d3e6e41d00a35312038ed", "620d3e3841d00a35312038cd");// 11
		relacionVillano("620d3e6e41d00a35312038ee", "620d3e3841d00a35312038ce");// 12
		relacionVillano("620d3e6e41d00a35312038ef", "620d3e3841d00a35312038cf");// 13
		relacionVillano("620d3e6e41d00a35312038f0", "620d3e3841d00a35312038d0");// 14
		relacionVillano("620d3e6e41d00a35312038f1", "620d3e3841d00a35312038d1");// 15
		relacionVillano("620d3e6e41d00a35312038f2", "620d3e3841d00a35312038d2");// 16
		relacionVillano("620d3e6e41d00a35312038f3", "620d3e3841d00a35312038d3");// 17
		relacionVillano("620d3e6e41d00a35312038f4", "620d3e3841d00a35312038d4");// 18
		relacionVillano("620d3e6e41d00a35312038f5", "620d3e3841d00a35312038d5");// 19
		relacionVillano("620d3e6e41d00a35312038f6", "620d3e3841d00a35312038d6");// 20
		relacionVillano("620d3e6e41d00a35312038f7", "620d3e3841d00a35312038d7");// 21
		relacionVillano("620d3e6e41d00a35312038f8", "620d3e3841d00a35312038d8");// 22
		relacionVillano("620d3e6e41d00a35312038f9", "620d3e3841d00a35312038d9");// 23
		relacionVillano("620d3e6e41d00a35312038fa", "620d3e3841d00a35312038da");// 24
		relacionVillano("620d3e6e41d00a35312038fb", "620d3e3841d00a35312038db");// 25
		relacionVillano("620d3e6e41d00a35312038fc", "620d3e3841d00a35312038dc");// 26

		// Relacion Principes
		relacionPrincipe("620d3e6e41d00a35312038e3", "620d3e2a41d00a35312038a7");// 1
		relacionPrincipe("620d3e6e41d00a35312038e4", "620d3e2a41d00a35312038a8");// 2
		relacionPrincipe("620d3e6e41d00a35312038e5", "620d3e2a41d00a35312038a9");// 3
		relacionPrincipe("620d3e6e41d00a35312038e6", "620d3e2a41d00a35312038aa");// 4
		relacionPrincipe("620d3e6e41d00a35312038e7", "620d3e2a41d00a35312038aa");// 5
		relacionPrincipe("620d3e6e41d00a35312038e8", "620d3e2a41d00a35312038ac");// 6
		relacionPrincipe("620d3e6e41d00a35312038e9", "620d3e2a41d00a35312038ad");// 7
		relacionPrincipe("620d3e6e41d00a35312038ea", "620d3e2a41d00a35312038ae");// 8
		relacionPrincipe("620d3e6e41d00a35312038eb", "620d3e2a41d00a35312038af");// 9
		relacionPrincipe("620d3e6e41d00a35312038ec", "620d3e2a41d00a35312038b0");// 10
		relacionPrincipe("620d3e6e41d00a35312038ed", "620d3e2a41d00a35312038b1");// 11
		relacionPrincipe("620d3e6e41d00a35312038ee", "620d3e2a41d00a35312038b2");// 12
		relacionPrincipe("620d3e6e41d00a35312038ef", "620d3e2a41d00a35312038b3");// 13
		relacionPrincipe("620d3e6e41d00a35312038f0", "620d3e2a41d00a35312038b4");// 14
		relacionPrincipe("620d3e6e41d00a35312038f1", "620d3e2a41d00a35312038b5");// 15
		relacionPrincipe("620d3e6e41d00a35312038f2", "620d3e2a41d00a35312038b6");// 16
		relacionPrincipe("620d3e6e41d00a35312038f3", "620d3e2a41d00a35312038b7");// 17
		relacionPrincipe("620d3e6e41d00a35312038f4", "620d3e2a41d00a35312038b8");// 18
		relacionPrincipe("620d3e6e41d00a35312038f5", "620d3e2a41d00a35312038b9");// 19
		relacionPrincipe("620d3e6e41d00a35312038f6", "620d3e2a41d00a35312038ba");// 20
		relacionPrincipe("620d3e6e41d00a35312038f7", "620d3e2a41d00a35312038bb");// 21
		relacionPrincipe("620d3e6e41d00a35312038f8", "620d3e2a41d00a35312038bc");// 22
		relacionPrincipe("620d3e6e41d00a35312038f9", "620d3e2a41d00a35312038bd");// 23
		relacionPrincipe("620d3e6e41d00a35312038fa", "620d3e2a41d00a35312038be");// 24
		relacionPrincipe("620d3e6e41d00a35312038fb", "620d3e2a41d00a35312038bf");// 25
		relacionPrincipe("620d3e6e41d00a35312038fc", "620d3e2a41d00a35312038c0");// 26

		// Relacion directores
		relacionDirector("620d3e6e41d00a35312038e3", "62093c2b6ed0cbd82eee0319");// 1
		relacionDirector("620d3e6e41d00a35312038e4", "62093c2b6ed0cbd82eee031a");// 2
		relacionDirector("620d3e6e41d00a35312038e5", "62093c2b6ed0cbd82eee031b");// 3
		relacionDirector("620d3e6e41d00a35312038e6", "62093c2b6ed0cbd82eee031c");// 4
		relacionDirector("620d3e6e41d00a35312038e7", "62093c2b6ed0cbd82eee031d");// 5
		relacionDirector("620d3e6e41d00a35312038e8", "62093c2b6ed0cbd82eee031e");// 6
		relacionDirector("620d3e6e41d00a35312038e9", "62093c2b6ed0cbd82eee031f");// 7
		relacionDirector("620d3e6e41d00a35312038ea", "62093c2b6ed0cbd82eee0320");// 8
		relacionDirector("620d3e6e41d00a35312038eb", "62093c2b6ed0cbd82eee0321");// 9
		relacionDirector("620d3e6e41d00a35312038ec", "62093c2b6ed0cbd82eee0322");// 10
		relacionDirector("620d3e6e41d00a35312038ed", "62093c2b6ed0cbd82eee0323");// 11
		relacionDirector("620d3e6e41d00a35312038ee", "62093c2b6ed0cbd82eee0324");// 12
		relacionDirector("620d3e6e41d00a35312038ef", "62093c2b6ed0cbd82eee0325");// 13
		relacionDirector("620d3e6e41d00a35312038f0", "62093c2b6ed0cbd82eee0326");// 14
		relacionDirector("620d3e6e41d00a35312038f1", "62093c2b6ed0cbd82eee0327");// 15
		relacionDirector("620d3e6e41d00a35312038f2", "62093c2b6ed0cbd82eee0328");// 16
		relacionDirector("620d3e6e41d00a35312038f3", "62093c2b6ed0cbd82eee0329");// 17
		relacionDirector("620d3e6e41d00a35312038f4", "62093c2b6ed0cbd82eee032a");// 18
		relacionDirector("620d3e6e41d00a35312038f5", "62093c2b6ed0cbd82eee032b");// 19
		relacionDirector("620d3e6e41d00a35312038f6", "62093c2b6ed0cbd82eee032c");// 20
		relacionDirector("620d3e6e41d00a35312038f7", "62093c2b6ed0cbd82eee032d");// 21
		relacionDirector("620d3e6e41d00a35312038f8", "62093c2b6ed0cbd82eee032e");// 22
		relacionDirector("620d3e6e41d00a35312038f9", "62093c2b6ed0cbd82eee032f");// 23
		relacionDirector("620d3e6e41d00a35312038fa", "62093c2b6ed0cbd82eee0330");// 24
		relacionDirector("620d3e6e41d00a35312038fb", "62093c2b6ed0cbd82eee0331");// 25
		relacionDirector("620d3e6e41d00a35312038fc", "62093c2b6ed0cbd82eee0332");// 26
	}
}
