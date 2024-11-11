package persistencia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import datosEstudiantes.DatosEstudianteAR;
import datosEstudiantes.DatosEstudianteActividad;
import datosEstudiantes.DatosEstudianteEncuesta;
import datosEstudiantes.DatosEstudianteExamen;
import datosEstudiantes.DatosEstudianteTarea;
import envios.EnvioEncuesta;
import envios.EnvioExamen;

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
			fileDatoEst = new FileWriter(pathCarpetaActividad+"/"+datosEst.getID()+".json");
			
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
	
	public static DatosEstudianteActividad cargarDatosEstudiante(JSONObject jDatoEst)
	{
		DatosEstudianteActividad datoEst=null;
		
		if (jDatoEst.getString("type").equals(DatosEstudianteActividad.ARDATO))
		{
			datoEst= new DatosEstudianteAR(jDatoEst.getString("IDEstudiante"), jDatoEst.getString("estado"),
					jDatoEst.getString("fechaInicio"), jDatoEst.getString("fechaFinal"), jDatoEst.getString("id"));
		}	
		
		else if (jDatoEst.getString("type").equals(DatosEstudianteActividad.ENCUESTADATO))
		{
			HashMap<String, String> respuestasHash = new HashMap<String, String>();
			
			//Saco las respuestas y preguntas para crear el hashmap
		   	JSONArray jRespuestas = jDatoEst.getJSONArray("envio");
	    	
	    	for (int i =0; i<jRespuestas.length(); i++)
	    	{
	    		String[] preguntaRespuesta = jRespuestas.getString(i).split("999_999");
	    		String pregunta=preguntaRespuesta[0];
	    		String respuesta=preguntaRespuesta[1];
	    		respuestasHash.put(pregunta, respuesta);
	    	}
			
			EnvioEncuesta envio = new EnvioEncuesta(respuestasHash);
			datoEst= new DatosEstudianteEncuesta (jDatoEst.getString("IDEstudiante"), jDatoEst.getString("estado"),
					jDatoEst.getString("fechaInicio"), jDatoEst.getString("fechaFinal"), jDatoEst.getString("id"), envio);
		}
		
		else if (jDatoEst.getString("type").equals(DatosEstudianteActividad.EXAMENDATO))
		{
			HashMap<String, String> respuestasHash = new HashMap<String, String>();
			
			//Saco las respuestas y preguntas para crear el hashmap
		   	JSONArray jRespuestas = jDatoEst.getJSONArray("envio");
	    	
	    	for (int i =0; i<jRespuestas.length(); i++)
	    	{
	    		String[] preguntaRespuesta = jRespuestas.getString(i).split("999_999");
	    		String pregunta=preguntaRespuesta[0];
	    		String respuesta=preguntaRespuesta[1];
	    		respuestasHash.put(pregunta, respuesta);
	    	}
			
			EnvioExamen envio = new EnvioExamen(respuestasHash, jDatoEst.getDouble("calificacion"));
			datoEst= new DatosEstudianteExamen (jDatoEst.getString("IDEstudiante"), jDatoEst.getString("estado"),
					jDatoEst.getString("fechaInicio"), jDatoEst.getString("fechaFinal"), jDatoEst.getDouble("calificacion"), 
					jDatoEst.getString("id"), envio);
		}
		
		else if (jDatoEst.getString("type").equals(DatosEstudianteActividad.QUIZDATO))
		{
	
		}
		
		else
		{
			datoEst= new DatosEstudianteTarea (jDatoEst.getString("IDEstudiante"), jDatoEst.getString("estado"),
					jDatoEst.getString("fechaInicio"), jDatoEst.getString("fechaFinal"), jDatoEst.getString("metodoEntrega"), 
					jDatoEst.getString("id"));
		}


		return datoEst;
	}

}
