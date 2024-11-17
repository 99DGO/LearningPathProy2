package editores;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;

public class EditorCamino 
{

	public static void editTitulo(String idCamino, String titulo) throws Exception 
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		HashMap<String, CaminoAprendizaje> caminos=LPS.getCaminos();
		CaminoAprendizaje camino=LPS.getCaminoIndividual(idCamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		for (CaminoAprendizaje caminoIterator: caminos.values())
		{
			if (caminoIterator.getTitulo().equals(titulo))
			{
				throw new Exception ("Ya existe un camino con ese titulo");
			}
		}
		
		
		camino.setTitulo(titulo);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
 	
	}

	
	public static void editDescripcion(String idCamino, String descripcion) throws Exception 
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino=LPS.getCaminoIndividual(idCamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}

		camino.setDescripcion(descripcion);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
	}



	public static void editDificultad(String idCamino, double dificultad) throws Exception 
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino=LPS.getCaminoIndividual(idCamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}

		camino.setDificultad(dificultad);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
	}

	
	public static void editAddObjetivo(String idCamino, String objetivo) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino=LPS.getCaminoIndividual(idCamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		camino.addObjetivo(objetivo);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
	}

	public static void editDelActividad(String idCamino, int pos) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino=LPS.getCaminoIndividual(idCamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		if (pos>camino.getActividades().size() || pos<=0)
		{
			throw new Exception ("El número de la actividad no existe");
		}
		else
		{
			camino.delActividad(pos-1);
		}
				
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
	}
	
	public static void editDelObjetivo(String idCamino, int pos) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino=LPS.getCaminoIndividual(idCamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		if (pos>camino.getObjetivos().size() || pos<=0)
		{
			throw new Exception ("El número del objetivo no existe");
		}
		else
		{
			camino.delObjetivo(pos-1);
		}
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
	}
	
	public static void cambiarPosActividad(String idCamino, String idActividad, int newPos) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino=LPS.getCaminoIndividual(idCamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		camino.cambiarPosActividad(idActividad, newPos-1);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
	}

}
