package menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import caminosActividades.*;
import creadores.*;
import usuarios.*;

public class MenuCreacionActividad
{
	// Variables comunes a todas las actividades
	static String IDCamino, titulo, descripcion;
	static int numObjetivos, duracion, dia, mes, anio, pos;
	static double dificultad, calificacionMin;
	static List<String> objetivos = new ArrayList<>();
	static List<String> preguntasExamen = new ArrayList<>();
	static boolean obligatoria, verdaderoFalso;

	public static void mostrarMenuCreacionActividad(String profesorID)
	{
		System.out.println("Creacion de actividad");
		System.out.println("Seleccione el tipo de actividad que desea crear: ");
		System.out.println("1. Quiz");
		System.out.println("2. Tarea");
		System.out.println("3. Examen");
		System.out.println("4. Encuesta");
		System.out.println("5. Recurso educativo");
		System.out.println("0. Salir");
		Scanner scanner = new Scanner(System.in);
		int tipoActividad = scanner.nextInt();
		scanner.nextLine(); // Consume the newline character

		switch (tipoActividad)
		{
		case 1:
			menuCreacionQuiz(profesorID);
			break;
		case 2:
			menuCreacionTarea(profesorID);
			break;
		case 3:
			menuCreacionExamen(profesorID);
			break;
		case 4:
			menuCreacionEncuesta(profesorID);
			break;
		case 5:
			menuCreacionRecurso(profesorID);
			break;
		default:
			System.out.println("Volviendo al menu principal. \n");
		}
	}

	private static void menuCreacionQuiz(String profesorID)
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Creacion de quiz");
		System.out.println("Ingrese el ID del camino al que pertenece el quiz: ");
		IDCamino = scanner.nextLine();
		System.out.println("Ingrese el titulo del quiz: ");
		titulo = scanner.nextLine();
		System.out.println("Ingrese la descripcion del quiz: ");
		descripcion = scanner.nextLine();
		System.out.println("Cuantos objetivos tiene el quiz?");
		numObjetivos = scanner.nextInt();
		scanner.nextLine();
		objetivos = new ArrayList<String>();
		for (int i = 0; i < numObjetivos; i++)
		{
			System.out.println("Ingrese el objetivo " + (i + 1) + ": ");
			String objetivo = scanner.nextLine();
			objetivos.add(objetivo);
		}
		System.out.println("Ingrese la dificultad del quiz: ");
		dificultad = scanner.nextDouble();
		System.out.println("Ingrese la duracion estimada del quiz (en minutos): ");
		duracion = scanner.nextInt();
		System.out.println("Ingrese la fecha de entrega del quiz: ");
		System.out.println("Dia: ");
		dia = scanner.nextInt();
		System.out.println("Mes: ");
		mes = scanner.nextInt();
		System.out.println("Año: ");
		anio = scanner.nextInt();
		int[] fechaLimiteQuiz =
		{ dia, mes, anio };
		System.out.println("Es obligatorio realizar este quiz? (true/false): ");
		obligatoria = scanner.nextBoolean();
		System.out.println("Ingrese la calificacion minima para aprobar el quiz: ");
		calificacionMin = scanner.nextDouble();
		System.out.println("El quiz es de verdadero o falso? (true/false): ");
		verdaderoFalso = scanner.nextBoolean();
		System.out.println("Ingrese la posición de la actividad dentro del camino: ");
		pos = scanner.nextInt();
		int cantidadOpciones = 0;
		if (verdaderoFalso)
		{
			cantidadOpciones = 2; // Quiz de verdadero o falso
		}
		else
		{
			cantidadOpciones = 4; // Quiz de opcion múltiple
		}
		System.out.println("Ingrese la cantidad de preguntas que tendra el quiz: ");
		int numPreguntas = scanner.nextInt();
		List<PreguntaQuiz> preguntas = new ArrayList<PreguntaQuiz>();
		for (int i = 0; i < numPreguntas; i++)
		{
			System.out.println("Ingrese el texto de la pregunta " + (i + 1) + ": ");
			String texto = scanner.nextLine();
			if (cantidadOpciones == 2) // Quiz de verdadero o falso
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
					i--;
				}
				else if (!correcta1 && !correcta2)
				{
					System.out.println("Al menos una de las opciones debe ser correcta.");
					i--;
				}
				OpcionQuiz opcion2 = new OpcionQuiz(respuesta2, explicacion2, correcta2);

				OpcionQuiz correcta = null;
				if (opcion1.isCorrecta())
				{
					correcta = opcion1;
				}
				else
				{
					correcta = opcion2;
				}
				PreguntaQuiz pregunta = new PreguntaQuiz(texto, correcta, cantidadOpciones);
				pregunta.setOpcion(1, opcion1);
				pregunta.setOpcion(2, opcion2);
				preguntas.add(pregunta);
			}
			else // Quiz de opcion multiple
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

				OpcionQuiz correcta = null;
				if (correcta1 && correcta2 && correcta3 && correcta4)
				{
					System.out.println("Solo una de las opciones puede ser correcta.");
					i--;
				}
				else if (!correcta1 && !correcta2 && !correcta3 && !correcta4)
				{
					System.out.println("Al menos una de las opciones debe ser correcta.");
					i--;
				}
				else
				{
					if (correcta1)
					{
						correcta = opcion1;
					}
					else if (correcta2)
					{
						correcta = opcion2;
					}
					else if (correcta3)
					{
						correcta = opcion3;
					}
					else
					{
						correcta = opcion4;
					}
				}
				PreguntaQuiz pregunta = new PreguntaQuiz(texto, correcta, cantidadOpciones);
				pregunta.setOpcion(1, opcion1);
				pregunta.setOpcion(2, opcion2);
				pregunta.setOpcion(3, opcion3);
				pregunta.setOpcion(4, opcion4);
				preguntas.add(pregunta);
			}
		}
		System.out.println("Creando quiz...");
		try
		{
			CreadorQuiz.crearQuizCero(IDCamino, titulo, descripcion, objetivos, dificultad, duracion, fechaLimiteQuiz,
					obligatoria, calificacionMin, preguntas, profesorID, verdaderoFalso, pos);
			System.out.println("Quiz creado exitosamente.");
		}
		catch (Exception e)
		{
			System.out.println("Ocurrió un error al crear el quiz.");
			e.getMessage();
			e.printStackTrace();
		}

	}

	private static void menuCreacionTarea(String profesorID)
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Creacion de tarea");
		System.out.println("Ingrese el ID del camino al que pertenece la tarea: ");
		IDCamino = scanner.nextLine();
		System.out.println("Ingrese el titulo de la tarea: ");
		titulo = scanner.nextLine();
		System.out.println("Ingrese la descripcion de la tarea: ");
		descripcion = scanner.nextLine();
		System.out.println("Cuantos objetivos tiene la tarea?");
		numObjetivos = scanner.nextInt();
		scanner.nextLine();
		objetivos = new ArrayList<String>();
		for (int i = 0; i < numObjetivos; i++)
		{
			System.out.println("Ingrese el objetivo " + (i + 1) + ": ");
			String objetivo = scanner.nextLine();
			objetivos.add(objetivo);
		}
		System.out.println("Ingrese la dificultad de la tarea: ");
		dificultad = scanner.nextDouble();
		System.out.println("Ingrese la duracion estimada de la tarea (en minutos): ");
		duracion = scanner.nextInt();
		System.out.println("Ingrese la fecha de entrega de la tarea: ");
		System.out.println("Dia: ");
		dia = scanner.nextInt();
		System.out.println("Mes: ");
		mes = scanner.nextInt();
		System.out.println("Año: ");
		anio = scanner.nextInt();
		int[] fechaLimiteTarea =
		{ dia, mes, anio };
		System.out.println("Es obligatorio realizar esta tarea? (true/false): ");
		obligatoria = scanner.nextBoolean();
		System.out.println("Ingrese las instrucciones de la tarea: ");
		scanner.nextLine();
		String instrucciones = scanner.nextLine();
		System.out.println("Ingrese la posicion de la actividad dentro del camino: ");
		pos = scanner.nextInt();
		System.out.println("Creando tarea...");
		try
		{
			CreadorTarea.crearTareaCero(IDCamino, titulo, descripcion, objetivos, dificultad, duracion,
					fechaLimiteTarea, obligatoria, instrucciones, profesorID, pos);
			System.out.println("Tarea creada exitosamente.");
		}
		catch (Exception e)
		{
			System.out.println("Ocurrió un error al crear la tarea.");
			e.getMessage();
			e.printStackTrace();
		}
	}

	private static void menuCreacionExamen(String profesorID)
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Creacion de examen");
		System.out.println("Creacion de examen");
		System.out.println("Ingrese el ID del camino al que pertenece el examen: ");
		IDCamino = scanner.nextLine();
		System.out.println("Ingrese el titulo del examen: ");
		titulo = scanner.nextLine();
		System.out.println("Ingrese la descripcion del examen: ");
		descripcion = scanner.nextLine();
		System.out.println("Cuantos objetivos tiene el examen?");
		numObjetivos = scanner.nextInt();
		scanner.nextLine();
		objetivos = new ArrayList<String>();
		for (int i = 0; i < numObjetivos; i++)
		{
			System.out.println("Ingrese el objetivo " + (i + 1) + ": ");
			String objetivo = scanner.nextLine();
			objetivos.add(objetivo);
		}
		System.out.println("Ingrese la dificultad del examen: ");
		dificultad = scanner.nextDouble();
		System.out.println("Ingrese la duracion estimada del examen (en minutos): ");
		duracion = scanner.nextInt();
		System.out.println("Ingrese la fecha de entrega del examen: ");
		System.out.println("Dia: ");
		dia = scanner.nextInt();
		System.out.println("Mes: ");
		mes = scanner.nextInt();
		System.out.println("Año: ");
		anio = scanner.nextInt();
		int[] fechaLimiteExamen =
		{ dia, mes, anio };
		System.out.println("Es obligatorio realizar este examen? (true/false): ");
		obligatoria = scanner.nextBoolean();
		System.out.println("Ingrese la calificacion minima para aprobar el examen: ");
		calificacionMin = scanner.nextDouble();
		System.out.println("Cuantas preguntas tiene el examen?");
		int numPreguntasExamen = scanner.nextInt();
		scanner.nextLine();
		preguntasExamen = new ArrayList<String>();
		for (int i = 0; i < numPreguntasExamen; i++)
		{
			System.out.println("Ingrese la pregunta " + (i + 1) + ": ");
			String pregunta = scanner.nextLine();
			preguntasExamen.add(pregunta);
		}
		System.out.println("Ingrese la posicion de la actividad dentro del camino: ");
		pos = scanner.nextInt();
		System.out.println("Creando examen...");
		try
		{
			CreadorExamen.crearExamenCero(IDCamino, titulo, descripcion, objetivos, dificultad, duracion,
					fechaLimiteExamen, obligatoria, calificacionMin, preguntasExamen, profesorID, pos);
			System.out.println("Examen creado exitosamente.");
		}
		catch (Exception e)
		{
			System.out.println("Ocurrió un error al crear el examen.");
			e.getMessage();
			e.printStackTrace();
		}
	}

	private static void menuCreacionEncuesta(String profesorID)
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Creacion de encuesta");
		System.out.println("Creacion de encuesta");
		System.out.println("Ingrese el ID del camino al que pertenece la encuesta: ");
		IDCamino = scanner.nextLine();
		System.out.println("Ingrese el titulo de la encuesta: ");
		titulo = scanner.nextLine();
		System.out.println("Ingrese la descripcion de la encuesta: ");
		descripcion = scanner.nextLine();
		System.out.println("Cuantos objetivos tiene la encuesta?");
		numObjetivos = scanner.nextInt();
		scanner.nextLine();
		objetivos = new ArrayList<String>();
		for (int i = 0; i < numObjetivos; i++)
		{
			System.out.println("Ingrese el objetivo " + (i + 1) + ": ");
			String objetivo = scanner.nextLine();
			objetivos.add(objetivo);
		}
		System.out.println("Ingrese la dificultad de la encuesta: ");
		dificultad = scanner.nextDouble();
		System.out.println("Ingrese la duracion estimada de la encuesta (en minutos): ");
		duracion = scanner.nextInt();
		System.out.println("Ingrese la fecha de entrega de la encuesta: ");
		System.out.println("Dia: ");
		dia = scanner.nextInt();
		System.out.println("Mes: ");
		mes = scanner.nextInt();
		System.out.println("Año: ");
		anio = scanner.nextInt();
		int[] fechaLimiteEncuesta =
		{ dia, mes, anio };
		System.out.println("Es obligatorio realizar esta encuesta? (true/false): ");
		obligatoria = scanner.nextBoolean();
		System.out.println("Cuantas preguntas tiene la encuesta?");
		int numPreguntasEncuesta = scanner.nextInt();
		scanner.nextLine();
		List<String> preguntasEncuesta = new ArrayList<String>();
		for (int i = 0; i < numPreguntasEncuesta; i++)
		{
			System.out.println("Ingrese la pregunta " + (i + 1) + ": ");
			String pregunta = scanner.nextLine();
			preguntasEncuesta.add(pregunta);
		}
		System.out.println("Ingrese la posicion de la actividad dentro del camino: ");
		pos = scanner.nextInt();
		System.out.println("Creando encuesta...");
		try
		{
			CreadorEncuesta.crearEncuestaCero(IDCamino, titulo, descripcion, objetivos, dificultad, duracion,
					fechaLimiteEncuesta, obligatoria, preguntasEncuesta, profesorID, pos);
			System.out.println("Encuesta creada exitosamente.");
		}
		catch (Exception e)
		{
			System.out.println("Ocurrió un error al crear la encuesta.");
			e.getMessage();
			e.printStackTrace();
		}
	}

	private static void menuCreacionRecurso(String profesorID)
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Creacion de recurso educativo");
		System.out.println("Ingrese el ID del camino al que pertenece el recurso educativo: ");
		IDCamino = scanner.nextLine();
		System.out.println("Ingrese el titulo del recurso educativo: ");
		titulo = scanner.nextLine();
		System.out.println("Ingrese la descripcion del recurso educativo: ");
		descripcion = scanner.nextLine();
		System.out.println("Cuantos objetivos tiene el recurso educativo?");
		numObjetivos = scanner.nextInt();
		scanner.nextLine();
		objetivos = new ArrayList<String>();
		for (int i = 0; i < numObjetivos; i++)
		{
			System.out.println("Ingrese el objetivo " + (i + 1) + ": ");
			String objetivo = scanner.nextLine();
			objetivos.add(objetivo);
		}
		System.out.println("Ingrese la dificultad del recurso educativo: ");
		dificultad = scanner.nextDouble();
		System.out.println("Ingrese la duracion estimada del recurso educativo (en minutos): ");
		duracion = scanner.nextInt();
		System.out.println("Ingrese la fecha de entrega del recurso educativo: ");
		System.out.println("Dia: ");
		dia = scanner.nextInt();
		System.out.println("Mes: ");
		mes = scanner.nextInt();
		System.out.println("Año: ");
		anio = scanner.nextInt();
		int[] fechaLimiteRecurso =
		{ dia, mes, anio };
		System.out.println("Es obligatorio realizar este recurso educativo? (true/false): ");
		obligatoria = scanner.nextBoolean();
		System.out.println("Ingrese la URL del recurso educativo: ");
		scanner.nextLine();
		String URL = scanner.nextLine();
		System.out.println("Ingrese las instrucciones para este recurso educativo: ");
		String instruccionesRecurso = scanner.nextLine();
		System.out.println("Ingrese la posicion de la actividad dentro del camino: ");
		pos = scanner.nextInt();
		System.out.println("Creando recurso educativo...");
		try
		{
			CreadorAR.crearARCero(IDCamino, titulo, descripcion, objetivos, dificultad, duracion, fechaLimiteRecurso,
					obligatoria, URL, instruccionesRecurso, profesorID, pos);
			System.out.println("Recurso educativo creado exitosamente.");
		}
		catch (Exception e)
		{
			System.out.println("Ocurrió un error al crear el recurso educativo.");
			e.getMessage();
			e.printStackTrace();
		}
	}
}
