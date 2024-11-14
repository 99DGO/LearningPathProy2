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
	public static void sendEnvioEncuesta(String idCamino, String idActividad, String idEstudiante, HashMap<String, String> respuestas)
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
		
		DatosEstudianteEncuesta datosEstudiante = (DatosEstudianteEncuesta) actividad.getDatoEstudianteIndividual(idEstudiante);
		EnvioEncuesta envio = new EnvioEncuesta(respuestas);
		datosEstudiante.setEnvio(envio);
		datosEstudiante.setEstado(DatosEstudianteEncuesta.EXITOSO);
		
		Estudiante estudiante = LPS.getEstudianteIndividual(idEstudiante);
		estudiante.setActividadActiva(false);
		estudiante.setNombreCaminoActividadActiva("Ninguna");
	}

}
