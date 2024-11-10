package persistencia;

import java.util.HashMap;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;

public class CentralPersistencia 
{
	
	public static void guardarCaminosActividadesDatosEstudiante()
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		HashMap<String, CaminoAprendizaje> caminosHash =LPS.getCaminos();
		
		for (String caminoID: caminosHash.keySet())
		{
			CaminoAprendizaje camino = caminosHash.get(caminoID);
			CaminosPersistencia.GuardarCaminoSingular(camino);
			
			for (Actividad actividadIterator: camino.getActividades())
			{
				ActividadesPersistencia.guardarActividad(actividadIterator, caminoID);
			}

		}
	}

}
