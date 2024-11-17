package editores;

import java.util.Date;

import caminosActividades.Actividad;
import caminosActividades.ActividadCalificable;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Encuesta;
import caminosActividades.Examen;
import controllers.LearningPathSystem;

public class EditorExamen extends EditorActividadGeneral
{
	public static void editDelPregunta(int pos, String IDcamino, String IDactividad) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		Examen actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				if (!actividadIterator.getType().equals(Actividad.EXAMEN))
				{
					throw new Exception ("La actividad pasada no fue un examen.");
				}
				
				actividad= (Examen) actividadIterator;
			}
		}
		
		if (actividad==null)
		{
			throw new Exception ("No se encontro la actividad");
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
		
		Examen actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				if (!actividadIterator.getType().equals(Actividad.EXAMEN))
				{
					throw new Exception ("La actividad pasada no fue un examen.");
				}
				
				actividad= (Examen) actividadIterator;
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
	
	public static void editAddActividadSigFracaso(String IDcamino, String IDactividad, String nombreActividadSigFracaso) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		Examen actividad=null;
		Actividad actividadSigFracaso=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				if (!actividadIterator.getType().equals(Actividad.EXAMEN))
				{
					throw new Exception ("La actividad pasada no fue un examen.");
				}
				
				actividad= (Examen) actividadIterator;
			}
		}
		
		if (actividad==null)
		{
			throw new Exception ("No se encontro la actividad");
		}
		
		actividad.addActividadSigFracaso(nombreActividadSigFracaso);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
	}
	
	
	public static void editDelActividadSigFracaso(String IDcamino, String IDactividad, int pos) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		Examen actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				if (!actividadIterator.getType().equals(Actividad.EXAMEN))
				{
					throw new Exception ("La actividad pasada no fue un examen.");
				}
				
				actividad= (Examen) actividadIterator;
			}
		}
		
		if (actividad==null)
		{
			throw new Exception ("No se encontro la actividad");
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
	
	public static void editCalificacionMin(String IDcamino, String IDactividad, double calificacionMin) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		Examen actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				if (!actividadIterator.getType().equals(Actividad.EXAMEN))
				{
					throw new Exception ("La actividad pasada no fue un examen.");
				}
				
				actividad= (Examen) actividadIterator;
			}
		}
		
		if (actividad==null)
		{
			throw new Exception ("No se encontro la actividad");
		}
		
		actividad.setCalificacionMin(calificacionMin);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
	}
	


}
