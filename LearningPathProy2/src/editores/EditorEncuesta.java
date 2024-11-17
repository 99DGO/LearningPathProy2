package editores;

import java.util.Date;

import caminosActividades.Actividad;
import caminosActividades.Encuesta;
import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;

public class EditorEncuesta extends EditorActividadGeneral
{
	public static void editDelPregunta(int pos, String IDcamino, String IDactividad) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		Encuesta actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				if (!actividadIterator.getType().equals(Actividad.ENCUESTA))
				{
					throw new Exception ("La actividad pasada no fue una encuesta.");
				}
				
				actividad= (Encuesta) actividadIterator;
			}
		}
		
		if (actividad==null)
		{
			throw new Exception ("No se encontro la actividad");
		}
		
		
		if (pos>actividad.getPreguntasAbiertas().size() || pos<=0)
		{
			throw new Exception ("El número de la pregunta no existe");
		}
		else
		{
			actividad.delPregunta(pos-1);
		}
		
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
	}
	
	public static void editAddPregunta(String pregunta, String IDcamino, String IDactividad) throws Exception 
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		Encuesta actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				if (!actividadIterator.getType().equals(Actividad.ENCUESTA))
				{
					throw new Exception ("La actividad pasada no fue una encuesta.");
				}
				
				actividad= (Encuesta) actividadIterator;
			}
		}
		
		if (actividad==null)
		{
			throw new Exception ("No se encontro la actividad");
		}
		
		actividad.addPregunta(pregunta);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
	}




}
