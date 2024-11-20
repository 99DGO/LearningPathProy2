package menu;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import senders.*;
import traductores.*;

public class MenuRealizarEnvio
{

	public static void mostrarMenuRealizarEnvio(String idEstudiante, String idCamino, String idActividad)
	{
		HashMap<String, String> infoActividad = new HashMap<String, String>();
		try
		{
			infoActividad = TraductorActividad.verInfoGeneralActividad(idCamino, idActividad);
		}
		catch (Exception e)
		{
			e.getMessage();
		}

		String tipoActividad = infoActividad.get("Tipo de actividad: ");
		switch (tipoActividad)
		{
		case "Examen":
			realizarEnvioExamen(idEstudiante, idCamino, idActividad);
			break;

		case "Quiz":

			break;

		case "Tarea":
			realizarEnvioTarea(idEstudiante, idCamino, idActividad);
			break;

		case "Encuesta":
			realizarEnvioEncuesta(idEstudiante, idCamino, idActividad);
			break;

		}

	}

	public static void realizarEnvioExamen(String idEstudiante, String idCamino, String idActividad)
	{
		Scanner scanner = new Scanner(System.in);
		try
		{
			// Llama al método TraductorExamen.retornarPreguntas
			List<String> preguntas = TraductorExamen.retornarPreguntas(idCamino, idActividad);

			System.out.println("=== Responder Examen ===");
			HashMap<String, String> respuestas = new HashMap<>();

			for (String pregunta : preguntas)
			{
				System.out.println("Pregunta: " + pregunta);
				System.out.println("Escribe tu respuesta:");
				String respuesta = scanner.nextLine();
				respuestas.put(pregunta, respuesta);
			}

			ExamenSender.sendEnvioExamen(idCamino, idActividad, idEstudiante, respuestas);
			System.out.println("Envío realizado con éxito");
		}
		catch (Exception e)
		{
			System.out.println("Error al realizar el envío del examen: " + e.getMessage());
		}
	}
	
	public static void realizarEnvioQuiz(String idEstudiante, String idCamino, String idActividad) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Obtener preguntas y opciones del quiz
            List<String[]> preguntas = TraductorQuiz.retornarPreguntasSinRespuesta(idCamino, idActividad);

            System.out.println("=== Realizar Quiz ===");
            HashMap<String, Integer> respuestas = new HashMap<>();

            for (String[] preguntaConOpciones : preguntas) {
                System.out.println("Pregunta: " + preguntaConOpciones[0]); 

                for (int i = 1; i < preguntaConOpciones.length; i++) {
                    System.out.println(i + ". " + preguntaConOpciones[i]);
                }

                System.out.print("Seleccione una opción (número): ");
                int opcionSeleccionada = scanner.nextInt();
                scanner.nextLine(); 

                if (opcionSeleccionada < 1 || opcionSeleccionada >= preguntaConOpciones.length) {
                    System.out.println("Opción inválida. Por favor intente nuevamente.");
                    break;
                }

                respuestas.put(preguntaConOpciones[0], opcionSeleccionada);
            }

            QuizSender.sendEnvioQuiz(idCamino, idActividad, idEstudiante, respuestas);
            System.out.println("Quiz enviado con éxito");

        } catch (Exception e) {
            System.out.println("Error al realizar el envío del quiz: " + e.getMessage());
        }
    }

	public static void realizarEnvioTarea(String idEstudiante, String idCamino, String idActividad)
	{
		Scanner scanner = new Scanner(System.in);

		try
		{
			String instrucciones = TraductorTarea.retornarInstrucciones(idCamino, idActividad);
			System.out.println("=== Enviar Tarea ===");
			System.out.println("Instrucciones: " + instrucciones);

			System.out.println(
					"Por favor, indique el método de entrega (por ejemplo: 'Correo electrónico' o 'Plataforma institucional'):");
			String metodoEntrega = scanner.nextLine();

			TareaSender.addMetodoEntregaTarea(idCamino, idActividad, idEstudiante, metodoEntrega);
			System.out.println("Tarea enviada con éxito");

		}
		catch (Exception e)
		{
			System.out.println("Error al realizar el envío de la tarea: " + e.getMessage());
		}
	}

	public static void realizarEnvioEncuesta(String idEstudiante, String idCamino, String idActividad)
	{
		Scanner scanner = new Scanner(System.in);

		try
		{
			List<String> preguntas = TraductorEncuesta.retornarPreguntas(idCamino, idActividad);

			System.out.println("=== Completar Encuesta ===");
			HashMap<String, String> respuestas = new HashMap<>();
			for (String pregunta : preguntas)
			{
				System.out.println("Pregunta: " + pregunta);
				System.out.println("Escribe tu respuesta:");
				String respuesta = scanner.nextLine();
				respuestas.put(pregunta, respuesta);
			}

			EncuestaSender.sendEnvioEncuesta(idCamino, idActividad, idEstudiante, respuestas);
			System.out.println("Encuesta enviada con éxito");

		}
		catch (Exception e)
		{
			System.out.println("Error al realizar el envío de la encuesta: " + e.getMessage());
		}
	}

}
