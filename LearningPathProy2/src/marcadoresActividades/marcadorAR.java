package marcadoresActividades;

import java.util.HashMap;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;
import datosEstudiantes.DatosEstudianteAR;
import datosEstudiantes.DatosEstudianteEncuesta;
import datosEstudiantes.DatosEstudianteTarea;
import envios.EnvioEncuesta;
import usuarios.Estudiante;

public class marcadorAR 
{
	public static void marcarARTerminado(String idCamino, String idActividad, String idEstudiante)
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		CaminoAprendizaje camino = LPS.getCaminoIndividual(idCamino);
		
		Actividad actividad = null;

		for (Actividad actividadIterator : camino.getActividades())
		{
			if (actividadIterator.getId().equals(idActividad))
			{
				actividad = actividadIterator;
			}
		}
		
		DatosEstudianteAR datosEstudiante = (DatosEstudianteAR) actividad.getDatoEstudianteIndividual(idEstudiante);
		datosEstudiante.setEstado(DatosEstudianteAR.EXITOSO);
		
		Estudiante estudiante = LPS.getEstudianteIndividual(idEstudiante);
		estudiante.setActividadActiva(false);
		estudiante.setNombreCaminoActividadActiva("Ninguna");
	}

}
