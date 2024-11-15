package creadores;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Encuesta;
import controllers.LearningPathSystem;
import usuarios.Profesor;

public class ChooserClonadorActividad 
{
	public static void ClonarActividad(String IDcaminoOG, String IDActividadOG, String IDprofesor, String IDcaminoNuevo, int pos)
			throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje caminoOG= LPS.getCaminoIndividual(IDcaminoOG);
		Actividad actividadOG=null;
		
		for (Actividad actividadIterator: caminoOG.getActividades())
		{
			if (actividadIterator.getId().equals(IDActividadOG))
			{
				actividadOG=actividadIterator;
				
				if (actividadIterator.getType().equals(Actividad.QUIZ))
				{
					CreadorQuiz.clonarQuiz(actividadOG, IDprofesor, IDcaminoNuevo, pos);
				}
				
				else if (actividadIterator.getType().equals(Actividad.ENCUESTA))
				{
					CreadorEncuesta.clonarEncuesta(actividadOG, IDprofesor, IDcaminoNuevo, pos);
				}
				
				else if (actividadIterator.getType().equals(Actividad.EXAMEN))
				{
					CreadorExamen.clonarExamen(actividadOG, IDprofesor, IDcaminoNuevo, pos);
				}
				
				else if (actividadIterator.getType().equals(Actividad.TAREA))
				{
					CreadorTarea.clonarTarea(actividadOG, IDprofesor, IDcaminoNuevo, pos);
				}
				else
				{
					CreadorAR.clonarAR(actividadOG, IDprofesor, IDcaminoNuevo, pos);
				}
			}
		}
		
	}
}
