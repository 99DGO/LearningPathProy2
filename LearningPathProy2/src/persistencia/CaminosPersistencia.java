package persistencia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;

public class CaminosPersistencia 
{
	public static void GuardarCaminoSingular(CaminoAprendizaje camino, String pathCaminos)
	{
		String caminoID = camino.getID();
		JSONObject jCamino= camino.salvarEnJSON();
	
		//Creo la carpeta del camino
	   File pathCarpetaCamino = new File(pathCaminos+caminoID);  
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
			output = new BufferedWriter(new FileWriter(pathCaminos+"CaminosDirectorio.txt", true));
			output.append(caminoID+"\n");
			output.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public static CaminoAprendizaje cargarCamino(JSONObject jcamino, String pathCarpeta) throws IOException, Exception
	    {
	    	//Saco los objetivos
	    	JSONArray jObjetivos = jcamino.getJSONArray("objetivos");
	    	List<String> objetivos = new LinkedList<String>();
	    	
	    	for (int i =0; i<jObjetivos.length(); i++)
	    	{
	    		String objetivo=jObjetivos.getString(i);
	    		objetivos.add(objetivo);
	    	}
	    	
	    	//Saco las actividades
	    	JSONArray jActividades = jcamino.getJSONArray("actividades");
	    	List<Actividad> actividades = new LinkedList<Actividad>();
	    	
	    	for (int i2 =0; i2<jActividades.length(); i2++)
	    	{
	    		//Saco el jObject de la actividad
	    		String idActividad=jActividades.getString(i2);
	    		String pathActividad= pathCarpeta+idActividad+"/";
	    		
		    	String contentActividad = new String(Files.readAllBytes(Paths.get(pathActividad+idActividad+".json")));
		    	
		    	JSONObject jActividad = new JSONObject(contentActividad);
		    	
	    		//Creo la actividad
		    	Actividad actividad=ActividadesPersistencia.cargarActividad(jActividad, pathActividad);
		    	actividades.add(actividad);
	    	}
	    	
	    	
	    	//Creo el camino
	    	CaminoAprendizaje camino = new CaminoAprendizaje (jcamino.getString("titulo"), jcamino.getString("descripcion"),
	    			objetivos, jcamino.getDouble("dificultad"), jcamino.getInt("duracion"), jcamino.getString("fechaCreacion"), 
	    			jcamino.getDouble("rating"), jcamino.getInt("ratingsTotales"), jcamino.getInt("version"), jcamino.getString("fechaModificacion"), 
	    			jcamino.getInt("numActividadesObligatorias"), actividades, jcamino.getString("creadorID"), jcamino.getString("id"));
	    	
	    	return camino;
	    }

}
