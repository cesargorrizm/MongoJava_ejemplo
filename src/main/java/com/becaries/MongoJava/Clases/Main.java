package com.becaries.MongoJava.Clases;

import java.time.LocalDateTime;
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
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class Main {

	/**
	 *
	 */
	private static int PADDING_SIZE = 2;
	private static String NEW_LINE = "\n";
	private static String TABLE_JOINT_SYMBOL = "+";
	private static String TABLE_V_SPLIT_SYMBOL = "|";
	private static String TABLE_H_SPLIT_SYMBOL = "-";

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
		MongoDatabase database = conexionMongoDB();

		// PASO 2: Crear colecciones necesarias para el desarrollo de la aplicacion
		// crearColecciones(database);

		// PASO 3: Meter datos de los csv a la base de datos para aligerar tiempo

		// PASO 4: obtener las colecciones para poder trabajar con ellas
		obtenerColecciones(database);

		// PASO 5: DESARROLLO DE LA APLICACIÓN

		// COMIENZA LA APLICACION
		// Mostrar un menu diferente por cada opcion
		// Pedir nombre y contra antes de inciar caulquier cosa
		// Poder hacerlo personalizable (Que el usuario eliga el color del texto o el
		// color de los detalles(dos opciones para facilitar))
		inicioSesion();

		// MOSTRAR TABLA ANTES DE CUALQUIER OPERACION PARA QUE EL USUARIO PUEDA CAMBIAR
		// LOS DATOS QUE NECESITA SIN SUBIR MUY ARRIBA.

		// Pelicula pelicula =new Pelicula("null", "titulo", "titulo", "ano",
		// "duron", "p", "guon", "mua", "foafia", "reo", "s",
		// "trer");
		// editarPelicula("620d5799a4e64776d3058b13", pelicula);

		// ************************************** INSERTAR
		// ****************************************
		// insertarPrincesa(princesa);
		// Pelicula pelicula =new Pelicula("null", "titulo", "tituloOriginal", "ano",
		// "duracion", "pais", "guion", "musica", "fotografia", "reparto", "sinopsis",
		// "trailer");
		// insertarPelicula(pelicula);

		// Principe principe = new Principe("id", "nombre", "edad", "ciudad",
		// "vehiculo", "email", "genero", "creacion", "ipAddress", "universidad",
		// "titulacion");
		// insertarPrincipe(principe);

		// Villano villano = new Villano("id", "nombre", "edad", "ciudad", "vehiculo",
		// "email", "genero", "creacion", "ipAddress", "universidad", "titulacion");
		// insertarVillano(villano);

		// Director director = new Director("id", "nombre", 2, "ciudad", "vehiculo",
		// "email", "genero", "creacion", "ipAddress", "universidad", "titulacion");
		// insertarDirector(director);

		// mongoClient.close();

		// editarPelicula("6204183901e6db753d0bd2f7", "Blancanieves");

	}

	private static void inicioSesion() {
		boolean salir = true;
		int i = 0;
		while (salir) {
			if (BOOLEAN) {
				limpiarConsola();
				System.out.println("¡Se ha cerrado sesion!");
				BOOLEAN = false;
			}
			System.out.println("¡BIENVENIDO A PELNEY!");
			System.out.print("Indica tu usuario: ");
			Scanner sc = new Scanner(System.in);
			String nom = sc.nextLine();

			System.out.print("Escribe tu contraseña: ");
			String contra = sc.nextLine();

			Document findDocument = new Document("Usuario", nom);
			MongoCursor<Document> us = usuario.find(findDocument).iterator();
			String dato1 = "";
			String dato2 = "";
			while (us.hasNext()) {
				Document p = us.next();
				dato1 = p.getString("Password");
				dato2 = p.getString("Rol");
			}
			if (dato1.equals(en.ecnode(contra))) {

				if (dato2.equals("admin")) {
					limpiarConsola();
					System.out.println("Bienvenido/a " + nom + "! ");
					menu(sc);
				} else {
					limpiarConsola();
					System.out.println("Bienvenido/a " + nom + "! ");
					menuUser(sc);
				}
			} else {
				limpiarConsola();
				System.out.println("Datos incorrectos");
				i++;
				if (i == 3) {
					System.out.println("Has sobrepasado los limites don/doña!!\nVete a la kgada!!");
					salir = false;
				}
			}
		}
	}

	private static void menuUser(Scanner sc) {
		boolean b = true;
		while (b) {
			System.out.println(" _____________________");
			System.out.println("|                     |");
			System.out.println("| 1. MOSTRAR DATOS    |");
			System.out.println("| 0. SALIR            |");
			System.out.println(" _____________________");

			System.out.println("¿Que quieres hacer?");
			String opcion = sc.nextLine();
			switch (opcion) {
				case "1":
					limpiarConsola();
					boolean a = true;
					while (a) {
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
								directoresPorGenero();
								volverAtras();
								break;
							case "7":
								limpiarConsola();
								Map<Integer, Pelicula> c = leerListaPelisTrailer();
								int peli = pregunta(sc);
								procesoVerTrailer(c.get(peli).getTrailer());
								btnAtras();
								break;
							case "8":
								limpiarConsola();
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
					break;
				case "0":
					b = false;
					System.out.println("                _\n" +
							"                ,=\"` `\"\"=,       o Y\n" +
							"               / ' ,==\"\"\"'=;    A   , __\n" +
							"       ~      /.:    ,--'/=,)  o    \\`\\\\\"._     _,\n" +
							"             |   .='/ <9(9.=\"   B   / _  |||;._//)\n" +
							"            / .:' (J    ^\\ \\     o_/@ @  ///  |=(\n" +
							"          .'    .' \\  '='/  '-.  ( (`__,     ,`\\|\n" +
							"         / .'  /    \\`-;_ . '  \\  '.\\_/ |\\_.'   \n" +
							"    ~   /      | ' /` _  \\  ::' )   `\"\"```\n" +
							"       | , .'  ;  /`\\/ `\\ \\.::'/.-._///_\n" +
							"       |/     '   \\_,\\__/\\ \\.-'.'----'`\n" +
							"        \\|     '.   \\     \\   /`-,   ~\n" +
							"          `\\ '.' _.-'\\    (`-`  .'\n" +
							"            `-.-' _.-')__./,--'`\n" +
							"         .--'`,-'`'\"\"`    ` \\\n" +
							"        /`\"`-`  :'      ::'  |          ~\n" +
							"       |  .:  .::'   ::  ::' /\n" +
							"    ~  | .:' .-'__    .::  .'\n" +
							"        \\   ;'\"`  `\"\"----'`\n" +
							"         \\ .'\\\n" +
							"          '.  `\\\n" +
							"            ):' `-.            ~\n" +
							"           / :..:' `-._\n" +
							"          |  :' ,      `-,\n" +
							"       ~  \\   .' `''----`\n" +
							"           `.(");
					BOOLEAN = true;

					break;

				default:
					System.out.println("Has introducido una opcion que no existe :(");
					break;
			}
		}
	}

	private static void menu(Scanner sc) {
		boolean b = true;
		while (b) {
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
					limpiarConsola();
					boolean a = true;
					while (a) {
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
								directoresPorGenero();
								volverAtras();
								break;
							case "7":
								limpiarConsola();
								Map<Integer, Pelicula> c = leerListaPelisTrailer();
								int peli = pregunta(sc);
								procesoVerTrailer(c.get(peli).getTrailer());
								btnAtras();
								break;
							case "8":
								limpiarConsola();
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
					break;
				case "2":
					limpiarConsola();
					boolean a1 = true;
					while (a1) {
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
								limpiarConsola();
								boolean a2 = true;
								while (a2) {
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
								break;
							case "2":
								limpiarConsola();
								boolean a3 = true;
								while (a3) {
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
								break;
							case "3":
								limpiarConsola();
								boolean a5 = true;
								while (a5) {
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
											a5 = false;
											break;

										default:
											System.out.println("Has introducido una opcion que no existe :(");
											break;
									}
								}
								break;
							case "4":
								limpiarConsola();
								registro(sc);
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
					break;
				case "0":
					b = false;
					System.out.println("                _\n" +
							"                ,=\"` `\"\"=,       o Y\n" +
							"               / ' ,==\"\"\"'=;    A   , __\n" +
							"       ~      /.:    ,--'/=,)  o    \\`\\\\\"._     _,\n" +
							"             |   .='/ <9(9.=\"   B   / _  |||;._//)\n" +
							"            / .:' (J    ^\\ \\     o_/@ @  ///  |=(\n" +
							"          .'    .' \\  '='/  '-.  ( (`__,     ,`\\|\n" +
							"         / .'  /    \\`-;_ . '  \\  '.\\_/ |\\_.'   \n" +
							"    ~   /      | ' /` _  \\  ::' )   `\"\"```\n" +
							"       | , .'  ;  /`\\/ `\\ \\.::'/.-._///_\n" +
							"       |/     '   \\_,\\__/\\ \\.-'.'----'`\n" +
							"        \\|     '.   \\     \\   /`-,   ~\n" +
							"          `\\ '.' _.-'\\    (`-`  .'\n" +
							"            `-.-' _.-')__./,--'`\n" +
							"         .--'`,-'`'\"\"`    ` \\\n" +
							"        /`\"`-`  :'      ::'  |          ~\n" +
							"       |  .:  .::'   ::  ::' /\n" +
							"    ~  | .:' .-'__    .::  .'\n" +
							"        \\   ;'\"`  `\"\"----'`\n" +
							"         \\ .'\\\n" +
							"          '.  `\\\n" +
							"            ):' `-.            ~\n" +
							"           / :..:' `-._\n" +
							"          |  :' ,      `-,\n" +
							"       ~  \\   .' `''----`\n" +
							"           `.(");
					BOOLEAN = true;
					break;

				default:
					System.out.println("Has introducido una opcion que no existe :(");
					break;
			}
		}
	}

	private static void registro(Scanner sc) {
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
			System.out.println("No existe... Se le adjudicara uauario.");
			rol = "usuario";
		}
		Usuario usr = new Usuario(nom, en.ecnode(pwd), rol);
		insertarUsuario(usr);
		System.out.println("Usuario insertado!!");
	}

	private static void leerSinopsis(Scanner sc) {
		ArrayList<Pelicula> pelis = leerPelicula();
		for (int i = 0; i < pelis.size(); i++) {
			System.out.println(i + ". " + pelis.get(i).getTitulo());
		}
		System.out.println("¿Cual es la sinopsis que mas te interesa?");
		String leeropcion = sc.nextLine();
		String sinopsis = pelis.get(Integer.parseInt(leeropcion)).getSinopsis();
		String sinopsisPeli = pelis.get(Integer.parseInt(leeropcion)).getTitulo();
		System.out.println(sinopsisPeli + ": " + sinopsis);
	}

	private static void eliminarPeliculaMenu(Scanner sc) {
		ArrayList<Pelicula> pelis = leerPelicula();
		for (int i = 0; i < pelis.size(); i++) {
			System.out.println(i + ". " + pelis.get(i).getTitulo());
		}
		System.out.print("¿Cual quieres eliminar?: ");
		String peliIndice = sc.nextLine();
		String iDpeliEliminar = pelis.get(Integer.parseInt(peliIndice)).getId();
		System.out.println("¿Esta seguro/a? s/n");
		String sn = sc.nextLine();
		if (sn.toLowerCase().equals("s")) {
			eliminarPelicula(iDpeliEliminar);
			System.out.println("Se elimino exitosamente!!");
		} else if (sn.toLowerCase().equals("n")) {
			System.out.println("Se cancelo exitosamente!!");
		} else {
			System.out.println("como es una opcion extraña se cancelara");
		}
	}

	private static void eliminarPeliculaMenuCascada(Scanner sc) {
		ArrayList<Pelicula> pelis = leerPelicula();
		for (int i = 0; i < pelis.size(); i++) {
			System.out.println(i + ". " + pelis.get(i).getTitulo());
		}
		System.out.print("¿Cual quieres eliminar?: ");
		String peliIndice = sc.nextLine();
		String iDpeliEliminar = pelis.get(Integer.parseInt(peliIndice)).getId();
		System.out.println("¿Esta seguro/a? s/n");
		String sn = sc.nextLine();
		if (sn.toLowerCase().equals("s")) {
			eliminarPelicula(iDpeliEliminar);
			eliminarDirectorCascada(iDpeliEliminar);
			eliminarPrincesaCascada(iDpeliEliminar);
			eliminarPrincipeCascada(iDpeliEliminar);
			eliminarVillanoCascada(iDpeliEliminar);
			System.out.println("Se elimino exitosamente!!");
		} else if (sn.toLowerCase().equals("n")) {
			System.out.println("Se cancelo exitosamente!!");
		} else {
			System.out.println("como es una opcion extraña se cancelara");
		}
	}

	private static void eliminarPrincesaMenu(Scanner sc) {
		ArrayList<Princesa> pelis = leerPrincesa();
		for (int i = 0; i < pelis.size(); i++) {
			System.out.println(i + ". " + pelis.get(i).getNombre() + pelis.get(i).getCreacion());
		}
		System.out.print("¿Cual quieres eliminar?: ");
		String indice = sc.nextLine();
		String iDeliminar = pelis.get(Integer.parseInt(indice)).getId();
		System.out.println("¿Esta seguro/a? s/n");
		String sn = sc.nextLine();
		if (sn.toLowerCase().equals("s")) {
			eliminarPrincesa(iDeliminar);
			System.out.println("Se elimino exitosamente!!");
		} else if (sn.toLowerCase().equals("n")) {
			System.out.println("Se cancelo exitosamente!!");
		} else {
			System.out.println("como es una opcion extraña se cancelara");
		}
	}

	private static void eliminarPrincipeMenu(Scanner sc) {
		ArrayList<Principe> pelis = leerPrincipes();
		for (int i = 0; i < pelis.size(); i++) {
			System.out.println(i + ". " + pelis.get(i).getNombre() + pelis.get(i).getCreacion());
		}
		System.out.print("¿Cual quieres eliminar?: ");
		String indice = sc.nextLine();
		String iDeliminar = pelis.get(Integer.parseInt(indice)).getId();
		System.out.println("¿Esta seguro/a? s/n");
		String sn = sc.nextLine();
		if (sn.toLowerCase().equals("s")) {
			eliminarPrincipe(iDeliminar);
			System.out.println("Se elimino exitosamente!!");
		} else if (sn.toLowerCase().equals("n")) {
			System.out.println("Se cancelo exitosamente!!");
		} else {
			System.out.println("como es una opcion extraña se cancelara");
		}
	}

	private static void eliminarDirectorMenu(Scanner sc) {
		ArrayList<Director> pelis = leerDirector();
		for (int i = 0; i < pelis.size(); i++) {
			System.out.println(i + ". " + pelis.get(i).getDirector());
		}
		System.out.print("¿Cual quieres eliminar?: ");
		String indice = sc.nextLine();
		String iDeliminar = pelis.get(Integer.parseInt(indice)).getId();
		System.out.println("¿Esta seguro/a? s/n");
		String sn = sc.nextLine();
		if (sn.toLowerCase().equals("s")) {
			eliminarDirector(iDeliminar);
			System.out.println("Se elimino exitosamente!!");
		} else if (sn.toLowerCase().equals("n")) {
			System.out.println("Se cancelo exitosamente!!");
		} else {
			System.out.println("como es una opcion extraña se cancelara");
		}
	}

	private static void eliminarVillanosMenu(Scanner sc) {
		ArrayList<Villano> pelis = leerVillanos();
		for (int i = 0; i < pelis.size(); i++) {
			System.out.println(i + ". " + pelis.get(i).getNombre() + pelis.get(i).getCreacion());
		}
		System.out.print("¿Cual quieres eliminar?: ");
		String indice = sc.nextLine();
		String iDeliminar = pelis.get(Integer.parseInt(indice)).getId();
		System.out.println("¿Esta seguro/a? s/n");
		String sn = sc.nextLine();
		if (sn.toLowerCase().equals("s")) {
			eliminarVillano(iDeliminar);
			System.out.println("Se elimino exitosamente!!");
		} else if (sn.toLowerCase().equals("n")) {
			System.out.println("Se cancelo exitosamente!!");
		} else {
			System.out.println("como es una opcion extraña se cancelara");
		}
	}

	private static void volverAtras() {
		btnAtras();
		pulsaContinuar();
	}

	private static int pregunta(Scanner sc) {
		System.out.println("¿Que trailer quieres ver?");
		String algo = sc.nextLine();
		int peli = Integer.parseInt(algo);
		return peli;
	}

	private static Map<Integer, Pelicula> leerListaPelisTrailer() {
		Map<Integer, Pelicula> c = leerPeliculaNombre2();
		c.forEach((k, v) -> System.out.println(k + ". " + v.getTitulo()));
		return c;
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

	public static void directoresPorGenero() {

		MongoDatabase database = conexionMongoDB();
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

	private static void obtenerColecciones(MongoDatabase database) {
		pelicula = database.getCollection("Pelicula");
		princesa = database.getCollection("Princesa");
		villano = database.getCollection("Villano");
		principe = database.getCollection("Principe");
		director = database.getCollection("Director");
		usuario = database.getCollection("Usuario");
	}

	private static void crearColecciones(MongoDatabase database) {
		database.createCollection("Princesa");
		database.createCollection("Principe");
		database.createCollection("Villano");
		database.createCollection("Pelicula");
		database.createCollection("Director");
		database.createCollection("Usuario");
	}

	private static MongoDatabase conexionMongoDB() {
		// Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		// mongoLogger.setLevel(Level.SEVERE);

		ConnectionString connectionString = new ConnectionString(
				"mongodb+srv://Becarios:Admin1234@cluster0.aq3pk.mongodb.net/test?retryWrites=true&w=majority");
		MongoClientSettings settings = MongoClientSettings.builder()
				.applyConnectionString(connectionString)
				.build();
		MongoClient mongoClient = MongoClients.create(settings);
		MongoDatabase database = mongoClient.getDatabase("Disney");
		return database;
	}

	private static void imprimirTablaPrincipes() {
		ArrayList<Principe> principes = leerPrincipes();
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
		limpiarConsola();
		System.out.println("**************************************************************** HISTORIAL de "
				+ LocalDateTime.now() + " ****************************************************************");
		System.out.println(generateTable(headersListprincipe, rowsListprincipe));
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
		System.out.println(generateTable(headersListprincipe1, rowsListprincipe1));
		System.out.flush();
	}

	private static void imprimirTablaPrincesa() {
		ArrayList<Princesa> princesas = leerPrincesa();
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
		limpiarConsola();
		System.out.println("**************************************************************** HISTORIAL de "
				+ LocalDateTime.now() + " ****************************************************************");
		System.out.println(generateTable(headersListprincesa, rowsListprincesa));
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
		System.out.println(generateTable(headersListprincesa1, rowsListprincesa1));
		System.out.flush();
	}

	private static void imprimirTablaDirector() {
		ArrayList<Director> directores = leerDirector();
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
		limpiarConsola();
		System.out.println("**************************************************************** HISTORIAL de "
				+ LocalDateTime.now() + " ****************************************************************");
		System.out.println(generateTable(headersListDirectores, rowsListDirector));
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
		System.out.println(generateTable(headersListDirectores1, rowsListDirector1));
		System.out.flush();

	}

	private static void imprimirTablavillanos() {
		ArrayList<Villano> villanos = leerVillanos();
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
		limpiarConsola();
		System.out.println("**************************************************************** HISTORIAL de "
				+ LocalDateTime.now() + " ****************************************************************");
		System.out.println(generateTable(headersList, rowsList));
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
		System.out.println(generateTable(headersList1, rowsListvillano1));
		System.out.flush();
	}

	private static void btnAtras() {
		System.out.println(" _________________________");
		System.out.println("|                         |");
		System.out.println("| Enter para volver Atras |");
		System.out.println(" _________________________");
	}

	private static void imprimirTablaPeliculas() {
		ArrayList<Pelicula> pelis = leerPelicula();
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
		limpiarConsola();
		System.out.println(generateTable(headersList, rowsList));

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
		System.out.println(generateTable(headersList1, rowsList1));

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
		System.out.println(generateTable(headersList2, rowsList2));
	}

	private static void limpiarConsola() {
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (Exception e) {
			/* No hacer nada */
		}
	}

	private static void procesoVerTrailer(String url) {
		try {
			new ProcessBuilder("cmd.exe", "/c", "start chrome " + url).inheritIO().start().waitFor();
		} catch (Exception e) {
			/* No hacer nada */
		}
	}

	public static String generateTable(List<String> headersList, List<List<String>> rowsList,
			int... overRiddenHeaderHeight) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.setLength(0);
		int rowHeight = overRiddenHeaderHeight.length > 0 ? overRiddenHeaderHeight[0] : 1;
		Map<Integer, Integer> columnMaxWidthMapping = getMaximumWidhtofTable(headersList, rowsList);
		columnMaxWidthMapping = getMaximumWidhtofTable(headersList, rowsList);
		stringBuilder.append(NEW_LINE);
		stringBuilder.append(NEW_LINE);
		createRowLine(stringBuilder, headersList.size(), columnMaxWidthMapping);
		stringBuilder.append(NEW_LINE);
		for (int headerIndex = 0; headerIndex < headersList.size(); headerIndex++) {
			fillCell(stringBuilder, headersList.get(headerIndex), headerIndex, columnMaxWidthMapping);
		}
		stringBuilder.append(NEW_LINE);
		createRowLine(stringBuilder, headersList.size(), columnMaxWidthMapping);
		for (List<String> row : rowsList) {
			for (int i = 0; i < rowHeight; i++) {
				stringBuilder.append(NEW_LINE);
			}
			for (int cellIndex = 0; cellIndex < row.size(); cellIndex++) {
				fillCell(stringBuilder, row.get(cellIndex), cellIndex, columnMaxWidthMapping);
			}
		}
		stringBuilder.append(NEW_LINE);
		createRowLine(stringBuilder, headersList.size(), columnMaxWidthMapping);
		stringBuilder.append(NEW_LINE);
		stringBuilder.append(NEW_LINE);
		String pasar = stringBuilder.toString();

		headersList.clear();
		rowsList.clear();
		stringBuilder.setLength(0);
		overRiddenHeaderHeight = null;
		columnMaxWidthMapping.clear();

		return pasar;
	}

	private static void fillSpace(StringBuilder stringBuilder, int length) {
		for (int i = 0; i < length; i++) {
			stringBuilder.append(" ");
		}
	}

	private static void createRowLine(StringBuilder stringBuilder, int headersListSize,
			Map<Integer, Integer> columnMaxWidthMapping) {
		for (int i = 0; i < headersListSize; i++) {
			if (i == 0) {
				stringBuilder.append(TABLE_JOINT_SYMBOL);
			}
			for (int j = 0; j < columnMaxWidthMapping.get(i) + PADDING_SIZE * 2; j++) {
				stringBuilder.append(TABLE_H_SPLIT_SYMBOL);
			}
			stringBuilder.append(TABLE_JOINT_SYMBOL);
		}
	}

	private static Map<Integer, Integer> getMaximumWidhtofTable(List<String> headersList, List<List<String>> rowsList) {
		Map<Integer, Integer> columnMaxWidthMapping = new HashMap<>();
		columnMaxWidthMapping.clear();
		for (int columnIndex = 0; columnIndex < headersList.size(); columnIndex++) {
			columnMaxWidthMapping.put(columnIndex, 0);
		}
		for (int columnIndex = 0; columnIndex < headersList.size(); columnIndex++) {
			if (headersList.get(columnIndex).length() > columnMaxWidthMapping.get(columnIndex)) {
				columnMaxWidthMapping.put(columnIndex, headersList.get(columnIndex).length());
			}
		}
		for (List<String> row : rowsList) {
			for (int columnIndex = 0; columnIndex < row.size(); columnIndex++) {
				if (row.get(columnIndex).length() > columnMaxWidthMapping.get(columnIndex)) {
					columnMaxWidthMapping.put(columnIndex, row.get(columnIndex).length());
				}
			}
		}
		for (int columnIndex = 0; columnIndex < headersList.size(); columnIndex++) {
			if (columnMaxWidthMapping.get(columnIndex) % 2 != 0) {
				columnMaxWidthMapping.put(columnIndex, columnMaxWidthMapping.get(columnIndex) + 1);
			}
		}
		return columnMaxWidthMapping;
	}

	private static int getOptimumCellPadding(int cellIndex, int datalength, Map<Integer, Integer> columnMaxWidthMapping,
			int cellPaddingSize) {
		if (datalength % 2 != 0) {
			datalength++;
		}
		if (datalength < columnMaxWidthMapping.get(cellIndex)) {
			cellPaddingSize = cellPaddingSize + (columnMaxWidthMapping.get(cellIndex) - datalength) / 2;
		}
		return cellPaddingSize;
	}

	private static void fillCell(StringBuilder stringBuilder, String cell, int cellIndex,
			Map<Integer, Integer> columnMaxWidthMapping) {
		int cellPaddingSize = getOptimumCellPadding(cellIndex, cell.length(), columnMaxWidthMapping, PADDING_SIZE);
		if (cellIndex == 0) {
			stringBuilder.append(TABLE_V_SPLIT_SYMBOL);
		}
		fillSpace(stringBuilder, cellPaddingSize);
		stringBuilder.append(cell);
		if (cell.length() % 2 != 0) {
			stringBuilder.append(" ");
		}
		fillSpace(stringBuilder, cellPaddingSize);
		stringBuilder.append(TABLE_V_SPLIT_SYMBOL);
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

	private static Map<Integer, Map<String, String>> leerPeliculaNombre() {
		Map<Integer, Map<String, String>> mapa = new HashMap<Integer, Map<String, String>>();
		Map<String, String> a = new HashMap<String, String>();
		MongoCursor<Document> resultDocument = pelicula.find().iterator();
		int i = 1;
		while (resultDocument.hasNext()) {
			a.clear();
			Document p = resultDocument.next();
			a.put(p.getString("Titulo"), p.getString("Trailer"));
			mapa.put(i, a);
			i++;
		}
		return mapa;
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

	private static Map<Integer, Pelicula> leerPeliculaNombre2() {

		Map<Integer, Pelicula> a = new HashMap<Integer, Pelicula>();
		MongoCursor<Document> resultDocument = pelicula.find().iterator();
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

	public static void insertarUsuario(Usuario usr) {
		usuario.insertOne(new Document()
				.append("_id", new ObjectId())
				.append("Usuario", usr.getUser())
				.append("Password", usr.getPasswword())
				.append("Rol", usr.getRol()));
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
				.append("A�o", p.getAno())
				.append("Duraci�n", p.getDuracion())
				.append("Pa�s", p.getPais())
				.append("Guion", p.getGuion())
				.append("M�sica", p.getMusica())
				.append("Fotograf�a", p.getFotografia())
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

	public static void eliminarDirectorCascada(String id) {
		Document findDocument = new Document("idPelicula", new ObjectId(id));
		director.deleteMany(findDocument);
	}

	public static void eliminarPrincipe(String id) {
		Document findDocument = new Document("_id", new ObjectId(id));
		principe.deleteMany(findDocument);
	}

	public static void eliminarPrincipeCascada(String id) {
		Document findDocument = new Document("idPelicula", new ObjectId(id));
		principe.deleteMany(findDocument);
	}

	public static void eliminarPrincesa(String id) {
		Document findDocument = new Document("_id", new ObjectId(id));
		princesa.deleteMany(findDocument);
	}

	public static void eliminarPrincesaCascada(String id) {
		Document findDocument = new Document("idPelicula", new ObjectId(id));
		princesa.deleteMany(findDocument);
	}

	public static void eliminarVillano(String id) {
		Document findDocument = new Document("_id", new ObjectId(id));
		villano.deleteMany(findDocument);
	}

	public static void eliminarVillanoCascada(String id) {
		Document findDocument = new Document("idPelicula", new ObjectId(id));
		villano.deleteMany(findDocument);
	}

	public static void editarPelicula(String id, Pelicula p) {
		Document findDocument2 = new Document("_id", new ObjectId(id));

		Document updateDocument2 = new Document("$set",
				new Document()
						.append("Titulo", p.getTitulo())
						.append("Titulo original", p.getTituloOriginal())
						.append("A�o", p.getAno())
						.append("Duraci�n", p.getDuracion())
						.append("Pa�s", p.getPais())
						.append("Guion", p.getGuion())
						.append("M�sica", p.getMusica())
						.append("Fotograf�a", p.getFotografia())
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
