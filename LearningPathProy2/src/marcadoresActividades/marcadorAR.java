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
	public static void marcarARTerminado(String idCamino, String idActividad, String idEstudiante) throws Exception
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		CaminoAprendizaje camino = LPS.getCaminoIndividual(idCamino);
		
		if (camino==null)
		{
			throw new Exception ("No existe un camino con ese id");
		}
		
		Actividad actividad = null;

		for (Actividad actividadIterator : camino.getActividades())
		{
			if (actividadIterator.getId().equals(idActividad))
			{
				actividad = actividadIterator;
			}
		}
		
		if (actividad==null) 
		{
			throw new Exception ("No existe una actividad con ese id");
		}
		else if (!actividad.getType().equals(Actividad.ACTIVIDADRECURSO))
		{
			throw new Exception ("La actividad pasada no fue una actividad de recurso.");
		}
		
		//Tira exception de que no se encontro el dato del estudiante
		DatosEstudianteAR datosEstudiante = (DatosEstudianteAR) actividad.getDatoEstudianteIndFromIDEstudiante(idEstudiante);
		Estudiante estudiante = LPS.getEstudianteIndividual(idEstudiante);
		
		if (!estudiante.getIdActividadActiva().equals(idActividad))
		{
			throw new Exception ("No se ha iniciado esta actividad");
		}
		
		datosEstudiante.setEstado(DatosEstudianteAR.EXITOSO);
		datosEstudiante.setFechaFinal();
		
		estudiante.setActividadActiva(false);
		estudiante.setNombreCaminoActividadActiva("Ninguna; ; ");
	}

}
