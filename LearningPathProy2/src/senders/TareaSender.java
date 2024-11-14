package senders;

import java.util.Date;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;
import datosEstudiantes.DatosEstudianteTarea;
import envios.EnvioEncuesta;
import usuarios.Estudiante;

public class TareaSender 
{

	public static void addMetodoEntregaTarea(String idCamino, String idActividad, String idEstudiante, String metodoEntrega)
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
		datosEstudiante.setMetodoEntrega(metodoEntrega);
		datosEstudiante.setFechaFinal();
		datosEstudiante.setEstado(DatosEstudianteTarea.ENVIADO);
		
		Estudiante estudiante = LPS.getEstudianteIndividual(idEstudiante);
		estudiante.setActividadActiva(false);
		estudiante.setNombreCaminoActividadActiva("Ninguna");
	}


}
