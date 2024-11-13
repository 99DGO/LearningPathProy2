package traductores;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Encuesta;
import caminosActividades.Quiz;
import controllers.LearningPathSystem;
import datosEstudiantes.DatosEstudianteActividad;
import datosEstudiantes.DatosEstudianteEncuesta;
import envios.EnvioEncuesta;

public class TraductorEncuesta 
{
	public static List<String> retornarPreguntas (String idCamino, String idActividad)
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		CaminoAprendizaje camino = LPS.getCaminoIndividual(idCamino);
		
		Encuesta encuesta = null;

		for (Actividad actividadIterator : camino.getActividades())
		{
			if (actividadIterator.getId().equals(idActividad))
			{
				encuesta = (Encuesta) actividadIterator;
			}
		}
		
		return encuesta.getPreguntasAbiertas();
		
	}
	
	/*
	 * Retorna un hashmap donde la llave es el nombre del estudiante y el valor una lista que contiene strings que estan compuestos por
	 * la pregunta y la respuesta del estudiante
	 */
	public static HashMap<String, List<String>> retornarEnvios(String idCamino, String idActividad)
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		CaminoAprendizaje camino = LPS.getCaminoIndividual(idCamino);
		HashMap<String, List<String>> enviosFormateados= new HashMap<String, List<String>>();
		
		Encuesta encuesta = null;

		//Encuestro la actividad
		for (Actividad actividadIterator : camino.getActividades())
		{
			if (actividadIterator.getId().equals(idActividad))
			{
				encuesta = (Encuesta) actividadIterator;
			}
		}
		
		HashMap<String, DatosEstudianteActividad> datosEstudiantes= encuesta.getDatosEstudiantes();
		
		//Recorro todos los envios
		for (String idEstudiante: datosEstudiantes.keySet())
		{
			String nombreEstudiante=TraductorEstudiante.getNombrefromID(idEstudiante);
			
			List<String> respuestasFormateadas= new LinkedList<String>();
			
			//Añado las respuestas con sus preguntas
			DatosEstudianteEncuesta datoEstInd= (DatosEstudianteEncuesta) datosEstudiantes.get(idEstudiante);
			EnvioEncuesta envioEstInd= datoEstInd.getEnvio();
			HashMap<String, String> respuestasHash= envioEstInd.getRespuestas();
			for (String pregunta: respuestasHash.keySet())
			{
				String respuesta= respuestasHash.get(pregunta);
				respuestasFormateadas.add(pregunta+"\n"+respuesta+"\n");
			}
			
			enviosFormateados.put(nombreEstudiante, respuestasFormateadas);
		}
		
		return enviosFormateados;
	}
}
