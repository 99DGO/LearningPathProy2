package controllers;

import java.util.List;

import caminosActividades.Actividad;
import caminosActividades.ActividadRecurso;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Encuesta;
import caminosActividades.Examen;
import caminosActividades.PreguntaQuiz;
import caminosActividades.Quiz;
import caminosActividades.Tarea;
import usuarios.Profesor;

public class CreadorCaminosActividades {

	
	
	
	
	
	
	
	
	public static boolean isProfCreador(CaminoAprendizaje camino, Profesor profesor)
	{
		return (profesor.getLogin().equals(camino.getCreadorLogin()));	
	}
	
	public static boolean isProfCreador(Actividad actividad, Profesor profesor)
	{
		return (profesor.getLogin().equals(actividad.getCreadorLogin()));	
	}
}