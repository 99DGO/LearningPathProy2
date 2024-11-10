package editores;

import java.util.Date;

import caminosActividades.Actividad;
import caminosActividades.ActividadRecurso;
import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;

public class EditorAR 
{
	public static void editRecurso(String recurso, String IDcamino, String IDactividad) {
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		ActividadRecurso actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= (ActividadRecurso) actividadIterator;
			}
		}
		
		actividad.setRecurso(recurso);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
		
	}


	public static void editInstrucciones(String instrucciones, String IDcamino, String IDactividad) 
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		ActividadRecurso actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= (ActividadRecurso) actividadIterator;
			}
		}
		
		actividad.setInstrucciones(instrucciones);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
	}

}
