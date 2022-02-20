package com.becaries.MongoJava.Clases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.becaries.MongoJava.modelos.Director;
import com.becaries.MongoJava.modelos.Pelicula;
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
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\033[1;91m";
	public static String ANSI_TEMA = "";

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

	public static void menuModificarPrincesa() {
		System.out.println(ANSI_TEMA + " _____________________________");
		System.out.println("|                            |");
		System.out.println("|       EDITAR PRINCESA      |");
		System.out.println(" ____________________________" + ANSI_RESET);
		ArrayList<Princesa> princesas = Logica.leerPrincesa();
		for (int i = 0; i < princesas.size(); i++) {
			System.out.println(i + ". " + princesas.get(i).getNombre() + " version " + princesas.get(i).getCreacion());
		}
		Scanner sc = new Scanner(System.in);
		System.out.print("Escoge la princesa que quieres editar: ");
		String num = sc.nextLine();
		Princesa princesa = princesas.get(Integer.parseInt(num));
		String menup = "1. Nombre\n2. Edad\n3. Ciudad\n4. Vehiculo\n5. Email\n6. Genero\n7. Creacion\n8. ip_address\n9. Universidad\n10.Titulacion";
		System.out.println(menup);
		System.out.print("Escoge el campo que quieres cambiar: ");
		String resul = sc.nextLine();
		switch (resul) {
			case "1":
				String n = sc.nextLine();
				princesa.setNombre(n);
				editarPricensa(princesa.getId(), princesa);
				break;
			case "2":
				String e = sc.nextLine();
				princesa.setEdad(e);
				editarPricensa(princesa.getId(), princesa);
				break;
			case "3":
				String ciu = sc.nextLine();
				princesa.setCiudad(ciu);
				editarPricensa(princesa.getId(), princesa);
				break;
			case "4":
				String vei = sc.nextLine();
				princesa.setVehiculo(vei);
				editarPricensa(princesa.getId(), princesa);
				break;
			case "5":
				String emai = sc.nextLine();
				princesa.setEmail(emai);
				editarPricensa(princesa.getId(), princesa);
				break;
			case "6":
				String genero = sc.nextLine();
				princesa.setGenero(genero);
				editarPricensa(princesa.getId(), princesa);
				break;
			case "7":
				String creacion = sc.nextLine();
				princesa.setCreacion(creacion);
				editarPricensa(princesa.getId(), princesa);
				break;
			case "8":
				String ipAddress = sc.nextLine();
				princesa.setIpAddress(ipAddress);
				editarPricensa(princesa.getId(), princesa);
				break;
			case "9":
				String universidad = sc.nextLine();
				princesa.setUniversidad(universidad);
				editarPricensa(princesa.getId(), princesa);
				break;
			case "10":
				String titulacion = sc.nextLine();
				princesa.setTitulacion(titulacion);
				editarPricensa(princesa.getId(), princesa);
				break;
			default:
				break;
		}

	}

	public static void menuModificarPrincipe() {
		System.out.println(ANSI_TEMA + " _____________________________");
		System.out.println("|                            |");
		System.out.println("|       EDITAR PRINCIPE      |");
		System.out.println(" ____________________________" + ANSI_RESET);
		ArrayList<Principe> principes = Logica.leerPrincipes();
		for (int i = 0; i < principes.size(); i++) {
			System.out.println(i + ". " + principes.get(i).getNombre() + " version " + principes.get(i).getCreacion());
		}
		Scanner sc = new Scanner(System.in);
		System.out.print("Escoge la principe que quieres editar: ");
		String num = sc.nextLine();
		Principe principe = principes.get(Integer.parseInt(num));
		String menup = "1. Nombre\n2. Edad\n3. Ciudad\n4. Vehiculo\n5. Email\n6. Genero\n7. Creacion\n8. ip_address\n9. Universidad\n10.Titulacion";
		System.out.println(menup);
		System.out.print("Escoge el campo que quieres cambiar: ");
		String resul = sc.nextLine();
		switch (resul) {
			case "1":
				String n = sc.nextLine();
				principe.setNombre(n);
				editarPrincipe(principe.getId(), principe);
				break;
			case "2":
				String e = sc.nextLine();
				principe.setEdad(e);
				editarPrincipe(principe.getId(), principe);
				break;
			case "3":
				String ciu = sc.nextLine();
				principe.setCiudad(ciu);
				editarPrincipe(principe.getId(), principe);
				break;
			case "4":
				String vei = sc.nextLine();
				principe.setVehiculo(vei);
				editarPrincipe(principe.getId(), principe);
				break;
			case "5":
				String emai = sc.nextLine();
				principe.setEmail(emai);
				editarPrincipe(principe.getId(), principe);
				break;
			case "6":
				String genero = sc.nextLine();
				principe.setGenero(genero);
				editarPrincipe(principe.getId(), principe);
				break;
			case "7":
				String creacion = sc.nextLine();
				principe.setCreacion(creacion);
				editarPrincipe(principe.getId(), principe);
				break;
			case "8":
				String ipAddress = sc.nextLine();
				principe.setIpAddress(ipAddress);
				editarPrincipe(principe.getId(), principe);
				break;
			case "9":
				String universidad = sc.nextLine();
				principe.setUniversidad(universidad);
				editarPrincipe(principe.getId(), principe);
				break;
			case "10":
				String titulacion = sc.nextLine();
				principe.setTitulacion(titulacion);
				editarPrincipe(principe.getId(), principe);
				break;
			default:
				break;
		}

	}

	public static void menuModificarVillano() {
		System.out.println(ANSI_TEMA + " _____________________________");
		System.out.println("|                            |");
		System.out.println("|       EDITAR VILLANO       |");
		System.out.println(" ____________________________" + ANSI_RESET);
		ArrayList<Villano> villano = Logica.leerVillanos();
		for (int i = 0; i < villano.size(); i++) {
			System.out.println(i + ". " + villano.get(i).getNombre() + " version " + villano.get(i).getCreacion());
		}
		Scanner sc = new Scanner(System.in);
		System.out.print("Escoge la villano que quieres editar: ");
		String num = sc.nextLine();
		Villano v = villano.get(Integer.parseInt(num));
		String menup = "1. Nombre\n2. Edad\n3. Ciudad\n4. Vehiculo\n5. Email\n6. Genero\n7. Creacion\n8. ip_address\n9. Universidad\n10.Titulacion";
		System.out.println(menup);
		System.out.print("Escoge el campo que quieres cambiar: ");
		String resul = sc.nextLine();
		switch (resul) {
			case "1":
				String n = sc.nextLine();
				v.setNombre(n);
				editarVillano(v.getId(), v);
				break;
			case "2":
				String e = sc.nextLine();
				v.setEdad(e);
				editarVillano(v.getId(), v);
				break;
			case "3":
				String ciu = sc.nextLine();
				v.setCiudad(ciu);
				editarVillano(v.getId(), v);
				break;
			case "4":
				String vei = sc.nextLine();
				v.setVehiculo(vei);
				editarVillano(v.getId(), v);
				break;
			case "5":
				String emai = sc.nextLine();
				v.setEmail(emai);
				editarVillano(v.getId(), v);
				break;
			case "6":
				String genero = sc.nextLine();
				v.setGenero(genero);
				editarVillano(v.getId(), v);
				break;
			case "7":
				String creacion = sc.nextLine();
				v.setCreacion(creacion);
				editarVillano(v.getId(), v);
				break;
			case "8":
				String ipAddress = sc.nextLine();
				v.setIpAddress(ipAddress);
				editarVillano(v.getId(), v);
				break;
			case "9":
				String universidad = sc.nextLine();
				v.setUniversidad(universidad);
				editarVillano(v.getId(), v);
				break;
			case "10":
				String titulacion = sc.nextLine();
				v.setTitulacion(titulacion);
				editarVillano(v.getId(), v);
				break;
			default:
				break;
		}

	}
	public static void menuModificarPelicula() {
		System.out.println(ANSI_TEMA + " _____________________________");
		System.out.println("|                            |");
		System.out.println("|       EDITAR PELICULA      |");
		System.out.println(" ____________________________" + ANSI_RESET);
		ArrayList<Pelicula> pelicula = Logica.leerPelicula();
		for (int i = 0; i < pelicula.size(); i++) {
			System.out.println(i + ". " + pelicula.get(i).getTitulo() + ": Año de estreno " + pelicula.get(i).getAno());
		}
		Scanner sc = new Scanner(System.in);
		System.out.print("Escoge la villano que quieres editar: ");
		String num = sc.nextLine();
		Pelicula peli = pelicula.get(Integer.parseInt(num));
		String menup = "1. Tiltulo\n2. Titulo original\n3. Año de estreno\n4. Duración\n5. Pais de rodaje\n6. Guion\n7. Música\n8. Fotografia\n9. Reparto\n10.Sinopsis";
		System.out.println(menup);
		System.out.print("Escoge el campo que quieres cambiar: ");
		String resul = sc.nextLine();
		switch (resul) {
			case "1":
				String n = sc.nextLine();
				peli.setTitulo(n);
				editarPelicula(peli.getId(), peli);
				break;
			case "2":
				String tit = sc.nextLine();
				peli.setTituloOriginal(tit);
				editarPelicula(peli.getId(), peli);
				break;
			case "3":
				String ciu = sc.nextLine();
				peli.setAno(ciu);
				editarPelicula(peli.getId(), peli);
				break;
			case "4":
				String vei = sc.nextLine();
				peli.setDuracion(vei);
				editarPelicula(peli.getId(), peli);
				break;
			case "5":
				String emai = sc.nextLine();
				peli.setPais(emai);
				editarPelicula(peli.getId(), peli);
				break;
			case "6":
				String genero = sc.nextLine();
				peli.setGuion(genero);
				editarPelicula(peli.getId(), peli);
				break;
			case "7":
				String creacion = sc.nextLine();
				peli.setMusica(creacion);
				editarPelicula(peli.getId(), peli);
				break;
			case "8":
				String ipAddress = sc.nextLine();
				peli.setFotografia(ipAddress);
				editarPelicula(peli.getId(), peli);
				break;
			case "9":
				String universidad = sc.nextLine();
				peli.setReparto(universidad);
				editarPelicula(peli.getId(), peli);
				break;
			case "10":
				String titulacion = sc.nextLine();
				peli.setSinopsis(titulacion);
				editarPelicula(peli.getId(), peli);
				break;
			default:
				break;
		}

	}
	public static void menuModificarDirector() {
		System.out.println(ANSI_TEMA + " _____________________________");
		System.out.println("|                            |");
		System.out.println("|       EDITAR DIRECTOR      |");
		System.out.println(" ____________________________" + ANSI_RESET);
		ArrayList<Director> directores = Logica.leerDirector();
		for (int i = 0; i < directores.size(); i++) {
			System.out.println(i + ". " + directores.get(i).getDirector());
		}
		Scanner sc = new Scanner(System.in);
		System.out.print("Escoge la director que quieres editar: ");
		String num = sc.nextLine();
		Director d = directores.get(Integer.parseInt(num));
		String menup = "1. Nombre\n2. Edad\n3. Ciudad nacimiento\n4. Vehiculo\n5. Email\n6. Genero\n7. ip_movi\n8. ip_address\n9. Empresa\n10.Titulacion";
		System.out.println(menup);
		System.out.print("Escoge el campo que quieres cambiar: ");
		String resul = sc.nextLine();
		switch (resul) {
			case "1":
				String n = sc.nextLine();
				d.setDirector(n);
				editarDirector(d.getId(), d);
				break;
			case "2":
				String e = sc.nextLine();
				d.setEdad(Integer.parseInt(e));
				editarDirector(d.getId(), d);
				break;
			case "3":
				String ciu = sc.nextLine();
				d.setCiudadNacimiento(ciu);
				editarDirector(d.getId(), d);
				break;
			case "4":
				String vei = sc.nextLine();
				d.setVehiculo(vei);
				editarDirector(d.getId(), d);
				break;
			case "5":
				String emai = sc.nextLine();
				d.setEmail(emai);
				editarDirector(d.getId(), d);
				break;
			case "6":
				String genero = sc.nextLine();
				d.setGenero(genero);
				editarDirector(d.getId(), d);
				break;
			case "7":
				String creacion = sc.nextLine();
				d.setIpMovil(creacion);
				editarDirector(d.getId(), d);
				break;
			case "8":
				String ipAddress = sc.nextLine();
				d.setIpAddress(ipAddress);
				editarDirector(d.getId(), d);
				break;
			case "9":
				String universidad = sc.nextLine();
				d.setEmpresa(universidad);
				editarDirector(d.getId(), d);
				break;
			case "10":
				String titulacion = sc.nextLine();
				d.setTitulacion(titulacion);
				editarDirector(d.getId(), d);
				break;
			default:
				break;
		}

	}

	static void editarOpcines(Scanner sc) {
		Logica.limpiarConsola();
		boolean a2 = true;
		while (a2) {
			System.out.println("");
			System.out.println(Logica.ANSI_TEMA + " __________________________");
			System.out.println("|                         |");
			System.out.println("|           MENU          |");
			System.out.println(" __________________________" + Logica.ANSI_RESET);
			System.out.println(" __________________________");
			System.out.println("|                          |");
			System.out.println("| 1. Editar pelicula       |");
			System.out.println("| 2. Editar princesa       |");
			System.out.println("| 3. Editar director       |");
			System.out.println("| 4. Editar villano        |");
			System.out.println("| 5. Editar principe       |");
			System.out.println("| 0. Atras                 |");
			System.out.println(" __________________________");
			System.out.println("¿Que quieres hacer?");
			String opcion1 = sc.nextLine();
			switch (opcion1) {
				case "1":
					Logica.limpiarConsola();

					Logica.volverAtras();
					break;
				case "2":
					Logica.limpiarConsola();
					menuModificarPrincesa();
					Logica.volverAtras();
					break;
				case "3":
					Logica.limpiarConsola();

					Logica.volverAtras();
					break;
				case "4":
					Logica.limpiarConsola();

					Logica.volverAtras();
					break;
				case "5":
					Logica.limpiarConsola();

					Logica.volverAtras();
					break;
				case "0":
					Logica.limpiarConsola();
					a2 = false;
					break;

				default:
					System.out.println("Has introducido una opcion que no existe :(");
					break;
			}
		}
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

}
