package traductores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.OpcionQuiz;
import caminosActividades.PreguntaQuiz;
import caminosActividades.Quiz;
import controllers.LearningPathSystem;

public class TraductorQuiz 
{
	/*
	 * Retorna una lista que contiene un String[] adentro (una para cada pregunta) con el texto de la pregunta en la poscion 0
	 	y el resto de las opciones en las otras posiciones.
	 */
	public static List<String[]> retornarPreguntasSinRespuesta(String idCamino, String idActividad)
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
		
		List<String[]> preguntas = new LinkedList<String[]>();
		
		for (PreguntaQuiz preguntaObjeto: quiz.getPreguntas())
		{
			String[] preguntaInd= new String[preguntaObjeto.getCantidadOpciones()+1];
			preguntaInd[0]=preguntaObjeto.getTextoPregunta();
			
			Map<Integer, OpcionQuiz> opcionesHash=preguntaObjeto.getOpciones();
			
			//el numero de opciones va de 1-4 o 1-2
			for (Integer numOpcion: opcionesHash.keySet())
			{
				preguntaInd[numOpcion]=opcionesHash.get(numOpcion).getTexto();

			}
		}
		
		return preguntas;
		
		
	}
	
	/*
	 * Retorna una lista que contiene un String[] adentro (una para cada pregunta) con el texto de la pregunta 
	 * en la poscion 0. El resto de las opciones estan en las otras posiciones del arreglo y su explicacion de si esta correcta o no. 
	 */
	public static List<String[]> retornarPreguntasConRespuesta(String idCamino, String idActividad)
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
		
		List<String[]> preguntas = new LinkedList<String[]>();
		
		for (PreguntaQuiz preguntaObjeto: quiz.getPreguntas())
		{
			String[] preguntaInd= new String[preguntaObjeto.getCantidadOpciones()+2];
			preguntaInd[0]=preguntaObjeto.getTextoPregunta();
			
			Map<Integer, OpcionQuiz> opcionesHash=preguntaObjeto.getOpciones();
			
			//el numero de opciones va de 1-4 o 1-2
			for (Integer numOpcion: opcionesHash.keySet())
			{
				OpcionQuiz opcion=opcionesHash.get(numOpcion);
				String opcionExplicacion=opcion.getTexto()+"\n";
				
				if (opcion.isCorrecta())
				{
					opcionExplicacion+="Esta opcion es correcta porque "+opcion.getExplicacion()+"\n";
				}
				else
				{
					opcionExplicacion+="Esta opcion es incorrecta porque "+opcion.getExplicacion()+"\n";
				}
				
				preguntaInd[numOpcion]=opcionExplicacion;

			}
			
			String respuesta="La respuesta es "+String.valueOf(preguntaObjeto.getRespuesta())+" porque ";
			preguntaInd[preguntaObjeto.getCantidadOpciones()+1]=respuesta;
		}
		
		return preguntas;
	}

	
}
