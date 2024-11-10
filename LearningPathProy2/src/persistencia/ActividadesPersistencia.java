package persistencia;

import caminosActividades.Actividad;
import caminosActividades.Tarea;
import creadores.CreadorAR;
import creadores.CreadorEncuesta;
import creadores.CreadorExamen;
import creadores.CreadorQuiz;
import creadores.CreadorTarea;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.json.JSONException;
import org.json.JSONObject;

public class ActividadesPersistencia 
{
	public static Actividad cargarActividadDesdeID(String IDActividad, String pathCarpetaCamino)
	{
		Actividad actividadToReturn=null;
		
		//Access ActividadesDirectorio con el path de la carpeta
		
		//Ir por cada linea del directorio accediendo para acceder la carpeta de la actividad
		
		//En la carpeta de la actividad acceder su info 
		
		//Crear actividad 
		return actividadToReturn;
	}
	
	public static void guardarActividad(Actividad actividad, String caminoID)
	{
		JSONObject jActividad=actividad.salvarEnJSON();
		

		//Creo la carpeta de la actividad
	   File pathCarpetaActividad = new File("datos/Caminos/"+caminoID+"/"+actividad.getId());  
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
			output = new BufferedWriter(new FileWriter("datos/Caminos/"+caminoID+"/ActividadesDirectorio.txt", true));
			output.append(actividad.getId()+"\n");
			output.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	

}
