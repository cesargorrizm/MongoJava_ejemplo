package com.becaries.MongoJava.Clases;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.becaries.MongoJava.modelos.Director;
import com.becaries.MongoJava.modelos.Pelicula;
import com.becaries.MongoJava.modelos.Princesa;
import com.becaries.MongoJava.modelos.Principe;
import com.becaries.MongoJava.modelos.Usuario;
import com.becaries.MongoJava.modelos.Villano;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class Logica {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\033[1;91m";
	public static String ANSI_TEMA = "";

	public static void inicioSesion() {

		System.out.println(" _________________________");
		System.out.println("|                         |");
		System.out.println("|      ELECCION TEMA      |");
		System.out.println(" _________________________");
		System.out.println("");
		System.out.println("Antes de empezar, ¿que color prefieres?");
		ANSI_TEMA = "\033[1;94m";
		System.out.println(ANSI_TEMA + "1. azul" + ANSI_RESET);
		ANSI_TEMA = "\u001B[35m";
		System.out.println(ANSI_TEMA + "2. morado" + ANSI_RESET);
		ANSI_TEMA = "\u001b[36;1m";
		System.out.println(ANSI_TEMA + "3. cian" + ANSI_RESET);
		ANSI_TEMA = "\u001b[32m";
		System.out.println(ANSI_TEMA + "4. verde" + ANSI_RESET);
		ANSI_TEMA = "\u001b[33;1m";
		System.out.println(ANSI_TEMA + "5. amarillo" + ANSI_RESET);
		ANSI_TEMA = "\u001b[35;1m";
		System.out.println(ANSI_TEMA + "6. rosa" + ANSI_RESET);

		Scanner color = new Scanner(System.in);
		String colorEscogido = color.nextLine();
		if (colorEscogido.equals("1")) {
			System.out.println("¡Se establecio el tema azul con exito!");
			ANSI_TEMA = "\033[1;94m";
		} else if (colorEscogido.equals("2")) {
			ANSI_TEMA = "\u001B[35m";
			System.out.println("¡Se establecio el tema morado con exito!");
		} else if (colorEscogido.equals("3")) {
			ANSI_TEMA = "\u001b[36;1m";
			System.out.println("¡Se establecio el tema cian con exito!");
		} else if (colorEscogido.equals("4")) {
			ANSI_TEMA = "\u001b[32m";
			System.out.println("¡Se establecio el tema verde con exito!");
		} else if (colorEscogido.equals("5")) {
			ANSI_TEMA = "\u001b[33;1m";
			System.out.println("¡Se establecio el tema amarillo con exito!");
		} else if (colorEscogido.equals("6")) {
			ANSI_TEMA = "\u001b[35;1m";
			System.out.println("¡Se establecio el tema rosa con exito!");
		} else {
			System.out.println("¡Al no exitir la opcion, se establecera el blanco!");
		}
		System.out.println("");

		boolean salir = true;
		int i = 0;
		while (salir) {
			if (Main.BOOLEAN) {
				Logica.limpiarConsola();
				System.out.println("¡Se ha cerrado sesion!");
				Main.BOOLEAN = false;
			}
			System.out.println(ANSI_TEMA + " _______________________");
			System.out.println("|                       |");
			System.out.println("| ¡BIENVENIDO A PELNEY! |");
			System.out.println(" _______________________ " + ANSI_RESET);
			System.out.println(" _______________________");
			System.out.println("|                       |");
			System.out.println("| 1. Iniciar sesion     |");
			System.out.println("| 2. Registrarme        |");
			System.out.println("| 0. Salir              |");
			System.out.println(" _______________________");
			System.out.println("¿Que quieres hacer?");
			Console console = System.console();
			Scanner sc = new Scanner(System.in);
			String ir = sc.nextLine();
			switch (ir) {
				case "1":
					boolean b = true;
					while (b) {
						System.out.println("");
						System.out.println(ANSI_TEMA + " ___________________");
						System.out.println("|                   |");
						System.out.println("|  INICIAR SESION   |");
						System.out.println(" ___________________" + ANSI_RESET);
						System.out.println("");
						System.out.print("Indica tu usuario: ");
						String nom = sc.nextLine();

						char[] passwordArray = console.readPassword("Escribe tu contraseña: ");

						Document findDocument = new Document("Usuario", nom);
						MongoCursor<Document> us = Main.usuario.find(findDocument).iterator();
						String dato1 = "";
						String dato2 = "";
						while (us.hasNext()) {
							Document p = us.next();
							dato1 = p.getString("Password");
							dato2 = p.getString("Rol");
						}
						if (dato1.equals(Main.en.ecnode((new String(passwordArray))))) {

							if (dato2.equals("admin")) {
								Logica.limpiarConsola();
								System.out.println("Bienvenido/a " + nom + "! ");
								b = false;
								Logica.menu(sc);
							} else {
								Logica.limpiarConsola();
								System.out.println("Bienvenido/a " + nom + "! ");
								b = false;
								Logica.menuUser(sc);
							}
						} else {
							System.out.println(
									ANSI_RED + "Datos incorrectos: te quedan " + (2 - i) + "  intentos" + ANSI_RESET);
							i++;
							if (i == 3) {
								System.out.println("Has sobrepasado los limites don/doña!!");
								Imagen.EnredadosKgaste();
								b = false;
								salir = false;
							}
						}
					}
					break;
				case "2":
					Logica.limpiarConsola();
					System.out.println("");
					System.out.println(ANSI_TEMA + " ___________________");
					System.out.println("|                   |");
					System.out.println("|      REGISTRO     |");
					System.out.println(" ___________________" + ANSI_RESET);
					System.out.println("");
					Logica.registroUser(sc, console);
					volverAtras();
					break;
				case "0":
					Logica.limpiarConsola();
					salir = false;
					Imagen.adiosAriel();
					break;

				default:
					break;
			}
		}

	}

	public static void imprimirTablaPrincipes() {
		ArrayList<Principe> principes = Logica.leerPrincipes();
		List<String> headersListprincipe = new ArrayList<>();
		headersListprincipe.add("Nombre");
		headersListprincipe.add("Edad");
		headersListprincipe.add("Ciudad");
		headersListprincipe.add("Vehiculo");
		headersListprincipe.add("Email");
		headersListprincipe.add("Genero");
		headersListprincipe.add("Creacion");
		List<List<String>> rowsListprincipe = new ArrayList<>();
		for (Principe principe : principes) {
			List<String> row = new ArrayList<>();
			row.add(principe.getNombre());
			row.add(principe.getEdad());
			row.add(principe.getCiudad());
			row.add(principe.getVehiculo());
			row.add(principe.getEmail());
			row.add(principe.getGenero());
			row.add(principe.getCreacion());
			rowsListprincipe.add(row);
		}
		Logica.limpiarConsola();
		System.out.println(GenerarTabla.generateTable(headersListprincipe, rowsListprincipe));
		System.out.flush();

		List<String> headersListprincipe1 = new ArrayList<>();
		headersListprincipe1.add("Ip_address");
		headersListprincipe1.add("Universidad");
		headersListprincipe1.add("Titulacion");
		List<List<String>> rowsListprincipe1 = new ArrayList<>();
		for (Principe princesa : principes) {
			List<String> row = new ArrayList<>();
			row.add(princesa.getIpAddress());
			row.add(princesa.getUniversidad());
			row.add(princesa.getTitulacion());
			rowsListprincipe1.add(row);
		}
		System.out.println(GenerarTabla.generateTable(headersListprincipe1, rowsListprincipe1));
		System.out.flush();
	}

	static void menuUser(Scanner sc) {
		boolean b = true;
		while (b) {
			System.out.println("");
			System.out.println(ANSI_TEMA + " _____________________");
			System.out.println("|                     |");
			System.out.println("|         MENU        |");
			System.out.println(" _____________________" + ANSI_RESET);
			System.out.println(" _____________________");
			System.out.println("|                     |");
			System.out.println("| 1. MOSTRAR DATOS    |");
			System.out.println("| 0. SALIR            |");
			System.out.println(" _____________________");

			System.out.println("¿Que quieres hacer?");
			String opcion = sc.nextLine();
			switch (opcion) {
				case "1":
					Logica.mostrarDatosMenu(sc);
					break;
				case "0":
					b = false;
					break;

				default:
					System.out.println("Has introducido una opcion que no existe :(");
					break;
			}
		}
	}

	public static void btnAtras() {
		System.out.println(ANSI_TEMA + " _________________________");
		System.out.println("|                         |");
		System.out.println("| Enter para volver Atras |");
		System.out.println(" _________________________" + ANSI_RESET);
	}

	public static void imprimirTablaPeliculas() {
		ArrayList<Pelicula> pelis = Logica.leerPelicula();
		List<String> headersList = new ArrayList<>();

		headersList.add("Titulo");
		headersList.add("Titulo Original");
		List<List<String>> rowsList = new ArrayList<>();
		for (Pelicula peli : pelis) {
			List<String> row = new ArrayList<>();
			row.add(peli.getTitulo());
			row.add(peli.getTituloOriginal());
			rowsList.add(row);
		}
		Logica.limpiarConsola();
		System.out.println(GenerarTabla.generateTable(headersList, rowsList));

		List<String> headersList1 = new ArrayList<>();
		headersList1.add("Año");
		headersList1.add("Duración");
		headersList1.add("País de rodaje");
		headersList1.add("Guion");
		List<List<String>> rowsList1 = new ArrayList<>();
		for (Pelicula peli : pelis) {
			List<String> row = new ArrayList<>();
			row.add(peli.getAno());
			row.add(peli.getDuracion());
			row.add(peli.getPais());
			row.add(peli.getGuion());
			rowsList1.add(row);
		}
		System.out.println(GenerarTabla.generateTable(headersList1, rowsList1));

		List<String> headersList2 = new ArrayList<>();
		headersList2.add("Música");
		headersList2.add("Fotografía");
		headersList2.add("Reparto");
		headersList2.add("Sinopsis");
		List<List<String>> rowsList2 = new ArrayList<>();
		for (Pelicula peli : pelis) {
			List<String> row = new ArrayList<>();
			row.add(peli.getMusica());
			row.add(peli.getFotografia());
			row.add(peli.getReparto());
			row.add((peli.getSinopsis()).substring(0, 30) + "...");
			rowsList2.add(row);
		}
		System.out.println(GenerarTabla.generateTable(headersList2, rowsList2));
	}

	static void imprimirTablavillanos() {
		ArrayList<Villano> villanos = Logica.leerVillanos();
		List<String> headersList = new ArrayList<>();
		headersList.add("Nombre");
		headersList.add("Edad");
		headersList.add("Ciudad");
		headersList.add("Vehiculo");
		headersList.add("Email");
		headersList.add("Genero");
		headersList.add("Creacion");
		List<List<String>> rowsList = new ArrayList<>();
		for (Villano villano : villanos) {
			List<String> row = new ArrayList<>();
			row.add(villano.getNombre());
			row.add(villano.getEdad());
			row.add(villano.getCiudad());
			row.add(villano.getVehiculo());
			row.add(villano.getEmail());
			row.add(villano.getGenero());
			row.add(villano.getCreacion());
			rowsList.add(row);
		}
		Logica.limpiarConsola();
		System.out.println(GenerarTabla.generateTable(headersList, rowsList));
		System.out.flush();

		List<String> headersList1 = new ArrayList<>();
		headersList1.add("Ip_address");
		headersList1.add("Universidad");
		headersList1.add("Titulacion");
		List<List<String>> rowsListvillano1 = new ArrayList<>();
		for (Villano villano : villanos) {
			List<String> row = new ArrayList<>();
			row.add(villano.getIpAddress());
			row.add(villano.getUniversidad());
			row.add(villano.getTitulacion());
			rowsListvillano1.add(row);
		}
		System.out.println(GenerarTabla.generateTable(headersList1, rowsListvillano1));
		System.out.flush();
	}

	static void imprimirTablaDirector() {
		ArrayList<Director> directores = Logica.leerDirector();
		List<String> headersListDirectores = new ArrayList<>();
		headersListDirectores.add("Nombre");
		headersListDirectores.add("Edad");
		headersListDirectores.add("Ciudad de nacimiento");
		headersListDirectores.add("Vehiculo");
		headersListDirectores.add("Email");
		headersListDirectores.add("Genero");
		headersListDirectores.add("Ip_movil");
		List<List<String>> rowsListDirector = new ArrayList<>();
		for (Director director : directores) {
			List<String> row = new ArrayList<>();
			row.add(director.getDirector());
			row.add(String.valueOf(director.getEdad()));
			row.add(director.getCiudadNacimiento());
			row.add(director.getVehiculo());
			row.add(director.getEmail());
			row.add(director.getGenero());
			row.add(director.getIpMovil());
			rowsListDirector.add(row);
		}
		Logica.limpiarConsola();
		System.out.println(GenerarTabla.generateTable(headersListDirectores, rowsListDirector));
		System.out.flush();

		List<String> headersListDirectores1 = new ArrayList<>();
		headersListDirectores1.add("Ip_address");
		headersListDirectores1.add("Empresa");
		headersListDirectores1.add("Titulacion");
		List<List<String>> rowsListDirector1 = new ArrayList<>();
		for (Director director : directores) {
			List<String> row = new ArrayList<>();
			row.add(director.getIpAddress());
			row.add(director.getEmpresa());
			row.add(director.getTitulacion());
			rowsListDirector1.add(row);
		}
		System.out.println(GenerarTabla.generateTable(headersListDirectores1, rowsListDirector1));
		System.out.flush();

	}

	static void imprimirTablaPrincesa() {
		ArrayList<Princesa> princesas = Logica.leerPrincesa();
		List<String> headersListprincesa = new ArrayList<>();
		headersListprincesa.add("Nombre");
		headersListprincesa.add("Edad");
		headersListprincesa.add("Ciudad");
		headersListprincesa.add("Vehiculo");
		headersListprincesa.add("Email");
		headersListprincesa.add("Genero");
		headersListprincesa.add("Creacion");
		List<List<String>> rowsListprincesa = new ArrayList<>();
		for (Princesa princesa : princesas) {
			List<String> row = new ArrayList<>();
			row.add(princesa.getNombre());
			row.add(princesa.getEdad());
			row.add(princesa.getCiudad());
			row.add(princesa.getVehiculo());
			row.add(princesa.getEmail());
			row.add(princesa.getGenero());
			row.add(princesa.getCreacion());
			rowsListprincesa.add(row);
		}
		Logica.limpiarConsola();
		System.out.println(GenerarTabla.generateTable(headersListprincesa, rowsListprincesa));
		System.out.flush();

		List<String> headersListprincesa1 = new ArrayList<>();
		headersListprincesa1.add("Ip_address");
		headersListprincesa1.add("Universidad");
		headersListprincesa1.add("Titulacion");
		List<List<String>> rowsListprincesa1 = new ArrayList<>();
		for (Princesa princesa : princesas) {
			List<String> row = new ArrayList<>();
			row.add(princesa.getIpAddress());
			row.add(princesa.getUniversidad());
			row.add(princesa.getTitulacion());
			rowsListprincesa1.add(row);
		}
		System.out.println(GenerarTabla.generateTable(headersListprincesa1, rowsListprincesa1));
		System.out.flush();
	}

	static void procesoVerTrailer(String url) {
		try {
			new ProcessBuilder("cmd.exe", "/c", "start chrome " + url).inheritIO().start().waitFor();
		} catch (Exception e) {
			/* No hacer nada */
		}
	}

	public static void limpiarConsola() {
		// System.out.flush();
		System.out.println("\n");
		// try {
		// new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		// } catch (Exception e) {
		// /* No hacer nada */
		// }
		// System.out.flush();
	}

	static void volverAtras() {
		btnAtras();
		Logica.pulsaContinuar();
	}

	// Este metodo se utilizara para que los datos se puedan revisar con
	// tranquilidad antes de elegir otro metodo.
	static public void pulsaContinuar() {
		try {
			int leerDato = System.in.read(new byte[2]); // Se tarta basicamente de leer un dato
			limpiarConsola();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static int pregunta(Scanner sc) {
		System.out.println("¿Que trailer quieres ver?");
		String algo = sc.nextLine();
		int peli = Integer.parseInt(algo);
		return peli;
	}

	static void eliminarVillanosMenu(Scanner sc) {
		ArrayList<Villano> pelis = Logica.leerVillanos();
		for (int i = 0; i < pelis.size(); i++) {
			System.out.println(i + ". " + pelis.get(i).getNombre() + pelis.get(i).getCreacion());
		}
		System.out.print("¿Cual quieres eliminar?: ");
		String indice = sc.nextLine();
		String iDeliminar = pelis.get(Integer.parseInt(indice)).getId();
		System.out.println("¿Esta seguro/a? s/n");
		String sn = sc.nextLine();
		if (sn.toLowerCase().equals("s")) {
			Logica.eliminarVillano(iDeliminar);
			System.out.println("Se elimino exitosamente!!");
		} else if (sn.toLowerCase().equals("n")) {
			System.out.println("Se cancelo exitosamente!!");
		} else {
			System.out.println("como es una opcion extraña se cancelara");
		}
	}

	static void eliminarDirectorMenu(Scanner sc) {
		ArrayList<Director> pelis = Logica.leerDirector();
		for (int i = 0; i < pelis.size(); i++) {
			System.out.println(i + ". " + pelis.get(i).getDirector());
		}
		System.out.print("¿Cual quieres eliminar?: ");
		String indice = sc.nextLine();
		String iDeliminar = pelis.get(Integer.parseInt(indice)).getId();
		System.out.println("¿Esta seguro/a? s/n");
		String sn = sc.nextLine();
		if (sn.toLowerCase().equals("s")) {
			Logica.eliminarDirector(iDeliminar);
			System.out.println("Se elimino exitosamente!!");
		} else if (sn.toLowerCase().equals("n")) {
			System.out.println("Se cancelo exitosamente!!");
		} else {
			System.out.println("como es una opcion extraña se cancelara");
		}
	}

	static void eliminarPrincipeMenu(Scanner sc) {
		ArrayList<Principe> pelis = Logica.leerPrincipes();
		for (int i = 0; i < pelis.size(); i++) {
			System.out.println(i + ". " + pelis.get(i).getNombre() + pelis.get(i).getCreacion());
		}
		System.out.print("¿Cual quieres eliminar?: ");
		String indice = sc.nextLine();
		String iDeliminar = pelis.get(Integer.parseInt(indice)).getId();
		System.out.println("¿Esta seguro/a? s/n");
		String sn = sc.nextLine();
		if (sn.toLowerCase().equals("s")) {
			Logica.eliminarPrincipe(iDeliminar);
			System.out.println("Se elimino exitosamente!!");
		} else if (sn.toLowerCase().equals("n")) {
			System.out.println("Se cancelo exitosamente!!");
		} else {
			System.out.println("como es una opcion extraña se cancelara");
		}
	}

	static void eliminarPrincesaMenu(Scanner sc) {
		ArrayList<Princesa> pelis = Logica.leerPrincesa();
		for (int i = 0; i < pelis.size(); i++) {
			System.out.println(i + ". " + pelis.get(i).getNombre() + pelis.get(i).getCreacion());
		}
		System.out.print("¿Cual quieres eliminar?: ");
		String indice = sc.nextLine();
		String iDeliminar = pelis.get(Integer.parseInt(indice)).getId();
		System.out.println("¿Esta seguro/a? s/n");
		String sn = sc.nextLine();
		if (sn.toLowerCase().equals("s")) {
			Logica.eliminarPrincesa(iDeliminar);
			System.out.println("Se elimino exitosamente!!");
		} else if (sn.toLowerCase().equals("n")) {
			System.out.println("Se cancelo exitosamente!!");
		} else {
			System.out.println("como es una opcion extraña se cancelara");
		}
	}

	static void eliminarPeliculaMenuCascada(Scanner sc) {
		ArrayList<Pelicula> pelis = Logica.leerPelicula();
		for (int i = 0; i < pelis.size(); i++) {
			System.out.println(i + ". " + pelis.get(i).getTitulo());
		}
		System.out.print("¿Cual quieres eliminar?: ");
		String peliIndice = sc.nextLine();
		String iDpeliEliminar = pelis.get(Integer.parseInt(peliIndice)).getId();
		System.out.println("¿Esta seguro/a? s/n");
		String sn = sc.nextLine();
		if (sn.toLowerCase().equals("s")) {
			Logica.eliminarPelicula(iDpeliEliminar);
			Logica.eliminarDirectorCascada(iDpeliEliminar);
			Logica.eliminarPrincesaCascada(iDpeliEliminar);
			Logica.eliminarPrincipeCascada(iDpeliEliminar);
			Logica.eliminarVillanoCascada(iDpeliEliminar);
			System.out.println("Se elimino exitosamente!!");
		} else if (sn.toLowerCase().equals("n")) {
			System.out.println("Se cancelo exitosamente!!");
		} else {
			System.out.println("como es una opcion extraña se cancelara");
		}
	}

	static void eliminarPeliculaMenu(Scanner sc) {
		ArrayList<Pelicula> pelis = Logica.leerPelicula();
		for (int i = 0; i < pelis.size(); i++) {
			System.out.println(i + ". " + pelis.get(i).getTitulo());
		}
		System.out.print("¿Cual quieres eliminar?: ");
		String peliIndice = sc.nextLine();
		String iDpeliEliminar = pelis.get(Integer.parseInt(peliIndice)).getId();
		System.out.println("¿Esta seguro/a? s/n");
		String sn = sc.nextLine();
		if (sn.toLowerCase().equals("s")) {
			Logica.eliminarPelicula(iDpeliEliminar);
			System.out.println("Se elimino exitosamente!!");
		} else if (sn.toLowerCase().equals("n")) {
			System.out.println("Se cancelo exitosamente!!");
		} else {
			System.out.println("como es una opcion extraña se cancelara");
		}
	}

	static void leerSinopsis(Scanner sc) {
		ArrayList<Pelicula> pelis = Logica.leerPelicula();
		for (int i = 0; i < pelis.size(); i++) {
			System.out.println(i + ". " + pelis.get(i).getTitulo());
		}
		System.out.println("¿Cual es la sinopsis que mas te interesa?");
		String leeropcion = sc.nextLine();
		String sinopsis = pelis.get(Integer.parseInt(leeropcion)).getSinopsis();
		String sinopsisPeli = pelis.get(Integer.parseInt(leeropcion)).getTitulo();
		System.out.println(sinopsisPeli + ": " + sinopsis);
	}

	static void editarOpcines(Scanner sc) {
		limpiarConsola();
		boolean a2 = true;
		while (a2) {
			System.out.println("");
			System.out.println(ANSI_TEMA + " __________________________");
			System.out.println("|                         |");
			System.out.println("|           MENU          |");
			System.out.println(" __________________________" + ANSI_RESET);
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
					limpiarConsola();

					volverAtras();
					break;
				case "2":
					limpiarConsola();

					volverAtras();
					break;
				case "3":
					limpiarConsola();

					volverAtras();
					break;
				case "4":
					limpiarConsola();

					volverAtras();
					break;
				case "5":
					limpiarConsola();

					volverAtras();
					break;
				case "0":
					limpiarConsola();
					a2 = false;
					break;

				default:
					System.out.println("Has introducido una opcion que no existe :(");
					break;
			}
		}
	}

	static void gestionarDatosMenu(Scanner sc) {
		limpiarConsola();
		boolean a1 = true;
		while (a1) {
			System.out.println("");
			System.out.println(ANSI_TEMA + " ____________________________");
			System.out.println("|                            |");
			System.out.println("|             MENU           |");
			System.out.println(" ____________________________" + ANSI_RESET);
			System.out.println(" _____________________________");
			System.out.println("|                             |");
			System.out.println("| 1. EDITAR DATOS             |");
			System.out.println("| 2. BORRAR DATOS             |");
			System.out.println("| 3. INSERTAR DATOS           |");
			System.out.println("| 4. NUEVO USUARIO            |");
			System.out.println("| 0. Atras                    |");
			System.out.println(" _____________________________");
			System.out.println("¿Que quieres hacer?");
			String opcion2 = sc.nextLine();
			switch (opcion2) {
				case "1":
					editarOpcines(sc);
					break;
				case "2":
					Logica.borrarOpciones(sc);
					break;
				case "3":
					Logica.insertarOpciones(sc);
					break;
				case "4":
					limpiarConsola();
					Logica.registro(sc);
					btnAtras();
					break;
				case "0":
					limpiarConsola();
					a1 = false;
					break;

				default:
					break;
			}
		}
	}

	public static void insertarUsuario(Usuario usr) {
		Main.usuario.insertOne(new Document()
				.append("_id", new ObjectId())
				.append("Usuario", usr.getUser())
				.append("Password", usr.getPasswword())
				.append("Rol", usr.getRol()));
	}

	static void menu(Scanner sc) {
		boolean b = true;
		while (b) {
			System.out.println("");
			System.out.println(ANSI_TEMA + " _____________________");
			System.out.println("|                     |");
			System.out.println("|         MENU        |");
			System.out.println(" _____________________" + ANSI_RESET);
			System.out.println(" _____________________");
			System.out.println("|                     |");
			System.out.println("| 1. MOSTRAR DATOS    |");
			System.out.println("| 2. GESTIONAR DATOS  |");
			System.out.println("| 0. SALIR            |");
			System.out.println(" _____________________");

			System.out.println("¿Que quieres hacer?");
			String opcion = sc.nextLine();
			switch (opcion) {
				case "1":
					Logica.mostrarDatosMenu(sc);
					break;
				case "2":
					gestionarDatosMenu(sc);
					break;
				case "0":
					b = false;
					break;

				default:
					System.out.println("Has introducido una opcion que no existe :(");
					break;
			}
		}
	}

	static void mostrarDatosMenu(Scanner sc) {
		limpiarConsola();
		boolean a = true;
		while (a) {
			System.out.println("");
			System.out.println(ANSI_TEMA + " ___________________________________");
			System.out.println("|                                   |");
			System.out.println("|                MENU               |");
			System.out.println(" ___________________________________" + ANSI_RESET);
			System.out.println(" ___________________________________");
			System.out.println("|                                   |");
			System.out.println("| 1. Mostrar peliculas              |");
			System.out.println("| 2. Mostrar princesas              |");
			System.out.println("| 3. Mostrar directores             |");
			System.out.println("| 4. Mostrar villanos               |");
			System.out.println("| 5. Mostrar principes              |");
			System.out.println("| 6. Grafico del genero de los      |");
			System.out.println("|    directores                     |");
			System.out.println("| 7. Ver trailer de una pelicula    |");
			System.out.println("| 8. Ver sinopsis de una pelicula   |");
			System.out.println("| 0.Atras                           |");
			System.out.println(" ___________________________________");
			System.out.println("¿Que quieres hacer?");
			String opcion1 = sc.nextLine();
			switch (opcion1) {
				case "1":
					limpiarConsola();
					imprimirTablaPeliculas();
					volverAtras();
					break;
				case "2":
					limpiarConsola();
					imprimirTablaPrincesa();
					volverAtras();
					break;
				case "3":
					limpiarConsola();
					imprimirTablaDirector();
					volverAtras();
					break;
				case "4":
					limpiarConsola();
					imprimirTablavillanos();
					volverAtras();
					break;
				case "5":
					limpiarConsola();
					imprimirTablaPrincipes();
					volverAtras();
					break;
				case "6":
					limpiarConsola();
					System.out.println(ANSI_TEMA + " _________________________");
					System.out.println("|                         |");
					System.out.println("|       ESTADISTICA       |");
					System.out.println(" _________________________" + ANSI_RESET);
					System.out.println("");
					Logica.directoresPorGenero();
					volverAtras();
					break;
				case "7":
					limpiarConsola();
					System.out.println(ANSI_TEMA + " _________________________");
					System.out.println("|                         |");
					System.out.println("|       VER TRAILER       |");
					System.out.println(" _________________________" + ANSI_RESET);
					System.out.println("");
					Map<Integer, Pelicula> c = Logica.leerListaPelisTrailer();
					int peli = pregunta(sc);
					procesoVerTrailer(c.get(peli).getTrailer());
					btnAtras();
					break;
				case "8":
					limpiarConsola();

					System.out.println(ANSI_TEMA + " _________________________");
					System.out.println("|                         |");
					System.out.println("|       VER SINOPSIS      |");
					System.out.println(" _________________________" + ANSI_RESET);
					System.out.println("");
					leerSinopsis(sc);
					volverAtras();
					break;
				case "0":
					limpiarConsola();
					a = false;
					break;

				default:
					System.out.println("Has introducido una opcion que no existe :(");
					break;
			}
		}
	}

	static void insertarOpciones(Scanner sc) {
		limpiarConsola();
		boolean a5 = true;
		while (a5) {
			System.out.println("");
			System.out.println(ANSI_TEMA + " _________________________");
			System.out.println("|                         |");
			System.out.println("|           MENU          |");
			System.out.println(" _________________________" + ANSI_RESET);
			System.out.println(" __________________________");
			System.out.println("|                          |");
			System.out.println("| 1. Insertar pelicula     |");
			System.out.println("| 2. Insertar princesa     |");
			System.out.println("| 3. Insertar director     |");
			System.out.println("| 4. Insertar villano      |");
			System.out.println("| 5. Insertar principe     |");
			System.out.println("| 0. Atras                 |");
			System.out.println(" __________________________");
			System.out.println("¿Que quieres hacer?");
			String opcion4 = sc.nextLine();
			switch (opcion4) {
				case "1":
					limpiarConsola();
					Logica.insertarPeliculaMenu(sc);
					volverAtras();
					break;
				case "2":
					limpiarConsola();
					Logica.insertarPrincesaMenu(sc);
					volverAtras();
					break;
				case "3":
					limpiarConsola();
					Logica.insertarDirectorMenu(sc);
					volverAtras();
					break;
				case "4":
					limpiarConsola();
					Logica.insertarVillanoMenu(sc);
					volverAtras();
					break;
				case "5":
					limpiarConsola();
					Logica.insertarPrincipeMenu(sc);
					volverAtras();
					break;
				case "0":
					limpiarConsola();
					a5 = false;
					break;

				default:
					System.out.println("Has introducido una opcion que no existe :(");
					break;
			}
		}
	}

	static void insertarDirectorMenu(Scanner sc) {
		System.out.println(ANSI_TEMA + " _____________________________");
		System.out.println("|                            |");
		System.out.println("|      INSERTAR DIRECTOR     |");
		System.out.println(" ____________________________" + ANSI_RESET);
		System.out.println("Introduce un nueva Director:");
		System.out.print("Nombre: ");
		String nombre = sc.nextLine();
		System.out.print("Edad: ");
		String edad = sc.nextLine();
		System.out.print("Ciudad de nacimeinto: ");
		String ciudadNacimiento = sc.nextLine();
		System.out.print("Vehiculo: ");
		String vehiculo = sc.nextLine();
		System.out.print("Email: ");
		String email = sc.nextLine();
		System.out.print("Genero: ");
		String genero = sc.nextLine();
		System.out.print("Ip_movil: ");
		String ipMovil = sc.nextLine();

		System.out.print("ipAddress: ");
		String ipAddress = sc.nextLine();

		System.out.print("Empresa: ");
		String empresa = sc.nextLine();
		System.out.print("Titulacion: ");
		String titulacion = sc.nextLine();

		System.out.println();
		System.out.println("¿A que pelicula lo quieres enlazar?");
		Map<Integer, Pelicula> c = Logica.leerListaPelisTrailer();
		System.out.print("Elige una pelicula: ");
		String idlista = sc.nextLine();
		int id = Integer.parseInt(idlista);
		String idPelicula = c.get(id).getId();
		Director d = new Director(null, nombre, Integer.parseInt(edad), ciudadNacimiento, vehiculo, email, genero,
				ipMovil, ipAddress, empresa, titulacion);
		System.out.println("¿Estas seguro de añadir la director?S/N");
		String respuesta = sc.nextLine();
		if (respuesta.toLowerCase().equals("s")) {
			Logica.insertarDirector(d, idPelicula);

			System.out.println("Se ha añadido la princesa");
		} else {
			System.out.println("No se ha añadido la princesa");
		}

	}

	static void insertarPrincesaMenu(Scanner sc) {
		System.out.println(ANSI_TEMA + " _____________________________");
		System.out.println("|                            |");
		System.out.println("|      INSERTAR PRINCIPE     |");
		System.out.println(" ____________________________" + ANSI_RESET);
		System.out.println("Introduce un nueva Princesa:");
		System.out.print("Nombre: ");
		String nombre = sc.nextLine();
		System.out.print("Edad: ");
		String edad = sc.nextLine();
		System.out.print("Ciudad: ");
		String ciudad = sc.nextLine();
		System.out.print("Vehiculo: ");
		String vehiculo = sc.nextLine();
		System.out.print("Email: ");
		String email = sc.nextLine();
		System.out.print("Genero: ");
		String genero = sc.nextLine();
		System.out.print("Creacion: ");
		String creacion = sc.nextLine();
		System.out.print("ip_address: ");
		String ip_address = sc.nextLine();
		System.out.print("Universidad: ");
		String universidad = sc.nextLine();
		System.out.print("Titulacion: ");
		String titulacion = sc.nextLine();
		System.out.println();
		System.out.println("¿A que pelicula lo quieres enlazar?");
		Map<Integer, Pelicula> c = Logica.leerListaPelisTrailer();
		System.out.print("Elige una pelicula: ");
		String idlista = sc.nextLine();
		int id = Integer.parseInt(idlista);
		String idPelicula = c.get(id).getId();
		Princesa princesa = new Princesa(null, nombre, edad, ciudad, vehiculo, email, genero,
				creacion, ip_address, universidad, titulacion);
		System.out.println("¿Estas seguro de añadir la princesa?S/N");
		String respuesta = sc.nextLine();
		if (respuesta.toLowerCase().equals("s")) {
			Logica.insertarPrincesa(princesa, idPelicula);
			System.out.println("Se ha añadido la princesa");
		} else {
			System.out.println("No se ha añadido la princesa");
		}

	}

	static void insertarPrincipeMenu(Scanner sc) {
		System.out.println(ANSI_TEMA + " _____________________________");
		System.out.println("|                            |");
		System.out.println("|      INSERTAR PRINCIPE     |");
		System.out.println(" ____________________________" + ANSI_RESET);
		System.out.println("Introduce un nueva principe:");
		System.out.print("Nombre: ");
		String nombre = sc.nextLine();
		System.out.print("Edad: ");
		String edad = sc.nextLine();
		System.out.print("Ciudad: ");
		String ciudad = sc.nextLine();
		System.out.print("Vehiculo: ");
		String vehiculo = sc.nextLine();
		System.out.print("Email: ");
		String email = sc.nextLine();
		System.out.print("Genero: ");
		String genero = sc.nextLine();
		System.out.print("Creacion: ");
		String creacion = sc.nextLine();
		System.out.print("ip_address: ");
		String ip_address = sc.nextLine();
		System.out.print("Universidad: ");
		String universidad = sc.nextLine();
		System.out.print("Titulacion: ");
		String titulacion = sc.nextLine();
		System.out.println();
		System.out.println("¿A que pelicula lo quieres enlazar?");
		Map<Integer, Pelicula> c = Logica.leerListaPelisTrailer();
		System.out.print("Elige una pelicula: ");
		String idlista = sc.nextLine();
		int id = Integer.parseInt(idlista);
		String idPelicula = c.get(id).getId();
		Principe principe = new Principe(null, nombre, edad, ciudad, vehiculo, email, genero,
				creacion, ip_address, universidad, titulacion);
		System.out.println("¿Estas seguro de añadir al principe?S/N");
		String respuesta = sc.nextLine();
		if (respuesta.toLowerCase().equals("s")) {
			Logica.insertarPrincipe(principe, idPelicula);
			System.out.println("Se ha añadido la principe");
		} else {
			System.out.println("No se ha añadido la principe");
		}

	}

	static void insertarVillanoMenu(Scanner sc) {
		System.out.println(ANSI_TEMA + " _____________________________");
		System.out.println("|                            |");
		System.out.println("|      INSERTAR VILLANO      |");
		System.out.println(" ____________________________" + ANSI_RESET);
		System.out.println("Introduce un nueva Villano:");
		System.out.print("Nombre: ");
		String nombre = sc.nextLine();
		System.out.print("Edad: ");
		String edad = sc.nextLine();
		System.out.print("Ciudad: ");
		String ciudad = sc.nextLine();
		System.out.print("Vehiculo: ");
		String vehiculo = sc.nextLine();
		System.out.print("Email: ");
		String email = sc.nextLine();
		System.out.print("Genero: ");
		String genero = sc.nextLine();
		System.out.print("Creacion: ");
		String creacion = sc.nextLine();
		System.out.print("ip_address: ");
		String ip_address = sc.nextLine();
		System.out.print("Universidad: ");
		String universidad = sc.nextLine();
		System.out.print("Titulacion: ");
		String titulacion = sc.nextLine();
		System.out.println();
		System.out.println("¿A que pelicula lo quieres enlazar?");
		Map<Integer, Pelicula> c = Logica.leerListaPelisTrailer();
		System.out.print("Elige una pelicula: ");
		String idlista = sc.nextLine();
		int id = Integer.parseInt(idlista);
		String idPelicula = c.get(id).getId();
		Villano villano = new Villano(null, nombre, edad, ciudad, vehiculo, email, genero,
				creacion, ip_address, universidad, titulacion);
		System.out.println("¿Estas seguro de añadir la Villano?S/N");
		String respuesta = sc.nextLine();
		if (respuesta.toLowerCase().equals("s")) {
			Logica.insertarVillano(villano, idPelicula);
			System.out.println("Se ha añadido la Villano");
		} else {
			System.out.println("No se ha añadido la Villano");
		}

	}

	static void insertarPeliculaMenu(Scanner sc) {
		System.out.println(ANSI_TEMA + " ____________________________");
		System.out.println("|                            |");
		System.out.println("|      INSERTAR PELICULA     |");
		System.out.println(" ____________________________" + ANSI_RESET);
		System.out.println("Introduce un nueva pelicula:");
		System.out.print("Titulo: ");
		String titulo = sc.nextLine();
		System.out.print("Titulo Original: ");
		String tituloOriginal = sc.nextLine();
		System.out.print("Año: ");
		String anio = sc.nextLine();
		System.out.print("Duración: ");
		String duracion = sc.nextLine();
		System.out.print("País: ");
		String pais = sc.nextLine();
		System.out.print("Guion: ");
		String guion = sc.nextLine();
		System.out.print("Musica: ");
		String musica = sc.nextLine();
		System.out.print("Fotografia: ");
		String fotografia = sc.nextLine();
		System.out.print("Reparto: ");
		String reparto = sc.nextLine();
		System.out.print("Sinopsis: ");
		String sipnosis = sc.nextLine();
		System.out.print("Trailer: ");
		String trailer = sc.nextLine();
		Pelicula pelicula = new Pelicula(null, titulo, tituloOriginal, anio, duracion, pais,
				guion, musica, fotografia, reparto, sipnosis, trailer);
		System.out.println("¿Estas seguro de añadir la pelicula?S/N");
		String respuesta = sc.nextLine();
		if (respuesta.toLowerCase().equals("s")) {
			Logica.insertarPelicula(pelicula);
			System.out.println("Se ha añadido la pelicula");
		} else {
			System.out.println("No se ha añadido la pelicula");
		}
	}

	public static void insertarDirector(Director d, String idPelicula) {
		Main.director.insertOne(new Document()
				.append("_id", new ObjectId())
				.append("Director", d.getDirector())
				.append("Edad", d.getEdad())
				.append("Ciudad_nacimiento", d.getCiudadNacimiento())
				.append("Vehiculo", d.getVehiculo())
				.append("Email", d.getEmail())
				.append("Genero", d.getGenero())
				.append("Ip_movil", d.getIpMovil())
				.append("ip_address", d.getIpAddress())
				.append("Empresa", d.getEmpresa())
				.append("Titulacion", d.getTitulacion())
				.append("idPelicula", new ObjectId(idPelicula))
				.append("trabajadoDisney", "si"));
	}

	public static void insertarVillano(Villano p, String idPelicula) {
		Main.villano.insertOne(new Document()
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
				.append("Titulacion", p.getTitulacion())
				.append("idPelicula", new ObjectId(idPelicula)));
	}

	public static void insertarPrincipe(Principe p, String idPelicula) {
		Main.principe.insertOne(new Document()
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
				.append("Titulacion", p.getTitulacion())
				.append("idPelicula", new ObjectId(idPelicula)));
	}

	public static void insertarPrincesa(Princesa p, String idPelicula) {
		Main.princesa.insertOne(new Document()
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
				.append("Titulacion", p.getTitulacion())
				.append("idPelicula", new ObjectId(idPelicula)));
	}

	public static void insertarPelicula(Pelicula p) {
		Main.pelicula.insertOne(new Document()
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

	private static void establecerRelaciones() {
		// RELACIONES PRINCESAS
		Logica.relacionPrincesa("620d3e6e41d00a35312038e3", "620d3e1241d00a353120388b");// 1
		Logica.relacionPrincesa("620d3e6e41d00a35312038e4", "620d3e1241d00a353120388c");// 2
		Logica.relacionPrincesa("620d3e6e41d00a35312038e5", "620d3e1241d00a353120388d");// 3
		Logica.relacionPrincesa("620d3e6e41d00a35312038e6", "620d3e1241d00a353120388e");// 4
		Logica.relacionPrincesa("620d3e6e41d00a35312038e7", "620d3e1241d00a353120388f");// 5
		Logica.relacionPrincesa("620d3e6e41d00a35312038e8", "620d3e1241d00a3531203890");// 6
		Logica.relacionPrincesa("620d3e6e41d00a35312038e9", "620d3e1241d00a3531203891");// 7
		Logica.relacionPrincesa("620d3e6e41d00a35312038ea", "620d3e1241d00a3531203892");// 8
		Logica.relacionPrincesa("620d3e6e41d00a35312038eb", "620d3e1241d00a3531203893");// 9
		Logica.relacionPrincesa("620d3e6e41d00a35312038ec", "620d3e1241d00a3531203894");// 10
		Logica.relacionPrincesa("620d3e6e41d00a35312038ed", "620d3e1241d00a3531203895");// 11
		Logica.relacionPrincesa("620d3e6e41d00a35312038ee", "620d3e1241d00a3531203896");// 12
		Logica.relacionPrincesa("620d3e6e41d00a35312038ef", "620d3e1241d00a3531203897");// 13
		Logica.relacionPrincesa("620d3e6e41d00a35312038f0", "620d3e1241d00a3531203898");// 14
		Logica.relacionPrincesa("620d3e6e41d00a35312038f1", "620d3e1241d00a3531203899");// 15
		Logica.relacionPrincesa("620d3e6e41d00a35312038f2", "620d3e1241d00a353120389a");// 16
		Logica.relacionPrincesa("620d3e6e41d00a35312038f3", "620d3e1241d00a353120389b");// 17
		Logica.relacionPrincesa("620d3e6e41d00a35312038f4", "620d3e1241d00a353120389c");// 18
		Logica.relacionPrincesa("620d3e6e41d00a35312038f5", "620d3e1241d00a353120389d");// 19
		Logica.relacionPrincesa("620d3e6e41d00a35312038f6", "620d3e1241d00a353120389e");// 20
		Logica.relacionPrincesa("620d3e6e41d00a35312038f7", "620d3e1241d00a353120389f");// 21
		Logica.relacionPrincesa("620d3e6e41d00a35312038f8", "620d3e1241d00a35312038a0");// 22
		Logica.relacionPrincesa("620d3e6e41d00a35312038f9", "620d3e1241d00a35312038a1");// 23
		Logica.relacionPrincesa("620d3e6e41d00a35312038fa", "620d3e1241d00a35312038a2");// 24
		Logica.relacionPrincesa("620d3e6e41d00a35312038fb", "620d3e1241d00a35312038a3");// 25
		Logica.relacionPrincesa("620d3e6e41d00a35312038fc", "620d3e1241d00a35312038a4");// 26

		// RELACIONES VILLANOS
		Logica.relacionVillano("620d3e6e41d00a35312038e3", "620d3e3841d00a35312038c3");// 1
		Logica.relacionVillano("620d3e6e41d00a35312038e4", "620d3e3841d00a35312038c4");// 2
		Logica.relacionVillano("620d3e6e41d00a35312038e5", "620d3e3841d00a35312038c5");// 3
		Logica.relacionVillano("620d3e6e41d00a35312038e6", "620d3e3841d00a35312038c6");// 4
		Logica.relacionVillano("620d3e6e41d00a35312038e7", "620d3e3841d00a35312038c7");// 5
		Logica.relacionVillano("620d3e6e41d00a35312038e8", "620d3e3841d00a35312038c8");// 6
		Logica.relacionVillano("620d3e6e41d00a35312038e9", "620d3e3841d00a35312038c9");// 7
		Logica.relacionVillano("620d3e6e41d00a35312038ea", "620d3e3841d00a35312038ca");// 8
		Logica.relacionVillano("620d3e6e41d00a35312038eb", "620d3e3841d00a35312038cb");// 9
		Logica.relacionVillano("620d3e6e41d00a35312038ec", "620d3e3841d00a35312038cc");// 10
		Logica.relacionVillano("620d3e6e41d00a35312038ed", "620d3e3841d00a35312038cd");// 11
		Logica.relacionVillano("620d3e6e41d00a35312038ee", "620d3e3841d00a35312038ce");// 12
		Logica.relacionVillano("620d3e6e41d00a35312038ef", "620d3e3841d00a35312038cf");// 13
		Logica.relacionVillano("620d3e6e41d00a35312038f0", "620d3e3841d00a35312038d0");// 14
		Logica.relacionVillano("620d3e6e41d00a35312038f1", "620d3e3841d00a35312038d1");// 15
		Logica.relacionVillano("620d3e6e41d00a35312038f2", "620d3e3841d00a35312038d2");// 16
		Logica.relacionVillano("620d3e6e41d00a35312038f3", "620d3e3841d00a35312038d3");// 17
		Logica.relacionVillano("620d3e6e41d00a35312038f4", "620d3e3841d00a35312038d4");// 18
		Logica.relacionVillano("620d3e6e41d00a35312038f5", "620d3e3841d00a35312038d5");// 19
		Logica.relacionVillano("620d3e6e41d00a35312038f6", "620d3e3841d00a35312038d6");// 20
		Logica.relacionVillano("620d3e6e41d00a35312038f7", "620d3e3841d00a35312038d7");// 21
		Logica.relacionVillano("620d3e6e41d00a35312038f8", "620d3e3841d00a35312038d8");// 22
		Logica.relacionVillano("620d3e6e41d00a35312038f9", "620d3e3841d00a35312038d9");// 23
		Logica.relacionVillano("620d3e6e41d00a35312038fa", "620d3e3841d00a35312038da");// 24
		Logica.relacionVillano("620d3e6e41d00a35312038fb", "620d3e3841d00a35312038db");// 25
		Logica.relacionVillano("620d3e6e41d00a35312038fc", "620d3e3841d00a35312038dc");// 26

		// Relacion Principes
		Logica.relacionPrincipe("620d3e6e41d00a35312038e3", "620d3e2a41d00a35312038a7");// 1
		Logica.relacionPrincipe("620d3e6e41d00a35312038e4", "620d3e2a41d00a35312038a8");// 2
		Logica.relacionPrincipe("620d3e6e41d00a35312038e5", "620d3e2a41d00a35312038a9");// 3
		Logica.relacionPrincipe("620d3e6e41d00a35312038e6", "620d3e2a41d00a35312038aa");// 4
		Logica.relacionPrincipe("620d3e6e41d00a35312038e7", "620d3e2a41d00a35312038aa");// 5
		Logica.relacionPrincipe("620d3e6e41d00a35312038e8", "620d3e2a41d00a35312038ac");// 6
		Logica.relacionPrincipe("620d3e6e41d00a35312038e9", "620d3e2a41d00a35312038ad");// 7
		Logica.relacionPrincipe("620d3e6e41d00a35312038ea", "620d3e2a41d00a35312038ae");// 8
		Logica.relacionPrincipe("620d3e6e41d00a35312038eb", "620d3e2a41d00a35312038af");// 9
		Logica.relacionPrincipe("620d3e6e41d00a35312038ec", "620d3e2a41d00a35312038b0");// 10
		Logica.relacionPrincipe("620d3e6e41d00a35312038ed", "620d3e2a41d00a35312038b1");// 11
		Logica.relacionPrincipe("620d3e6e41d00a35312038ee", "620d3e2a41d00a35312038b2");// 12
		Logica.relacionPrincipe("620d3e6e41d00a35312038ef", "620d3e2a41d00a35312038b3");// 13
		Logica.relacionPrincipe("620d3e6e41d00a35312038f0", "620d3e2a41d00a35312038b4");// 14
		Logica.relacionPrincipe("620d3e6e41d00a35312038f1", "620d3e2a41d00a35312038b5");// 15
		Logica.relacionPrincipe("620d3e6e41d00a35312038f2", "620d3e2a41d00a35312038b6");// 16
		Logica.relacionPrincipe("620d3e6e41d00a35312038f3", "620d3e2a41d00a35312038b7");// 17
		Logica.relacionPrincipe("620d3e6e41d00a35312038f4", "620d3e2a41d00a35312038b8");// 18
		Logica.relacionPrincipe("620d3e6e41d00a35312038f5", "620d3e2a41d00a35312038b9");// 19
		Logica.relacionPrincipe("620d3e6e41d00a35312038f6", "620d3e2a41d00a35312038ba");// 20
		Logica.relacionPrincipe("620d3e6e41d00a35312038f7", "620d3e2a41d00a35312038bb");// 21
		Logica.relacionPrincipe("620d3e6e41d00a35312038f8", "620d3e2a41d00a35312038bc");// 22
		Logica.relacionPrincipe("620d3e6e41d00a35312038f9", "620d3e2a41d00a35312038bd");// 23
		Logica.relacionPrincipe("620d3e6e41d00a35312038fa", "620d3e2a41d00a35312038be");// 24
		Logica.relacionPrincipe("620d3e6e41d00a35312038fb", "620d3e2a41d00a35312038bf");// 25
		Logica.relacionPrincipe("620d3e6e41d00a35312038fc", "620d3e2a41d00a35312038c0");// 26

		// Relacion directores
		Logica.relacionDirector("620d3e6e41d00a35312038e3", "62093c2b6ed0cbd82eee0319");// 1
		Logica.relacionDirector("620d3e6e41d00a35312038e4", "62093c2b6ed0cbd82eee031a");// 2
		Logica.relacionDirector("620d3e6e41d00a35312038e5", "62093c2b6ed0cbd82eee031b");// 3
		Logica.relacionDirector("620d3e6e41d00a35312038e6", "62093c2b6ed0cbd82eee031c");// 4
		Logica.relacionDirector("620d3e6e41d00a35312038e7", "62093c2b6ed0cbd82eee031d");// 5
		Logica.relacionDirector("620d3e6e41d00a35312038e8", "62093c2b6ed0cbd82eee031e");// 6
		Logica.relacionDirector("620d3e6e41d00a35312038e9", "62093c2b6ed0cbd82eee031f");// 7
		Logica.relacionDirector("620d3e6e41d00a35312038ea", "62093c2b6ed0cbd82eee0320");// 8
		Logica.relacionDirector("620d3e6e41d00a35312038eb", "62093c2b6ed0cbd82eee0321");// 9
		Logica.relacionDirector("620d3e6e41d00a35312038ec", "62093c2b6ed0cbd82eee0322");// 10
		Logica.relacionDirector("620d3e6e41d00a35312038ed", "62093c2b6ed0cbd82eee0323");// 11
		Logica.relacionDirector("620d3e6e41d00a35312038ee", "62093c2b6ed0cbd82eee0324");// 12
		Logica.relacionDirector("620d3e6e41d00a35312038ef", "62093c2b6ed0cbd82eee0325");// 13
		Logica.relacionDirector("620d3e6e41d00a35312038f0", "62093c2b6ed0cbd82eee0326");// 14
		Logica.relacionDirector("620d3e6e41d00a35312038f1", "62093c2b6ed0cbd82eee0327");// 15
		Logica.relacionDirector("620d3e6e41d00a35312038f2", "62093c2b6ed0cbd82eee0328");// 16
		Logica.relacionDirector("620d3e6e41d00a35312038f3", "62093c2b6ed0cbd82eee0329");// 17
		Logica.relacionDirector("620d3e6e41d00a35312038f4", "62093c2b6ed0cbd82eee032a");// 18
		Logica.relacionDirector("620d3e6e41d00a35312038f5", "62093c2b6ed0cbd82eee032b");// 19
		Logica.relacionDirector("620d3e6e41d00a35312038f6", "62093c2b6ed0cbd82eee032c");// 20
		Logica.relacionDirector("620d3e6e41d00a35312038f7", "62093c2b6ed0cbd82eee032d");// 21
		Logica.relacionDirector("620d3e6e41d00a35312038f8", "62093c2b6ed0cbd82eee032e");// 22
		Logica.relacionDirector("620d3e6e41d00a35312038f9", "62093c2b6ed0cbd82eee032f");// 23
		Logica.relacionDirector("620d3e6e41d00a35312038fa", "62093c2b6ed0cbd82eee0330");// 24
		Logica.relacionDirector("620d3e6e41d00a35312038fb", "62093c2b6ed0cbd82eee0331");// 25
		Logica.relacionDirector("620d3e6e41d00a35312038fc", "62093c2b6ed0cbd82eee0332");// 26
	}

	public static void eliminarPelicula(String id) {
		Document findDocument = new Document("_id", new ObjectId(id));
		Main.pelicula.deleteMany(findDocument);
	}

	static void borrarOpciones(Scanner sc) {
		limpiarConsola();
		boolean a3 = true;
		while (a3) {
			System.out.println("");
			System.out.println(ANSI_TEMA + " _________________________");
			System.out.println("|                         |");
			System.out.println("|           MENU          |");
			System.out.println(" _________________________" + ANSI_RESET);
			System.out.println(" __________________________");
			System.out.println("|                          |");
			System.out.println("| 1. Borrar pelicula       |");
			System.out.println("| 2. Borrar princesa       |");
			System.out.println("| 3. Borrar director       |");
			System.out.println("| 4. Borrar villano        |");
			System.out.println("| 5. Borrar principe       |");
			System.out.println("| 0. Atras                 |");
			System.out.println(" __________________________");
			System.out.println("¿Que quieres hacer?");
			String opcion1 = sc.nextLine();
			switch (opcion1) {
				case "1":
					limpiarConsola();
					System.out.println("¿Quieres que se realice el borrado en cascada? s/n");
					String sn = sc.nextLine();
					if (sn.toLowerCase().equals("s")) {
						eliminarPeliculaMenuCascada(sc);
						System.out.println(
								"Se elimino exitosamente la pelicula y sus personajes!!");
					} else if (sn.toLowerCase().equals("n")) {
						eliminarPeliculaMenu(sc);
						System.out.println("Se elimino exitosamente la pelicula!!");
					} else {
						System.out.println("como es una opcion extraña se cancelara");
					}
					volverAtras();
					break;
				case "2":
					limpiarConsola();
					eliminarPrincesaMenu(sc);
					volverAtras();
					break;
				case "3":
					limpiarConsola();
					eliminarDirectorMenu(sc);
					volverAtras();
					break;
				case "4":
					limpiarConsola();
					eliminarVillanosMenu(sc);
					volverAtras();
					break;
				case "5":
					limpiarConsola();
					eliminarPrincipeMenu(sc);
					volverAtras();
					break;
				case "0":
					limpiarConsola();
					a3 = false;
					break;

				default:
					System.out.println("Has introducido una opcion que no existe :(");
					break;
			}
		}
	}

	static void registroUser(Scanner sc, Console console) {
		System.out.print("Introduce el nuevo user: ");
		String nom = sc.nextLine();
		System.out.println();
		char[] passwordArray = console.readPassword("Introduce una contraseña: ");
		String rol = "usuario";
		Usuario usr = new Usuario(nom, ecriptar.ecnode(new String(passwordArray)), rol);
		insertarUsuario(usr);
		limpiarConsola();
		System.out.println("Ya puedes iniciar sesion " + nom + "!!");
	}

	static void registro(Scanner sc) {
		System.out.println("");
		System.out.println(ANSI_TEMA + " ___________________");
		System.out.println("|                   |");
		System.out.println("|      REGISTRO     |");
		System.out.println(" ___________________" + ANSI_RESET);
		System.out.println("");
		System.out.println("Introduce el nuevo user");
		String nom = sc.nextLine();
		System.out.println("Introduce una contraseña");
		String pwd = sc.nextLine();
		System.out.println("¿Cual es su rol?");
		System.out.println("1. Administrador");
		System.out.println("2. Usuario");
		String opcionRol = sc.nextLine();
		String rol = "";
		if (opcionRol.equals("1")) {
			rol = "admin";
		} else if (opcionRol.equals("2")) {
			rol = "usuario";
		} else {
			System.out.println("No existe... Se le adjudicara usuario.");
			rol = "usuario";
		}

		Usuario usr = new Usuario(nom, ecriptar.ecnode(pwd), rol);
		insertarUsuario(usr);
		System.out.println("Usuario insertado!!");

	}

	static Map<Integer, Pelicula> leerListaPelisTrailer() {
		Map<Integer, Pelicula> c = Logica.leerPeliculaNombre2();
		c.forEach((k, v) -> System.out.println(k + ". " + v.getTitulo()));
		return c;
	}

	public static void directoresPorGenero() {

		MongoDatabase database = Conecion.conexionMongoDB();
		// Select the "actor" collection
		MongoCollection<Document> collection = database.getCollection("Director");

		// Create our pipeline operations, first with the $match
		Document match = new Document("$match", new Document("trabajadoDisney", "si"));

		// Now the $group operation
		Document groupFields = new Document("_id", "$Genero").append("total", new Document("$sum", 1));
		Document group = new Document("$group", groupFields);

		// run aggregation
		List<Document> pipeline = Arrays.asList(match, group);
		MongoCursor<Document> output = collection.aggregate(pipeline).iterator();

		DefaultPieDataset dataset = new DefaultPieDataset();
		while (output.hasNext()) {
			Document document = output.next();
			dataset.setValue(document.getString("_id"), document.getInteger("total"));
		}

		JFreeChart chart = ChartFactory.createPieChart3D("Genero de los directores", dataset, true, true, true);

		ChartFrame frame = new ChartFrame("Genero de los directores", chart);
		frame.pack();
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);

	}

	private static void crearColecciones(MongoDatabase database) {
		database.createCollection("Princesa");
		database.createCollection("Principe");
		database.createCollection("Villano");
		database.createCollection("Pelicula");
		database.createCollection("Director");
		database.createCollection("Usuario");
	}

	public static void obtenerColecciones(MongoDatabase database) {
		Main.pelicula = database.getCollection("Pelicula");
		Main.princesa = database.getCollection("Princesa");
		Main.villano = database.getCollection("Villano");
		Main.principe = database.getCollection("Principe");
		Main.director = database.getCollection("Director");
		Main.usuario = database.getCollection("Usuario");
	}

	static void relacionPrincesa(String idPelicula, String idPrincesa) {
		Document findDocument2 = new Document("_id", new ObjectId(idPrincesa));
		Document updateDocument2 = new Document("$set",
				new Document("idPelicula", new ObjectId(idPelicula)));
		Main.princesa.updateMany(findDocument2, updateDocument2);
		System.out.println("Update ejecutado");
	}

	static void relacionVillano(String idPelicula, String idVillano) {
		Document findDocument2 = new Document("_id", new ObjectId(idVillano));
		Document updateDocument2 = new Document("$set",
				new Document("idPelicula", new ObjectId(idPelicula)));
		Main.villano.updateMany(findDocument2, updateDocument2);
		System.out.println("Update ejecutado");
	}

	static void relacionPrincipe(String idPelicula, String idPrincipe) {
		Document findDocument = new Document("_id", new ObjectId(idPrincipe));
		Document updateDocument = new Document("$set",
				new Document("idPelicula", new ObjectId(idPelicula)));
		Main.principe.updateMany(findDocument, updateDocument);
		System.out.println("Update ejecutado");
	}

	static void relacionDirector(String idPelicula, String idDirector) {
		Document findDocument = new Document("_id", new ObjectId(idDirector));
		Document updateDocument = new Document("$set",
				new Document("idPelicula", new ObjectId(idPelicula)));
		Main.director.updateMany(findDocument, updateDocument);
		System.out.println("Update ejecutado");
	}

	static ArrayList<Princesa> leerPrincesa() {
		ArrayList<Princesa> listaprincesas = new ArrayList<Princesa>();
		MongoCursor<Document> resultDocument = Main.princesa.find().iterator();

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

	static ArrayList<Pelicula> leerPelicula() {
		ArrayList<Pelicula> listapPeliculas = new ArrayList<Pelicula>();
		MongoCursor<Document> resultDocument = Main.pelicula.find().iterator();

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

	public static void insertarPrincesa(Princesa p) {
		Main.princesa.insertOne(new Document()
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

	static Map<Integer, Pelicula> leerPeliculaNombre2() {

		Map<Integer, Pelicula> a = new HashMap<Integer, Pelicula>();
		MongoCursor<Document> resultDocument = Main.pelicula.find().iterator();
		int i = 1;
		while (resultDocument.hasNext()) {

			Document p = resultDocument.next();
			Pelicula pelicula = new Pelicula(p.getObjectId("_id").toString(), p.getString("Titulo"),
					p.getString("Titulo original"), p.getString("Año"), p.getString("Duración"), p.getString("País"),
					p.getString("Guion"), p.getString("Música"), p.getString("Fotografía"), p.getString("Reparto"),
					p.getString("Sinopsis"), p.getString("Trailer"));
			a.put(i, pelicula);

			i++;
		}
		return a;
	}

	public static ArrayList<Principe> leerPrincipes() {
		ArrayList<Principe> listaPrincipe = new ArrayList<Principe>();
		MongoCursor<Document> resultDocument = Main.principe.find().iterator();

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

	static ArrayList<Villano> leerVillanos() {
		ArrayList<Villano> listaVillano = new ArrayList<Villano>();
		MongoCursor<Document> resultDocument = Main.villano.find().iterator();

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

	static ArrayList<Director> leerDirector() {
		ArrayList<Director> listaDirector = new ArrayList<Director>();
		MongoCursor<Document> resultDocument = Main.director.find().iterator();

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

	public static void eliminarVillanoCascada(String id) {
		Document findDocument = new Document("idPelicula", new ObjectId(id));
		Main.villano.deleteMany(findDocument);
	}

	public static void eliminarVillano(String id) {
		Document findDocument = new Document("_id", new ObjectId(id));
		Main.villano.deleteMany(findDocument);
	}

	public static void eliminarPrincesa(String id) {
		Document findDocument = new Document("_id", new ObjectId(id));
		Main.princesa.deleteMany(findDocument);
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
		Main.pelicula.updateMany(findDocument2, updateDocument2);
		System.out.println("Update ejecutado");
	}

	public static void eliminarPrincipe(String id) {
		Document findDocument = new Document("_id", new ObjectId(id));
		Main.principe.deleteMany(findDocument);
	}

	public static void eliminarDirector(String id) {
		Document findDocument = new Document("_id", new ObjectId(id));
		Main.director.deleteMany(findDocument);
	}

	public static void eliminarDirectorCascada(String id) {
		Document findDocument = new Document("idPelicula", new ObjectId(id));
		Main.director.deleteMany(findDocument);
	}

	public static void eliminarPrincipeCascada(String id) {
		Document findDocument = new Document("idPelicula", new ObjectId(id));
		Main.principe.deleteMany(findDocument);
	}

	public static void eliminarPrincesaCascada(String id) {
		Document findDocument = new Document("idPelicula", new ObjectId(id));
		Main.princesa.deleteMany(findDocument);
	}

}
