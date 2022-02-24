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
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class Logica {
	// Incializar las variables necesarias para el tema
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\033[1;91m";
	public static String ANSI_TEMA = "";
	public static String ANSI_TEMA1 = "";
	public static String ANSI_TEMA2 = "";
	public static String ANSI_TEMA3 = "";
	public static String ANSI_TEMA4 = "";
	public static String ANSI_TEMA5 = "";

	// Crear las variables necesarias de mongoCollection y encriptar
	public static MongoCollection<Document> principe;
	public static MongoCollection<Document> director;
	public static MongoCollection<Document> usuario;
	public static Ecriptar en = new Ecriptar();
	public static MongoCollection<Document> pelicula = null;
	public static MongoCollection<Document> villano;
	public static MongoCollection<Document> princesa;
	public static boolean BOOLEAN = false;

	// Menu principal
	public static void inicioSesion() {
		// Se crean losdiferentes temas antes de inciar y se hace que el usuario elija
		// una ocpciion para que sienta que el programa es suyo
		ANSI_TEMA = "\033[1;94m";
		ANSI_TEMA1 = "\u001B[35m";
		ANSI_TEMA2 = "\u001b[36;1m";
		ANSI_TEMA3 = "\u001b[32m";
		ANSI_TEMA4 = "\u001b[33;1m";
		ANSI_TEMA5 = "\u001b[35;1m";
		// Se imprime un simpatiquisimo menu
		menuTema();

		// Se imprimi el menu principal
		boolean salir = true;
		int i = 0;
		while (salir) {
			if (Logica.BOOLEAN) {
				Logica.limpiarConsola();
				// Si se cierra sesion informa al usuario de una forma grandiosa
				System.out.println("¡Se ha cerrado sesion!");
				Logica.BOOLEAN = false;
			}
			// Imprimir menu
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
			// Depende de que ocion sale una cosa u otra
			switch (ir) {
				case "1":
					boolean b = true;
					while (b) {
						// Se inicia sesion perfectamente
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
						MongoCursor<Document> us = Logica.usuario.find(findDocument).iterator();
						String dato1 = "";
						String dato2 = "";
						while (us.hasNext()) {
							Document p = us.next();
							dato1 = p.getString("Password");
							dato2 = p.getString("Rol");
						}
						// Se encripta la contraseñaf
						if (dato1.equals(Logica.en.ecnode((new String(passwordArray))))) {

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
							// Numero de intentos
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
					// Registro
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

	// Menu de eleccion de tema
	private static void menuTema() {
		System.out.println(" _________________________");
		System.out.println("|                         |");
		System.out.println("|      ELECCION TEMA      |");
		System.out.println(" _________________________");
		System.out.println(" __________________________");
		System.out.println("|                          |");
		System.out.println(
				"| " + ANSI_TEMA + "1. Azul   " + ANSI_RESET + "|" + ANSI_TEMA3 + "  4. Verde" + ANSI_RESET + "    |");
		System.out.println(
				"| " + ANSI_TEMA1 + "2. Morado " + ANSI_RESET + "|" + ANSI_TEMA4 + "  5. Amarillo" + ANSI_RESET + " |");
		System.out.println("| " + ANSI_TEMA2 + "3. Cian   " + ANSI_RESET + "|" + ANSI_TEMA5 + "  6. Rosa" + ANSI_RESET
				+ "     | ");
		System.out.println("|__________________________|");
		// Se pide all usuario que introduzca uno
		System.out.print("Antes de empezar, ¿que color prefieres?: ");

		Scanner color = new Scanner(System.in);
		// Y se establece
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
			System.out.println("¡Al no exitir la opcion, se establecera el azul!");
		}
		System.out.println("");
	}

	// Se imprime la tabla en una tabla bonitaa
	public static void imprimirTablaPrincipes() {
		// Se tarata de pasarle los adtos y decirle cuales van a ser los header y cuales
		// van a ser los rows de la tabla y asi el propio metodo se encarga de hacerlo
		// bonito ala vista del usuarioF
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
		// Se limpia consola
		Logica.limpiarConsola();
		// Se imprime la tabla una vez creada
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

	// Metodo que hace referencia al menu del usuario pues el usuario solo tiene
	// permiso de consultar y ya
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
					// Si una opcion no se elige bien pues eso
					System.out.println("Has introducido una opcion que no existe :(");
					break;
			}
		}
	}

	// Se imprime el campo de volver atra con el tema establecido
	public static void btnAtras() {
		System.out.println(ANSI_TEMA + " _________________________");
		System.out.println("|                         |");
		System.out.println("| Enter para volver Atras |");
		System.out.println(" _________________________" + ANSI_RESET);
	}

	// Se imprime la tabla en una tabla bonitaa
	public static void imprimirTablaPeliculas() {
		ArrayList<Pelicula> pelis = Logica.leerPelicula();
		List<String> headersList = new ArrayList<>();
		// Se poenen los haders para poder ponerlso enla cabecera obviamente
		headersList.add("Titulo");
		headersList.add("Titulo Original");
		List<List<String>> rowsList = new ArrayList<>();
		for (Pelicula peli : pelis) {
			// Se indican los rows
			List<String> row = new ArrayList<>();
			row.add(peli.getTitulo());
			row.add(peli.getTituloOriginal());
			rowsList.add(row);
		}
		Logica.limpiarConsola();
		System.out.println(GenerarTabla.generateTable(headersList, rowsList));

		// Sigue la misma logica anterior
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

		// Sigue la misma logica anterior
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
			row.add(peli.getSinopsis().length() > 30 ? (peli.getSinopsis()).substring(0, 30) + "..."
					: peli.getSinopsis());
			rowsList2.add(row);
		}
		System.out.println(GenerarTabla.generateTable(headersList2, rowsList2));
	}

	// Se imprime la tabla en una tabla bonitaa
	static void imprimirTablavillanos() {
		ArrayList<Villano> villanos = Logica.leerVillanos();
		List<String> headersList = new ArrayList<>();
		// Se indican las cabeceras
		headersList.add("Nombre");
		headersList.add("Edad");
		headersList.add("Ciudad");
		headersList.add("Vehiculo");
		headersList.add("Email");
		headersList.add("Genero");
		headersList.add("Creacion");
		// y los datos impresos en orden
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
		// Sifgue la misma logica anterior
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

	// Se imprime la tabla en una tabla bonitaa
	static void imprimirTablaDirector() {
		ArrayList<Director> directores = Logica.leerDirector();
		List<String> headersListDirectores = new ArrayList<>();
		// Se indican las cabeceras
		headersListDirectores.add("Nombre");
		headersListDirectores.add("Edad");
		headersListDirectores.add("Ciudad de nacimiento");
		headersListDirectores.add("Vehiculo");
		headersListDirectores.add("Email");
		headersListDirectores.add("Genero");
		headersListDirectores.add("Ip_movil");
		List<List<String>> rowsListDirector = new ArrayList<>();
		// Se indican los datos
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
		// Sifgue la misma logica anterior
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

	// Se imprime la tabla en una tabla bonitaa
	static void imprimirTablaPrincesa() {
		ArrayList<Princesa> princesas = Logica.leerPrincesa();
		List<String> headersListprincesa = new ArrayList<>();
		// Se indican las cabeceras
		headersListprincesa.add("Nombre");
		headersListprincesa.add("Edad");
		headersListprincesa.add("Ciudad");
		headersListprincesa.add("Vehiculo");
		headersListprincesa.add("Email");
		headersListprincesa.add("Genero");
		headersListprincesa.add("Creacion");
		List<List<String>> rowsListprincesa = new ArrayList<>();
		// Se indican los datos
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

		// Sifgue la misma logica anterior
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

	// Proceso que lanza un cmd y ejecuta el comento que abre chrome con el trailer
	// de cada peli
	static void procesoVerTrailer(String url) {
		try {
			new ProcessBuilder("cmd.exe", "/c", "start chrome " + url).inheritIO().start().waitFor();
		} catch (Exception e) {
			/* No hacer nada */
		}
	}

	// Simplemnete se añade un salto de linea pra que sea algo mas limpio, se probo
	// con un cls pero el programa como que csolo oculta y al hacer varios print
	// imprime lo anterior asi que lo mas optimo fue hacer un salto de linea pra
	// disipar un poco la atencion de lo anterior
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

	// Se imprime el boton atras y se hace una pausa
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

	// Se pregunta que trailer se quiere observar
	static int pregunta(Scanner sc) {
		System.out.println("¿Que trailer quieres ver?");
		String algo = sc.nextLine();
		int peli = Integer.parseInt(algo);
		return peli;
	}

	// Metodo que pide confirmacion antes de borrar
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
		} else if (sn.toLowerCase().equals("n")) {
			System.out.println("Se cancelo exitosamente!!");
		} else {
			System.out.println("como es una opcion extraña se cancelara");
		}
	}

	// Metodo que pide confirmacion antes de borrar
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
		} else if (sn.toLowerCase().equals("n")) {
			System.out.println("Se cancelo exitosamente!!");
		} else {
			System.out.println("como es una opcion extraña se cancelara");
		}
	}

	// Metodo que pide confirmacion antes de borrar
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
		} else if (sn.toLowerCase().equals("n")) {
			System.out.println("Se cancelo exitosamente!!");
		} else {
			System.out.println("como es una opcion extraña se cancelara");
		}
	}

	// Metodo que pide confirmacion antes de borrar
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
		} else if (sn.toLowerCase().equals("n")) {
			System.out.println("Se cancelo exitosamente!!");
		} else {
			System.out.println("como es una opcion extraña se cancelara");
		}
	}

	// Metodo que pide confirmacion antes de borrar
	static void eliminarPeliculaMenuCascada(Scanner sc) {
		// se muestra las peliculas y se eliminar en cascada
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
		} else if (sn.toLowerCase().equals("n")) {
			System.out.println("Se cancelo exitosamente!!");
		} else {
			System.out.println("como es una opcion extraña se cancelara");
		}
	}

	// Metodo que pide confirmacion antes de borrar
	static void eliminarPeliculaMenu(Scanner sc) {
		// elegi la pelicula y elimina la pelicula elegida
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
		} else if (sn.toLowerCase().equals("n")) {
			System.out.println("Se cancelo exitosamente!!");
		} else {
			System.out.println("como es una opcion extraña se cancelara");
		}
	}

	// Metodo que se encarga de motrar la sinopsis de una pelicula seleccioanddaf
	static void leerSinopsis(Scanner sc) {
		ArrayList<Pelicula> pelis = Logica.leerPelicula();
		// mostramos las peliculas
		for (int i = 0; i < pelis.size(); i++) {
			System.out.println(i + ". " + pelis.get(i).getTitulo());
		}
		System.out.println("¿Cual es la sinopsis que mas te interesa?");
		String leeropcion = sc.nextLine();
		String sinopsis = pelis.get(Integer.parseInt(leeropcion)).getSinopsis();
		String sinopsisPeli = pelis.get(Integer.parseInt(leeropcion)).getTitulo();
		// mostramos la sipnosis sin cortar
		System.out.println(sinopsisPeli + ": " + sinopsis);
	}

	// menu que se imprime para gestionar los datos, algoq ue solo pùede hacer el
	// admin
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
					Logica.editarOpcines(sc);
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

	// metodo para insetar un nuevo usuario
	public static void insertarUsuario(Usuario usr) {
		// se crea un documento con los datos nuevos
		Logica.usuario.insertOne(new Document()
				.append("_id", new ObjectId())
				.append("Usuario", usr.getUser())
				.append("Password", usr.getPasswword())
				.append("Rol", usr.getRol()));
	}

	// Menu del admin, puede ver y gestionar los datos
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

	// Menu para motrar las opcipones de las cosas que se pueden hacer
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
			System.out.println("| 9. Personajes de cada pelicula    |");
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
				case "9":
					limpiarConsola();

					System.out.println(ANSI_TEMA + " ______________________________________________ ");
					System.out.println("|                                              |");
					System.out.println("|                VER PERSONAJES                |");
					System.out.println(" ______________________________________________" + ANSI_RESET);
					System.out.println("");
					subconsultaConJoin();
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

	// Menu que se encarga de mostrar las ociones de inserccion
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

	// Metodo que se encaraga de la inserccion de datos por parte del usuario
	static void insertarDirectorMenu(Scanner sc) {
		// Pedimos los datos al usuario para insertar en la coleccion
		System.out.println(ANSI_TEMA + " _____________________________");
		System.out.println("|                            |");
		System.out.println("|      INSERTAR DIRECTOR     |");
		System.out.println(" ____________________________" + ANSI_RESET);
		System.out.println("Introduce un nueva Director:");
		System.out.print("Nombre: ");
		String nombre = sc.nextLine();
		System.out.print("Edad: ");
		int edad = 0;
		while (!sc.hasNextInt()) {
			System.out.print("Edad: ");
			sc.nextLine();

		}
		edad = sc.nextInt();
		sc.nextLine();
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
		int idlista = comprobarNumero(c, sc);
		String idPelicula = c.get(idlista).getId();
		Director d = new Director(null, nombre, edad, ciudadNacimiento, vehiculo, email, genero,
				ipMovil, ipAddress, empresa, titulacion);
		System.out.println("¿Estas seguro de añadir la director?S/N");
		String respuesta = sc.nextLine();
		if (respuesta.toLowerCase().equals("s")) {
			// metodo para insertar
			Logica.insertarDirector(d, idPelicula);

			System.out.println("Se ha añadido la director");
		} else {
			System.out.println("No se ha añadido la director");
		}

	}

	// Metodo que se encaraga de la inserccion de datos por parte del usuario
	static void insertarPrincesaMenu(Scanner sc) {
		// Pedimos los datos al usuario para insertar en la coleccion
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
		int idlista = comprobarNumero(c, sc);

		String idPelicula = c.get(idlista).getId();
		Princesa princesa = new Princesa(null, nombre, edad, ciudad, vehiculo, email, genero,
				creacion, ip_address, universidad, titulacion);
		System.out.println("¿Estas seguro de añadir la princesa?S/N");
		String respuesta = sc.nextLine();
		if (respuesta.toLowerCase().equals("s")) {
			// llama al metodo de insertar princesa
			Logica.insertarPrincesa(princesa, idPelicula);
			System.out.println("Se ha añadido la principe");
		} else {
			System.out.println("No se ha añadido la principe");
		}

	}

	// Metodo que se encaraga de la inserccion de datos por parte del usuario
	static void insertarPrincipeMenu(Scanner sc) {
		// Pedimos los datos al usuario para insertar en la coleccion
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

		int idlista = comprobarNumero(c, sc);
		String idPelicula = c.get(idlista).getId();
		Principe principe = new Principe(null, nombre, edad, ciudad, vehiculo, email, genero,
				creacion, ip_address, universidad, titulacion);
		System.out.println("¿Estas seguro de añadir al principe?S/N");
		String respuesta = sc.nextLine();
		if (respuesta.toLowerCase().equals("s")) {
			// Metodo insertar
			Logica.insertarPrincipe(principe, idPelicula);
			System.out.println("Se ha añadido la principe");
		} else {
			System.out.println("No se ha añadido la principe");
		}

	}

	// Metodo que se encaraga de la inserccion de datos por parte del usuario
	static void insertarVillanoMenu(Scanner sc) {
		// Pedimos los datos al usuario para insertar en la coleccion
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
		int idlista = comprobarNumero(c, sc);
		String idPelicula = c.get(idlista).getId();

		Villano villano = new Villano(null, nombre, edad, ciudad, vehiculo, email, genero,
				creacion, ip_address, universidad, titulacion);
		System.out.println("¿Estas seguro de añadir la Villano?S/N");
		String respuesta = sc.nextLine();
		if (respuesta.toLowerCase().equals("s")) {
			// metodo insertar
			Logica.insertarVillano(villano, idPelicula);
			System.out.println("Se ha añadido la Villano");
		} else {
			System.out.println("No se ha añadido la Villano");
		}

	}

	// Metodo que se encarga de comprobar si el entrarte es un numero que se
	// encuatra en la lista y es un numero
	private static int comprobarNumero(Map<Integer, Pelicula> c, Scanner sc) {
		// Metodo para comprobar que el dato que introduce esta en la lista
		int idlista = 0;
		while (true) {
			System.out.print("Elige una pelicula: ");
			String prueba = sc.nextLine();
			int b;
			try {
				b = Integer.parseInt(prueba);
				int a = c.size();
				if (((b <= a) && (b >= 1))) {
					idlista = b;
					break;
				} else {

					System.out.println("Has introducido una opcion que no existe :(");
				}
			} catch (Exception e) {
				System.out.println("Has introducido una opcion que no existe :(");
			}
		}
		return idlista;
	}

	static void insertarPeliculaMenu(Scanner sc) {
		// Pedimos los datos al usuario para insertar en la coleccion
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
		// confirmacion para insertar
		if (respuesta.toLowerCase().equals("s")) {
			// llamamos al metodo para insertar
			Logica.insertarPelicula(pelicula);
			System.out.println("Se ha añadido la pelicula");
		} else {
			System.out.println("No se ha añadido la pelicula");
		}
	}

	// metodo para insertar
	public static void insertarDirector(Director d, String idPelicula) {
		// Se crea un documento con los campos que se quiere insertar y se insertar el
		// la collecion
		Logica.director.insertOne(new Document()
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

	// metodo para insertar
	public static void insertarVillano(Villano p, String idPelicula) {
		// Se crea un documento con los campos que se quiere insertar y se insertar el
		// la collecion
		Logica.villano.insertOne(new Document()
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

	// metodo para insertar
	public static void insertarPrincipe(Principe p, String idPelicula) {
		// Se crea un documento con los campos que se quiere insertar y se insertar el
		// la collecion
		Logica.principe.insertOne(new Document()
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

	// metodo para insertar
	public static void insertarPrincesa(Princesa p, String idPelicula) {
		// Se crea un documento con los campos que se quiere insertar y se insertar el
		// la collecion
		Logica.princesa.insertOne(new Document()
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

	// metodo para insertar
	public static void insertarPelicula(Pelicula p) {
		// Se crea un documento con los campos que se quiere insertar y se insertar el
		// la collecion
		Logica.pelicula.insertOne(new Document()
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

	/************** RELACIONAR *****************/
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
		// documento con el id que se quiere eliminar
		Document findDocument = new Document("_id", new ObjectId(id));
		// elimnar
		Logica.pelicula.deleteMany(findDocument);
	}

	static void borrarOpciones(Scanner sc) {
		// menu para borrar
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
		// registrar el usuario por defecto se le pone el rol de usuario
		System.out.print("Introduce el nuevo user: ");
		String nom = sc.nextLine();
		System.out.println();
		char[] passwordArray = console.readPassword("Introduce una contraseña: ");
		String rol = "usuario";
		// Encriptar la contraseña
		Usuario usr = new Usuario(nom, Ecriptar.ecnode(new String(passwordArray)), rol);
		insertarUsuario(usr);
		limpiarConsola();
		System.out.println("Ya puedes iniciar sesion " + nom + "!!");
	}

	static void registro(Scanner sc) {
		// Registra Usuaario
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
		// elegir el rol
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
		// Encriptar la contraseña
		Usuario usr = new Usuario(nom, Ecriptar.ecnode(pwd), rol);
		// metodo para insertar Usuario en mognodb
		insertarUsuario(usr);
		System.out.println("Usuario insertado!!");

	}

	static Map<Integer, Pelicula> leerListaPelisTrailer() {
		Map<Integer, Pelicula> c = Logica.leerPeliculaNombre2();
		// mostrar listas
		c.forEach((k, v) -> System.out.println(k + ". " + v.getTitulo()));
		return c;
	}

	public static void directoresPorGenero() {

		MongoDatabase database = Conexion.conexionMongoDB();
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

		// Crear Grafico
		JFreeChart chart = ChartFactory.createPieChart3D("Genero de los directores", dataset, true, true, true);
		// Mostrar el grafico
		ChartFrame frame = new ChartFrame("Genero de los directores", chart);
		frame.pack();
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);

	}

	static void crearColecciones(MongoDatabase database) {
		// Crear las colletion en mongodb
		database.createCollection("Princesa");
		database.createCollection("Principe");
		database.createCollection("Villano");
		database.createCollection("Pelicula");
		database.createCollection("Director");
		database.createCollection("Usuario");
	}

	public static void obtenerColecciones(MongoDatabase database) {
		// obtener las colecciones para despues trabajar con ellas
		Logica.pelicula = database.getCollection("Pelicula");
		Logica.princesa = database.getCollection("Princesa");
		Logica.villano = database.getCollection("Villano");
		Logica.principe = database.getCollection("Principe");
		Logica.director = database.getCollection("Director");
		Logica.usuario = database.getCollection("Usuario");
	}

	static void relacionPrincesa(String idPelicula, String idPrincesa) {
		// Metodo para la relacion de Princiesa
		Document findDocument2 = new Document("_id", new ObjectId(idPrincesa));
		Document updateDocument2 = new Document("$set",
				new Document("idPelicula", new ObjectId(idPelicula)));
		Logica.princesa.updateMany(findDocument2, updateDocument2);
		System.out.println("Update ejecutado");
	}

	static void relacionVillano(String idPelicula, String idVillano) {
		// Metodo para la relacion de Villano
		Document findDocument2 = new Document("_id", new ObjectId(idVillano));
		Document updateDocument2 = new Document("$set",
				new Document("idPelicula", new ObjectId(idPelicula)));
		Logica.villano.updateMany(findDocument2, updateDocument2);
		System.out.println("Update ejecutado");
	}

	static void relacionPrincipe(String idPelicula, String idPrincipe) {
		// Metodo para la relacion de Principe
		Document findDocument = new Document("_id", new ObjectId(idPrincipe));
		Document updateDocument = new Document("$set",
				new Document("idPelicula", new ObjectId(idPelicula)));
		Logica.principe.updateMany(findDocument, updateDocument);
		System.out.println("Update ejecutado");
	}

	static void relacionDirector(String idPelicula, String idDirector) {
		// Metodo para la relacion de director
		// Documento con el id del director para filtrar
		Document findDocument = new Document("_id", new ObjectId(idDirector));
		// Documento que añade un campo o modifica la id
		Document updateDocument = new Document("$set",
				new Document("idPelicula", new ObjectId(idPelicula)));
		// Actualiza
		Logica.director.updateMany(findDocument, updateDocument);
		System.out.println("Update ejecutado");
	}

	static ArrayList<Princesa> leerPrincesa() {
		// Metodo para leer una coleccion de mongodb y pasarlo a una lista
		ArrayList<Princesa> listaprincesas = new ArrayList<Princesa>();
		MongoCursor<Document> resultDocument = Logica.princesa.find().iterator();

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
		// Metodo para leer una coleccion de mongodb y pasarlo a una lista
		ArrayList<Pelicula> listapPeliculas = new ArrayList<Pelicula>();
		MongoCursor<Document> resultDocument = Logica.pelicula.find().iterator();

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
		// Insertar Colletion para eso creamos un documento con los campos que se van a
		// introducir
		Logica.princesa.insertOne(new Document()
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
		// Metodo para leer una coleccion de mongodb y pasarlo a una lista
		Map<Integer, Pelicula> a = new HashMap<Integer, Pelicula>();
		MongoCursor<Document> resultDocument = Logica.pelicula.find().iterator();
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
		// Metodo para leer una coleccion de mongodb y pasarlo a una lista
		ArrayList<Principe> listaPrincipe = new ArrayList<Principe>();
		MongoCursor<Document> resultDocument = Logica.principe.find().iterator();

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
		// Metodo para leer una coleccion de mongodb y pasarlo a una lista
		ArrayList<Villano> listaVillano = new ArrayList<Villano>();
		MongoCursor<Document> resultDocument = Logica.villano.find().iterator();

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
		// Metodo para leer una coleccion de mongodb y pasarlo a una lista
		ArrayList<Director> listaDirector = new ArrayList<Director>();
		MongoCursor<Document> resultDocument = Logica.director.find().iterator();

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
		// Documento con el id de la pelicula que se quiere eliminar
		Document findDocument = new Document("idPelicula", new ObjectId(id));
		// eliminar
		Logica.villano.deleteMany(findDocument);
	}

	public static void eliminarVillano(String id) {
		// Documento con el id de la Villano que se quiere eliminar
		Document findDocument = new Document("_id", new ObjectId(id));
		// eliminar
		Logica.villano.deleteMany(findDocument);
	}

	public static void eliminarPrincesa(String id) {
		// Documento con el id de la Princesa que se quiere eliminar
		Document findDocument = new Document("_id", new ObjectId(id));
		// eliminar
		Logica.princesa.deleteMany(findDocument);
	}

	public static void eliminarPrincipe(String id) {
		// Documento con el id de la Principe que se quiere eliminar
		Document findDocument = new Document("_id", new ObjectId(id));
		// eliminar
		Logica.principe.deleteMany(findDocument);
	}

	public static void eliminarDirector(String id) {
		// Documento con el id de Director que se quiere eliminar
		Document findDocument = new Document("_id", new ObjectId(id));
		// eliminar
		Logica.director.deleteMany(findDocument);
	}

	public static void eliminarDirectorCascada(String id) {
		// Documento con el id de la pelicula que se quiere eliminar
		Document findDocument = new Document("idPelicula", new ObjectId(id));
		// eliminar
		Logica.director.deleteMany(findDocument);
	}

	public static void eliminarPrincipeCascada(String id) {
		// Documento con el id de la pelicula que se quiere eliminar
		Document findDocument = new Document("idPelicula", new ObjectId(id));
		// eliminar
		Logica.principe.deleteMany(findDocument);
	}

	public static void eliminarPrincesaCascada(String id) {
		// Documento con el id de la pelicula que se quiere eliminar
		Document findDocument = new Document("idPelicula", new ObjectId(id));
		// eliminar
		Logica.princesa.deleteMany(findDocument);
	}

	public static void editarDirector(String id, Director p) {
		// documento para filtrar por id
		Document findDocument2 = new Document("_id", new ObjectId(id));
		// documento con los campos que se va a modificar
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
		// Se actualiza en mongodb
		Logica.director.updateMany(findDocument2, updateDocument2);
		System.out.println("Cambios reallizados!");
	}

	public static void editarPricensa(String id, Princesa p) {
		// documento para filtrar por id
		Document findDocument2 = new Document("_id", new ObjectId(id));
		// documento con los campos que se va a modificar
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
		// Se actualiza en mongodb
		Logica.princesa.updateMany(findDocument2, updateDocument2);
		System.out.println("Cambios reallizados!");
	}

	public static void editarVillano(String id, Villano p) {
		// documento para filtrar por id
		Document findDocument2 = new Document("_id", new ObjectId(id));
		// documento con los campos que se va a modificar
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
		// Se actualiza en mongodb
		Logica.villano.updateMany(findDocument2, updateDocument2);
		System.out.println("Cambios reallizados!");
	}

	public static void editarPrincipe(String id, Principe p) {
		// documento para filtrar por id
		Document findDocument2 = new Document("_id", new ObjectId(id));
		// documento con los campos que se va a modificar
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
		// Se actualiza en mongodb
		Logica.principe.updateMany(findDocument2, updateDocument2);
		System.out.println("Cambios reallizados!");
	}

	public static void menuModificarPrincesa() {
		// Menu modificar princesa
		System.out.println(ANSI_TEMA + " _____________________________");
		System.out.println("|                            |");
		System.out.println("|       EDITAR PRINCESA      |");
		System.out.println(" ____________________________" + ANSI_RESET);
		// lista de princesas de mongodb
		ArrayList<Princesa> princesas = leerPrincesa();
		for (int i = 0; i < princesas.size(); i++) {
			// mostramos las princesa
			System.out.println(i + ". " + princesas.get(i).getNombre() + " version " + princesas.get(i).getCreacion());
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("");
		System.out.print(ANSI_TEMA + "Escoge la princesa que quieres editar: " + ANSI_RESET);
		String num = sc.nextLine();
		Princesa princesa = princesas.get(Integer.parseInt(num));
		String menup = "1. Nombre\n2. Edad\n3. Ciudad\n4. Vehiculo\n5. Email\n6. Genero\n7. Creacion\n8. ip_address\n9. Universidad\n10.Titulacion";
		System.out.println(menup);
		System.out.println("");
		System.out.print(ANSI_TEMA + "Escoge el campo que quieres cambiar: " + ANSI_RESET);
		// Pedimos al usuario que modificque un campo
		String resul = sc.nextLine();
		// Segun la opcion modifica una cosa o otra
		System.out.print("Introduce el nuevo dato: ");
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
				System.out.println("Has introducido una opcion que no existe :(");
				break;
		}

	}

	public static void menuModificarPrincipe() {
		// Menu para modificar Principe
		System.out.println(ANSI_TEMA + " _____________________________");
		System.out.println("|                            |");
		System.out.println("|       EDITAR PRINCIPE      |");
		System.out.println(" ____________________________" + ANSI_RESET);
		// ArrayList de principes de monogodb
		ArrayList<Principe> principes = leerPrincipes();
		for (int i = 0; i < principes.size(); i++) {
			// Mostrar el principe
			System.out.println(i + ". " + principes.get(i).getNombre() + " version " + principes.get(i).getCreacion());
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("");
		System.out.print(ANSI_TEMA + "Escoge la principe que quieres editar: " + ANSI_RESET);
		String num = sc.nextLine();
		Principe principe = principes.get(Integer.parseInt(num));
		String menup = "1. Nombre\n2. Edad\n3. Ciudad\n4. Vehiculo\n5. Email\n6. Genero\n7. Creacion\n8. ip_address\n9. Universidad\n10.Titulacion";
		System.out.println(menup);
		System.out.println("");
		System.out.print(ANSI_TEMA + "Escoge el campo que quieres cambiar: " + ANSI_RESET);
		// Para modificar la opcion adecuada
		String resul = sc.nextLine();
		System.out.print("Introduce el nuevo dato: ");
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
				System.out.println("Has introducido una opcion que no existe :(");
				break;
		}

	}

	public static void menuModificarVillano() {
		// Metodo menu modificar Villano
		System.out.println(ANSI_TEMA + " _____________________________");
		System.out.println("|                            |");
		System.out.println("|       EDITAR VILLANO       |");
		System.out.println(" ____________________________" + ANSI_RESET);
		ArrayList<Villano> villano = leerVillanos();
		for (int i = 0; i < villano.size(); i++) {
			// mostrar villano para que el usuario eligan uno
			System.out.println(i + ". " + villano.get(i).getNombre() + " version " + villano.get(i).getCreacion());
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("");
		System.out.print(ANSI_TEMA + "Escoge la villano que quieres editar: " + ANSI_RESET);
		String num = sc.nextLine();
		Villano v = villano.get(Integer.parseInt(num));
		String menup = "1. Nombre\n2. Edad\n3. Ciudad\n4. Vehiculo\n5. Email\n6. Genero\n7. Creacion\n8. ip_address\n9. Universidad\n10.Titulacion";
		System.out.println(menup);
		System.out.println("");
		System.out.print(ANSI_TEMA + "Escoge el campo que quieres cambiar: " + ANSI_RESET);
		String resul = sc.nextLine();
		// para modificar la opcion
		System.out.print("Introduce el nuevo dato: ");
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
				System.out.println("Has introducido una opcion que no existe :(");
				break;
		}

	}

	public static void menuModificarPelicula() {
		// metodo para menu modificar Pelicula
		System.out.println(ANSI_TEMA + " _____________________________");
		System.out.println("|                            |");
		System.out.println("|       EDITAR PELICULA      |");
		System.out.println(" ____________________________" + ANSI_RESET);
		ArrayList<Pelicula> pelicula = leerPelicula();
		for (int i = 0; i < pelicula.size(); i++) {
			// mostramos la peliculas
			System.out.println(i + ". " + pelicula.get(i).getTitulo() + ": Año de estreno " + pelicula.get(i).getAno());
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("");
		System.out.print(ANSI_TEMA + "Escoge la pelicula que quieres editar: " + ANSI_RESET);
		String num = sc.nextLine();
		// cogemos la pelicula seleccionada
		Pelicula peli = pelicula.get(Integer.parseInt(num));
		String menup = "1. Tiltulo\n2. Titulo original\n3. Año de estreno\n4. Duración\n5. Pais de rodaje\n6. Guion\n7. Música\n8. Fotografia\n9. Reparto\n10.Sinopsis";
		System.out.println(menup);
		System.out.println("");
		System.out.print(ANSI_TEMA + "Escoge el campo que quieres cambiar: " + ANSI_RESET);
		String resul = sc.nextLine();
		// Para modificar el campo seleccionado
		System.out.print("Introduce el nuevo dato: ");
		switch (resul) {
			case "1":
				String n = sc.nextLine();
				peli.setTitulo(n);
				Logica.editarPelicula(peli.getId(), peli);
				break;
			case "2":
				String tit = sc.nextLine();
				peli.setTituloOriginal(tit);
				Logica.editarPelicula(peli.getId(), peli);
				break;
			case "3":
				String ciu = sc.nextLine();
				peli.setAno(ciu);
				Logica.editarPelicula(peli.getId(), peli);
				break;
			case "4":
				String vei = sc.nextLine();
				peli.setDuracion(vei);
				Logica.editarPelicula(peli.getId(), peli);
				break;
			case "5":
				String emai = sc.nextLine();
				peli.setPais(emai);
				Logica.editarPelicula(peli.getId(), peli);
				break;
			case "6":
				String genero = sc.nextLine();
				peli.setGuion(genero);
				Logica.editarPelicula(peli.getId(), peli);
				break;
			case "7":
				String creacion = sc.nextLine();
				peli.setMusica(creacion);
				Logica.editarPelicula(peli.getId(), peli);
				break;
			case "8":
				String ipAddress = sc.nextLine();
				peli.setFotografia(ipAddress);
				Logica.editarPelicula(peli.getId(), peli);
				break;
			case "9":
				String universidad = sc.nextLine();
				peli.setReparto(universidad);
				Logica.editarPelicula(peli.getId(), peli);
				break;
			case "10":
				String titulacion = sc.nextLine();
				peli.setSinopsis(titulacion);
				Logica.editarPelicula(peli.getId(), peli);
				break;
			default:
				System.out.println("Has introducido una opcion que no existe :(");
				break;
		}

	}

	public static void menuModificarDirector() {
		// menu para modificarDirector
		System.out.println(ANSI_TEMA + " _____________________________");
		System.out.println("|                            |");
		System.out.println("|       EDITAR DIRECTOR      |");
		System.out.println(" ____________________________" + ANSI_RESET);
		ArrayList<Director> directores = leerDirector();
		for (int i = 0; i < directores.size(); i++) {
			// mostramos los directores
			System.out.println(i + ". " + directores.get(i).getDirector());
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("");
		System.out.print(ANSI_TEMA + "Escoge el director que quieres editar: " + ANSI_RESET);
		String num = sc.nextLine();
		Director d = directores.get(Integer.parseInt(num));
		String menup = "1. Nombre\n2. Edad\n3. Ciudad nacimiento\n4. Vehiculo\n5. Email\n6. Genero\n7. ip_movi\n8. ip_address\n9. Empresa\n10.Titulacion";
		System.out.println(menup);
		System.out.println("");
		System.out.print(ANSI_TEMA + "Escoge el campo que quieres cambiar: " + ANSI_RESET);
		// Elegir la opcion adecuada para modificar
		String resul = sc.nextLine();
		System.out.print("Introduce el nuevo dato: ");
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
				System.out.println("Has introducido una opcion que no existe :(");
				break;
		}

	}

	static void editarOpcines(Scanner sc) {
		limpiarConsola();
		// Menu para editar las colecciones
		boolean a2 = true;
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
				menuModificarPelicula();
				volverAtras();
				break;
			case "2":
				limpiarConsola();
				menuModificarPrincesa();
				volverAtras();
				break;
			case "3":
				limpiarConsola();
				menuModificarDirector();
				volverAtras();
				break;
			case "4":
				limpiarConsola();
				menuModificarVillano();
				volverAtras();
				break;
			case "5":
				limpiarConsola();
				menuModificarPrincipe();
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

	public static void editarPelicula(String id, Pelicula p) {
		// Metodo para editar Pelicula
		// Filtramos por el id que para editar esa pelicula
		Document findDocument2 = new Document("_id", new ObjectId(id));
		// Creamos el documento con los campos que vamos a editar
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
		// Actualizamoe en el documento en la colecion
		Logica.pelicula.updateMany(findDocument2, updateDocument2);
		System.out.println("Cambios reallizados!");
	}

	static void subconsultaConJoin() {
		// Creamos la conexion, la lista de Bson
		MongoDatabase database = Conexion.conexionMongoDB();
		List<Bson> filters = new ArrayList<>();

		// Creamos los look up haciendo referencia a lo que buscamos
		Bson lookupPrincesa = new Document("$lookup",
				new Document("from", "Princesa")
						.append("localField", "_id")
						.append("foreignField", "idPelicula")
						.append("as", "look_collPrincesa"));

		Bson lookupPrincipe = new Document("$lookup",
				new Document("from", "Principe")
						.append("localField", "_id")
						.append("foreignField", "idPelicula")
						.append("as", "look_collPrincipe"));

		Bson lookupVillano = new Document("$lookup",
				new Document("from", "Villano")
						.append("localField", "_id")
						.append("foreignField", "idPelicula")
						.append("as", "look_collVillano"));

		Bson lookupDirector = new Document("$lookup",
				new Document("from", "Director")
						.append("localField", "_id")
						.append("foreignField", "idPelicula")
						.append("as", "look_collDirector"));

		// Se añaden a una lista los filtros que vamos a ejecutar
		filters.add(lookupPrincesa);
		filters.add(lookupPrincipe);
		filters.add(lookupVillano);
		filters.add(lookupDirector);

		// Recorremos con un cursor los documentos y obtenemos los datos que necesitamos
		MongoCursor<Document> it = database.getCollection("Pelicula").aggregate(filters).iterator();
		int i = 0;
		while (it.hasNext()) {
			Document next = it.next();
			// Obtenemso el array de elemntos que hay dentro de cada lookup y obtenemos el
			// dato que necesitamos
			ArrayList<Document> princesaArrayList = next.get("look_collPrincesa", ArrayList.class);
			ArrayList<Document> principeArrayList = next.get("look_collPrincipe", ArrayList.class);
			ArrayList<Document> villanoArrayList = next.get("look_collVillano", ArrayList.class);
			ArrayList<Document> directorArrayList = next.get("look_collDirector", ArrayList.class);
			System.out.println("");
			// Imprimimos
			System.out.println(
					"         _________________ " + ANSI_TEMA + i + ANSI_RESET + " _________________         ");
			System.out.println("");
			System.out.println(ANSI_TEMA + "Pelicula: " + ANSI_RESET + next.getString("Titulo"));
			// Imprimimos
			for (var bson : princesaArrayList) {
				System.out.println("	· Princesa: " + bson.getString("Nombre"));
			}
			// Imprimimos
			for (var bson : principeArrayList) {
				System.out.println("	· Principe: " + bson.getString("Nombre"));
			}
			// Imprimimos
			for (var bson : villanoArrayList) {
				System.out.println("	· Villano: " + bson.getString("Nombre"));
			}
			// Imprimimos
			for (var bson : directorArrayList) {
				System.out.println("	· Director: " + bson.getString("Director"));
			}
			System.out.println("");
			// Esto sirve para darel cabecera
			i++;
		}
	}

}
