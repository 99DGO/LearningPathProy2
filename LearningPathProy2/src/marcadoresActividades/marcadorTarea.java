package marcadoresActividades;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;
import datosEstudiantes.DatosEstudianteAR;
import datosEstudiantes.DatosEstudianteExamen;
import datosEstudiantes.DatosEstudianteTarea;
import usuarios.Estudiante;

public class marcadorTarea 
{
	public static void marcarTareaExito(String idCamino, String idActividad, String idEstudiante, Boolean exito) throws Exception
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
				if (!actividadIterator.getType().equals(Actividad.TAREA))
				{
					throw new Exception ("La actividad pasada no es una tarea.");
				}
				
				actividad = actividadIterator;	
			}
		}
		 
		if (actividad==null)
		{
			throw new Exception ("No existe una actividad con ese id");
		}
		
		DatosEstudianteTarea datosEstudiante=null;
		try
		{
			datosEstudiante = (DatosEstudianteTarea) actividad.getDatoEstudianteIndFromIDEstudiante(idEstudiante);
		}
		catch(Exception e)
		{
			throw new Exception ("No se ha inscrito a este camino el estudiante");
		}
		
		if (datosEstudiante.getEstado().equals(DatosEstudianteExamen.PENDIENTE))
		{
			throw new Exception ("El estudiante no ha hecho un envio para esta tarea.");
		}
		
		
		
		if (exito)
		{
			datosEstudiante.setEstado(DatosEstudianteAR.EXITOSO);
		}
		else
		{
			datosEstudiante.setEstado(DatosEstudianteAR.NOEXITOSO);
		}
		
	}

}
