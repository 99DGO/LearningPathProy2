package persistencia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

import org.json.JSONObject;

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

}
