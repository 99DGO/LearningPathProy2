package menu;

import java.util.HashMap;
import java.util.Scanner;

import editores.EditorQuiz;
import traductores.TraductorActividad;
import usuarios.Profesor;

public class MenuEdicionActividad
{
	public static void mostrarMenuEdicionActividad(Profesor profesor)
	{
		// TODO Crear menu de edicion de actividad
		System.out.println("Edicion de actividad");
		System.out.println("Ingrese el ID del camino al que pertenece la actividad a editar: ");
		Scanner scanner = new Scanner(System.in);
		String IDCamino = scanner.nextLine();
		System.out.println("Ingrese el ID de la actividad que desea editar: ");
		String IDActividad = scanner.nextLine();
		try
		{
			HashMap<String, String> infoActividad = TraductorActividad.verInfoGeneralActividad(IDCamino, IDActividad);
			for (String key : infoActividad.keySet())
			{
				if (key.equals("Objetivos"))
				{
					System.out.println("Objetivos: ");
					String[] objetivosArr = infoActividad.get(key).split("\n");
					for (String objetivoActual : objetivosArr)
					{
						System.out.println(objetivoActual);
					}
				}
				else
				{
					System.out.println(key + ": " + infoActividad.get(key));
				}
			}

			String tipoActividad = infoActividad.get("Tipo de actividad: ");
			switch (tipoActividad)
			{
			case "Quiz":
				mostrarMenuEdicionQuiz(IDCamino, IDActividad);
				break;

			case "Tarea":
				mostrarMenuEdicionTarea(IDCamino, IDActividad);
				break;

			case "Encuesta":
				mostrarMenuEdicionEncuesta(IDCamino, IDActividad);
				break;

			case "Examen":
				mostarMenuEdicionExamen(IDCamino, IDActividad);
				break;

			case "Actividad Recurso":
				mostrarMenuEdicionAR(IDCamino, IDActividad);
				break;

			default:
				System.out.println("Actividad no reconocida. Volviendo al menu principal. \n");
			}
		}
		catch (Exception e)
		{
			e.getMessage();
			e.printStackTrace();
		}
	}

	public static void mostrarMenuEdicionQuiz(String IDCamino, String IDActividad)
	{
		System.out.println("Menú de edición de quiz");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Seleccione una opcion: ");
        System.out.println("1. Editar titulo");
        System.out.println("2. Editar descripcion");
        System.out.println("3. Editar dificultad");
        System.out.println("4. Editar duracion");
        System.out.println("5. Añadir objetivos");
        System.out.println("6. Eliminar objetivos");
        System.out.println("7. Editar fecha de entrega");
        System.out.println("8. Editar obligatoriedad");
        System.out.println("9. Añadir actividad prerequisito");
        System.out.println("10. Eliminar actividad prerequisito");
        System.out.println("11. Añadir actividad siguiente (exitosa)");
        System.out.println("12. Eliminar actividad siguiente (exitosa)");
        System.out.println("13. Editar calificacion minima");
        System.out.println("14. Añadir pregunta");
        System.out.println("15. Eliminar pregunta");
        System.out.println("16. Añadir actividad siguiente (fallida)");
        System.out.println("17. Eliminar actividad siguiente (fallida)");
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
            	EditorQuiz.editNombre(IDCamino, IDActividad, nuevoTitulo);
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
                EditorQuiz.editDescripcion(IDCamino, IDActividad, nuevaDescripcion);
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
				EditorQuiz.editDificultad(IDCamino, IDActividad, nuevaDificultad);
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
				EditorQuiz.editDuracion(IDCamino, IDActividad, nuevaDuracion);
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
        		EditorQuiz.editAddObjetivo(IDCamino, IDActividad, nuevoObjetivo);
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
        	// Eliminar objetivo
        	System.out.println("Ingrese la posicion del objetivo que desea eliminar: ");
        	scanner.nextLine();
        	int objetivoEliminar = scanner.nextInt();
        	try
			{
        		EditorQuiz.editDelObjetivo(IDCamino, IDActividad, objetivoEliminar);
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
        	// Editar fecha de entrega
        	System.out.println("Ingrese la nueva fecha de entrega: ");
        	System.out.println("Dia: ");
        	int dia = scanner.nextInt();
        	System.out.println("Mes: ");
        	int mes = scanner.nextInt();
        	System.out.println("Año: ");
        	int anio = scanner.nextInt();
        	int[] fechaLimite = {dia, mes, anio};
        	try
        	{
        		EditorQuiz.editFechaLim(IDCamino, IDActividad, fechaLimite);
				System.out.println(
						"Fecha de entrega editada exitosamente.\nNueva fecha de entrega: " + fechaLimite + "\n");
			}
			catch (Exception e)
			{
				System.out.println("Ocurrió un error al editar la fecha de entrega.");
				e.getMessage();
				e.printStackTrace();
        	}
        	break;
        
        case 8:
        	// Editar obligatoriedad
        	System.out.println("Es obligatorio realizar esta actividad? (true/false): ");
        	boolean newObligatoria = scanner.nextBoolean();
        	try
        	{
        		EditorQuiz.editObligatoria(IDCamino, IDActividad, newObligatoria);
        		System.out.println("Obligatoriedad editada exitosamente.\nNueva obligatoriedad: " + newObligatoria + "\n");
        	}
			catch (Exception e)
			{
				System.out.println("Ocurrió un error al editar la obligatoriedad.");
				e.getMessage();
				e.printStackTrace();
			}
        	break;
        	
        case 9:
        	// Añadir actividad prerequisito
        	System.out.println("Ingrese el ID de la actividad prerequisito: ");
        	scanner.nextLine();
        	String IDPrerequisito = scanner.nextLine();
        	try
        	{
        		EditorQuiz.editAddActividadPrereq(IDCamino, IDActividad, IDPrerequisito);
        		System.out.println("Prerequisito añadido exitosamente.\nID del prerequisito: " + IDPrerequisito + "\n");
        	}
			catch (Exception e)
			{
				System.out.println("Ocurrió un error al añadir el prerequisito.");
				e.getMessage();
				e.printStackTrace();
			}
        	break;
        	
        case 10:
        	// Eliminar actividad prerequisito
        	System.out.println("Ingrese la posición de la actividad prerequisito que desea eliminar: ");
        	scanner.nextLine();
        	int PosPrerequisitoEliminar = scanner.nextInt();
        	try
        	{
        		EditorQuiz.editDelActividadPrereq(IDCamino, IDActividad, PosPrerequisitoEliminar);
        		System.out.println("Prerequisito eliminado exitosamente.");
        	}
            catch (Exception e)
			{
				System.out.println("Ocurrió un error al eliminar el prerequisito.");
				e.getMessage();
				e.printStackTrace();
			}
        	break;
        	
        case 11:
        	// Añadir actividad siguiente (exitosa)
        	System.out.println("Ingrese el ID de la actividad siguiente (exitosa): ");
        	scanner.nextLine();
        	String IDSiguienteExitosa = scanner.nextLine();
        	try
        	{
        		EditorQuiz.editAddActividadSiguienteExitosa(IDCamino, IDActividad, IDSiguienteExitosa);
        		System.out.println("Actividad siguiente (exitosa) añadida exitosamente.\nID de la actividad siguiente: " + IDSiguienteExitosa + "\n");
        	}
        	catch (Exception e)
        	{
				System.out.println("Ocurrió un error al añadir la actividad siguiente (exitosa).");
				e.getMessage();
				e.printStackTrace();
        	}
        	break;
        	
        case 12:
        	// Eliminar actividad siguiente (exitosa)
        	System.out.println("Ingrese la posición de la actividad siguiente (exitosa) que desea eliminar: ");
        	scanner.nextLine();
        	int PosSiguienteExitosaEliminar = scanner.nextInt();
        	try
        	{
        		EditorQuiz.editDelActividadSiguienteExitosa(IDCamino, IDActividad, PosSiguienteExitosaEliminar);
        		System.out.println("Actividad siguiente (exitosa) eliminada exitosamente.");
        	}
        	catch (Exception e)
        	{
        		System.out.println("Ocurrió un error al eliminar la actividad siguiente (exitosa).");
        		e.getMessage();
        		e.printStackTrace();
        	}	
        	break;
            
        case 13:
        	// Editar calificacion minima
        	break;
            
            
            
        default:
			System.out.println("Volviendo al menu principal. \n");
			break;
		}
	}

	public static void mostrarMenuEdicionTarea(String IDCamino, String IDActividad)
	{
		// TODO
	}

	public static void mostrarMenuEdicionEncuesta(String IDCamino, String IDActividad)
	{
		// TODO
	}

	public static void mostarMenuEdicionExamen(String IDCamino, String IDActividad)
	{
		// TODO
	}

	public static void mostrarMenuEdicionAR(String IDCamino, String IDActividad)
	{
		// TODO
	}

}
