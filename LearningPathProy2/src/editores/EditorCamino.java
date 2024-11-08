package editores;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;

public class EditorCamino 
{

	public static void editTitulo(String idCamino, String titulo) throws Exception 
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		HashMap<String, CaminoAprendizaje> caminos=LPS.getCaminos();
		CaminoAprendizaje camino=LPS.getCaminoIndividual(idCamino);
		
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
		camino.setFechaModificacion(fecha);
 	
	}

	
	public static void editDescripcion(String idCamino, String descripcion) 
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino=LPS.getCaminoIndividual(idCamino);

		camino.setDescripcion(descripcion);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha);
	}

	public static void editObjetivos(String idCamino, List<String> objetivos) 
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino=LPS.getCaminoIndividual(idCamino);

		camino.setObjetivos(objetivos);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha);
	}


	public void editDificultad(String idCamino, double dificultad) 
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino=LPS.getCaminoIndividual(idCamino);

		camino.setDificultad(dificultad);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha);
	}

	public void editDuracion(String idCamino, int duracion) 
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino=LPS.getCaminoIndividual(idCamino);

		camino.setDuracion(duracion);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha);
	}
	

	
	public void editAddObjetivo(String idCamino, String objetivo)
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino=LPS.getCaminoIndividual(idCamino);
		
		camino.addObjetivo(objetivo);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha);
	}
	

	public void editDelActividad(String idCamino, int pos)
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino=LPS.getCaminoIndividual(idCamino);
		
		camino.delActividad(pos);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha);
	}
	
	public void editDelObjetivo(String idCamino, int pos)
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino=LPS.getCaminoIndividual(idCamino);
		
		camino.delObjetivo(pos);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha);
	}

}
