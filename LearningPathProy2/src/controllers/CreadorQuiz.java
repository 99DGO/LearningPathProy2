package controllers;

import java.util.List;

import caminosActividades.CaminoAprendizaje;
import caminosActividades.PreguntaQuiz;
import caminosActividades.Quiz;
import usuarios.Profesor;

public class CreadorQuiz {
	
	public static void crearActividadCero(CaminoAprendizaje camino, String nombre, String descripcion,
			List<String> objetivos, double dificultad, int duracion, int[] fechaLim, boolean obligatoria, 
			double calificacionMin, List<PreguntaQuiz> preguntas, Profesor profesor)
	{
		Quiz quiz= new Quiz(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria,
				calificacionMin, preguntas, profesor.getLogin(), camino);
		
		camino.addActividad(quiz);
	}

	public static void clonarActividad(Quiz quizOG, Profesor profesor, CaminoAprendizaje camino)
	{
		Quiz quiz = new Quiz(profesor.getLogin(), quizOG, camino);
		camino.addActividad(quiz);
	}
}
