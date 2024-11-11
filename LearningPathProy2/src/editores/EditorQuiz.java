package editores;

import java.util.Date;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.PreguntaQuiz;
import caminosActividades.Quiz;
import controllers.LearningPathSystem;

public class EditorQuiz 
{
	public static void editDelPregunta(int pos, String IDcamino, String IDactividad) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		Quiz actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= (Quiz) actividadIterator;
			}
		}
		
		if (pos>=actividad.getPreguntas().size() || pos<=0)
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
		camino.setFechaModificacion(fecha.toString());
	}
	
	public static void editAddPregunta(PreguntaQuiz pregunta, String IDcamino, String IDactividad) 
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		Quiz actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= (Quiz) actividadIterator;
			}
		}
		
		actividad.addPregunta(pregunta);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		String fecha = new Date().toString();
		camino.setFechaModificacion(fecha);
	}

	
	public static void editAddActividadSigFracaso(String IDcamino, String IDactividad, String IDactividadSigFracaso)
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		Quiz actividad=null;
		Actividad actividadSigFracaso=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= (Quiz) actividadIterator;
			}
		}
		
		actividad.addActividadSigFracaso(IDactividadSigFracaso);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
	}
	
	
	public static void editDelActividadSigFracaso(String IDcamino, String IDactividad, int pos) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		Quiz actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= (Quiz) actividadIterator;
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
		camino.setFechaModificacion(fecha.toString());
	}
	
	public static void editCalificacionMin(String IDcamino, String IDactividad, double calificacionMin)
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		Quiz actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= (Quiz) actividadIterator;
			}
		}
		
		actividad.setCalificacionMin(calificacionMin);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
	}
	

}
