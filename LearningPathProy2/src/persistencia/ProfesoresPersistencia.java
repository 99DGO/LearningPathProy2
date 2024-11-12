package persistencia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;
import usuarios.Estudiante;
import usuarios.Profesor;

public class ProfesoresPersistencia 
{
	public static void guardarProfesorSingular(Profesor profesor, String pathProfesor, String pathDirectorio) throws Exception
	{
		JSONObject jProfesor = profesor.salvarJSON();
		//Creo el archivo con la información del camino
		FileWriter fileCamino;
		
		fileCamino = new FileWriter(pathProfesor+"/"+profesor.getID()+".json");
		
		fileCamino.write(jProfesor.toString(1));

		fileCamino.flush();
		fileCamino.close();
		
		//Añado al directorio de estudiantes el ID de este camino
		File fileEstudiantesDirectorio= new File(pathDirectorio);
		Writer output;
		output = new BufferedWriter(new FileWriter(fileEstudiantesDirectorio, true));
		output.append(profesor.getID()+"\n");
		output.close();
	}

	public static Profesor cargarProfesor(JSONObject jProfesor) 
	{
    	
    	List<CaminoAprendizaje> caminos = cargarListaCaminosProfesor(jProfesor);
    	
		Profesor profesor = new Profesor(jProfesor.getString("login"), jProfesor.getString("password"), jProfesor.getString("type"),
				caminos, jProfesor.getString("nombre"));
	
		return profesor;
	}
	
	public static List<CaminoAprendizaje> cargarListaCaminosProfesor(JSONObject jProfesor) 
	{
		LearningPathSystem LPS=LearningPathSystem.getInstance(); 
		
		List<CaminoAprendizaje> caminos = new LinkedList<CaminoAprendizaje>();
		
    	JSONArray jCaminosIDs = jProfesor.getJSONArray("caminos");
    	
    	for (int i =0; i<jCaminosIDs.length(); i++)
    	{
    		String caminoID=jCaminosIDs.getString(i);
    		CaminoAprendizaje camino = LPS.getCaminoIndividual(caminoID);
    		caminos.add(camino);
    	}
		
		return caminos;
	}

}
