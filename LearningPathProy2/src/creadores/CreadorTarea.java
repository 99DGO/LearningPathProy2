package creadores;

import java.util.List;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Quiz;
import caminosActividades.Tarea;
import controllers.LearningPathSystem;
import usuarios.Profesor;

public class CreadorTarea 
{
	public static void crearTareaCero(String IDcamino, String nombre, String descripcion,
			List<String> objetivos, double dificultad, int duracion, int[] fechaLim, boolean obligatoria, 
			String instrucciones, String IDprofesor)
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		Profesor profesor= LPS.getProfesorIndividual(IDprofesor);
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		Tarea tarea= new Tarea(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria,
				instrucciones, profesor.getID(), camino);
		
	}

	public static void clonarTarea(Actividad tareaOG, String IDprofesor, String IDcaminoNuevo) 
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		Profesor profesor= LPS.getProfesorIndividual(IDprofesor);
		CaminoAprendizaje caminoNuevo= LPS.getCaminoIndividual(IDcaminoNuevo);
		
		Tarea tarea = new Tarea(profesor.getID(), (Tarea) tareaOG, caminoNuevo);

	}
}
