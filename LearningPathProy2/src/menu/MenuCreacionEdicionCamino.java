package menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import creadores.CreadorCamino;
import editores.EditorCamino;
import traductores.TraductorCamino;
import usuarios.Profesor;

public class MenuCreacionEdicionCamino 
{
	public static void mostrarMenuCreacionCamino(Profesor profesor)
	{
		System.out.println("Creacion de camino de aprendizaje");
		System.out.println("Ingrese el titulo del camino: ");
		Scanner scanner = new Scanner(System.in);
		String titulo = scanner.nextLine();
		System.out.println("Ingrese la descripcion del camino: ");
		String descripcion = scanner.nextLine();
		System.out.println(
				"Ingrese la dificultad del camino (en una escala del 1.0 al 10.0, puede ingresar un numero decimal utilizando el .): ");
		double dificultad = scanner.nextDouble();
		System.out.println("Ingrese la duración estimada del camino (en horas): ");
		int duracion = scanner.nextInt();
		System.out.println("Cuantos objetivos tiene el camino?");
		int numObjetivos = scanner.nextInt();
		scanner.nextLine();
		List<String> objetivos = new ArrayList<String>();
		for (int i = 0; i < numObjetivos; i++)
		{
			System.out.println("Ingrese el objetivo " + (i + 1) + ": ");
			String objetivo = scanner.nextLine();
			objetivos.add(objetivo);
		}
		System.out.println("Creando camino...");
		try
		{
			CreadorCamino.crearCaminoCero(titulo, descripcion, objetivos, dificultad, duracion, profesor.getID());
			System.out.println("Camino creado exitosamente.");
		}
		catch (Exception e)
		{
			System.out.println("Ocurrió un error al crear el camino.");
			e.getMessage();
			e.printStackTrace();
		}

	}
	
	public static void mostrarMenuEdicionCamino(Profesor profesor)
	{
		System.out.println("Edicion de camino de aprendizaje");
		System.out.println("Ingrese el ID del camino que desea editar: ");
		Scanner scanner = new Scanner(System.in);
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
			switch (opcion)
			{
			case 1:
				// Editar titulo
				System.out.println("Ingrese el nuevo titulo: ");
				scanner.nextLine();
				String nuevoTitulo = scanner.nextLine();
				try
				{
					EditorCamino.editTitulo(IDCamino, nuevoTitulo);
					System.out.println("Titulo editado exitosamente.\nNuevo titulo: " + nuevoTitulo + "\n");
				}
				catch (Exception e)
				{
					System.out.println("Ocurrió un error al editar el titulo.");
					e.getMessage();
					e.printStackTrace();
				}
				break;

			case 2:
				// Editar descripcion
				System.out.println("Ingrese la nueva descripcion: ");
				scanner.nextLine();
				String nuevaDescripcion = scanner.nextLine();
				try
				{
					EditorCamino.editDescripcion(IDCamino, nuevaDescripcion);
					System.out.println(
							"Descripcion editada exitosamente.\nNueva descripcion: " + nuevaDescripcion + "\n");
				}
				catch (Exception e)
				{
					System.out.println("Ocurrió un error al editar la descripcion.");
					e.getMessage();
					e.printStackTrace();
				}
				break;

			case 3:
				// Editar dificultad
				System.out.println("Ingrese la nueva dificultad: ");
				scanner.nextLine();
				Float nuevaDificultad = scanner.nextFloat();
				try
				{
					EditorCamino.editDificultad(IDCamino, nuevaDificultad);
					System.out.println("Dificultad editada exitosamente.\nNueva dificultad: " + nuevaDificultad + "\n");
				}
				catch (Exception e)
				{
					System.out.println("Ocurrió un error al editar la dificultad.");
					e.getMessage();
					e.printStackTrace();
				}
				break;

			case 4:
				// Editar duracion
				System.out.println("Ingrese la nueva duracion: ");
				scanner.nextLine();
				int nuevaDuracion = scanner.nextInt();
				try
				{
					EditorCamino.editDuracion(IDCamino, nuevaDuracion);
					System.out.println("Duracion editada exitosamente.\nNueva duracion: " + nuevaDuracion + "\n");
				}
				catch (Exception e)
				{
					System.out.println("Ocurrió un error al editar la duracion.");
					e.getMessage();
					e.printStackTrace();
				}
				break;

			case 5:
				// Añadir objetivos
				System.out.println("Ingrese el nuevo objetivo: ");
				scanner.nextLine();
				String nuevoObjetivo = scanner.nextLine();
				try
				{
					EditorCamino.editAddObjetivo(IDCamino, nuevoObjetivo);
					System.out.println("Objetivo añadido exitosamente.\nObjetivo añadido: " + nuevoObjetivo + "\n");
				}
				catch (Exception e)
				{
					System.out.println("Ocurrió un error al añadir el objetivo.");
					e.getMessage();
					e.printStackTrace();
				}
				break;

			case 6:
				// Eliminar objetivos
				System.out.println("Ingrese la posición del objetivo que desea eliminar: ");
				scanner.nextLine();
				int objetivoEliminar = scanner.nextInt();
				try
				{
					EditorCamino.editDelObjetivo(IDCamino, objetivoEliminar);
					System.out.println("Objetivo eliminado exitosamente.");
				}
				catch (Exception e)
				{
					System.out.println("Ocurrió un error al eliminar el objetivo.");
					e.getMessage();
					e.printStackTrace();
				}
				break;

			case 7:
				// TODO Crear y agregar actividad
				MenuCreacionActividad.mostrarMenuCreacionActividad(profesor);
				break;

			case 8:
				// Eliminar actividad
				System.out.println("Ingrese la posicion de la actividad que desea eliminar: ");
				scanner.nextLine();
				int posActividad = scanner.nextInt();
				try
				{
					EditorCamino.editDelActividad(IDCamino, posActividad);
					System.out.println("Actividad eliminada exitosamente.");
				}
				catch (Exception e)
				{
					System.out.println("Ocurrió un error al eliminar la actividad.");
					e.getMessage();
					e.printStackTrace();
				}

			case 9:
				// Cambiar orden de actividades
				System.out.println(
						"Advertencia \nAl cambiar el orden de las actividades, se perderá la información de la actividad en dicha posicion. \nDesea continuar? \n1. Si \n2. No");
				scanner.nextLine();
				int confirmacion = scanner.nextInt();
				if (confirmacion == 1)
				{
					System.out.println("Continuando con el cambio de orden de actividades. \n");
				}
				else
				{
					System.out.println("Volviendo al menu principal. \n");
					break;
				}
				System.out.println("Ingrese el ID de la actividad que desea mover: ");
				String actividadMover = scanner.nextLine();
				System.out.println("Ingrese la nueva posicion de la actividad: ");
				scanner.nextLine();
				int nuevaPosicion = scanner.nextInt();
				try
				{
					EditorCamino.cambiarPosActividad(IDCamino, actividadMover, nuevaPosicion);
				}
				catch (Exception e)
				{
					System.out.println("Ocurrió un error al mover la actividad.");
					e.getMessage();
					e.printStackTrace();
				}

			default:
				System.out.println("Volviendo al menu principal. \n");
				break;
			}
		}
		catch (Exception e)
		{
			e.getMessage();
			e.printStackTrace();
		}
	}

}
