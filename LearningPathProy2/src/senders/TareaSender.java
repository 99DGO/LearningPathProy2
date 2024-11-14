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

	public static void addMetodoEntregaTarea(String idCamino, String idActividad, String idEstudiante, String metodoEntrega) throws Exception
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

		DatosEstudianteTarea datosEstudiante = (DatosEstudianteTarea) actividad.getDatoEstudianteIndFromIDEstudiante(idEstudiante);
		Estudiante estudiante = LPS.getEstudianteIndividual(idEstudiante);
		
		if (!estudiante.isActividadActiva() || estudiante.getIdActividadActiva().equals(idActividad))
		{
			throw new Exception ("No se ha iniciado esta actividad");
		}
		else
		{
			datosEstudiante.setMetodoEntrega(metodoEntrega);
			datosEstudiante.setFechaFinal();
			datosEstudiante.setEstado(DatosEstudianteTarea.ENVIADO);
			
			estudiante.setActividadActiva(false);
			estudiante.setNombreCaminoActividadActiva("Ninguna");	
		}
	}


}
