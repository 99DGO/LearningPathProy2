package senders;

import java.util.HashMap;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;
import datosEstudiantes.DatosEstudianteEncuesta;
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
	public static void sendEnvioExamen(String idCamino, String idActividad, String idEstudiante, HashMap<String, String> respuestas) throws Exception
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
		
		if (!actividad.getType().equals(Actividad.EXAMEN)||actividad==null)
		{
			throw new Exception ("El id pasado no es el de un examen");
		}
		
		DatosEstudianteExamen datosEstudiante =null;
		try
		{
			datosEstudiante = (DatosEstudianteExamen) actividad.getDatoEstudianteIndFromIDEstudiante(idEstudiante);
		}
		catch (Exception e)
		{
			throw new Exception("No se ha inscrito a este camino");
		}
		
		Estudiante estudiante = LPS.getEstudianteIndividual(idEstudiante);

		if (!estudiante.isActividadActiva() || !estudiante.getIdActividadActiva().equals(idActividad))
		{
			throw new Exception ("No se ha iniciado esta actividad");
		}
		else
		{
			EnvioExamen envio = new EnvioExamen(respuestas);
			datosEstudiante.setEnvio(envio);
			datosEstudiante.setEstado(DatosEstudianteTarea.ENVIADO);
			datosEstudiante.setFechaFinal();
			
			estudiante.setActividadActiva(false);
			estudiante.setNombreCaminoActividadActiva("Ninguna; ; ");

		}
	}


}
