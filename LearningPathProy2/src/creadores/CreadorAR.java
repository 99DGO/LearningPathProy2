package creadores;

import java.util.List;

import caminosActividades.ActividadRecurso;
import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;
import usuarios.Profesor;

public class CreadorAR 
{
	public static void crearARCero(String IDcamino, String nombre, String descripcion,
			List<String> objetivos, double dificultad, int duracion, int[] fechaLim, boolean obligatoria, 
			String recurso, String instrucciones, String IDprofesor)
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		Profesor profesor=(Profesor) LPS.getUsuarioIndividal(IDprofesor);
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		ActividadRecurso AR= new ActividadRecurso(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, 
				obligatoria, recurso, instrucciones, profesor.getID(), camino);
		
	}
	
	public static void clonarActividadRecurso(ActividadRecurso AROG, Profesor profesor, CaminoAprendizaje camino)
	{
		
	}
	

}
