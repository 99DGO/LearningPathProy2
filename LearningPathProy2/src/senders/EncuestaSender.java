package senders;

import java.util.HashMap;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;
import datosEstudiantes.DatosEstudianteEncuesta;
import datosEstudiantes.DatosEstudianteTarea;
import envios.EnvioEncuesta;
import envios.EnvioExamen;
import usuarios.Estudiante;

public class EncuestaSender 
{

	/*
	 * Las respuestas deben ser llave=pregunta, value=Respuesta del estudiante
	 */
	public static void sendEnvioEncuesta(String idCamino, String idActividad, String idEstudiante, HashMap<String, String> respuestas) throws Exception
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
		
		
		Estudiante estudiante = LPS.getEstudianteIndividual(idEstudiante);

		if (!estudiante.isActividadActiva() || estudiante.getIdActividadActiva().equals(idActividad))
		{
			throw new Exception ("No se ha iniciado esta actividad");
		}
		else
		{
			DatosEstudianteEncuesta datosEstudiante = (DatosEstudianteEncuesta) actividad.getDatoEstudianteIndFromIDEstudiante(idEstudiante);
			EnvioEncuesta envio = new EnvioEncuesta(respuestas);
			datosEstudiante.setEnvio(envio);
			datosEstudiante.setEstado(DatosEstudianteEncuesta.EXITOSO);
			datosEstudiante.setFechaFinal();
			
			estudiante.setActividadActiva(false);
			estudiante.setNombreCaminoActividadActiva("Ninguna");

		}
	}

}
