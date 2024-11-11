package persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;
import datosEstudiantes.DatosEstudianteActividad;

public class CentralPersistencia 
{
	
	public static void guardarCaminosActividadesDatosEstudiante(boolean test)
	{
		String pathCaminosDirectorio;
		String pathCaminos;
		
		if (test)
		{
			pathCaminosDirectorio = "datosTests/Caminos/CaminosDirectorio.txt";
			pathCaminos="datosTests/Caminos/";
		}
		else
		{
			pathCaminosDirectorio = "datos/Caminos/CaminosDirectorio.txt";
			pathCaminos="datos/Caminos/";

		}

		File fileCaminosDirectorio = new File(pathCaminosDirectorio);

		//Limpio el directorio para que no queden dobles nombres
		try(PrintWriter pwCaminosDirectorio = new PrintWriter(fileCaminosDirectorio))
		{
		} 
		catch (FileNotFoundException e) 
		{
		  e.printStackTrace();
		}
		
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		HashMap<String, CaminoAprendizaje> caminosHash =LPS.getCaminos();
		
		//Recorro cada camino para guardarlo
		for (String caminoID: caminosHash.keySet())
		{
			CaminoAprendizaje camino = caminosHash.get(caminoID);
			CaminosPersistencia.GuardarCaminoSingular(camino, pathCaminos);
			
			//Limpio el directorio para que no queden dobles nombres
			String pathActividadesDirectorio = pathCaminos+caminoID+"/ActividadesDirectorio.txt";
			File fileActividadesDirectorio = new File(pathCaminosDirectorio);
			try(PrintWriter pwActividadesDirectorio = new PrintWriter(fileCaminosDirectorio))
			{
			} 
			catch (FileNotFoundException e) 
			{
			  e.printStackTrace();
			}
			
			//Recorro las actividades para guardarlas
			for (Actividad actividadIterator: camino.getActividades())
			{
				ActividadesPersistencia.guardarActividad(actividadIterator, caminoID, pathCaminos);
				
				//Recorro los datos de estudiantes para guardarlos
				for (DatosEstudianteActividad datosEstIterator: actividadIterator.getDatosEstudiantes().values())
				{
					DatosEstudiantesPersistencia.guardarDatosEstudiante(datosEstIterator, caminoID, actividadIterator.getId(), pathCaminos);
				}
			}

		}
	}

}
