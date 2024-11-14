package senders;

import java.util.HashMap;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;
import datosEstudiantes.DatosEstudianteExamen;
import datosEstudiantes.DatosEstudianteTarea;
import envios.Envio;
import envios.EnvioEncuesta;
import envios.EnvioExamen;
import usuarios.Estudiante;

public class ExamenSender
{
	/*
	 * Las respuestas deben ser llave=pregunta, value=Respuesta del estudiante
	 */
	public static void sendEnvioExamen(String idCamino, String idActividad, String idEstudiante, HashMap<String, String> respuestas)
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
		
		DatosEstudianteExamen datosEstudiante = (DatosEstudianteExamen) actividad.getDatoEstudianteIndividual(idEstudiante);
		EnvioExamen envio = new EnvioExamen(respuestas);
		datosEstudiante.setEnvio(envio);
		datosEstudiante.setEstado(DatosEstudianteTarea.ENVIADO);
		
		Estudiante estudiante = LPS.getEstudianteIndividual(idEstudiante);
		estudiante.setActividadActiva(false);
		estudiante.setNombreCaminoActividadActiva("Ninguna");
	}


}
