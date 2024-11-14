package senders;

import java.util.HashMap;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.PreguntaQuiz;
import caminosActividades.Quiz;
import controllers.LearningPathSystem;
import datosEstudiantes.DatosEstudianteExamen;
import datosEstudiantes.DatosEstudianteQuiz;
import datosEstudiantes.DatosEstudianteTarea;
import envios.EnvioEncuesta;
import envios.EnvioExamen;
import envios.EnvioQuiz;
import usuarios.Estudiante;

public class QuizSender 
{
	/*
	 * Las respuestas deben estar String=texto pregunta, integer=numero de la opcion escogida
	 */
	public static void sendEnvioQuiz(String idCamino, String idActividad, String idEstudiante, HashMap<String, Integer> respuestas) throws Exception
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		CaminoAprendizaje camino = LPS.getCaminoIndividual(idCamino);
		
		Quiz quiz = null;

		for (Actividad actividadIterator : camino.getActividades())
		{
			if (actividadIterator.getId().equals(idActividad))
			{
				quiz = (Quiz) actividadIterator;
			}
		}
		
		HashMap<PreguntaQuiz, Integer> respuestasFormateadas = new HashMap<PreguntaQuiz, Integer>();
		
		//Recorro cada pregunta que tiene el quiz
		for (PreguntaQuiz preguntaObjeto : quiz.getPreguntas())
		{
			//Si la pregunta del quiz match el texto de pregunta de la respuesta, significa que son la misma pregunta
				//y se guarda en el hashmap de respuestas formateadas
			for (String preguntaStringKey: respuestas.keySet())
			{
				if (preguntaStringKey.equals(preguntaObjeto.getTextoPregunta()))
				{
					respuestasFormateadas.put(preguntaObjeto, respuestas.get(preguntaStringKey));
				}
			}
		}
		
		DatosEstudianteQuiz datosEstudiante = (DatosEstudianteQuiz) quiz.getDatoEstudianteIndFromIDEstudiante(idEstudiante);
		Estudiante estudiante = LPS.getEstudianteIndividual(idEstudiante);
		
		if (!estudiante.isActividadActiva() || estudiante.getIdActividadActiva().equals(idActividad))
		{
			throw new Exception ("No se ha iniciado esta actividad");
		}
		else
		{
			EnvioQuiz envio = new EnvioQuiz(respuestasFormateadas);
			datosEstudiante.setEnvioQuiz(envio);
	
			if (datosEstudiante.getCalificacion()<quiz.getCalificacionMin())
			{
				datosEstudiante.setEstado(DatosEstudianteTarea.NOEXITOSO);
			}
			else
			{
				datosEstudiante.setEstado(DatosEstudianteTarea.EXITOSO);
			}
			
			estudiante.setActividadActiva(false);
			estudiante.setNombreCaminoActividadActiva("Ninguna");
		}
	}


}
