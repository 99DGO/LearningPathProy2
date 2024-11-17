package menu;

import java.util.HashMap;
import java.util.Scanner;

import editores.*;
import traductores.*;
import usuarios.*;
import caminosActividades.*;

public class MenuEdicionActividad
{
	public static void mostrarMenuEdicionActividad(String profesor)
	{
		// Menu de edicion de actividad
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
				boolean tipoQuiz = TraductorActividad.getTipoQuiz(IDCamino, IDActividad);
				mostrarMenuEdicionQuiz(IDCamino, IDActividad, tipoQuiz);
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

	public static void mostrarMenuEdicionQuiz(String IDCamino, String IDActividad, boolean tipoQuiz)
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
				System.out.println("Descripcion editada exitosamente.\nNueva descripcion: " + nuevaDescripcion + "\n");
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
			int[] fechaLimite =
			{ dia, mes, anio };
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
				System.out.println(
						"Obligatoriedad editada exitosamente.\nNueva obligatoriedad: " + newObligatoria + "\n");
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
				System.out.println("Actividad siguiente (exitosa) añadida exitosamente.\nID de la actividad siguiente: "
						+ IDSiguienteExitosa + "\n");
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
			System.out.println("Ingrese la nueva calificacion minima: ");
			scanner.nextLine();
			double nuevaCalificacionMin = scanner.nextDouble();
			try
			{
				EditorQuiz.editCalificacionMin(IDCamino, IDActividad, nuevaCalificacionMin);
				System.out.println("Calificacion minima editada exitosamente.\nNueva calificacion minima: "
						+ nuevaCalificacionMin + "\n");
			}
			catch (Exception e)
			{
				System.out.println("Ocurrió un error al editar la calificacion minima.");
				e.getMessage();
				e.printStackTrace();
			}
			break;

		case 14:
			// Añadir pregunta
			System.out.println("Ingrese el texto de la nueva pregunta: ");
			scanner.nextLine();
			String nuevaPreguntaTexto = scanner.nextLine();
			if (tipoQuiz == true) // Quiz de verdadero o falso
			{
				System.out.println("Ingrese la primera opcion de respuesta: ");
				String respuesta1 = scanner.nextLine();
				System.out.println("La primera opción es correcta? (true/false): ");
				boolean correcta1 = scanner.nextBoolean();
				System.out.println("Ingrese la explicacion de la respuesta: ");
				String explicacion1 = scanner.nextLine();
				OpcionQuiz opcion1 = new OpcionQuiz(respuesta1, explicacion1, correcta1);

				System.out.println("Ingrese la segunda opcion de respuesta: ");
				String respuesta2 = scanner.nextLine();
				System.out.println("La segunda opción es correcta? (true/false): ");
				boolean correcta2 = scanner.nextBoolean();
				System.out.println("Ingrese la explicacion de la respuesta: ");
				String explicacion2 = scanner.nextLine();

				if (correcta1 && correcta2)
				{
					System.out.println("Solo una de las opciones puede ser correcta.");
				}
				else if (!correcta1 && !correcta2)
				{
					System.out.println("Al menos una de las opciones debe ser correcta.");
				}
				OpcionQuiz opcion2 = new OpcionQuiz(respuesta2, explicacion2, correcta2);

				int correcta = 0;
				if (opcion1.isCorrecta())
				{
					correcta = 1;
				}
				else
				{
					correcta = 2;
				}
				PreguntaQuiz newPregunta = new PreguntaQuiz(nuevaPreguntaTexto, correcta, 2);
				newPregunta.setOpcion(1, opcion1);
				newPregunta.setOpcion(2, opcion2);
				try
				{
					EditorQuiz.editAddPregunta(newPregunta, IDCamino, IDActividad);
				}
				catch (Exception e)
				{
					e.getMessage();
				}

			}
			else // Quiz de opción múltiple
			{
				System.out.println("Ingrese la primera opcion de respuesta: ");
				String respuesta1 = scanner.nextLine();
				System.out.println("La primera opción es correcta? (true/false): ");
				boolean correcta1 = scanner.nextBoolean();
				System.out.println("Ingrese la explicacion de la respuesta: ");
				String explicacion1 = scanner.nextLine();
				OpcionQuiz opcion1 = new OpcionQuiz(respuesta1, explicacion1, correcta1);

				System.out.println("Ingrese la segunda opcion de respuesta: ");
				String respuesta2 = scanner.nextLine();
				System.out.println("La segunda opción es correcta? (true/false): ");
				boolean correcta2 = scanner.nextBoolean();
				System.out.println("Ingrese la explicacion de la respuesta: ");
				String explicacion2 = scanner.nextLine();
				OpcionQuiz opcion2 = new OpcionQuiz(respuesta2, explicacion2, correcta2);

				System.out.println("Ingrese la tercera opcion de respuesta: ");
				String respuesta3 = scanner.nextLine();
				System.out.println("La tercera opción es correcta? (true/false): ");
				boolean correcta3 = scanner.nextBoolean();
				System.out.println("Ingrese la explicacion de la respuesta: ");
				String explicacion3 = scanner.nextLine();
				OpcionQuiz opcion3 = new OpcionQuiz(respuesta3, explicacion3, correcta3);

				System.out.println("Ingrese la cuarta opcion de respuesta: ");
				String respuesta4 = scanner.nextLine();
				System.out.println("La cuarta opción es correcta? (true/false): ");
				boolean correcta4 = scanner.nextBoolean();
				System.out.println("Ingrese la explicacion de la respuesta: ");
				String explicacion4 = scanner.nextLine();
				OpcionQuiz opcion4 = new OpcionQuiz(respuesta4, explicacion4, correcta4);

				int correcta = 0;
				if (correcta1 && correcta2 && correcta3 && correcta4)
				{
					System.out.println("Solo una de las opciones puede ser correcta.");
				}
				else if (!correcta1 && !correcta2 && !correcta3 && !correcta4)
				{
					System.out.println("Al menos una de las opciones debe ser correcta.");
				}
				else
				{
					if (correcta1)
					{
						correcta = 1;
					}
					else if (correcta2)
					{
						correcta = 2;
					}
					else if (correcta3)
					{
						correcta = 3;
					}
					else
					{
						correcta = 4;
					}
				}
				PreguntaQuiz pregunta = new PreguntaQuiz(nuevaPreguntaTexto, correcta, 4);
				pregunta.setOpcion(1, opcion1);
				pregunta.setOpcion(2, opcion2);
				pregunta.setOpcion(3, opcion3);
				pregunta.setOpcion(4, opcion4);
				try
				{
					EditorQuiz.editAddPregunta(pregunta, IDCamino, IDActividad);
				}
				catch (Exception e)
				{
					e.getMessage();
				}
			}
			break;

		case 15:
			// Eliminar pregunta
			System.out.println("Ingrese la posición de la pregunta que desea eliminar: ");
			scanner.nextLine();
			int PosPreguntaEliminar = scanner.nextInt();
			try
			{
				EditorQuiz.editDelPregunta(PosPreguntaEliminar, IDCamino, IDActividad);
				System.out.println("Pregunta eliminada exitosamente.");
			}
			catch (Exception e)
			{
				System.out.println("Ocurrió un error al eliminar la pregunta.");
				e.getMessage();
				e.printStackTrace();
			}
			break;

		case 16:
			// Añadir actividad siguiente (fallida)
			System.out.println("Ingrese el nombre de la actividad siguiente (fallida): ");
			scanner.nextLine();
			String nombreSiguienteFallida = scanner.nextLine();
			try
			{
				EditorQuiz.editAddActividadSigFracaso(IDCamino, IDActividad, nombreSiguienteFallida);
				System.out.println(
						"Actividad siguiente (fallida) añadida exitosamente.\nNombre de la actividad siguiente: "
								+ nombreSiguienteFallida + "\n");
			}
			catch (Exception e)
			{
				System.out.println("Ocurrió un error al añadir la actividad siguiente (fallida).");
				e.getMessage();
				e.printStackTrace();
			}
			break;

		case 17:
			// Eliminar actividad siguiente (fallida)
			System.out.println("Ingrese la posición de la actividad siguiente (fallida) que desea eliminar: ");
			scanner.nextLine();
			int PosSiguienteFallidaEliminar = scanner.nextInt();
			try
			{
				EditorQuiz.editDelActividadSigFracaso(IDCamino, IDActividad, PosSiguienteFallidaEliminar);
				System.out.println("Actividad siguiente (fallida) eliminada exitosamente.");
			}
			catch (Exception e)
			{
				System.out.println("Ocurrió un error al eliminar la actividad siguiente (fallida).");
				e.getMessage();
				e.printStackTrace();
			}
			break;

		default:
			System.out.println("Volviendo al menu principal. \n");
			break;
		}
	}

	public static void mostrarMenuEdicionTarea(String IDCamino, String IDActividad)
	{
		// TODO
		System.out.println("Menú de edición de tarea");
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
		System.out.println("13. Añadir actividad siguiente (fallida)");
		System.out.println("14. Eliminar actividad siguiente (fallida)");
		System.out.println("15. Editar instrucciones");
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
				EditorTarea.editNombre(IDCamino, IDActividad, nuevoTitulo);
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
				EditorTarea.editDescripcion(IDCamino, IDActividad, nuevaDescripcion);
				System.out.println("Descripcion editada exitosamente.\nNueva descripcion: " + nuevaDescripcion + "\n");
			}
			catch (Exception e)
			{
				System.out.println("Ocurrió un error al editar la descripcion.");
				e.getMessage();
				e.printStackTrace();
			}

		case 3:
			// Editar dificultad
			System.out.println("Ingrese la nueva dificultad: ");
			scanner.nextLine();
			Float nuevaDificultad = scanner.nextFloat();
			try
			{
				EditorTarea.editDificultad(IDCamino, IDActividad, nuevaDificultad);
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
				EditorTarea.editDuracion(IDCamino, IDActividad, nuevaDuracion);
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
				EditorTarea.editAddObjetivo(IDCamino, IDActividad, nuevoObjetivo);
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
				EditorTarea.editDelObjetivo(IDCamino, IDActividad, objetivoEliminar);
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
			int[] fechaLimite =
			{ dia, mes, anio };
			try
			{
				EditorTarea.editFechaLim(IDCamino, IDActividad, fechaLimite);
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
				EditorTarea.editObligatoria(IDCamino, IDActividad, newObligatoria);
				System.out.println(
						"Obligatoriedad editada exitosamente.\nNueva obligatoriedad: " + newObligatoria + "\n");
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
				EditorTarea.editAddActividadPrereq(IDCamino, IDActividad, IDPrerequisito);
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
				EditorTarea.editDelActividadPrereq(IDCamino, IDActividad, PosPrerequisitoEliminar);
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
				EditorTarea.editAddActividadSiguienteExitosa(IDCamino, IDActividad, IDSiguienteExitosa);
				System.out.println("Actividad siguiente (exitosa) añadida exitosamente.\nID de la actividad siguiente: "
						+ IDSiguienteExitosa + "\n");
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
				EditorTarea.editDelActividadSiguienteExitosa(IDCamino, IDActividad, PosSiguienteExitosaEliminar);
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
			// Añadir actividad siguiente (fallida)
			System.out.println("Ingrese el nombre de la actividad siguiente (fallida): ");
			scanner.nextLine();
			String nombreSiguienteFallida = scanner.nextLine();
			try
			{
				EditorTarea.editAddActividadSigFracaso(IDCamino, IDActividad, nombreSiguienteFallida);
				System.out.println(
						"Actividad siguiente (fallida) añadida exitosamente.\nNombre de la actividad siguiente: "
								+ nombreSiguienteFallida + "\n");
			}
			catch (Exception e)
			{
				System.out.println("Ocurrió un error al añadir la actividad siguiente (fallida).");
				e.getMessage();
				e.printStackTrace();
			}
			break;

		case 14:
			// Eliminar actividad siguiente (fallida)
			System.out.println("Ingrese la posición de la actividad siguiente (fallida) que desea eliminar: ");
			scanner.nextLine();
			int PosSiguienteFallidaEliminar = scanner.nextInt();
			try
			{
				EditorTarea.editDelActividadSigFracaso(IDCamino, IDActividad, PosSiguienteFallidaEliminar);
				System.out.println("Actividad siguiente (fallida) eliminada exitosamente.");
			}
			catch (Exception e)
			{
				System.out.println("Ocurrió un error al eliminar la actividad siguiente (fallida).");
				e.getMessage();
				e.printStackTrace();
			}
			break;

		case 15:
			// Editar instrucciones
			System.out.println("Ingrese las nuevas instrucciones: ");
			scanner.nextLine();
			String nuevasInstrucciones = scanner.nextLine();
			try
			{
				EditorTarea.editInstrucciones(nuevasInstrucciones, IDCamino, IDActividad);
				System.out.println(
						"Instrucciones editadas exitosamente.\nNuevas instrucciones: " + nuevasInstrucciones + "\n");
			}
			catch (Exception e)
			{
				System.out.println("Ocurrió un error al editar las instrucciones.");
				e.getMessage();
				e.printStackTrace();
			}
			break;

		default:
			System.out.println("Volviendo al menu principal. \n");
		}
	}

	public static void mostrarMenuEdicionEncuesta(String IDCamino, String IDActividad)
	{
		// TODO
		System.out.println("Menú de edición de encuesta");
		Scanner scanner = new Scanner(System.in);
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
		System.out.println("13. Añadir pregunta");
		System.out.println("14. Eliminar pregunta");
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
				EditorEncuesta.editNombre(IDCamino, IDActividad, nuevoTitulo);
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
				EditorEncuesta.editDescripcion(IDCamino, IDActividad, nuevaDescripcion);
				System.out.println("Descripcion editada exitosamente.\nNueva descripcion: " + nuevaDescripcion + "\n");
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
				EditorEncuesta.editDificultad(IDCamino, IDActividad, nuevaDificultad);
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
				EditorEncuesta.editDuracion(IDCamino, IDActividad, nuevaDuracion);
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
				EditorEncuesta.editAddObjetivo(IDCamino, IDActividad, nuevoObjetivo);
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
				EditorEncuesta.editDelObjetivo(IDCamino, IDActividad, objetivoEliminar);
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
			int[] fechaLimite =
			{ dia, mes, anio };
			try
			{
				EditorEncuesta.editFechaLim(IDCamino, IDActividad, fechaLimite);
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
				EditorEncuesta.editObligatoria(IDCamino, IDActividad, newObligatoria);
				System.out.println(
						"Obligatoriedad editada exitosamente.\nNueva obligatoriedad: " + newObligatoria + "\n");
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
				EditorEncuesta.editAddActividadPrereq(IDCamino, IDActividad, IDPrerequisito);
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
				EditorEncuesta.editDelActividadPrereq(IDCamino, IDActividad, PosPrerequisitoEliminar);
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
				EditorEncuesta.editAddActividadSiguienteExitosa(IDCamino, IDActividad, IDSiguienteExitosa);
				System.out.println("Actividad siguiente (exitosa) añadida exitosamente.\nID de la actividad siguiente: "
						+ IDSiguienteExitosa + "\n");
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
				EditorEncuesta.editDelActividadSiguienteExitosa(IDCamino, IDActividad, PosSiguienteExitosaEliminar);
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
			// Añadir pregunta
			System.out.println("Ingrese el texto de la nueva pregunta: ");
			scanner.nextLine();
			String nuevaPreguntaTexto = scanner.nextLine();
			try
			{
				EditorEncuesta.editAddPregunta(nuevaPreguntaTexto, IDCamino, IDActividad);
				System.out
						.println("Pregunta añadida exitosamente.\nTexto de la pregunta: " + nuevaPreguntaTexto + "\n");
			}
			catch (Exception e)
			{
				System.out.println("Ocurrió un error al añadir la pregunta.");
				e.getMessage();
				e.printStackTrace();
			}
			break;

		case 14:
			// Eliminar pregunta
			System.out.println("Ingrese la posición de la pregunta que desea eliminar: ");
			scanner.nextLine();
			int PosPreguntaEliminar = scanner.nextInt();
			try
			{
				EditorEncuesta.editDelPregunta(PosPreguntaEliminar, IDCamino, IDActividad);
				System.out.println("Pregunta eliminada exitosamente.");
			}
			catch (Exception e)
			{
				System.out.println("Ocurrió un error al eliminar la pregunta.");
				e.getMessage();
				e.printStackTrace();
			}
			break;

		default:
			System.out.println("Volviendo al menu principal. \n");
		}
	}

	public static void mostarMenuEdicionExamen(String IDCamino, String IDActividad)
	{
		// TODO
		System.out.println("Menú de edición de examen");
		Scanner scanner = new Scanner(System.in);
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
		System.out.println("13. Añadir pregunta");
		System.out.println("14. Eliminar pregunta");
		System.out.println("15. Añadir actividad siguiente (fallida)");
		System.out.println("16. Eliminar actividad siguiente (fallida)");
		System.out.println("17. Editar calificacion minima");
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
				EditorExamen.editNombre(IDCamino, IDActividad, nuevoTitulo);
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
				EditorExamen.editDescripcion(IDCamino, IDActividad, nuevaDescripcion);
				System.out.println("Descripcion editada exitosamente.\nNueva descripcion: " + nuevaDescripcion + "\n");
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
				EditorExamen.editDificultad(IDCamino, IDActividad, nuevaDificultad);
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
				EditorExamen.editDuracion(IDCamino, IDActividad, nuevaDuracion);
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
				EditorExamen.editAddObjetivo(IDCamino, IDActividad, nuevoObjetivo);
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
				EditorExamen.editDelObjetivo(IDCamino, IDActividad, objetivoEliminar);
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
			int[] fechaLimite =
			{ dia, mes, anio };
			try
			{
				EditorExamen.editFechaLim(IDCamino, IDActividad, fechaLimite);
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
				EditorExamen.editObligatoria(IDCamino, IDActividad, newObligatoria);
				System.out.println(
						"Obligatoriedad editada exitosamente.\nNueva obligatoriedad: " + newObligatoria + "\n");
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
				EditorExamen.editAddActividadPrereq(IDCamino, IDActividad, IDPrerequisito);
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
				EditorExamen.editDelActividadPrereq(IDCamino, IDActividad, PosPrerequisitoEliminar);
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
				EditorExamen.editAddActividadSiguienteExitosa(IDCamino, IDActividad, IDSiguienteExitosa);
				System.out.println("Actividad siguiente (exitosa) añadida exitosamente.\nID de la actividad siguiente: "
						+ IDSiguienteExitosa + "\n");
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
				EditorExamen.editDelActividadSiguienteExitosa(IDCamino, IDActividad, PosSiguienteExitosaEliminar);
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
			// Añadir pregunta
			System.out.println("Ingrese el texto de la nueva pregunta: ");
			scanner.nextLine();
			String nuevaPreguntaTexto = scanner.nextLine();
			System.out.println("Ingrese la respuesta correcta: ");
			scanner.nextLine();
			try
			{
				EditorExamen.editAddPregunta(nuevaPreguntaTexto, IDCamino, IDActividad);
				System.out
						.println("Pregunta añadida exitosamente.\nTexto de la pregunta: " + nuevaPreguntaTexto + "\n");
			}
			catch (Exception e)
			{
				System.out.println("Ocurrió un error al añadir la pregunta.");
				e.getMessage();
				e.printStackTrace();
			}
			break;

		case 14:
			// Eliminar pregunta
			System.out.println("Ingrese la posición de la pregunta que desea eliminar: ");
			scanner.nextLine();
			int PosPreguntaEliminar = scanner.nextInt();
			try
			{
				EditorExamen.editDelPregunta(PosPreguntaEliminar, IDCamino, IDActividad);
				System.out.println("Pregunta eliminada exitosamente.");
			}
			catch (Exception e)
			{
				System.out.println("Ocurrió un error al eliminar la pregunta.");
				e.getMessage();
				e.printStackTrace();
			}
			break;

		case 15:
			// Añadir actividad siguiente (fallida)
			System.out.println("Ingrese el ID de la actividad siguiente (fallida): ");
			scanner.nextLine();
			String IDSiguienteFallida = scanner.nextLine();
			try
			{
				EditorExamen.editAddActividadSigFracaso(IDCamino, IDActividad, IDSiguienteFallida);
				System.out.println("Actividad siguiente (fallida) añadida exitosamente.\nID de la actividad siguiente: "
						+ IDSiguienteFallida + "\n");
			}
			catch (Exception e)
			{
				System.out.println("Ocurrió un error al añadir la actividad siguiente (fallida).");
				e.getMessage();
				e.printStackTrace();
			}
			break;

		case 16:
			// Eliminar actividad siguiente (fallida)
			System.out.println("Ingrese la posición de la actividad siguiente (fallida) que desea eliminar: ");
			scanner.nextLine();
			int PosSiguienteFallidaEliminar = scanner.nextInt();
			try
			{
				EditorExamen.editDelActividadSigFracaso(IDCamino, IDActividad, PosSiguienteFallidaEliminar);
				System.out.println("Actividad siguiente (fallida) eliminada exitosamente.");
			}
			catch (Exception e)
			{
				System.out.println("Ocurrió un error al eliminar la actividad siguiente (fallida).");
				e.getMessage();
				e.printStackTrace();
			}
			break;

		case 17:
			// Editar calificacion minima
			System.out.println("Ingrese la nueva calificacion minima: ");
			scanner.nextLine();
			Float nuevaCalificacionMinima = scanner.nextFloat();
			try
			{
				EditorExamen.editCalificacionMin(IDCamino, IDActividad, nuevaCalificacionMinima);
				System.out.println("Calificacion minima editada exitosamente.\nNueva calificacion minima: "
						+ nuevaCalificacionMinima + "\n");
			}
			catch (Exception e)
			{
				System.out.println("Ocurrió un error al editar la calificacion minima.");
				e.getMessage();
				e.printStackTrace();
			}
			break;

		default:
			System.out.println("Volviendo al menu principal. \n");
		}

	}

	public static void mostrarMenuEdicionAR(String IDCamino, String IDActividad)
	{
		// TODO
		System.out.println("Menú de edición de actividad de refuerzo");
		Scanner scanner = new Scanner(System.in);
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
		System.out.println("13. Editar recurso");
		System.out.println("14. Editar instrucciones");
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
				EditorAR.editNombre(IDCamino, IDActividad, nuevoTitulo);
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
				EditorAR.editDescripcion(IDCamino, IDActividad, nuevaDescripcion);
				System.out.println("Descripcion editada exitosamente.\nNueva descripcion: " + nuevaDescripcion + "\n");
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
				EditorAR.editDificultad(IDCamino, IDActividad, nuevaDificultad);
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
				EditorAR.editDuracion(IDCamino, IDActividad, nuevaDuracion);
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
				EditorAR.editAddObjetivo(IDCamino, IDActividad, nuevoObjetivo);
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
				EditorAR.editDelObjetivo(IDCamino, IDActividad, objetivoEliminar);
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
			int[] fechaLimite =
			{ dia, mes, anio };
			try
			{
				EditorAR.editFechaLim(IDCamino, IDActividad, fechaLimite);
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
				EditorAR.editObligatoria(IDCamino, IDActividad, newObligatoria);
				System.out.println(
						"Obligatoriedad editada exitosamente.\nNueva obligatoriedad: " + newObligatoria + "\n");
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
				EditorAR.editAddActividadPrereq(IDCamino, IDActividad, IDPrerequisito);
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
				EditorAR.editDelActividadPrereq(IDCamino, IDActividad, PosPrerequisitoEliminar);
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
				EditorAR.editAddActividadSiguienteExitosa(IDCamino, IDActividad, IDSiguienteExitosa);
				System.out.println("Actividad siguiente (exitosa) añadida exitosamente.\nID de la actividad siguiente: "
						+ IDSiguienteExitosa + "\n");
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
				EditorAR.editDelActividadSiguienteExitosa(IDCamino, IDActividad, PosSiguienteExitosaEliminar);
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
			// Editar recurso
			System.out.println("Ingrese el nuevo recurso: ");
			scanner.nextLine();
			String nuevoRecurso = scanner.nextLine();
			try
			{
				EditorAR.editRecurso(nuevoRecurso, IDCamino, IDActividad);
				System.out.println("Recurso editado exitosamente.\nNuevo recurso: " + nuevoRecurso + "\n");
			}
			catch (Exception e)
			{
				System.out.println("Ocurrió un error al editar el recurso.");
				e.getMessage();
				e.printStackTrace();
			}
			break;
			
		case 14:
			// Editar instrucciones
			System.out.println("Ingrese las nuevas instrucciones: ");
			scanner.nextLine();
			String nuevasInstrucciones = scanner.nextLine();
			try
			{
				EditorAR.editInstrucciones(nuevasInstrucciones, IDCamino, IDActividad);
				System.out.println(
						"Instrucciones editadas exitosamente.\nNuevas instrucciones: " + nuevasInstrucciones + "\n");
			}
			catch (Exception e)
			{
				System.out.println("Ocurrió un error al editar las instrucciones.");
				e.getMessage();
				e.printStackTrace();
			}
			break;
			
			default:
				System.out.println("Volviendo al menu principal. \n");
		}
	}

}
