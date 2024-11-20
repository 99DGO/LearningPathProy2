package menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

import datosEstudiantes.DatosEstudianteActividad;
import marcadoresActividades.*;
import traductores.*;

public class MenuCalificarEnvios
{

	public static void mostrarMenuCalificarEnvios(String profesorId)
	{
		System.out.println("Menu de calificacion de envios");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Seleccione una opcion:");
		System.out.println("1. Ver envios asociados a una actividad");
		System.out.println("2. Calificar envio");
		System.out.println("3. Volver al menu principal");
		int opcion = scanner.nextInt();
		switch (opcion)
		{
		case 1:
			listarEnviosActividad(profesorId);
			break;

		case 2:
			calificarEnvio(profesorId);
			break;

		default:
			System.out.println("Volviendo al menu principal. \n");
		}
	}

	private static void listarEnviosActividad(String profesorId)
	{
		String IDCamino = null;
		System.out.println("Listado de envios asociados a una actividad");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el nombre del camino:");
		String nombreCamino = scanner.nextLine();
		System.out.println("Ingrese el nombre de la actividad:");
		String nombreActividad = scanner.nextLine();
		try
		{
			IDCamino = TraductorCamino.getIDfromNombre(nombreCamino);
		}
		catch (Exception e)
		{
			System.out.println("Ocurrió un error al obtener el ID del camino: " + e.getMessage());
		}

		if (IDCamino != null)
		{
			try
			{
				String IDActividad = TraductorActividad.getIDfromNombre(IDCamino, nombreActividad);
				HashMap<String, String> infoActividad = TraductorActividad.verInfoGeneralActividad(IDCamino,
						nombreActividad);
				String tipoActividad = infoActividad.get("Tipo de actividad: ");

				if (tipoActividad.equals("Examen"))
				{
					HashMap<String, String[]> envios = TraductorExamen.retornarListaEstudiantesEnvios(IDCamino,
							IDActividad);
					for (Entry<String, String[]> envio : envios.entrySet())
					{
						String loginEstudiante = envio.getKey();
						String nombreEstudiante = envio.getValue()[0]; // Nombre del estudiante
						String estado = envio.getValue()[1]; // Estado del envío

						System.out.println("Estudiante: " + nombreEstudiante + " | Estado: " + estado);
					}
				}
				else if (tipoActividad.equals("Tarea"))
				{
					HashMap<String, String[]> entregas = TraductorTarea.retornarListaEstudiantesEntrega(IDCamino,
							IDActividad);
					System.out.println("Entregas de Tarea:");
					for (Entry<String, String[]> entrega : entregas.entrySet())
					{
						String loginEstudiante = entrega.getKey();
						String nombreEstudiante = entrega.getValue()[0];
						String estado = entrega.getValue()[1];
						System.out.println(
								"Estudiante: " + nombreEstudiante + " (" + loginEstudiante + "), Estado: " + estado);
					}
				}
				else
				{
					System.out.println("La actividad no es calificable.");
				}

			}
			catch (Exception e)
			{
				System.out.println("Ocurrió un error al listar los envíos: " + e.getMessage());
			}
		}

	}

	private static void calificarEnvio(String profesorId)
	{
		String IDCamino = null;
		String tipoActividad = null;
		HashMap<String, String> infoActividad = null;

		System.out.println("Calificar envio");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el nombre del camino:");
		String nombreCamino = scanner.nextLine();
		System.out.println("Ingrese el nombre de la actividad:");
		String nombreActividad = scanner.nextLine();
		System.out.println("Ingrese el login del estudiante:");
		String loginEstudiante = scanner.nextLine();
		try
		{
			infoActividad = TraductorActividad.verInfoGeneralActividad(IDCamino, nombreActividad);
			tipoActividad = infoActividad.get("Tipo de actividad: ");
		}
		catch (Exception e)
		{
			System.out.println("Ocurrió un error al obtener la información de la actividad: " + e.getMessage());
		}

		if (tipoActividad != null)
		{
			String idEstudiante = "";
			try
			{
				idEstudiante = TraductorEstudiante.getIDfromLogin(loginEstudiante);
			}
			catch (Exception e)
			{

				System.out.println("Ocurrió un error al obtener el ID del estudiante: " + e.getMessage());
			}
			String idActividad = "";
			try
			{
				idActividad = TraductorActividad.getIDfromNombre(IDCamino, nombreActividad);
			}
			catch (Exception e)
			{
				System.out.println("Ocurrió un error al obtener el ID de la actividad: " + e.getMessage());
			}
			if (tipoActividad.equals("Examen"))
			{		
				System.out.println("Información del envío del examen:");
                List<String> envio = null;
				try
				{
					envio = TraductorExamen.retornarEnvioIndividual(IDCamino, idActividad, idEstudiante);
				}
				catch (Exception e)
				{
					System.out.println("Ocurrió un error al obtener la información del envío: " + e.getMessage());
				}
                envio.forEach(System.out::println);
				
				System.out.println("La actividad es un examen, ingrese la calificacion:");
				double calificacion = scanner.nextDouble();
				try
				{
					calificadorExamen.calificarExamen(IDCamino, idActividad, idEstudiante,
							calificacion);
                    System.out.println("El examen fue calificado exitosamente.");
				}
				catch (Exception e)
				{
					System.out.println("Ocurrió un error al calificar el examen: " + e.getMessage());
				}
			}
			else if (tipoActividad.equals("Tarea"))
			{
				System.out.println("Información del envío de la tarea:");
                String metodoEntrega = "";
				try
				{
					metodoEntrega = TraductorTarea.retornarEntregaIndividual(IDCamino, idActividad, idEstudiante);
				}
				catch (Exception e)
				{
					System.out.println("Ocurrió un error al obtener la información de la entrega: " + e.getMessage());
				}
                System.out.println("Método de entrega: " + metodoEntrega);

                System.out.println("La actividad es una tarea. Ingrese el estado (true si es Exitoso/false si es No Exitoso):");
                boolean estado = scanner.nextBoolean();
                try {
                    marcadorTarea.marcarTareaExito(IDCamino, idActividad, idEstudiante, estado);
                    System.out.println("La tarea fue calificada exitosamente.");
                }
				catch (Exception e)
				{
					System.out.println("Ocurrió un error al calificar la tarea: " + e.getMessage());
				}
			}
            else if (tipoActividad.equals("Actividad recurso"))
            {
			System.out.println("La actividad es un recurso educativo, marcar como completado?");
			System.out.println("1. Si");
			System.out.println("2. No");
			int calificacion = scanner.nextInt();
			if (calificacion == 1)
			{
				try
				{
					marcadorAR.marcarARTerminado(IDCamino, idActividad, idEstudiante);
					System.out.println("La actividad fue marcada como completada.");
				}
				catch (Exception e)
				{
					System.out.println("Ocurrió un error al marcar la actividad como completada: " + e.getMessage());
				}
			}
			else
			{
				System.out.println("No se ha calificado la actividad.");
			}
		}
		}
	}
}
