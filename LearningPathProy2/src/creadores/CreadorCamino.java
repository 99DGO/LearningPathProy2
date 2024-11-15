package creadores;

import java.util.HashMap;
import java.util.List;

import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;
import usuarios.Profesor;

public class CreadorCamino  
{
	public static void crearCaminoCero(String titulo, String descripcion, List<String> objetivos, double dificultad, 
			String IDprofesor) throws Exception 
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		Profesor profesor= LPS.getProfesorIndividual(IDprofesor);
		HashMap<String, CaminoAprendizaje> caminos = LPS.getCaminos();
		
		//Chequear que otro camino no tenga el mismo titulo
		for (String IDCamino: caminos.keySet())
		{
			CaminoAprendizaje caminoIterator=caminos.get(IDCamino);
			
			if (caminoIterator.getTitulo().equals(titulo))
			{
				throw new Exception ("Ya existe un camino con ese titulo"); 
			}
		}
		
		CaminoAprendizaje camino= new CaminoAprendizaje(titulo, descripcion, objetivos, dificultad, profesor.getID());
		profesor.addCamino(camino);
		LPS.addCamino(camino);
		
	}
	

	public static void clonarCamino(String IDcaminoOG, String titulo,  String IDprofesor) throws Exception 
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		Profesor profesor=LPS.getProfesorIndividual(IDprofesor);
		CaminoAprendizaje caminoOG= LPS.getCaminoIndividual(IDcaminoOG);
		
		HashMap<String, CaminoAprendizaje> caminos = LPS.getCaminos();
		
		//Chequear que otro camino no tenga el mismo titulo
		for (String IDCamino: caminos.keySet())
		{
			CaminoAprendizaje caminoIterator=caminos.get(IDCamino);
			
			if (caminoIterator.getTitulo().equals(titulo))
			{
				throw new Exception ("Ya existe un camino con ese titulo");
			}
		}
		
		CaminoAprendizaje camino= new CaminoAprendizaje(caminoOG, profesor.getID(), titulo);
		profesor.addCamino(camino);
		LPS.addCamino(camino);
		
	}

}
