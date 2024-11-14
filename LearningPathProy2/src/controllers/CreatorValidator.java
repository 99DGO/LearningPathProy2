package controllers;

import java.util.HashMap;

import caminosActividades.CaminoAprendizaje;

public class CreatorValidator 
{
	public static void profCreadorChecker(String idCamino, String idProfesor) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino=LPS.getCaminoIndividual(idCamino);
		
		if (!camino.getCreadorID().equals(idProfesor))
		{
			throw new Exception("El profesor no es creador");
		}
		
	}

}
