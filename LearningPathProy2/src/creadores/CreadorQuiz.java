package creadores;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.PreguntaQuiz;
import caminosActividades.Quiz;
import controllers.LearningPathSystem;
import java.util.List;
import usuarios.Profesor;

public class CreadorQuiz {
	
	public static void clonarQuiz(Actividad quizOG, String IDprofesor, String IDcaminoNuevo, int pos) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		Profesor profesor= LPS.getProfesorIndividual(IDprofesor);
		CaminoAprendizaje caminoNuevo= LPS.getCaminoIndividual(IDcaminoNuevo);
		
		Quiz quiz = new Quiz(profesor.getID(), (Quiz) quizOG, caminoNuevo, ((Quiz) quizOG).isVerdaderoFalso(), pos);

	}

	public static void crearQuizCero(String IDcamino, String nombre, String descripcion,
			List<String> objetivos, double dificultad, int duracion, int[] fechaLim, boolean obligatoria, 
			double calificacionMin, List<PreguntaQuiz> preguntas, String IDprofesor, boolean verdaderoFalso, int pos) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		Profesor profesor= LPS.getProfesorIndividual(IDprofesor);
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		Quiz quiz= new Quiz(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria,
				calificacionMin, preguntas, profesor.getID(), camino, verdaderoFalso, pos);
		
	}
}
