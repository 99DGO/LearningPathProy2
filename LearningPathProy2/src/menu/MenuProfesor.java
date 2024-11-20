package menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import caminosActividades.*;
import controllers.Autentificador;
import controllers.LearningPathSystem;
import creadores.*;
import usuarios.Profesor;
import usuarios.Usuario;
import traductores.*;
import editores.*;
import persistencia.CentralPersistencia;

public class MenuProfesor
{
	public static LearningPathSystem LPS = null;
	public static Autentificador autentificador = null;
	private static Usuario profesor;

	public static void main(String[] args)
	{
		try
		{
			CentralPersistencia.cargarTodo(true);
		}
		catch (Exception e)
		{
			System.out.println("Error al cargar los datos: " + e.getMessage());
		}
		
		if (LPS == null)
			LPS = LearningPathSystem.getInstance();

		if (autentificador == null)
			autentificador = Autentificador.getInstance(LPS);

		mostrarMenuInicioSesion();
	}

	public static void mostrarMenuInicioSesion()
	{
		System.out.println("Bienvenido al sistema de aprendizaje");
		System.out.println("Por favor, ingrese su login y contraseña");
		System.out.println("Login: ");
		Scanner scanner = new Scanner(System.in);
		String ID = scanner.nextLine();
		System.out.println("Contraseña: ");
		String contrasena = scanner.nextLine();
		try
		{
			profesor = autentificador.autentificar(ID, contrasena);
			while (profesor != null)
			{
				mostrarMenuProfesor((Profesor) profesor);
			}
		}
		catch (Exception e)
		{
			System.out.println("Ocurrio un error al iniciar sesión: "+ e.getMessage());
		}

	}

	public static void mostrarMenuProfesor(Profesor profesor)
	{
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

		switch (opcion)
		{
		case 1:
			// Crear camino de aprendizaje
			MenuCreacionEdicionCamino.mostrarMenuCreacionCamino(profesor.getID());
			break;

		case 2:
			// Crear actividad
			MenuCreacionActividad.mostrarMenuCreacionActividad(profesor.getID());
			break;

		case 3:
			// Ver todos los caminos de aprendizaje
			HashMap<String, String> allCaminos = TraductorCamino.verTodosCaminos();
			for (String ID : allCaminos.keySet())
			{
				System.out.println("ID: " + ID + ", Nombre: " + allCaminos.get(ID));
			}
			break;

		case 4:
			// Ver detalles de un camino de aprendizaje
			System.out.println("Ingrese el ID del camino de aprendizaje que desea ver: ");
			scanner.nextLine();
			String IDCamino = scanner.nextLine();
			try
			{
				HashMap<String, String> infoCamino = TraductorCamino.verInfoGeneralCamino(IDCamino);
				for (String key : infoCamino.keySet())
				{
					if (key.equals("Objetivos"))
					{
						System.out.println("Objetivos: ");
						String[] objetivosArr = infoCamino.get(key).split("\n");
						for (String objetivoActual : objetivosArr)
						{
							System.out.println(objetivoActual);
						}
					}
					else
					{
						System.out.println(key + ": " + infoCamino.get(key));
					}
				}
				System.out.println("Desea clonar este camino?");
				System.out.println("1. Quiero clonar todo el camino");
				System.out.println("2. Quiero clonar actividades del camino");
				System.out.println("0. No deseo clonar el camino");
				int clonar = scanner.nextInt();
				scanner.nextLine();
				switch (clonar)
				{
				case 1:
					// Clonar todo el camino
					try
					{
						System.out.println("Ingrese el nuevo nombre del camino: ");
						String nuevoNombre = scanner.nextLine();
						CreadorCamino.clonarCamino(IDCamino, nuevoNombre, profesor.getID());
						System.out.println("Camino clonado exitosamente.");
					}
					catch (Exception e)
					{
						System.out.println("Ocurrió un error al clonar el camino: "+ e.getMessage());
					}
					break;

				case 2:
					// Clonar algunas actividades del camino
					try
					{
						System.out.println("Ingrese el ID del camino del que desea clonar actividades: ");
						String IDCaminoOG = scanner.nextLine();
						System.out.println("Ingrese el ID del camino al que desea clonar: ");
						String IDCaminoNew = scanner.nextLine();
						System.out.println("Ingrese el ID de la actividad que desea clonar: ");
						String IDactividadOG = scanner.nextLine();
						System.out.println("Ingrese la posición de la actividad dentro del camino: ");
						int pos = scanner.nextInt();
						ChooserClonadorActividad.ClonarActividad(IDCaminoOG, IDactividadOG, profesor.getID(),
								IDCaminoNew, pos);
						System.out.println("Actividad clonada exitosamente.");
					}
					catch (Exception e)
					{
						System.out.println("Ocurrió un error al clonar la actividad: "+ e.getMessage());
					}
					break;

				default:
					System.out.println("Volviendo al menu principal. \n");
				}

			}
			catch (Exception e)
			{
				System.out.println("Ocurrió un error al ver los detalles del camino: " + e.getMessage());
			}
			break;

		case 5:
			// Ver caminos de aprendizaje creados
			List<CaminoAprendizaje> caminosCreados = profesor.getCaminos();

			for (CaminoAprendizaje camino : caminosCreados)
			{
				System.out.println("ID: " + camino.getID() + ", Nombre: " + camino.getTitulo());
			}

			break;

		case 6:
			// Ver envios de actividades
			MenuCalificarEnvios.mostrarMenuCalificarEnvios(profesor.getID());
			break;

		case 7:
			// Editar un camino de aprendizaje
			MenuCreacionEdicionCamino.mostrarMenuEdicionCamino(profesor.getID()); 
			break;

		case 8:
			// Editar una actividad
			MenuEdicionActividad.mostrarMenuEdicionActividad(profesor.getID());
			break;

		case 0:
			// Salir
			System.out.println("Desea salir de la aplicacion?");
			System.out.println("1. Si");
			System.out.println("2. No");
			int cerrar = scanner.nextInt();
			if (cerrar == 1)
			{
				System.out.println("Gracias por usar el sistema. \n¡Hasta luego!");
				try
				{
					CentralPersistencia.guardarTodo(true);
				}
				catch (Exception e)
				{
					System.out.println("Error al guardar los datos: " + e.getMessage());
				}
				System.exit(0);
			}
			else if (cerrar == 2)
			{
				System.out.println("Volviendo al menu. \n");
			}
			else
			{
				System.out.println("Opcion no valida. Volviendo al menu principal. \n");
			}
			break;

		default:
			System.out.println("Opcion no valida. Por favor intente de nuevo. \n");
		}
	}

	
}
