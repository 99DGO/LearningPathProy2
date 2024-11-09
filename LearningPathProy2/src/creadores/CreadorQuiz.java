package creadores;

import java.util.List;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.PreguntaQuiz;
import caminosActividades.Quiz;
import controllers.LearningPathSystem;
import usuarios.Profesor;

public class CreadorQuiz {
	
	public static void clonarQuiz(Actividad quizOG, String IDprofesor, String IDcaminoNuevo) 
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		Profesor profesor= LPS.getProfesorIndividual(IDprofesor);
		CaminoAprendizaje caminoNuevo= LPS.getCaminoIndividual(IDcaminoNuevo);
		
		Quiz quiz = new Quiz(profesor.getID(), (Quiz) quizOG, caminoNuevo);

	}

	public static void crearQuizCero(String IDcamino, String nombre, String descripcion,
			List<String> objetivos, double dificultad, int duracion, int[] fechaLim, boolean obligatoria, 
			double calificacionMin, List<PreguntaQuiz> preguntas, String IDprofesor) 
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		Profesor profesor= LPS.getProfesorIndividual(IDprofesor);
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		Quiz quiz= new Quiz(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria,
				calificacionMin, preguntas, profesor.getID(), camino);
		
	}
}
