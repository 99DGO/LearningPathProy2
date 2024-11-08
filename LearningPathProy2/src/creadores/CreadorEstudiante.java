package creadores;

import java.util.HashMap;

import controllers.LearningPathSystem;
import usuarios.Estudiante;

public class CreadorEstudiante 
{
	public static void crearEstudiante(String login, String password) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		HashMap<String, Estudiante> estudiantes=LPS.getEstudiantes();
		
		for (Estudiante estudiante: estudiantes.values())
		{
			if (estudiante.getLogin().equals(login))
			{
				throw new Exception ("Ya existe un estudiante con ese login");
			}
		}
 	
        Estudiante nuevoEstudiante = new Estudiante(login, password);  
		LPS.addEstudiante(nuevoEstudiante);
            
	}
	

}
