package traductores;

import java.util.HashMap;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;

public class TraductorActividad {
	
	public static String getIDfromNombre(String idCamino, String nombre) throws Exception
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		CaminoAprendizaje camino =LPS.getCaminoIndividual(idCamino);
		String IDtoReturn=null;

		for (Actividad actividad: camino.getActividades())
		{
			
			if (actividad.getNombre().equals(nombre))
			{
				IDtoReturn= actividad.getId();
			}
		}
		
		if (IDtoReturn==null)
		{
			throw new Exception("No se encontro el camino");
		}
		else
		{
			return IDtoReturn;
		}
	}

}
