package persistencia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;

public class CaminosPersistencia 
{
	public static void GuardarCaminoSingular(CaminoAprendizaje camino)
	{
		String caminoID = camino.getID();
		JSONObject jCamino= camino.salvarEnJSON();
	
		//Creo la carpeta del camino
	   File pathCarpetaCamino = new File("datos/Caminos/"+caminoID);  
	   boolean bool = pathCarpetaCamino.mkdir();  
	      
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
		
		//Creo el directorio de actividades dentro la carpeta del camino
		FileWriter fileDirectorioActividades;
		try 
		{
			fileDirectorioActividades = new FileWriter(pathCarpetaCamino+"/"+"ActividadesDirectorio"+".txt");		
			fileDirectorioActividades.flush();
			fileDirectorioActividades.close();
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		

		//Añado al directorio de caminos el ID de este camino
		Writer output;
		try 
		{
			output = new BufferedWriter(new FileWriter("datos/Caminos/CaminosDirectorio.txt", true));
			output.append(caminoID+"\n");
			output.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	
	public static void CargarCaminos()
	{
		
	}

}
