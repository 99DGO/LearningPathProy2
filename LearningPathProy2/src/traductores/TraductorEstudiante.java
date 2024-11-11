package traductores;

import java.util.HashMap;
import java.util.Iterator;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;
import datosEstudiantes.DatosEstudianteActividad;
import usuarios.Estudiante;
import usuarios.Profesor;

public class TraductorEstudiante 
{
	/**
	 * retorna un HashMap<String, String> que contiene como llave el nombre del camino y como valor el porcentaje
	 * logrado en ese camino
	 */
	public static HashMap<String, String> getAvancesCaminos(String IDestudiante) 
	{

		LearningPathSystem LPS= LearningPathSystem.getInstance();
		Estudiante estudiante=LPS.getEstudianteIndividual(IDestudiante);
		
		HashMap<String, String> avances = new HashMap<String, String>();
		
		
		for (CaminoAprendizaje camino: estudiante.getHistorialCaminos())
		{
			int actvCompletadas =0;
			
			for (Actividad actividad: camino.getActividades())
			{
				DatosEstudianteActividad datoEst = actividad.getDatoEstudianteIndividual(estudiante.getID());
				
				if (actividad.isObligatoria()  && (datoEst.getEstado().equals(DatosEstudianteActividad.EXITOSO)))
				{
					actvCompletadas+=1;
				}
				
			}
			
			int porcentaje = (actvCompletadas/camino.getNumActividadesObligatorias())*100;
			
			avances.put(camino.getTitulo(), String.valueOf(porcentaje)+"%");

		}
		
		return avances;
	}

	public static String getIDfromLogin(String login) throws Exception
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		HashMap<String, Estudiante> estudiantes = LPS.getEstudiantes();
		String IDtoReturn=null;
		
		for (String IDestudiante: estudiantes.keySet())
		{
			Estudiante estudianteIterator=estudiantes.get(IDestudiante);
			
			if (estudianteIterator.getLogin().equals(login))
			{
				IDtoReturn= IDestudiante;
			}
		}
		
		if (IDtoReturn==null)
		{
			throw new Exception("No se encontro el login");
		}
		else
		{
			return IDtoReturn;
		}
	}
}
