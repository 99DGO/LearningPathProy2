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
			List<String> preguntas, String IDprofesor, int pos) throws Exception
	{

		LearningPathSystem LPS= LearningPathSystem.getInstance();
		Profesor profesor=LPS.getProfesorIndividual(IDprofesor);
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		Encuesta encuesta= new Encuesta(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria,
				preguntas, profesor.getID(), camino, pos);
		
	}
	
	public static void clonarEncuesta(Actividad encuestaOG, String IDprofesor, String IDcaminoNuevo, int pos) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		Profesor profesor= LPS.getProfesorIndividual(IDprofesor);
		CaminoAprendizaje caminoNuevo= LPS.getCaminoIndividual(IDcaminoNuevo);
		
		if (caminoNuevo==null)
		{
			throw new Exception ("No se encontro el camino al cual se quiere añadir la actividad");
		}
		
		Encuesta encuesta = new Encuesta(profesor.getID(), (Encuesta) encuestaOG, caminoNuevo, pos);

	}

}
