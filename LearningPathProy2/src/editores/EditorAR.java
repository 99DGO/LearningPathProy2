package editores;

import java.util.Date;
import caminosActividades.Actividad;
import caminosActividades.ActividadRecurso;
import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;

public class EditorAR extends EditorActividadGeneral
{
	public static void editRecurso(String recurso, String IDcamino, String IDactividad) throws Exception 
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		ActividadRecurso actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{				
				if (!actividadIterator.getType().equals(Actividad.ACTIVIDADRECURSO))
				{
					throw new Exception ("La actividad pasada no fue una actividad de recurso.");
				}
				
				actividad= (ActividadRecurso) actividadIterator;
			}
		}
		
		if (actividad==null)
		{
			throw new Exception ("No se encontro la actividad");
		}
		
		actividad.setRecurso(recurso);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
		
	}


	public static void editInstrucciones(String instrucciones, String IDcamino, String IDactividad) throws Exception 
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		ActividadRecurso actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				if (!actividadIterator.getType().equals(Actividad.ACTIVIDADRECURSO))
				{
					throw new Exception ("La actividad pasada no fue una actividad de recurso.");
				}
				
				actividad= (ActividadRecurso) actividadIterator;
			}
		}
		
		if (actividad==null)
		{
			throw new Exception ("No se encontro la actividad");
		}
		
		actividad.setInstrucciones(instrucciones);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
	}

}
