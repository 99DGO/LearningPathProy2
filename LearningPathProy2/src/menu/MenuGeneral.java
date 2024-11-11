package menu;

import java.util.Scanner;
import controllers.Autentificador;
import controllers.LearningPathSystem;
import usuarios.Estudiante;
import usuarios.Profesor;
import usuarios.Usuario;

public class MenuGeneral {
	public static LearningPathSystem LPS = null;
	public static Autentificador autentificador = null;

	public static void main(String[] args) {
		if (LPS == null)
			LPS = LearningPathSystem.getInstance();
		
		if (autentificador == null)
			autentificador = Autentificador.getInstance(LPS);
		
		while (true) {
			mostrarMenu();
		}
	}

	public static void mostrarMenu() {
		System.out.println("¡Bienvenido al sistema de gestión de caminos de aprendizaje!");
		System.out.println("Seleccione una opcion:");
		System.out.println("1. Iniciar sesion");
		System.out.println("2. Registrarse");
		System.out.println("3. Salir");

		Scanner scanner = new Scanner(System.in);
		int opcion = scanner.nextInt();
		switch (opcion) {
		case 1:
			// Iniciar sesion
			iniciarSesion();
			break;
		case 2:
			// Registrarse
			registrarse();
			break;
		case 3:
			// Salir
			System.out.println("Desea cerrar la aplicacion?");
			System.out.println("1. Si");
			System.out.println("2. No");
			int cerrar = scanner.nextInt();
			if (cerrar == 1) {
				System.out.println("Gracias por usar el sistema. \n¡Hasta luego!");
				System.exit(0);
			} else if (cerrar == 2) {
				System.out.println("Volviendo al menu principal. \n");
			} else {
				System.out.println("Opcion no valida. Volviendo al menu principal. \n");
			}
			break;
		default:
			System.out.println("Opcion no valida. Por favor intente de nuevo. \n");
		}

	}

	public static void iniciarSesion() {
		System.out.println("Ingrese su login:");
		Scanner scanner = new Scanner(System.in);
		String login = scanner.nextLine();
		System.out.println("Ingrese su password:");
		String password = scanner.nextLine();
		// Validar login y password

		try {
			Usuario usuario = autentificador.autentificar(login, password);
			if (usuario != null) {
				if (usuario.getType().equals("profesor")) {
					System.out.println("Bienvenido profesor " + login);
					MenuEstudiante.mostarMenuEstudiante();
				} else if (usuario.getType().equals("estudiante")) {
					System.out.println("Bienvenido estudiante " + login);
					MenuProfesor.mostrarMenuProfesor();
				} else {
					System.out.println("Usuario no encontrado. \n");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


	}

	public static void registrarse() {
		Usuario newUsuario = null;

		System.out.println("Por favor complete los siguientes campos para registrarse:");
		System.out.println("Ingrese su nombre:");
		Scanner scanner = new Scanner(System.in);
		String nombre = scanner.nextLine();
		System.out.println("Ingrese su login:");
		String login = scanner.nextLine();
		System.out.println("Ingrese su password:");
		String password = scanner.nextLine();
		System.out.println("Seleccione el tipo de usuario:");
		System.out.println("1. Estudiante");
		System.out.println("2. Profesor");
		int type = scanner.nextInt();
		switch (type) {
		case 1:
			// estudiante
			newUsuario = new Estudiante(login, password, nombre);
			break;
		case 2:
			// profesor
			newUsuario = new Profesor(login, password, nombre);
			break;
		default:
			System.out.println("Opcion no valida. \n");
		}

		if (newUsuario != null) {
			if (autentificador.registrarUsuario(newUsuario)) {
				System.out.println( LPS.getEstudiantes().get(login));
				System.out.println("Usuario registrado exitosamente. \n");
			} else {
				System.out.println("Error en el proceso de registro. \n");
			}
		}

	}
}
