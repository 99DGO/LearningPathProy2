package traductores;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Encuesta;
import caminosActividades.Tarea;
import controllers.LearningPathSystem;
import datosEstudiantes.DatosEstudianteActividad;
import datosEstudiantes.DatosEstudianteEncuesta;
import datosEstudiantes.DatosEstudianteExamen;
import datosEstudiantes.DatosEstudianteTarea;
import envios.EnvioEncuesta;
import usuarios.Estudiante;

public class TraductorTarea 
{
	
	public static String retornarInstrucciones (String idCamino, String idActividad) throws Exception
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		CaminoAprendizaje camino = LPS.getCaminoIndividual(idCamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		Tarea tarea = null;

		for (Actividad actividadIterator : camino.getActividades())
		{
			if (actividadIterator.getId().equals(idActividad))
			{
				if (!actividadIterator.getType().equals(Actividad.TAREA))
				{
					throw new Exception ("La actividad no es una tarea");
				}
				
				tarea = (Tarea) actividadIterator;
			}
		}
		
		return tarea.getInstrucciones();
		
	}

	/*
	 * Retorna un hashmap donde la llave es el id del estudiante y el valor es un String[] con el nombre del estudiante en la primera
	 * posicion y en la segunda si fue exitoso, no fue exitoso, o si solo esta enviado
	 */
	public static HashMap<String, String[]> retornarListaEstudiantesEntrega(String idCamino, String idActividad) throws Exception
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		CaminoAprendizaje camino = LPS.getCaminoIndividual(idCamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		HashMap<String, String[]> estudiantesConEntregas= new HashMap<String, String[]>();
		
		Tarea tarea = null;

		//Encuestro la actividad
		for (Actividad actividadIterator : camino.getActividades())
		{
			if (actividadIterator.getId().equals(idActividad))
			{
				if (!actividadIterator.getType().equals(Actividad.TAREA))
				{
					throw new Exception ("La actividad no es una tarea");
				}
				
				tarea = (Tarea) actividadIterator;
			}
		}
		
		HashMap<String, DatosEstudianteActividad> datosEstudiantes= tarea.getDatosEstudiantes();
		
		//Recorro todos los datos de estudiantes 
		for (String idDatosEstudiante: datosEstudiantes.keySet())
		{						
			DatosEstudianteTarea datoEstInd= (DatosEstudianteTarea) datosEstudiantes.get(idDatosEstudiante);
			Estudiante estudiante=LPS.getEstudianteIndividual(datoEstInd.getIDEstudiante());

			if (!datoEstInd.getEstado().equals(DatosEstudianteActividad.PENDIENTE))
			{
				String[] nombreEstado= new String[] {estudiante.getNombre(), datoEstInd.getEstado()};
				estudiantesConEntregas.put(datoEstInd.getIDEstudiante(), nombreEstado);
			}
		}
		
		return estudiantesConEntregas;
	}
	
	/*
	 * Retorna unalista que contiene strings que estan compuestos por la pregunta y la respuesta del estudiante
	 */
	public static String retornarEntregaIndividual(String idCamino, String idActividad, String idEstudiante) throws Exception
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		CaminoAprendizaje camino = LPS.getCaminoIndividual(idCamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		String metodoEntrega;
		
		Tarea tarea = null;

		//Encuestro la actividad
		for (Actividad actividadIterator : camino.getActividades())
		{
			if (actividadIterator.getId().equals(idActividad))
			{
				if (!actividadIterator.getType().equals(Actividad.TAREA))
				{
					throw new Exception ("La actividad no es una tarea");
				}
				
				tarea = (Tarea) actividadIterator;
			}
		}
		
		HashMap<String, DatosEstudianteActividad> datosEstudiantes= tarea.getDatosEstudiantes();

		DatosEstudianteTarea datoEstInd=null;
		
		for (DatosEstudianteActividad datoEstIterator: datosEstudiantes.values())
		{
			if(datoEstIterator.getIDEstudiante().equals(idEstudiante))
			{
				datoEstInd= (DatosEstudianteTarea) datoEstIterator;
			}
		}
		
		if (datoEstInd==null)
		{
			throw new Exception("No se encontro el estudiante inscrito en el camino");
		}
		
		if (!datoEstInd.getType().equals(DatosEstudianteActividad.PENDIENTE))
		{	
			metodoEntrega=datoEstInd.getMetodoEntrega();
			return metodoEntrega;
		}
		else
		{
			throw new Exception ("Este estudiante no tiene un envio realizado");
		}
	}
}
