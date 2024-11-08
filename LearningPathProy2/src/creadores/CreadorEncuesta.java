package creadores;

import java.util.List;

import caminosActividades.CaminoAprendizaje;
import caminosActividades.Encuesta;
import controllers.LearningPathSystem;
import usuarios.Profesor;

public class CreadorEncuesta 
{
	public static void crearEncuestaCero(String IDcamino, String nombre, String descripcion,
			List<String> objetivos, double dificultad, int duracion, int[] fechaLim, boolean obligatoria, 
			List<String> preguntas, String IDprofesor)
	{

		LearningPathSystem LPS= LearningPathSystem.getInstance();
		Profesor profesor=LPS.getProfesorIndividual(IDprofesor);
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		Encuesta encuesta= new Encuesta(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria,
				preguntas, profesor.getID(), camino);
		
	}
	
	public static void clonarEncuesta(Encuesta encuestaOG, Profesor profesor, CaminoAprendizaje camino)
	{
		
	}

}
