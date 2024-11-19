package menu;

import java.util.HashMap;
import java.util.List;
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

	public static void verAvancesCaminos(String idEstudiante)
	{
		System.out.println("Avances en todos los caminos");
		try
		{
			HashMap<String, String> avances = TraductorEstudiante.getAvancesCaminos(idEstudiante);
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

	public static void realizarEnvioActividad(String idEstudiante)
	{
		System.out.println("Realizar envío de actividad");
		System.out.println("Actividad actual: ");
		try
		{
			String actividad = TraductorEstudiante.verActividadActiva(idEstudiante);
			System.out.println("Actividad: " + actividad);
			// TODO continuar creando envío de una actividad, hay que mostrar los detalles de la act y 
			// preguntas y todo al estudiante para que sepa qué está respondiendo
			String[] actividadAll = actividad.split(";");
			String nombreActividad = actividadAll[0];
			String nombreCamino = actividadAll[2];
			String idCamino = TraductorCamino.getIDfromNombre(nombreCamino);
			String idActividad = TraductorActividad.getIDfromNombre(idCamino, nombreActividad);
			HashMap<String, String> infoActividad = TraductorActividad.verInfoGeneralActividad(idCamino, idActividad);
			for (HashMap.Entry<String, String> info : infoActividad.entrySet())
			{
				System.out.println(info.getKey() + ": " + info.getValue());
			}
			MenuRealizarEnvio.mostrarMenuRealizarEnvio(idEstudiante, idCamino, idActividad);
			
		}
		catch (Exception e)
		{
			e.getMessage();
		}
		
	}

	public static void verCaminosDisponibles(String idEstudiante)
	{
		System.out.println("Caminos disponibles");
		try
		{
			HashMap<String, String> caminos = TraductorCamino.verTodosCaminos();
			for (HashMap.Entry<String, String> camino : caminos.entrySet())
			{
				System.out.println("Titulo: " + camino.getKey() + " | Creador: " + camino.getValue());
			}
		}
		catch (Exception e)
		{
			e.getMessage();
		}
		System.out.println("Desea inscribirse a un camino?");
		System.out.println("1. Si");
		System.out.println("2. No");
		Scanner scanner = new Scanner(System.in);
		int inscribir = scanner.nextInt();
		scanner.nextLine();
		if (inscribir == 1)
		{
			System.out.println("Ingrese el titulo del camino al que desea inscribirse: ");
			String titulo = scanner.nextLine();
			try
			{
				String idCamino = TraductorCamino.getIDfromNombre(titulo);
				Inscriptor.inscribirseCamino(idCamino, idEstudiante);
				System.out.println("Inscripción exitosa.");
			}
			catch (Exception e)
			{
				e.getMessage();
			}
		}
		
	}

	public static void iniciarActividad(String idEstudiante)
	{
		String idCamino = "";
		System.out.println("Iniciar actividad");
		System.out.println("Ingrese el nombre del camino: ");
		Scanner scanner = new Scanner(System.in);
		String nombreCamino = scanner.nextLine();
		try
		{
			idCamino = TraductorCamino.getIDfromNombre(nombreCamino);
		} 
		catch (Exception e)
		{
			e.getMessage();
		}
		
		try
		{
			HashMap<String, String> caminosMap = TraductorEstudiante.getAvancesCaminos(idEstudiante);
			for (String titulo : caminosMap.keySet())
			{
				if (titulo.equals(nombreCamino))
				{
					List<String[]> actividades  = TraductorCamino.verActividadesCamino(idCamino);
					for (String[] actividad : actividades)
					{
						System.out.println("Actividad: " + actividad[1]);
					}
			System.out.println("Ingrese el nombre de la actividad que desea iniciar: ");
			String nombreActividad = scanner.nextLine();
			String idActividad = TraductorActividad.getIDfromNombre(idCamino, nombreActividad);
			Inscriptor.iniciarActivad(idCamino, idActividad, idEstudiante);
			System.out.println("Actividad iniciada.");
				}
			}
		}
		catch (Exception e)
		{
			e.getMessage();
		}
		
		
		
	}

}
