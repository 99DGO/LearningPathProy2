package creadores;

import java.util.List;

import caminosActividades.CaminoAprendizaje;
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
		Profesor profesor=(Profesor) LPS.getUsuarioIndividal(IDprofesor);
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		Tarea tarea= new Tarea(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria,
				instrucciones, profesor.getID(), camino);
		
	}

	public static void clonarTarea(Tarea tareaOG, Profesor profesor, CaminoAprendizaje camino)
	{
		
	}
}
