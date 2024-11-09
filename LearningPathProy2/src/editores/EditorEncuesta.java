package editores;

import java.util.Date;

import caminosActividades.Actividad;
import caminosActividades.Encuesta;
import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;

public class EditorEncuesta 
{
	public static void editDelPregunta(int pos, String IDcamino, String IDactividad) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		Encuesta actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= (Encuesta) actividadIterator;
			}
		}
		
		if (pos>=actividad.getPreguntasAbiertas().size() || pos<=0)
		{
			throw new Exception ("El número de la pregunta no existe");
		}
		else
		{
			actividad.delPregunta(pos);
		}
		
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha);
	}
	
	public static void editAddPregunta(String pregunta, String IDcamino, String IDactividad) 
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		Encuesta actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= (Encuesta) actividadIterator;
			}
		}
		
		actividad.addPregunta(pregunta);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha);
	}




}
