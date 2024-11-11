package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;
import datosEstudiantes.DatosEstudianteActividad;
import usuarios.Estudiante;

public class CentralPersistencia 
{
	
	public static void guardarCaminosActividadesDatosEstudiante(boolean test) throws Exception
	{
		String pathCaminosDirectorio;
		String pathCaminos;
		
		if (test)
		{
			pathCaminosDirectorio = "LearningPathProy2/datosTests/Caminos/CaminosDirectorio.txt";
			pathCaminos="LearningPathProy2/datosTests/Caminos/";
		}
		else
		{
			pathCaminosDirectorio = "LearningPathProy2/datos/Caminos/CaminosDirectorio.txt";
			pathCaminos="LearningPathProy2/datos/Caminos/";

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

		}
		
		//Recorro cada camino para guardarlo
		//Toca aparte para que las actividades no pongan problemas por el directorio
		for (String caminoID: caminosHash.keySet())
		{
			CaminoAprendizaje camino = caminosHash.get(caminoID);
			
			//Recorro las actividades para guardarlas
			for (Actividad actividadIterator: camino.getActividades())
			{
				ActividadesPersistencia.guardarActividad(actividadIterator, caminoID, pathCaminos);
			}
			
			//Recorro las actividades para guardarlas
			for (Actividad actividadIterator: camino.getActividades())
			{
				//Recorro los datos de estudiantes para guardarlos
				for (DatosEstudianteActividad datosEstIterator: actividadIterator.getDatosEstudiantes().values())
				{
					DatosEstudiantesPersistencia.guardarDatosEstudiante(datosEstIterator, caminoID, actividadIterator.getId(), pathCaminos);
				}
			}

		}
	}
	
	public static void cargarCaminosActividadesDatosEstudiante(boolean test) throws Exception
	{

    	LearningPathSystem LPS = LearningPathSystem.getInstance();
    	
		String pathCaminosDirectorio;
		String pathCaminos;
		
		if (test)
		{
			pathCaminosDirectorio = "LearningPathProy2/datosTests/Caminos/CaminosDirectorio.txt";
			pathCaminos="LearningPathProy2/datosTests/Caminos/";
		}
		else
		{
			pathCaminosDirectorio = "LearningPathProy2/datos/Caminos/CaminosDirectorio.txt";
			pathCaminos="LearningPathProy2/datos/Caminos/";
		}
		
		File fileCaminosDirectorio = new File(pathCaminosDirectorio);
		

		//Leo el archivo
		try (BufferedReader br = new BufferedReader(new FileReader(fileCaminosDirectorio))) 
		{		        
			
		    String line;
		    //Recorro el directorio
		    while ((line = br.readLine()) != null) 
		    {
		       // Saco el objeto JSON del camino por cada archivo que hay
		    	String content = new String(Files.readAllBytes(Paths.get(pathCaminos+line+"/"+line+".json")));
		    	JSONObject jcamino = new JSONObject(content);
		    	
		    	CaminoAprendizaje camino = CaminosPersistencia.cargarCamino(jcamino, pathCaminos+line+"/");
		    	//Añado al LPS
		    	LPS.addCamino(camino);
		    }
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void guardarEstudiantes(boolean test)
	{
    	LearningPathSystem LPS = LearningPathSystem.getInstance();
		HashMap<String, Estudiante> estudiantesHash=LPS.getEstudiantes();
		
		String pathEstudiantesDirectorio;
		String pathEstudiantes;
		
		if (test)
		{
			pathEstudiantesDirectorio = "LearningPathProy2/datosTests/estudiantes/estudiantesDirectorio.txt";
			pathEstudiantes="LearningPathProy2/datosTests/estudiantes/";
		}
		else
		{
			pathEstudiantesDirectorio = "LearningPathProy2/datos/estudiantes/estudiantesDirectorio.txt";
			pathEstudiantes="LearningPathProy2/datos/estudiantes/";

		}

		File fileEstudidantesDirectorio = new File(pathEstudiantesDirectorio);

		//Limpio el directorio para que no queden dobles nombres
		try(PrintWriter pwCaminosDirectorio = new PrintWriter(fileEstudidantesDirectorio))
		{
		} 
		catch (FileNotFoundException e) 
		{
		  e.printStackTrace();
		}
		
		
		for (Estudiante estudiante : estudiantesHash.values())
		{
			EstudiantesPersistencia.guardarEstudianteSingular(estudiante, pathEstudiantes);
			
		}
	}

}
