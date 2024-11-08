package creadores;

import java.util.List;

import caminosActividades.CaminoAprendizaje;
import caminosActividades.Examen;
import controllers.LearningPathSystem;
import usuarios.Profesor;

public class CreadorExamen
{

	public static void crearExamenCero(String IDcamino, String nombre, String descripcion,
			List<String> objetivos, double dificultad, int duracion, int[] fechaLim, boolean obligatoria, 
			double calificacionMin, List<String> preguntas, String IDprofesor)
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		Profesor profesor=LPS.getProfesorIndividual(IDprofesor);
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		Examen examen= new Examen(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria,
				calificacionMin, preguntas, profesor.getID(), camino);
		
	}
	
	
	public static void clonarExamen(Examen examenOG, Profesor profesor, CaminoAprendizaje camino)
	{
	}
	

}
