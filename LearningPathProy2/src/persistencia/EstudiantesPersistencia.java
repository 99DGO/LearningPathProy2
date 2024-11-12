package persistencia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;
import usuarios.Estudiante;

public class EstudiantesPersistencia {
	
	public static void guardarEstudianteSingular(Estudiante estudiante, String pathCarpetaEstudiante, String pathDirectorio) throws Exception
	{
		JSONObject jEstudiante = estudiante.salvarJSON();
		//Creo el archivo con la información del camino
		FileWriter fileCamino;
		
		fileCamino = new FileWriter(pathCarpetaEstudiante+"/"+estudiante.getID()+".json");
		
		fileCamino.write(jEstudiante.toString(1));

		fileCamino.flush();
		fileCamino.close();
		
		//Añado al directorio de estudiantes el ID de este camino
		File fileEstudiantesDirectorio= new File(pathDirectorio);
		Writer output;
		output = new BufferedWriter(new FileWriter(fileEstudiantesDirectorio, true));
		output.append(estudiante.getID()+"\n");
		output.close();
		
	
		
	}

	public static List<CaminoAprendizaje> cargarListaCaminosEstudiantes(JSONObject jEstudiante) 
	{
		LearningPathSystem LPS=LearningPathSystem.getInstance(); 
		
		List<CaminoAprendizaje> caminos = new LinkedList<CaminoAprendizaje>();
		
    	JSONArray jCaminosIDs = jEstudiante.getJSONArray("historialCaminos");
    	
    	for (int i =0; i<jCaminosIDs.length(); i++)
    	{
    		String caminoID=jCaminosIDs.getString(i);
    		CaminoAprendizaje camino = LPS.getCaminoIndividual(caminoID);
    		caminos.add(camino);
    	}
		
		return caminos;
	}

	public static Estudiante cargarEstudiante(JSONObject jEstudiante) {
		
    	//Saco los intereses
    	JSONArray jIntereses = jEstudiante.getJSONArray("intereses");
    	List<String> intereses = new LinkedList<String>();
    	
    	for (int i =0; i<jIntereses.length(); i++)
    	{
    		String interes=jIntereses.getString(i);
    		intereses.add(interes);
    	}
    	
    	List<CaminoAprendizaje> historialCaminos = cargarListaCaminosEstudiantes(jEstudiante);
    	
		Estudiante estudiante = new Estudiante(jEstudiante.getString("login"), jEstudiante.getString("password"), jEstudiante.getString("type"),
				historialCaminos, intereses, jEstudiante.getBoolean("actividadActiva"), jEstudiante.getString("nombre"));
	
		return estudiante;
	}

}
