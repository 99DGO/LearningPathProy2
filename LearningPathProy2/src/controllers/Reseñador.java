package controllers;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import usuarios.Estudiante;
import usuarios.Profesor;

public class Reseñador 
{
	public static void dejarResenia(String resenia, String IDcamino, String IDactividad)
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
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
		
		actividad.addResenia(resenia);
		
	}
	
	public static void dejarRatingActividad(float rating, String IDcamino, String IDactividad)
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
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
		
		actividad.addRating(rating);
		
	}
	
	
	public static void dejarRatingCamino(float rating, String IDcamino)
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
	
		camino.addRating(rating);
		
	}

}
