package traductores;

import java.util.List;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Examen;
import controllers.LearningPathSystem;

public class TraductorExamen 
{
	public static List<String> retornarPreguntas (String idCamino, String idActividad)
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		CaminoAprendizaje camino = LPS.getCaminoIndividual(idCamino);
		
		Examen examen = null;

		for (Actividad actividadIterator : camino.getActividades())
		{
			if (actividadIterator.getId().equals(idActividad))
			{
				examen = (Examen) actividadIterator;
			}
		}
		
		return examen.getPreguntasAbiertas();
		
	}

}
