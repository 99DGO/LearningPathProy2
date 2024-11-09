package editores;

import java.util.Date;

import caminosActividades.Actividad;
import caminosActividades.ActividadRecurso;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Examen;
import caminosActividades.Tarea;
import controllers.LearningPathSystem;

public class EditorTarea 
{
	public static void editInstrucciones(String instrucciones, String IDcamino, String IDactividad) 
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		Tarea actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= (Tarea) actividadIterator;
			}
		}
		
		actividad.setInstrucciones(instrucciones);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha);
	}
	
	public static void editAddActividadSigFracaso(String IDcamino, String IDactividad, String IDactividadSigFracaso)
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		Tarea actividad=null;
		Actividad actividadSigFracaso=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= (Tarea) actividadIterator;
			}
		}
		
		for (Actividad actividadIterator2: camino.getActividades())
		{
			if (actividadIterator2.getId().equals(IDactividadSigFracaso))
			{
				actividadSigFracaso= actividadIterator2;
			}
		}
		
		actividad.addActividadSigFracaso(actividadSigFracaso);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha);
	}
	
	
	public static void editDelActividadSigFracaso(String IDcamino, String IDactividad, int pos)
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		Tarea actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= (Tarea) actividadIterator;
			}
		}
		
		actividad.delActividadSigFracaso(pos);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha);
	}


}
