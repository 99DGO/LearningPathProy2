package menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import caminosActividades.CaminoAprendizaje;
import controllers.Autentificador;
import controllers.LearningPathSystem;
import creadores.CreadorCamino;
import usuarios.Profesor;
import usuarios.Usuario;
import traductores.TraductorCamino;
import editores.EditorCamino;

public class MenuProfesor {
	public static LearningPathSystem LPS = null;
	public static Autentificador autentificador = null;
	private static Usuario profesor;

	public static void main(String[] args) {
		if (LPS == null)
			LPS = LearningPathSystem.getInstance();

		if (autentificador == null)
			autentificador = Autentificador.getInstance(LPS);

		mostrarMenuInicioSesion();
	}

	public static void mostrarMenuInicioSesion() {
		Profesor testProfesor = new Profesor("profesor", "1234", "Profesor");
		LPS.addProfesor(testProfesor); // Profesor de prueba, solo para probar interfaz directa de inicio de sesion de
										// profesor

		System.out.println("Bienvenido al sistema de aprendizaje");
		System.out.println("Por favor, ingrese su login y contraseña");
		System.out.println("Login: ");
		Scanner scanner = new Scanner(System.in);
		String ID = scanner.nextLine();
		System.out.println("Contraseña: ");
		String contrasena = scanner.nextLine();
		try {
			profesor = autentificador.autentificar(ID, contrasena);
			while (profesor != null) {
				mostrarMenuProfesor((Profesor) profesor);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void mostrarMenuProfesor(Profesor profesor) {
		System.out.println("Bienvenido, " + profesor.getNombre() + "\n");
		System.out.println("Seleccione una opción: ");
		System.out.println("1. Crear camino de aprendizaje");
		System.out.println("2. Crear actividad");
		System.out.println("3. Ver todos los caminos de aprendizaje");
		System.out.println("4. Ver detalles de un camino de aprendizaje");
		System.out.println("5. Ver caminos de aprendizaje creados");
		System.out.println("6. Ver envíos de actividades");
		System.out.println("7. Editar un camino de aprendizaje");
		System.out.println("8. Editar una actividad");
		System.out.println("0. Salir");
		Scanner scanner = new Scanner(System.in);
		Integer opcion = scanner.nextInt();

		switch (opcion) {
		case 1:
			// Crear camino de aprendizaje
			mostrarMenuCreacionCamino();
			break;

		case 2:
			// Crear actividad
			mostrarMenuCreacionActividad();
			break;

		case 3:
			// Ver todos los caminos de aprendizaje
			HashMap<String, String> allCaminos = TraductorCamino.verTodosCaminos();
			for (String ID : allCaminos.keySet()) {
				System.out.println("ID: " + ID + ", Nombre: " + allCaminos.get(ID));
			}
			break;

		case 4:
			// Ver detalles de un camino de aprendizaje
			System.out.println("Ingrese el ID del camino de aprendizaje que desea ver: ");
			String IDCamino = scanner.nextLine();
			try {

				HashMap<String, String> infoCamino = TraductorCamino.verInfoGeneralCamino(IDCamino);
				for (String key : infoCamino.keySet()) {
					System.out.println(key + ": " + infoCamino.get(key));
				}
				System.out.println("Desea clonar este camino?");
				System.out.println("1. Quiero clonar todo el camino");
				System.out.println("2. Quiero clonar alguna actividades del camino");
				System.out.println("0. No deseo clonar el camino");
				int clonar = scanner.nextInt();
				switch (clonar) {
				case 1:
					// Clonar todo el camino
					try {
						CreadorCamino.clonarCamino(IDCamino, infoCamino.get("Titulo"), profesor.getID());
					} catch (Exception e) {
						System.out.println("Ocurrió un error al clonar el camino.");
						e.getMessage();
						e.printStackTrace();
					}
					break;

				case 2:
					// TODO Clonar algunas actividades del camino
					break;

				default:
					System.out.println("Volviendo al menu principal. \n");
				}

			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
			break;

		case 5:
			// Ver caminos de aprendizaje creados
			List<CaminoAprendizaje> caminosCreados = profesor.getCaminos();

			for (CaminoAprendizaje camino : caminosCreados) {
				System.out.println("ID: " + camino.getID() + ", Nombre: " + camino.getTitulo());
			}

			break;

		case 6:
			// TODO Ver envios de actividades
			break;

		case 7:
			// Editar un camino de aprendizaje
			mostrarMenuEdicionCamino();
			break;

		case 8:
			// TODO Editar una actividad
			mostrarMenuEdicionActividad();
			break;

		case 0:
			// Salir
			System.out.println("Desea salir de la aplicacion?");
			System.out.println("1. Si");
			System.out.println("2. No");
			int cerrar = scanner.nextInt();
			if (cerrar == 1) {
				System.out.println("Gracias por usar el sistema. \n¡Hasta luego!");
				System.exit(0);
			} else if (cerrar == 2) {
				System.out.println("Volviendo al menu. \n");
			} else {
				System.out.println("Opcion no valida. Volviendo al menu principal. \n");
			}
			break;

		default:
			System.out.println("Opcion no valida. Por favor intente de nuevo. \n");
		}
	}

	public static void mostrarMenuCreacionCamino() {
		System.out.println("Creacion de camino de aprendizaje");
		System.out.println("Ingrese el titulo del camino: ");
		Scanner scanner = new Scanner(System.in);
		String titulo = scanner.nextLine();
		System.out.println("Ingrese la descripcion del camino: ");
		String descripcion = scanner.nextLine();
		System.out.println("Ingrese la dificultad del camino (en una escala del 1 al 10): ");
		double dificultad = scanner.nextInt();
		System.out.println("Ingrese la duración estimada del camino (en horas): ");
		int duracion = scanner.nextInt();
		System.out.println("Cuantos objetivos tiene el camino?");
		int numObjetivos = scanner.nextInt();
		List<String> objetivos = new ArrayList<>();
		for (int i = 0; i < numObjetivos; i++) {
			System.out.println("Ingrese el objetivo " + (i + 1) + ": ");
			objetivos.add(scanner.nextLine());
		}

		System.out.println("Creando camino...");
		try {
			CreadorCamino.crearCaminoCero(titulo, descripcion, objetivos, dificultad, duracion, profesor.getID());
		} catch (Exception e) {
			System.out.println("Ocurrió un error al crear el camino.");
			e.getMessage();
			e.printStackTrace();
		}

	}

	public static void mostrarMenuCreacionActividad() {
		// TODO Crear menu de creacion de actividad

	}

	public static void mostrarMenuEdicionCamino() {
		System.out.println("Edicion de camino de aprendizaje");
		System.out.println("Ingrese el ID del camino que desea editar: ");
		Scanner scanner = new Scanner(System.in);
		String IDCamino = scanner.nextLine();
		try {
			HashMap<String, String> infoCamino = TraductorCamino.verInfoGeneralCamino(IDCamino);
			for (String key : infoCamino.keySet()) {
				System.out.println(key + ": " + infoCamino.get(key));
			}

			System.out.println("Seleccione una opcion: ");
			System.out.println("1. Editar titulo");
			System.out.println("2. Editar descripcion");
			System.out.println("3. Editar dificultad");
			System.out.println("4. Editar duracion");
			System.out.println("5. Añadir objetivos");
			System.out.println("6. Eliminar objetivos");
			System.out.println("7. Agregar actividad");
			System.out.println("8. Eliminar actividad");
			System.out.println("9. Cambiar orden de actividades");
			System.out.println("0. Salir");
			int opcion = scanner.nextInt();
			switch (opcion) {
			case 1:
				// Editar titulo
				System.out.println("Ingrese el nuevo titulo: ");
				String nuevoTitulo = scanner.nextLine();
				try {
					EditorCamino.editTitulo(IDCamino, nuevoTitulo);
				} catch (Exception e) {
					System.out.println("Ocurrió un error al editar el titulo.");
					e.getMessage();
					e.printStackTrace();
				}
				break;

			case 2:
				// Editar descripcion
				System.out.println("Ingrese la nueva descripcion: ");
				String nuevaDescripcion = scanner.nextLine();
				try {
					EditorCamino.editDescripcion(IDCamino, nuevaDescripcion);
				} catch (Exception e) {
					System.out.println("Ocurrió un error al editar la descripcion.");
					e.getMessage();
					e.printStackTrace();
				}
				break;

			case 3:
				// Editar dificultad
				System.out.println("Ingrese la nueva dificultad: ");
				Float nuevaDificultad = scanner.nextFloat();
				try {
					EditorCamino.editDificultad(IDCamino, nuevaDificultad);
				} catch (Exception e) {
					System.out.println("Ocurrió un error al editar la dificultad.");
					e.getMessage();
					e.printStackTrace();
				}
				break;

			case 4:
				// Editar duracion
				System.out.println("Ingrese la nueva duracion: ");
				int nuevaDuracion = scanner.nextInt();
				try {
					EditorCamino.editDuracion(IDCamino, nuevaDuracion);
				} catch (Exception e) {
					System.out.println("Ocurrió un error al editar la duracion.");
					e.getMessage();
					e.printStackTrace();
				}
				break;

			case 5:
				// Añadir objetivos
				System.out.println("Ingrese el nuevo objetivo: ");
				String nuevoObjetivo = scanner.nextLine();
				try {
					EditorCamino.editAddObjetivo(IDCamino, nuevoObjetivo);
				} catch (Exception e) {
					System.out.println("Ocurrió un error al añadir el objetivo.");
					e.getMessage();
					e.printStackTrace();
				}
				break;

			case 6:
				// Eliminar objetivos
				System.out.println("Ingrese la posición del objetivo que desea eliminar: ");
				int objetivoEliminar = scanner.nextInt();

				try {
					EditorCamino.editDelObjetivo(IDCamino, objetivoEliminar);
				} catch (Exception e) {
					System.out.println("Ocurrió un error al eliminar el objetivo.");
					e.getMessage();
					e.printStackTrace();
				}
				break;

			case 7:
				// TODO Crear y agregar actividad
				break;

			case 8:
				// Eliminar actividad
				System.out.println("Ingrese la posicion de la actividad que desea eliminar: ");
				int posActividad = scanner.nextInt();
				try {
					EditorCamino.editDelActividad(IDCamino, posActividad);
				} catch (Exception e) {
					System.out.println("Ocurrió un error al eliminar la actividad.");
					e.getMessage();
					e.printStackTrace();
				}

			case 9:
				// Cambiar orden de actividades
				System.out.println(
						"Advertencia \nAl cambiar el orden de las actividades, se perderá la información de la actividad en dicha posicion. \nDesea continuar? \n1. Si \n2. No");
				int confirmacion = scanner.nextInt();
				if (confirmacion == 1) {
					System.out.println("Continuando con el cambio de orden de actividades. \n");
				} else {
					System.out.println("Volviendo al menu principal. \n");
					break;
				}
				System.out.println("Ingrese el ID de la actividad que desea mover: ");
				String actividadMover = scanner.nextLine();
				System.out.println("Ingrese la nueva posicion de la actividad: ");
				int nuevaPosicion = scanner.nextInt();
				try {
					EditorCamino.cambiarPosActividad(IDCamino, actividadMover, nuevaPosicion);
				} catch (Exception e) {
					System.out.println("Ocurrió un error al mover la actividad.");
					e.getMessage();
					e.printStackTrace();
				}

			default:
				System.out.println("Volviendo al menu principal. \n");
				break;
			}
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	public static void mostrarMenuEdicionActividad() {
		// TODO Crear menu de edicion de actividad
	}
}
