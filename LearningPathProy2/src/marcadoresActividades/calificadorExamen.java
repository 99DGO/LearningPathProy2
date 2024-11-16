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
	public static void calificarExamen(String idCamino, String idActividad, String idEstudiante, double calificacion) throws Exception
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		CaminoAprendizaje camino = LPS.getCaminoIndividual(idCamino);
		
		if (camino==null)
		{
			throw new Exception ("No existe un camino con ese id");
		}
		
		Examen examen = null;

		for (Actividad actividadIterator : camino.getActividades())
		{
			if (actividadIterator.getId().equals(idActividad))
			{
				examen = (Examen) actividadIterator;
			}
		}
		
		if (examen==null)
		{
			throw new Exception ("No existe una actividad con ese id");
		}
		
		
		//Tira exception de que no se encontro el dato del estudiante
		DatosEstudianteExamen datosEstudiante = (DatosEstudianteExamen) examen.getDatoEstudianteIndFromIDEstudiante(idEstudiante);
		
		if (datosEstudiante.getEstado().equals(DatosEstudianteExamen.PENDIENTE))
		{
			throw new Exception ("El estudiante no ha hecho un envio para este examen.");
		}
		
		
		datosEstudiante.setCalificacion(calificacion);
		
		if (calificacion<examen.getCalificacionMin())
		{
			datosEstudiante.setEstado(DatosEstudianteAR.NOEXITOSO);
		}
		else
		{
			datosEstudiante.setEstado(DatosEstudianteAR.EXITOSO);
		}
		
	}

}
