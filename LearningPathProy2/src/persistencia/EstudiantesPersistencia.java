package persistencia;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONException;

import usuarios.Estudiante;

public class EstudiantesPersistencia {
	
	public static void guardarEstudianteSingular(Estudiante estudiante, String pathEstudiantes, String pathCarpetaEstudiante)
	{
		//Creo el archivo con la información del camino
		FileWriter fileCamino;
		try 
		{
			fileCamino = new FileWriter(pathCarpetaCamino+"/"+caminoID+".json");
			
			try 
			{
				fileCamino.write(jCamino.toString(1));
			} 
			catch (JSONException e) 
			{
				e.printStackTrace();
			}
			
			fileCamino.flush();
			fileCamino.close();
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}

}
