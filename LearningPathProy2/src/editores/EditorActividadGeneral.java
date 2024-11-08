package editores;

import java.util.HashMap;
import java.util.List;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;
import datosEstudiantes.DatosEstudianteActividad;

public class EditorActividadGeneral 
{
	

	public static void editNombre(String IDcamino, String IDactividad, String nombre)
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		Actividad actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= actividadIterator;
			}
		}
		
		actividad.setNombre(nombre);
		
	}


	public static void editDescripcion(String IDcamino, String IDactividad, String descripcion)
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		Actividad actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= actividadIterator;
			}
		}
		
		actividad.setDescripcion(descripcion);
	}

	
	public static void editDificultad(String IDcamino, String IDactividad, double dificultad)
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		Actividad actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= actividadIterator;
			}
		}
		
		actividad.setDificultad(dificultad);
	}


	public static void editDuracion(String IDcamino, String IDactividad, int duracion)
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		Actividad actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= actividadIterator;
			}
		}
		
		actividad.setDuracion(duracion);
	
	}

	public static void editFechaLim(String IDcamino, String IDactividad, int[] fechaLim)
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		Actividad actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= actividadIterator;
			}
		}
		
		actividad.setFechaLim(fechaLim);
	
	}

	

	public static void editObligatoria(String IDcamino, String IDactividad, boolean oblig)
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		Actividad actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= actividadIterator;
			}
		}
		
		actividad.setObligatoria(oblig);
	
	}
	
	public static void editAddObjetivo(String IDcamino, String IDactividad, String objetivo)
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		Actividad actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= actividadIterator;
			}
		}
		
		actividad.addObjetivo(objetivo);
	}
	
	
	public void editDelObjetivo(String IDcamino, String IDactividad, int pos)
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		Actividad actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= actividadIterator;
			}
		}
		
		actividad.delObjetivo(pos);
	}
	
	
	
	public static void editAddActividadSiguienteExitosa(String IDcamino, String IDactividad, String IDactividadSiguiente)
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		Actividad actividad=null;
		Actividad actividadSiguiente=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= actividadIterator;
			}
		}
		
		for (Actividad actividadIterator2: camino.getActividades())
		{
			if (actividadIterator2.getId().equals(IDactividadSiguiente))
			{
				actividadSiguiente= actividadIterator2;
			}
		}
		
		actividad.addActividadSiguienteExitosa(actividadSiguiente);
	}
	
	public static void editAddActividadPrereq(String IDcamino, String IDactividad, String IDactividadPrereq)
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		Actividad actividad=null;
		Actividad actividadPrereq=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= actividadIterator;
			}
		}
		
		for (Actividad actividadIterator2: camino.getActividades())
		{
			if (actividadIterator2.getId().equals(IDactividadPrereq))
			{
				actividadPrereq= actividadIterator2;
			}
		}
		
		actividad.addActividadPrereq(actividadPrereq);
	}
	
	
	
	public static void editDelActividadPrereq(String IDcamino, String IDactividad, int pos)
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		Actividad actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= actividadIterator;
			}
		}
		
		actividad.delActividadPrereq(pos);
	}
	
	
	public static void editDelActividadSiguienteExitosa(String IDcamino, String IDactividad, int pos)
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		Actividad actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= actividadIterator;
			}
		}
		
		actividad.delActividadSiguienteExitosa(pos);
	}
	
	

}
