package controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import datosEstudiantes.DatosEstudianteAR;
import datosEstudiantes.DatosEstudianteActividad;
import datosEstudiantes.DatosEstudianteEncuesta;
import datosEstudiantes.DatosEstudianteExamen;
import datosEstudiantes.DatosEstudianteQuiz;
import datosEstudiantes.DatosEstudianteTarea;
import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Quiz;
import usuarios.Estudiante;
import usuarios.Profesor;

public class Inscriptor {

	public static void inscribirseCamino(String IDcamino, String IDestudiante)
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		Estudiante estudiante=LPS.getEstudianteIndividual(IDestudiante);
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		Iterator<Actividad> it1= camino.getActividades().iterator();
		DatosEstudianteActividad datoEst;
		
		while (it1.hasNext())
		{
			Actividad act= it1.next();
			if (act.getType().equals(Actividad.ENCUESTA))
			{
				datoEst= new DatosEstudianteEncuesta(estudiante.getID());
			}
			else if (act.getType().equals(Actividad.QUIZ))
			{
				datoEst= new DatosEstudianteQuiz(estudiante.getID());
			}
			else if (act.getType().equals(Actividad.EXAMEN))
			{
				datoEst= new DatosEstudianteExamen(estudiante.getID());
			}
			else if (act.getType().equals(Actividad.TAREA))
			{
				datoEst= new DatosEstudianteTarea(estudiante.getID(), "Sin enviar");
			}
			else 
			{
				datoEst= new DatosEstudianteAR(estudiante.getID());
			}
			
			act.putDatoEstudiante(datoEst);
			
		}
		
		estudiante.addCamino(camino);
	}
	
	public static void iniciarActivad(String IDcamino,String IDactividad, String IDestudiante) throws
	Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		Estudiante estudiante=LPS.getEstudianteIndividual(IDestudiante);
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		Actividad actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				actividad= actividadIterator;
			}
		}
		
		if (!estudiante.isActividadActiva())
		{
			estudiante.setActividadActiva(true);
			DatosEstudianteActividad datoEst = actividad.getDatoEstudianteIndividual(estudiante.getLogin());
			datoEst.setFechaInicio();
		}
		else
		{
			throw new Exception ("No se puede iniciar una nueva actividad porque ya hay una iniciada");
		}
	
	}
	
	
}
