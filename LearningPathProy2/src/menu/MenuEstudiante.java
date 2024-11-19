package menu;

import java.util.HashMap;
import java.util.Scanner;

import controllers.*;
import traductores.*;
import usuarios.*;

public class MenuEstudiante
{
	public static LearningPathSystem LPS = null;
	public static Autentificador autentificador = null;
	private static Usuario estudiante;

	public static void main(String[] args)
	{
		if (LPS == null)
			LPS = LearningPathSystem.getInstance();

		if (autentificador == null)
			autentificador = Autentificador.getInstance(LPS);

		mostrarMenuInicioSesion();
	}

	public static void mostrarMenuInicioSesion()
	{
		Estudiante testEstudiante = new Estudiante("estudiante", "1234", "Estudiante");
		LPS.addEstudiante(testEstudiante); // Estudiante de prueba, solo para probar interfaz directa de inicio de sesion
											// de estudiante
		
		System.out.println("Bienvenido al sistema de aprendizaje");
		System.out.println("Por favor, ingrese su login y contraseña");
		System.out.println("Login: ");
		Scanner scanner = new Scanner(System.in);
		String ID = scanner.nextLine();
		System.out.println("Contraseña: ");
		String contrasena = scanner.nextLine();
		try
		{
			estudiante = autentificador.autentificar(ID, contrasena);
			while (estudiante != null)
			{
				mostrarMenuEstudiante((Estudiante) estudiante);
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	public static void mostrarMenuEstudiante(Estudiante estudiante)
	{
		System.out.println("Bienvenido, " + estudiante.getNombre() + "\n");
		System.out.println("Seleccione una opción: ");
		System.out.println("1. Ver avances en todos los caminos");
		System.out.println("2. Ver actividad iniciada y realizar un envío");
		System.out.println("3. Ver caminos disponibles e inscribirse a uno");
		System.out.println("4. Ver actividades de caminos inscritos e iniciar una");
		System.out.println("0. Salir");

		Scanner scanner = new Scanner(System.in);
		Integer opcion = scanner.nextInt();
		scanner.nextLine();

		switch (opcion)
		{
		case 1:
			verAvancesCaminos(estudiante.getID());
			break;
		case 2:
			realizarEnvioActividad(estudiante.getID());
			break;
		case 3:
			verCaminosDisponibles(estudiante.getID());
			break;
		case 4:
			iniciarActividad(estudiante.getID());
			break;
		case 0:
			System.out.println("Gracias por usar el sistema. \n¡Hasta luego!");
			System.exit(0);
			break;
		default:
			System.out.println("Opción no válida. Por favor intente de nuevo.");
		}
	}

	private static void verAvancesCaminos(String estudianteID)
	{
		System.out.println("Avances en todos los caminos");
		String ID = estudiante.getID();
		try
		{
			HashMap<String, String> avances = TraductorEstudiante.getAvancesCaminos(ID);
			for (HashMap.Entry<String, String> camino : avances.entrySet())
			{
				System.out.println("Camino: " + camino.getKey() + " | Avance: " + camino.getValue());
			}
			
		}
		catch (Exception e)
		{
			e.getMessage();
		}
	}

}
