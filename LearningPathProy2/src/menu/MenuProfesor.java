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

public class MenuProfesor
{
	public static LearningPathSystem LPS = null;
	public static Autentificador autentificador = null;
	private static Usuario profesor;

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
			System.out.println(e.getMessage());
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
			mostrarMenuCreacionCamino();
			break;

		case 2:
			// Crear actividad
			mostrarMenuCreacionActividad();
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
						System.out.println("Ocurrió un error al clonar el camino.");
						e.getMessage();
						e.printStackTrace();
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
						System.out.println("Ocurrió un error al clonar la actividad.");
						e.getMessage();
						e.printStackTrace();
					}
					break;

				default:
					System.out.println("Volviendo al menu principal. \n");
				}

			}
			catch (Exception e)
			{
				e.getMessage();
				e.printStackTrace();
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
			if (cerrar == 1)
			{
				System.out.println("Gracias por usar el sistema. \n¡Hasta luego!");
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

	public static void mostrarMenuCreacionCamino()
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

	public static void mostrarMenuCreacionActividad()
	{
		// TODO Crear menu de creacion de actividad
		System.out.println("Creacion de actividad");
		System.out.println("Seleccione el tipo de actividad que desea crear: ");
		System.out.println("1. Quiz");
		System.out.println("2. Tarea");
		System.out.println("3. Examen");
		System.out.println("4. Encuesta");
		System.out.println("5. Recurso educativo");
		System.out.println("0. Salir");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		int tipoActividad = scanner.nextInt();

		// Variables comunes a todas las actividades
		String IDCamino, titulo, descripcion;
		int numObjetivos, duracion, dia, mes, anio, pos;
		double dificultad, calificacionMin;
		List<String> objetivos = new ArrayList<>();
		List<String> preguntasExamen = new ArrayList<>();
		boolean obligatoria, verdaderoFalso;

		switch (tipoActividad)
		{
		case 1:
			// Quiz
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
				CreadorQuiz.crearQuizCero(IDCamino, titulo, descripcion, objetivos, dificultad, duracion,
						fechaLimiteQuiz, obligatoria, calificacionMin, preguntas, profesor.getID(), verdaderoFalso,
						pos);
				System.out.println("Quiz creado exitosamente.");
			}
			catch (Exception e)
			{
				System.out.println("Ocurrió un error al crear el quiz.");
				e.getMessage();
				e.printStackTrace();
			}
			break;

		case 2:
			// Tarea
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
						fechaLimiteTarea, obligatoria, instrucciones, profesor.getID(), pos);
				System.out.println("Tarea creada exitosamente.");
			}
			catch (Exception e)
			{
				System.out.println("Ocurrió un error al crear la tarea.");
				e.getMessage();
				e.printStackTrace();
			}
			break;

		case 3:
			// Examen
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
						fechaLimiteExamen, obligatoria, calificacionMin, preguntasExamen, profesor.getID(), pos);
				System.out.println("Examen creado exitosamente.");
			}
			catch (Exception e)
			{
				System.out.println("Ocurrió un error al crear el examen.");
				e.getMessage();
				e.printStackTrace();
			}
			break;

		case 4:
			// Encuesta
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
						fechaLimiteEncuesta, obligatoria, preguntasEncuesta, profesor.getID(), pos);
				System.out.println("Encuesta creada exitosamente.");
			}
			catch (Exception e)
			{
				System.out.println("Ocurrió un error al crear la encuesta.");
				e.getMessage();
				e.printStackTrace();
			}
			break;

		case 5:
			// Recurso educativo
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
				CreadorAR.crearARCero(IDCamino, titulo, descripcion, objetivos, dificultad, duracion,
						fechaLimiteRecurso, obligatoria, URL, instruccionesRecurso, profesor.getID(), pos);
				System.out.println("Recurso educativo creado exitosamente.");
			}
			catch (Exception e)
			{
				System.out.println("Ocurrió un error al crear el recurso educativo.");
				e.getMessage();
				e.printStackTrace();
			}
			break;

		default:
			System.out.println("Volviendo al menu principal. \n");
		}

	}

	public static void mostrarMenuEdicionCamino()
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

	public static void mostrarMenuEdicionActividad()
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
