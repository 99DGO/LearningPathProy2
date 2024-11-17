package traductores;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Encuesta;
import caminosActividades.Quiz;
import caminosActividades.Tarea;
import controllers.LearningPathSystem;
import datosEstudiantes.DatosEstudianteActividad;
import datosEstudiantes.DatosEstudianteEncuesta;
import datosEstudiantes.DatosEstudianteTarea;
import envios.EnvioEncuesta;

public class TraductorEncuesta 
{
	public static List<String> retornarPreguntas (String idCamino, String idActividad) throws Exception
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		CaminoAprendizaje camino = LPS.getCaminoIndividual(idCamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		Encuesta encuesta = null;

		for (Actividad actividadIterator : camino.getActividades())
		{
			if (actividadIterator.getId().equals(idActividad))
			{
				if (!actividadIterator.getType().equals(Actividad.ENCUESTA))
				{
					throw new Exception ("La actividad pasada no fue una encuesta.");
				}
				
				encuesta = (Encuesta) actividadIterator;
			}
		}
		
		return encuesta.getPreguntasAbiertas();
		
	}
	
	/*
	 * Retorna unalista que contiene strings que estan compuestos por la pregunta y la respuesta del estudiante
	 */
	public static List<String> retornarEnvioIndividual(String idCamino, String idActividad, String idEstudiante) throws Exception
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		CaminoAprendizaje camino = LPS.getCaminoIndividual(idCamino);
		List<String> respuestasFormateadas= new LinkedList<String>();
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		Encuesta encuesta = null;

		//Encuestro la actividad
		for (Actividad actividadIterator : camino.getActividades())
		{
			if (actividadIterator.getId().equals(idActividad))
			{
				if (!actividadIterator.getType().equals(Actividad.ENCUESTA))
				{
					throw new Exception ("La actividad pasada no fue una encuesta.");
				}
				
				encuesta = (Encuesta) actividadIterator;
			}
		}
		
		HashMap<String, DatosEstudianteActividad> datosEstudiantes= encuesta.getDatosEstudiantes();

		DatosEstudianteEncuesta datoEstInd= (DatosEstudianteEncuesta) datosEstudiantes.get(idEstudiante);
		
		if (!datoEstInd.getType().equals(DatosEstudianteActividad.PENDIENTE)) 
		{
			//Añado las respuestas con sus preguntas
			EnvioEncuesta envioEstInd= datoEstInd.getEnvio();
			HashMap<String, String> respuestasHash= envioEstInd.getRespuestas();
			for (String pregunta: respuestasHash.keySet())
			{
				String respuesta= respuestasHash.get(pregunta);
				respuestasFormateadas.add(pregunta+"\n"+respuesta+"\n");
			}
			
			return respuestasFormateadas;
		}
		else
		{
			throw new Exception ("Este estudiante no tiene un envio realizado");
		}
	}
	
	/*
	 * Retorna un hashmap donde la llave es el id del estudiante y el valor el nombre del estudiante 
	 */
	public static HashMap<String, String> retornarListaEstudiantesEnvios(String idCamino, String idActividad) throws Exception
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		CaminoAprendizaje camino = LPS.getCaminoIndividual(idCamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		HashMap<String, String> estudiantesConEnvios= new HashMap<String, String>();
		
		Encuesta encuesta = null;

		//Encuestro la actividad
		for (Actividad actividadIterator : camino.getActividades())
		{
			if (actividadIterator.getId().equals(idActividad))
			{
				if (!actividadIterator.getType().equals(Actividad.ENCUESTA))
				{
					throw new Exception ("La actividad pasada no fue una encuesta.");
				}
				
				encuesta = (Encuesta) actividadIterator;
			}
		}
		
		HashMap<String, DatosEstudianteActividad> datosEstudiantes= encuesta.getDatosEstudiantes();
		
		//Recorro todos los datos de estudiantes envios
		for (String idEstudiante: datosEstudiantes.keySet())
		{
			String nombreEstudiante=TraductorEstudiante.getNombrefromID(idEstudiante);
						
			DatosEstudianteEncuesta datoEstInd= (DatosEstudianteEncuesta) datosEstudiantes.get(idEstudiante);
			
			if (!datoEstInd.getEstado().equals(DatosEstudianteActividad.PENDIENTE))
			{
				estudiantesConEnvios.put(idEstudiante, nombreEstudiante);
			}
		}
		
		return estudiantesConEnvios;
	}
}
