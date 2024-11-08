package creadores;

import java.util.List;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.PreguntaQuiz;
import caminosActividades.Quiz;
import controllers.LearningPathSystem;
import usuarios.Profesor;

public class CreadorQuiz {
	
	public static void clonarQuiz(String IDcaminoOG, String IDQuizOG, String IDprofesor, String IDcaminoNuevo) 
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		Profesor profesor=(Profesor) LPS.getUsuarioIndividal(IDprofesor);
		CaminoAprendizaje caminoOG= LPS.getCaminoIndividual(IDcaminoOG);
		CaminoAprendizaje caminoNuevo= LPS.getCaminoIndividual(IDcaminoNuevo);
		Quiz quizOG=null;
		
		for (Actividad actividad: caminoOG.getActividades())
		{
			if (actividad.getId().equals(IDcaminoOG))
			{
				quizOG= (Quiz) actividad;
			}
		}
		
		Quiz quiz = new Quiz(profesor.getID(), quizOG, caminoNuevo);

	}

	public static void crearQuizCero(String IDcamino, String nombre, String descripcion,
			List<String> objetivos, double dificultad, int duracion, int[] fechaLim, boolean obligatoria, 
			double calificacionMin, List<PreguntaQuiz> preguntas, String IDprofesor) 
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		Profesor profesor=(Profesor) LPS.getUsuarioIndividal(IDprofesor);
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		Quiz quiz= new Quiz(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria,
				calificacionMin, preguntas, profesor.getID(), camino);
		
	}
}
