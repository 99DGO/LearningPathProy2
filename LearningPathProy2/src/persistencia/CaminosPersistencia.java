package persistencia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;

public class CaminosPersistencia 
{
	public static void GuardarCaminos()
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		HashMap<String, CaminoAprendizaje> caminosHash =LPS.getCaminos();
		
		for (String caminoID: caminosHash.keySet())
		{
			CaminoAprendizaje camino = caminosHash.get(caminoID);
			JSONObject jCamino= camino.salvarEnJSON();
			
			FileWriter file;
			
			try 
			{
				file = new FileWriter("datos/Caminos/"+caminoID+".json");
				
				try 
				{
					file.write(jCamino.toString(1));
				} 
				catch (JSONException e) 
				{
					e.printStackTrace();
				}
				
				file.flush();
				file.close();
				
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			
			Writer output;
			try 
			{
				output = new BufferedWriter(new FileWriter("datos/Caminos/CaminosDirectorio.txt", true));
				output.append(caminoID);
				output.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		
			
		}
	}

}
