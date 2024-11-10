package creadores;

import java.util.List;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Encuesta;
import caminosActividades.Examen;
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
	
	public static void clonarEncuesta(Actividad encuestaOG, String IDprofesor, String IDcaminoNuevo) 
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		Profesor profesor= LPS.getProfesorIndividual(IDprofesor);
		CaminoAprendizaje caminoNuevo= LPS.getCaminoIndividual(IDcaminoNuevo);
		
		Encuesta encuesta = new Encuesta(profesor.getID(), (Encuesta) encuestaOG, caminoNuevo);

	}

}
