package creadores;

import java.util.List;

import caminosActividades.Actividad;
import caminosActividades.ActividadRecurso;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Encuesta;
import controllers.LearningPathSystem;
import usuarios.Profesor;

public class CreadorAR 
{
	public static void crearARCero(String IDcamino, String nombre, String descripcion,
			List<String> objetivos, double dificultad, int duracion, int[] fechaLim, boolean obligatoria, 
			String recurso, String instrucciones, String IDprofesor, int pos) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		Profesor profesor=LPS.getProfesorIndividual(IDprofesor);
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		ActividadRecurso AR= new ActividadRecurso(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, 
				obligatoria, recurso, instrucciones, profesor.getID(), camino, pos);
		
	}
	
	public static void clonarAR(Actividad AROG, String IDprofesor, String IDcaminoNuevo, int pos) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		Profesor profesor= LPS.getProfesorIndividual(IDprofesor);
		CaminoAprendizaje caminoNuevo= LPS.getCaminoIndividual(IDcaminoNuevo);
		
		ActividadRecurso AR = new ActividadRecurso(profesor.getID(), (ActividadRecurso) AROG, caminoNuevo, pos);

	}

}
