package traductores;

import caminosActividades.Actividad;
import caminosActividades.ActividadRecurso;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Encuesta;
import controllers.LearningPathSystem;

public class TraductorAR 
{
	/*
	 * Retorna un String[] que contiene en la primera posicion las instrucciones y en la segunda el recurso
	 */
	public static String[] retornarInstruccionesRecurso(String idCamino, String idActividad) throws Exception
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		CaminoAprendizaje camino = LPS.getCaminoIndividual(idCamino); 
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		ActividadRecurso AR = null;

		for (Actividad actividadIterator : camino.getActividades())
		{
			if (actividadIterator.getId().equals(idActividad))
			{
				if (!actividadIterator.getType().equals(Actividad.ACTIVIDADRECURSO))
				{
					throw new Exception ("La actividad no es una actividad de recurso");
				}
				
				AR = (ActividadRecurso) actividadIterator;
			}
		}
		
		String[] instruccionesRecurso = new String[] {AR.getInstrucciones(), AR.getRecurso()};
		
		return instruccionesRecurso;
		
	}

}
