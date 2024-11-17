package editores;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;
import datosEstudiantes.DatosEstudianteActividad;

public abstract class EditorActividadGeneral 
{
	

	public static void editNombre(String IDcamino, String IDactividad, String nombre) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		
		Actividad actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= actividadIterator;
			}
		}
		
		if (actividad==null)
		{
			throw new Exception ("No se encontro la actividad");
		}
		
		actividad.setNombre(nombre);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
	}


	public static void editDescripcion(String IDcamino, String IDactividad, String descripcion) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		Actividad actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= actividadIterator;
			}
		}
		
		if (actividad==null)
		{
			throw new Exception ("No se encontro la actividad");
		}
		
		actividad.setDescripcion(descripcion);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
	}

	
	public static void editDificultad(String IDcamino, String IDactividad, double dificultad) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		
		Actividad actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= actividadIterator;
			}
		}
		
		if (actividad==null)
		{
			throw new Exception ("No se encontro la actividad");
		}
		
		actividad.setDificultad(dificultad);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
	}


	public static void editDuracion(String IDcamino, String IDactividad, int duracion) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		Actividad actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= actividadIterator;
			}
		}
		
		if (actividad==null)
		{
			throw new Exception ("No se encontro la actividad");
		}
		
		actividad.setDuracion(duracion);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
	
	}

	public static void editFechaLim(String IDcamino, String IDactividad, int[] fechaLim) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		Actividad actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= actividadIterator;
			}
		}
		
		if (actividad==null)
		{
			throw new Exception ("No se encontro la actividad");
		}
		
		actividad.setFechaLim(fechaLim);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
	
	}

	

	public static void editObligatoria(String IDcamino, String IDactividad, boolean oblig) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		Actividad actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= actividadIterator;
			}
		}
		
		if (actividad==null)
		{
			throw new Exception ("No se encontro la actividad");
		}
		
		actividad.setObligatoria(oblig);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
	
	}
	
	public static void editAddObjetivo(String IDcamino, String IDactividad, String objetivo) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance(); 
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		Actividad actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= actividadIterator;
			}
		}
		
		if (actividad==null)
		{
			throw new Exception ("No se encontro la actividad");
		}
		
		actividad.addObjetivo(objetivo);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
	}
	
	
	public static void editDelObjetivo(String IDcamino, String IDactividad, int pos) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		Actividad actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= actividadIterator;
			}
		}
		
		if (actividad==null)
		{
			throw new Exception ("No se encontro la actividad");
		}
		
		if (pos>actividad.getObjetivos().size() || pos<=0)
		{
			throw new Exception ("El número del objetivo no existe");
		}
		else
		{
			actividad.delObjetivo(pos-1);
		}
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
	}
	
	
	
	public static void editAddActividadSiguienteExitosa(String IDcamino, String IDactividad, String nombreActividadSigExitosa) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		Actividad actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= actividadIterator;
			}
		}
		
		if (actividad==null)
		{
			throw new Exception ("No se encontro la actividad");
		}
		
		actividad.addActividadSiguienteExitosa(nombreActividadSigExitosa);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
	}
	
	public static void editAddActividadPrereq(String IDcamino, String IDactividad, String nombreActividadPrereq) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		Actividad actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= actividadIterator;
			}
		}
		
		if (actividad==null)
		{
			throw new Exception ("No se encontro la actividad");
		}
		
		actividad.addActividadPrereq(nombreActividadPrereq);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
	}
	
	
	
	public static void editDelActividadPrereq(String IDcamino, String IDactividad, int pos) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		Actividad actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= actividadIterator;
			}
		}
		
		if (actividad==null)
		{
			throw new Exception ("No se encontro la actividad");
		}
		
		if (pos>actividad.getActividadesPrereqs().size() || pos<=0)
		{
			throw new Exception ("El número de la actividad no existe");
		}
		else
		{
			actividad.delActividadPrereq(pos-1);
		}
		
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
	}
	
	
	public static void editDelActividadSiguienteExitosa(String IDcamino, String IDactividad, int pos) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		Actividad actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= actividadIterator;
			}
		}
		
		if (actividad==null)
		{
			throw new Exception ("No se encontro la actividad");
		}
		
		if (pos>actividad.getActividadesSigExitoso().size() || pos<=0)
		{
			throw new Exception ("El número de la actividad no existe");
		}
		else
		{
			actividad.delActividadSiguienteExitosa(pos-1);
		}
		
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
	}
	
	

}
