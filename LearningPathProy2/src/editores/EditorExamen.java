package editores;

import java.util.Date;

import caminosActividades.Actividad;
import caminosActividades.ActividadCalificable;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Encuesta;
import caminosActividades.Examen;
import controllers.LearningPathSystem;

public class EditorExamen 
{
	public static void editDelPregunta(int pos, String IDcamino, String IDactividad) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		Examen actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= (Examen) actividadIterator;
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
		Examen actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= (Examen) actividadIterator;
			}
		}
		
		actividad.addPregunta(pregunta);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha);
	}
	
	public static void editAddActividadSigFracaso(String IDcamino, String IDactividad, String IDactividadSigFracaso)
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		Examen actividad=null;
		Actividad actividadSigFracaso=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= (Examen) actividadIterator;
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
	
	
	public static void editDelActividadSigFracaso(String IDcamino, String IDactividad, int pos) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		Examen actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= (Examen) actividadIterator;
			}
		}
		
		if (pos>=actividad.getActividadesSigFracaso().size() || pos<=0)
		{
			throw new Exception ("El número de la actividad no existe");
		}
		else
		{
			actividad.delActividadSigFracaso(pos);
		}
				
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha);
	}
	
	public static void editCalificacionMin(String IDcamino, String IDactividad, double calificacionMin)
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		Examen actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= (Examen) actividadIterator;
			}
		}
		
		actividad.setCalificacionMin(calificacionMin);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha);
	}
	


}
