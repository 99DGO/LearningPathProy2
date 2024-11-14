package persistencia;

import static org.junit.Assert.fail;

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
import usuarios.Profesor;

public class CentralPersistencia 
{

	
	public static void cargarTodo(boolean test) throws Exception
	{
		cargarCaminosActividadesDatosEstudiante(test);
		cargarEstudiantes(test);
		cargarProfesores(test);
	}
	
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
		metodosAuxPersistencia.cleanDatos(test);

		LearningPathSystem LPS = LearningPathSystem.getInstance();
		HashMap<String, CaminoAprendizaje> caminosHash =LPS.getCaminos();
		
		//Recorro cada camino para guardarlo
		for (String caminoID: caminosHash.keySet())
		{
			CaminoAprendizaje camino = caminosHash.get(caminoID);
			CaminosPersistencia.GuardarCaminoSingular(camino, pathCaminos, fileCaminosDirectorio);

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
		BufferedReader br = new BufferedReader(new FileReader(fileCaminosDirectorio)); 		        
			
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

	public static void cargarEstudiantes(boolean test) throws Exception
	{

    	LearningPathSystem LPS = LearningPathSystem.getInstance(); 
    	
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
		

		File fileEstudiantesDirectorio = new File(pathEstudiantesDirectorio);
		
		if (!fileEstudiantesDirectorio.exists())
		{
			throw new Exception ("No se encontro el directorio");
		}

		//Leo el archivo
		BufferedReader br = new BufferedReader(new FileReader(fileEstudiantesDirectorio)); 		        
			
	    String line;
	    //Recorro el directorio
	    while ((line = br.readLine()) != null) 
	    {
	       // Saco el objeto JSON del estudiante por cada archivo que hay
	    	String content = new String(Files.readAllBytes(Paths.get(pathEstudiantes+"/"+line+".json")));
	    	JSONObject jEstudiante = new JSONObject(content);
	    	
	    	Estudiante estudiante = EstudiantesPersistencia.cargarEstudiante(jEstudiante);
	    	//Añado al LPS
	    	LPS.addEstudiante(estudiante);
	    }
	}
	

	
	public static void guardarEstudiantes(boolean test) throws Exception
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
		
		metodosAuxPersistencia.cleanDatosEstudiantes(test);

		File fileEstudidantesDirectorio = new File(pathEstudiantesDirectorio);

		
		//Limpio el directorio para que no queden dobles nombres
		PrintWriter pwEstudiantesDirectorio = new PrintWriter(fileEstudidantesDirectorio);
		pwEstudiantesDirectorio.print("");
		pwEstudiantesDirectorio.close();
		

		for (Estudiante estudiante : estudiantesHash.values())
		{
			EstudiantesPersistencia.guardarEstudianteSingular(estudiante, pathEstudiantes, pathEstudiantesDirectorio);
			
		}
	}

	public static void guardarProfesores(boolean test) throws Exception
	{
	   	LearningPathSystem LPS = LearningPathSystem.getInstance();
			HashMap<String, Profesor> profesoresHash=LPS.getProfesores();
			
			String pathProfesoresDirectorio;
			String pathProfesores;
			
			if (test)
			{
				pathProfesoresDirectorio = "LearningPathProy2/datosTests/profesores/profesoresDirectorio.txt";
				pathProfesores="LearningPathProy2/datosTests/profesores/";
			}
			else
			{
				pathProfesoresDirectorio = "LearningPathProy2/datos/profesores/profesoresDirectorio.txt";
				pathProfesores="LearningPathProy2/datos/profesores/";

			}

			File fileEstudidantesDirectorio = new File(pathProfesoresDirectorio);

			//Limpio el directorio para que no queden dobles nombres
			PrintWriter pwProfesoresDirectorio = new PrintWriter(fileEstudidantesDirectorio);
			pwProfesoresDirectorio.write("");
			pwProfesoresDirectorio.close();

			
			
			for (Profesor profesor : profesoresHash.values())
			{
				ProfesoresPersistencia.guardarProfesorSingular(profesor, pathProfesores, pathProfesoresDirectorio);
				
			}		
	}
	

	public static void cargarProfesores(boolean test) throws Exception
	{
	  	LearningPathSystem LPS = LearningPathSystem.getInstance(); 
    	
		String pathProfesoresDirectorio;
		String pathProfesores;
		
		if (test)
		{
			pathProfesoresDirectorio = "LearningPathProy2/datosTests/profesores/profesoresDirectorio.txt";
			pathProfesores="LearningPathProy2/datosTests/profesores/";
		}
		else
		{
			pathProfesoresDirectorio = "LearningPathProy2/datos/profesores/profesoresDirectorio.txt";
			pathProfesores="LearningPathProy2/datos/profesores/";
		}
		

		File fileProfesoresDirectorio = new File(pathProfesoresDirectorio);
		
		if (!fileProfesoresDirectorio.exists())
		{
			throw new Exception ("No se encontro el directorio");
		}

		//Leo el archivo
		BufferedReader br = new BufferedReader(new FileReader(fileProfesoresDirectorio)); 		        
			
	    String line;
	    //Recorro el directorio
	    while ((line = br.readLine()) != null) 
	    {
	       // Saco el objeto JSON del estudiante por cada archivo que hay
	    	String content = new String(Files.readAllBytes(Paths.get(pathProfesores+"/"+line+".json")));
	    	JSONObject jProfesor = new JSONObject(content);
	    	
	    	Profesor profesor =ProfesoresPersistencia.cargarProfesor(jProfesor);
	    	//Añado al LPS
	    	LPS.addProfesor(profesor);
	    }
	}
	

}
