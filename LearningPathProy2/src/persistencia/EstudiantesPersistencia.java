package persistencia;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import usuarios.Estudiante;

public class EstudiantesPersistencia {
	
	public static void guardarEstudianteSingular(Estudiante estudiante, String pathCarpetaEstudiante) throws Exception
	{
		JSONObject jEstudiante = estudiante.salvarJSON();
		//Creo el archivo con la información del camino
		FileWriter fileCamino;
		
		fileCamino = new FileWriter(pathCarpetaEstudiante+"/"+estudiante.getID()+".json");
		
		fileCamino.write(jEstudiante.toString(1));

		fileCamino.flush();
		fileCamino.close();
	
		
	}

	public static void cargarActividadesEstudiantes() 
	{
		// TODO Auto-generated method stub
		
	}

}
