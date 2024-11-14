package creadores;

import java.util.HashMap;
import java.util.List;

import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;
import usuarios.Profesor;

public class CreadorCamino 
{
	public static void crearCaminoCero(String titulo, String descripcion, List<String> objetivos, double dificultad, 
			int duracion, String IDprofesor) throws Exception 
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		Profesor profesor= LPS.getProfesorIndividual(IDprofesor);
		
		if (!(LPS.getCaminoIndividual(titulo)==null))
		{
			throw new Exception ("Ya existe un camino con ese titulo");
		}
		else
		{
			CaminoAprendizaje camino= new CaminoAprendizaje(titulo, descripcion, objetivos, dificultad, duracion,
					 profesor.getID());
			profesor.addCamino(camino);
			LPS.addCamino(camino);
		}
		
	}
	

	public static void clonarCamino(String IDcaminoOG, String tituloCamino,  String IDprofesor) throws Exception 
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		Profesor profesor=LPS.getProfesorIndividual(IDprofesor);
		CaminoAprendizaje caminoOG= LPS.getCaminoIndividual(IDcaminoOG);
		
		String caminoForCheck = null;
		HashMap<String, CaminoAprendizaje> allCaminos = LPS.getCaminos();
		
		for (String caminoTitulo : allCaminos.keySet())
        {
            if (caminoTitulo.equals(tituloCamino))
            {
                caminoForCheck = caminoTitulo;
            }
        }
		
		if (caminoForCheck != null)
		{
			throw new Exception ("Ya existe un camino con ese titulo");
		}
		else
		{
			CaminoAprendizaje camino= new CaminoAprendizaje(caminoOG, profesor.getID(), tituloCamino);
			profesor.addCamino(camino);
			LPS.addCamino(camino);
		}
	}

}
