package traductores;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Encuesta;
import caminosActividades.Examen;
import controllers.LearningPathSystem;
import datosEstudiantes.DatosEstudianteActividad;
import datosEstudiantes.DatosEstudianteEncuesta;
import datosEstudiantes.DatosEstudianteExamen;
import envios.EnvioEncuesta;
import envios.EnvioExamen;
import usuarios.Estudiante;

public class TraductorExamen 
{
	public static List<String> retornarPreguntas (String idCamino, String idActividad)
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		CaminoAprendizaje camino = LPS.getCaminoIndividual(idCamino);
		
		Examen examen = null;

		for (Actividad actividadIterator : camino.getActividades())
		{
			if (actividadIterator.getId().equals(idActividad))
			{
				examen = (Examen) actividadIterator;
			}
		}
		
		return examen.getPreguntasAbiertas();
		
	}
	
	/*
	 * Retorna un hashmap donde la llave es el login del estudiante y el valor es un string[] que contiene en la primera posicion
	 * el nombre del estudiante y en la segunda la calificacion si esta calificado o "No esta calificado".
	 * Retorna el login porque este es único, si solo fuera el nombre pueden haber repetidos.
	 */
	public static HashMap<String, String[]> retornarListaEstudiantesEnvios(String idCamino, String idActividad)
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		CaminoAprendizaje camino = LPS.getCaminoIndividual(idCamino);
		HashMap<String, String[]> estudiantesConEnvios= new HashMap<String, String[]>();
		
		Examen examen = null;

		//Encuestro la actividad
		for (Actividad actividadIterator : camino.getActividades())
		{
			if (actividadIterator.getId().equals(idActividad))
			{
				examen = (Examen) actividadIterator;
			}
		}
		
		HashMap<String, DatosEstudianteActividad> datosEstudiantes= examen.getDatosEstudiantes();
		
		//Recorro todos los datos de estudiantes envios
		for (String idEstudiante: datosEstudiantes.keySet())
		{
			Estudiante estudiante=LPS.getEstudianteIndividual(idEstudiante);
						
			DatosEstudianteExamen datoEstInd= (DatosEstudianteExamen) datosEstudiantes.get(idEstudiante);
						
			if (!datoEstInd.getEstado().equals(DatosEstudianteActividad.PENDIENTE))
			{
				String calificacionString;
				
				if (datoEstInd.getEstado()!=DatosEstudianteActividad.ENVIADO)
				{
					calificacionString="No esta calificado";
				}
				else
				{
					calificacionString=String.valueOf(datoEstInd.getCalificacion());
				}
				
				String[] nombreCalificacion = new String[]{estudiante.getNombre(), calificacionString};
				estudiantesConEnvios.put(estudiante.getLogin(), nombreCalificacion);
			}
		}
		
		return estudiantesConEnvios;
	}
	
	/*
	 * Retorna una lista que contiene strings que estan compuestos por la pregunta y la respuesta del estudiante
	 */
	public static List<String> retornarEnvioIndividual(String idCamino, String idActividad, String idEstudiante) throws Exception
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		CaminoAprendizaje camino = LPS.getCaminoIndividual(idCamino);
		List<String> respuestasFormateadas= new LinkedList<String>();
		
		Examen examen = null;

		//Encuestro la actividad
		for (Actividad actividadIterator : camino.getActividades())
		{
			if (actividadIterator.getId().equals(idActividad))
			{
				examen = (Examen) actividadIterator;
			}
		}
		
		HashMap<String, DatosEstudianteActividad> datosEstudiantes= examen.getDatosEstudiantes();
		
		//Añado las respuestas con sus preguntas
		DatosEstudianteExamen datoEstInd= (DatosEstudianteExamen) datosEstudiantes.get(idEstudiante);
		
		if (!datoEstInd.getType().equals(DatosEstudianteActividad.PENDIENTE))
		{
			EnvioExamen envioEstInd= datoEstInd.getEnvio();
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

}
