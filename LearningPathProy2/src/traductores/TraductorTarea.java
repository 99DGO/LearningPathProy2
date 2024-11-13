package traductores;

import java.util.List;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Tarea;
import controllers.LearningPathSystem;

public class TraductorTarea 
{
	
	public static String retornarInstrucciones (String idCamino, String idActividad)
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		CaminoAprendizaje camino = LPS.getCaminoIndividual(idCamino);
		
		Tarea tarea = null;

		for (Actividad actividadIterator : camino.getActividades())
		{
			if (actividadIterator.getId().equals(idActividad))
			{
				tarea = (Tarea) actividadIterator;
			}
		}
		
		return tarea.getInstrucciones();
		
	}

}
