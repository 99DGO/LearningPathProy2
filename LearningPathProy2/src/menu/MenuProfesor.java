package menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import caminosActividades.CaminoAprendizaje;
import caminosActividades.OpcionQuiz;
import caminosActividades.PreguntaQuiz;
import controllers.Autentificador;
import controllers.LearningPathSystem;
import creadores.*;
import usuarios.Profesor;
import usuarios.Usuario;
import traductores.TraductorCamino;
import editores.EditorCamino;

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
				System.out.println("2. Quiero clonar alguna actividades del camino");
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
					// TODO Clonar algunas actividades del camino
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
		switch (tipoActividad)
		{
		case 1:
			// Quiz
			System.out.println("Creacion de quiz");
			System.out.println("Ingrese el ID del camino al que pertenece el quiz: ");
			String IDCamino = scanner.nextLine();
			System.out.println("Ingrese el titulo del quiz: ");
			String titulo = scanner.nextLine();
			System.out.println("Ingrese la descripcion del quiz: ");
			String descripcion = scanner.nextLine();
			System.out.println("Cuantos objetivos tiene el quiz?");
			int numObjetivos = scanner.nextInt();
			scanner.nextLine();
			List<String> objetivos = new ArrayList<String>();
			for (int i = 0; i < numObjetivos; i++)
			{
				System.out.println("Ingrese el objetivo " + (i + 1) + ": ");
				String objetivo = scanner.nextLine();
				objetivos.add(objetivo);
			}
			System.out.println("Ingrese la dificultad del quiz: ");
			double dificultad = scanner.nextDouble();
			System.out.println("Ingrese la duracion estimada del quiz (en minutos): ");
			int duracion = scanner.nextInt();
			System.out.println("Ingrese la fecha de entrega del quiz: ");
			System.out.println("Dia: ");
			int dia = scanner.nextInt();
			System.out.println("Mes: ");
			int mes = scanner.nextInt();
			System.out.println("Año: ");
			int anio = scanner.nextInt();
			int[] fechaLimite = { dia, mes, anio };
			System.out.println("Es obligatorio realizar este quiz? (true/false): ");
			boolean obligatoria = scanner.nextBoolean();
			System.out.println("Ingrese la calificacion minima para aprobar el quiz: ");
			double calificacionMin = scanner.nextDouble();
			System.out.println("El quiz es de verdadero o falso? (true/false): ");
			boolean verdaderoFalso = scanner.nextBoolean();
			System.out.println("Ingrese la posición de la actividad dentro del camino: ");
			int pos = scanner.nextInt();
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
				CreadorQuiz.crearQuizCero(IDCamino, titulo, descripcion, objetivos, dificultad, duracion, fechaLimite,
						obligatoria, calificacionMin, preguntas, profesor.getID(), verdaderoFalso, pos);
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
			break;

		case 3:
			// Examen
			break;

		case 4:
			// Encuesta
			break;

		case 5:
			// Recurso educativo
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
	}
}
