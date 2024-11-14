package marcadoresActividades;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;
import datosEstudiantes.DatosEstudianteAR;
import datosEstudiantes.DatosEstudianteTarea;
import usuarios.Estudiante;

public class marcadorTarea 
{
	public static void marcarTareaExito(String idCamino, String idActividad, String idEstudiante, Boolean exito)
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
		
		DatosEstudianteTarea datosEstudiante = (DatosEstudianteTarea) actividad.getDatoEstudianteIndividual(idEstudiante);
		if (exito)
		{
			datosEstudiante.setEstado(DatosEstudianteAR.EXITOSO);
		}
		else
		{
			datosEstudiante.setEstado(DatosEstudianteAR.NOEXITOSO);
		}
		
		Estudiante estudiante = LPS.getEstudianteIndividual(idEstudiante);
		estudiante.setActividadActiva(false);
		estudiante.setNombreCaminoActividadActiva("Ninguna");
	}

}
