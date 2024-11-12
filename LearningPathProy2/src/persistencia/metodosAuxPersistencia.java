package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class metodosAuxPersistencia 
{
	public static boolean deleteDirectory(File directoryToBeDeleted) 
	{
	    File[] allContents = directoryToBeDeleted.listFiles();
	    if (allContents != null) 
	    {
	        for (File file : allContents) 
	        {
	            deleteDirectory(file);
	        }
	    }
	    return directoryToBeDeleted.delete();
	}
	
	//Borra todas las carpetas de caminos y limpia el directorio
	public static void cleanDatos(boolean test) throws Exception
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
		//Limpio las carpetas
		BufferedReader br = new BufferedReader(new FileReader(fileCaminosDirectorio));
		String line;
	    //Recorro el directorio para borrar las carpetas
	    while ((line = br.readLine()) != null) 
	    {
	    	File carpetaCamino = new File(pathCaminos+line);
	       
	        deleteDirectory(carpetaCamino);
	    	
	    }
        
		//Limpio el directorio para que no queden dobles nombres
		PrintWriter pwCaminosDirectorio = new PrintWriter(fileCaminosDirectorio);
		pwCaminosDirectorio.print("");
		pwCaminosDirectorio.close();
		
	}
	
	public static int contadorLineasCaminosDirectorio(boolean test) throws Exception
	{
		String pathCaminosDirectorio;

		if (test)
		{
			pathCaminosDirectorio = "LearningPathProy2/datosTests/Caminos/CaminosDirectorio.txt";
		}
		else
		{
			pathCaminosDirectorio = "LearningPathProy2/datos/Caminos/CaminosDirectorio.txt";
		}
		
		BufferedReader reader = null;
		reader = new BufferedReader(new FileReader(pathCaminosDirectorio));

		
		int lines = 0;
		while (reader.readLine() != null) lines++;
		reader.close();
		
		return lines;
		
	}
	
	public static int contadorFoldersCaminos(boolean test) throws Exception
	{
		String pathCaminos;

		if (test)
		{
			pathCaminos = "LearningPathProy2/datosTests/Caminos/";
		}
		else
		{
			pathCaminos = "LearningPathProy2/datos/Caminos/";
		}
		return new File(pathCaminos).listFiles().length;
	}

	public static void cleanDatosEstudiantes(boolean test) {
		// TODO Auto-generated method stub
		
	}

}
