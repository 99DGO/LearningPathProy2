package persistencia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.json.JSONException;
import org.json.JSONObject;

import datosEstudiantes.DatosEstudianteActividad;

public class DatosEstudiantesPersistencia 
{
	public static void guardarDatosEstudiante(DatosEstudianteActividad datosEst, String caminoID, String actividadID, String pathCaminos)
	{
		JSONObject jDatosEst=datosEst.salvarEnJSON();
		

		//Creo la carpeta de la actividad
	   String pathCarpetaActividad = (pathCaminos+caminoID+"/"+actividadID);  
	      
		//Creo el archivo con la información del dato del estudiante
		FileWriter fileDatoEst;
		try 
		{
			fileDatoEst = new FileWriter("/"+datosEst.getID()+".json");
			
			try 
			{
				fileDatoEst.write(jDatosEst.toString(1));
			} 
			catch (JSONException e) 
			{
				e.printStackTrace();
			}
			
			fileDatoEst.flush();
			fileDatoEst.close();
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		

		//Añado al directorio de actividades el ID de esta actividad
		Writer output;
		try 
		{
			output = new BufferedWriter(new FileWriter(pathCarpetaActividad+"/"+"DatosEstudiantesDirectorio.txt", true));
			output.append(datosEst.getID()+"\n");
			output.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

}
