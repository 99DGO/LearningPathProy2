package persistencia;

import caminosActividades.Actividad;
import caminosActividades.ActividadRecurso;
import caminosActividades.Tarea;
import creadores.CreadorAR;
import creadores.CreadorEncuesta;
import creadores.CreadorExamen;
import creadores.CreadorQuiz;
import creadores.CreadorTarea;
import datosEstudiantes.DatosEstudianteActividad;

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

public class ActividadesPersistencia 
{
	
	public static void guardarActividad(Actividad actividad, String caminoID, String pathCaminos)
	{
		JSONObject jActividad=actividad.salvarEnJSON();
		

		//Creo la carpeta de la actividad
	   File pathCarpetaActividad = new File(pathCaminos+caminoID+"/"+actividad.getId());  
	   boolean bool = pathCarpetaActividad.mkdir();  
	      
		//Creo el archivo con la información de la actividad
		FileWriter fileActividad;
		try 
		{
			fileActividad = new FileWriter(pathCarpetaActividad+"/"+actividad.getId()+".json");
			
			try 
			{
				fileActividad.write(jActividad.toString(1));
			} 
			catch (JSONException e) 
			{
				e.printStackTrace();
			}
			
			fileActividad.flush();
			fileActividad.close();
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		//Creo el directorio de actividades dentro la carpeta del camino
		FileWriter fileDirectorioDatosEstudiantes;
		try 
		{
			fileDirectorioDatosEstudiantes = new FileWriter(pathCarpetaActividad+"/"+"DatosEstudiantesDirectorio"+".txt");		
			fileDirectorioDatosEstudiantes.flush();
			fileDirectorioDatosEstudiantes.close();
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		

		//Añado al directorio de actividades el ID de esta actividad
		Writer output;
		try 
		{
			output = new BufferedWriter(new FileWriter(pathCaminos+caminoID+"/ActividadesDirectorio.txt", true));
			output.append(actividad.getId()+"\n");
			output.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public static Actividad cargarActividad(JSONObject jActividad, String pathActividad)
	{
		
		Actividad actividad=null;
		
    	//Saco los objetivos
    	JSONArray jObjetivos = jActividad.getJSONArray("objetivos");
    	List<String> objetivos = new LinkedList<String>();
    	
    	for (int i =0; i<jObjetivos.length(); i++)
    	{
    		String objetivo=jObjetivos.getString(i);
    		objetivos.add(objetivo);
    	}
    	
    	//Saco la fecha lim
    	JSONArray jFechaLim = jActividad.getJSONArray("objetivos");
    	int[] fechaLim = new int[3];
    	
    	for (int i2 =0; i2<jFechaLim.length(); i2++)
    	{
    		fechaLim[i2]=(int) jFechaLim.get(i2);
    	}
    	
       	//Saco las reseñas
    	JSONArray jResenias = jActividad.getJSONArray("resenias");
    	List<String> resenias = new LinkedList<String>();
    	
    	for (int i =0; i<jResenias.length(); i++)
    	{
    		String resenia=jResenias.getString(i);
    		resenias.add(resenia);
    	}
    	
    	//Saco los datosEstudiantes
    	JSONArray jDatosEstIDs = jActividad.getJSONArray("resenias");
    	HashMap<String, DatosEstudianteActividad> datosEstHash = new HashMap<String, DatosEstudianteActividad>();
    	
    	for (int i3 =0; i3<jDatosEstIDs.length(); i3++)
    	{
    		//Saco el jObcet del datoEstudiante
    		String idDatoEst=jDatosEstIDs.getString(i3);
    		
	    	String contentDatoEst=null;
	    	
			try
			{
				contentDatoEst = new String(Files.readAllBytes(Paths.get(pathActividad+idDatoEst+".json")));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
	    	
	    	JSONObject jDatosEst = new JSONObject(contentDatoEst);
	    			
    		DatosEstudianteActividad datosEstInd= DatosEstudiantesPersistencia.cargarDatosEstudiante(jDatosEst);
    		
    		datosEstHash.put(idDatoEst, datosEstInd);
    	}
    	
  
		if (jActividad.getString("type").equals(Actividad.ACTIVIDADRECURSO))
		{
			actividad = new ActividadRecurso(jActividad.getString("nombre"), jActividad.getString("descripcion"), objetivos,
					jActividad.getDouble("dificultad"), jActividad.getInt("duracion"), fechaLim, jActividad.getBoolean("obligatoria"),
					jActividad.getDouble("rating"), jActividad.getInt("ratingsTotales"), resenias, jActividad.getString("creadorLogin"),
					jActividad.getString("type"), datosEstHash, jActividad.getString("recurso"), jActividad.getString("instrucciones"),
					jActividad.getString("id"));
		}
		
		return actividad;
	}
	

}
