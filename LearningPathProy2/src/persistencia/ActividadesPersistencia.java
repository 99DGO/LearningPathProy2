package persistencia;

import caminosActividades.Actividad;
import caminosActividades.ActividadRecurso;
import caminosActividades.Encuesta;
import caminosActividades.Examen;
import caminosActividades.OpcionQuiz;
import caminosActividades.PreguntaQuiz;
import caminosActividades.Quiz;
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
import java.util.ArrayList;
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
	
	public static Actividad cargarActividad(JSONObject jActividad, String pathActividad) throws Exception
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
    	JSONArray jFechaLim = jActividad.getJSONArray("fechaLim");
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
    	
       	//Saco las actividades siguientes
    	JSONArray jActividadesSigExitoso = jActividad.getJSONArray("actividadesSigExitoso");
    	List<String> actividadesSigExitoso = new LinkedList<String>();
    	
    	for (int i =0; i<jActividadesSigExitoso.length(); i++)
    	{
    		String actividadSigExitoso=jActividadesSigExitoso.getString(i);
    		actividadesSigExitoso.add(actividadSigExitoso);
    	}
    	
       	//Saco las actividades prerequisitos
    	JSONArray jActividadesPrereqs = jActividad.getJSONArray("actividadesPrereqs");
    	List<String> actividadesPrereqs = new LinkedList<String>();
    	
    	for (int i =0; i<jActividadesPrereqs.length(); i++)
    	{
    		String actividadPrereqs=jActividadesPrereqs.getString(i);
    		actividadesPrereqs.add(actividadPrereqs);
    	}
    	
    	//Saco los datosEstudiantes
    	JSONArray jDatosEstIDs = jActividad.getJSONArray("datosEstudiantes");
    	HashMap<String, DatosEstudianteActividad> datosEstHash = new HashMap<String, DatosEstudianteActividad>();
    	
    	for (int i3 =0; i3<jDatosEstIDs.length(); i3++)
    	{
    		//Saco el jObcet del datoEstudiante
    		String idDatoEst=jDatosEstIDs.getString(i3);
    		
	    	String contentDatoEst=null;
	    	
	    	contentDatoEst = new String(Files.readAllBytes(Paths.get(pathActividad+idDatoEst+".json")));
	    	
	    	JSONObject jDatosEst = new JSONObject(contentDatoEst);
	    			
    		DatosEstudianteActividad datosEstInd= DatosEstudiantesPersistencia.cargarDatosEstudiante(jDatosEst);
    		
    		if (datosEstInd==null)
    		{
    			throw new Exception ("No se esta creando el datoEstInd");
    		}
    		
    		datosEstHash.put(idDatoEst, datosEstInd);
    	}
    	
  
		if (jActividad.getString("type").equals(Actividad.ACTIVIDADRECURSO))
		{
			actividad = new ActividadRecurso(jActividad.getString("nombre"), jActividad.getString("descripcion"), objetivos,
					jActividad.getDouble("dificultad"), jActividad.getInt("duracion"), fechaLim, jActividad.getBoolean("obligatoria"),
					jActividad.getDouble("rating"), jActividad.getInt("ratingsTotales"), resenias, jActividad.getString("creadorID"),
					jActividad.getString("type"), datosEstHash, jActividad.getString("recurso"), jActividad.getString("instrucciones"),
					jActividad.getString("id"), actividadesPrereqs, actividadesSigExitoso);
		}
		
		else if (jActividad.getString("type").equals(Actividad.ENCUESTA))
		{
	       	//Saco las preguntas
	    	JSONArray jPreguntas = jActividad.getJSONArray("preguntasAbiertas");
	    	List<String> preguntasAbiertas = new LinkedList<String>();
	    	
	    	for (int i =0; i<jPreguntas.length(); i++)
	    	{
	    		String pregunta=jPreguntas.getString(i);
	    		preguntasAbiertas.add(pregunta);
	    	}
	    	
			actividad= new Encuesta(jActividad.getString("nombre"), jActividad.getString("descripcion"), objetivos,
					jActividad.getDouble("dificultad"), jActividad.getInt("duracion"), fechaLim, jActividad.getBoolean("obligatoria"),
					jActividad.getDouble("rating"), jActividad.getInt("ratingsTotales"), resenias, jActividad.getString("creadorID"),
					jActividad.getString("type"), datosEstHash, preguntasAbiertas, jActividad.getString("id"), actividadesPrereqs,
					actividadesSigExitoso);
		}
		
		else if (jActividad.getString("type").equals(Actividad.EXAMEN))
		{
	       	//Saco las preguntas
	    	JSONArray jPreguntas = jActividad.getJSONArray("preguntasAbiertas");
	    	List<String> preguntasAbiertas = new LinkedList<String>();
	    	
	    	for (int i =0; i<jPreguntas.length(); i++)
	    	{
	    		String pregunta=jPreguntas.getString(i);
	    		preguntasAbiertas.add(pregunta);
	    	}
	    	
	       	//Saco las actividades fracaso
	    	JSONArray jActividadesSigFracaso = jActividad.getJSONArray("actividadesSigFracaso");
	    	List<String> actividadesSigFracaso = new LinkedList<String>();
	    	
	    	for (int i =0; i<jActividadesSigFracaso.length(); i++)
	    	{
	    		String IDactividadesSigFracaso=jActividadesSigFracaso.getString(i);
	    		actividadesSigFracaso.add(IDactividadesSigFracaso);
	    	}
	    	
			actividad= new Examen(jActividad.getString("nombre"), jActividad.getString("descripcion"), objetivos,
					jActividad.getDouble("dificultad"), jActividad.getInt("duracion"), fechaLim, jActividad.getBoolean("obligatoria"),
					jActividad.getDouble("rating"), jActividad.getInt("ratingsTotales"), resenias, jActividad.getString("creadorID"),
					jActividad.getString("type"), datosEstHash, jActividad.getDouble("calificacionMin"), actividadesSigFracaso,
					preguntasAbiertas, jActividad.getString("id"), actividadesPrereqs, actividadesSigExitoso);
		}
		
		else if (jActividad.getString("type").equals(Actividad.TAREA))
		{
	
	       	//Saco las actividades fracaso
	    	JSONArray jActividadesSigFracaso = jActividad.getJSONArray("actividadesSigFracaso");
	    	List<String> actividadesSigFracaso = new LinkedList<String>();
	    	
	    	for (int i =0; i<jActividadesSigFracaso.length(); i++)
	    	{
	    		String IDactividadesSigFracaso=jActividadesSigFracaso.getString(i);
	    		actividadesSigFracaso.add(IDactividadesSigFracaso);
	    	}
	    	
			actividad= new Tarea(jActividad.getString("nombre"), jActividad.getString("descripcion"), objetivos,
					jActividad.getDouble("dificultad"), jActividad.getInt("duracion"), fechaLim, jActividad.getBoolean("obligatoria"),
					jActividad.getDouble("rating"), jActividad.getInt("ratingsTotales"), resenias, jActividad.getString("creadorID"),
					jActividad.getString("type"), datosEstHash, jActividad.getString("instrucciones"), actividadesSigFracaso,
					jActividad.getString("id"), actividadesPrereqs, actividadesSigExitoso);
		}
		
		else if (jActividad.getString("type").equals(Actividad.QUIZ))
		{
	       	//Saco las actividades fracaso
	    	JSONArray jActividadesSigFracaso = jActividad.getJSONArray("actividadesSigFracaso");
	    	List<String> actividadesSigFracaso = new LinkedList<String>();
	    	
	    	for (int i =0; i<jActividadesSigFracaso.length(); i++)
	    	{
	    		String IDactividadesSigFracaso=jActividadesSigFracaso.getString(i);
	    		actividadesSigFracaso.add(IDactividadesSigFracaso);
	    	}
	    	
	    	//Saco las preguntas 
	    	List<PreguntaQuiz> preguntasQuiz=new ArrayList<PreguntaQuiz>();
	    	JSONArray jPreguntas = jActividad.getJSONArray("preguntas");
	    	for (int i =0; i<jPreguntas.length(); i++)
	    	{
	    		JSONObject jPreguntaInd=jPreguntas.getJSONObject(i);
	    		//Declaro variables 
	    		OpcionQuiz respuesta=null;
	    		HashMap<Integer, OpcionQuiz> mapaOpciones= new HashMap<Integer, OpcionQuiz>();
	    		
	    		//Consigo las opciones
	    		JSONArray jOpciones = jPreguntaInd.getJSONArray("opciones");
	    		//Recorro las opciones
	    		for (int i2 =0; i2<jOpciones.length(); i2++)
	    		{
	    			//Creo las opciones
	    			JSONObject jOpcionInd=jOpciones.getJSONObject(i2);
	    			
	    			boolean correcta =jOpcionInd.getBoolean("correcta");
	    			
	    			OpcionQuiz opcionInd = new OpcionQuiz(jOpcionInd.getString("texto"), jOpcionInd.getString("explicacion"), correcta);
	    			
	    			mapaOpciones.put(jOpcionInd.getInt("numOpcion"), opcionInd);
	    		}
	    		
	    		
	    		PreguntaQuiz preguntaObjeto = new PreguntaQuiz(jPreguntaInd.getInt("cantidadOpciones"), mapaOpciones, 
	    				jPreguntaInd.getString("textoPregunta"), jPreguntaInd.getInt("respuesta"));
	    		
	    		preguntasQuiz.add(preguntaObjeto);
	    	}

	    	
			actividad= new Quiz(jActividad.getString("nombre"), jActividad.getString("descripcion"), objetivos,
					jActividad.getDouble("dificultad"), jActividad.getInt("duracion"), fechaLim, jActividad.getBoolean("obligatoria"),
					jActividad.getDouble("rating"), jActividad.getInt("ratingsTotales"), resenias, jActividad.getString("creadorID"),
					jActividad.getString("type"), datosEstHash, jActividad.getDouble("calificacionMin"), actividadesSigFracaso,
					preguntasQuiz, jActividad.getString("id"), jActividad.getBoolean("verdaderoFalso"), actividadesPrereqs, 
					actividadesSigExitoso);
		}
		
		return actividad;
	}
	

}
