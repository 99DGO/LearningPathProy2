package marcadoresActividades;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Examen;
import controllers.LearningPathSystem;
import datosEstudiantes.DatosEstudianteAR;
import datosEstudiantes.DatosEstudianteExamen;
import datosEstudiantes.DatosEstudianteTarea;
import usuarios.Estudiante;

public class calificadorExamen 
{
	public static void calificarExamen(String idCamino, String idActividad, String idEstudiante, double calificacion)
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
		
		DatosEstudianteExamen datosEstudiante = (DatosEstudianteExamen) examen.getDatoEstudianteIndividual(idEstudiante);
		datosEstudiante.setCalificacion(calificacion);
		
		if (calificacion<examen.getCalificacionMin())
		{
			datosEstudiante.setEstado(DatosEstudianteAR.NOEXITOSO);
		}
		else
		{
			datosEstudiante.setEstado(DatosEstudianteAR.EXITOSO);
		}
		
		Estudiante estudiante = LPS.getEstudianteIndividual(idEstudiante);
		estudiante.setActividadActiva(false);
		estudiante.setNombreCaminoActividadActiva("Ninguna");
	}

}
