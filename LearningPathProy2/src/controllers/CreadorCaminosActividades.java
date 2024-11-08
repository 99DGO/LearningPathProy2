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

	
	
	public static void crearTareaCero(CaminoAprendizaje camino, String nombre, String descripcion,
			List<String> objetivos, double dificultad, int duracion, int[] fechaLim, boolean obligatoria, 
			String instrucciones, Profesor profesor)
	{
		Tarea tarea= new Tarea(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria,
				instrucciones, profesor.getLogin());
		
		camino.addActividad(tarea);
	}
	
	public static void crearExamenCero(CaminoAprendizaje camino, String nombre, String descripcion,
			List<String> objetivos, double dificultad, int duracion, int[] fechaLim, boolean obligatoria, 
			double calificacionMin, List<String> preguntas, Profesor profesor)
	{
		Examen examen= new Examen(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria,
				calificacionMin, preguntas, profesor.getLogin());
		
		camino.addActividad(examen);
	}
	
	public static void crearEncuestaCero(CaminoAprendizaje camino, String nombre, String descripcion,
			List<String> objetivos, double dificultad, int duracion, int[] fechaLim, boolean obligatoria, 
			List<String> preguntas, Profesor profesor)
	{
		Encuesta encuesta= new Encuesta(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria,
				preguntas, profesor.getLogin());
		
		camino.addActividad(encuesta);
	}
	
	public static void crearARCero(CaminoAprendizaje camino, String nombre, String descripcion,
			List<String> objetivos, double dificultad, int duracion, int[] fechaLim, boolean obligatoria, 
			String recurso, String instrucciones, Profesor profesor)
	{
		ActividadRecurso AR= new ActividadRecurso(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, 
				obligatoria, recurso, instrucciones, profesor.getLogin());
		
		camino.addActividad(AR);
	}
	
	
	public static void clonarTarea(Tarea tareaOG, Profesor profesor, CaminoAprendizaje camino)
	{
		Tarea tarea = new Tarea(profesor.getLogin(), tareaOG);
		camino.addActividad(tarea);
	}
	
	public static void clonarExamen(Examen examenOG, Profesor profesor, CaminoAprendizaje camino)
	{
		Examen examen = new Examen(profesor.getLogin(), examenOG);
		camino.addActividad(examen);
	}
	
	
	
	public static void clonarEncuesta(Encuesta encuestaOG, Profesor profesor, CaminoAprendizaje camino)
	{
		Encuesta encuesta = new Encuesta(profesor.getLogin(), encuestaOG);
		camino.addActividad(encuesta);
	}
	
	public static void clonarActividadRecurso(ActividadRecurso AROG, Profesor profesor, CaminoAprendizaje camino)
	{
		ActividadRecurso AR = new ActividadRecurso(profesor.getLogin(), AROG);
		camino.addActividad(AR);
	}
	
	public static boolean isProfCreador(CaminoAprendizaje camino, Profesor profesor)
	{
		return (profesor.getLogin().equals(camino.getCreadorLogin()));	
	}
	
	public static boolean isProfCreador(Actividad actividad, Profesor profesor)
	{
		return (profesor.getLogin().equals(actividad.getCreadorLogin()));	
	}
}