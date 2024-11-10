package creadores;

import java.util.HashMap;

import controllers.LearningPathSystem;
import usuarios.Profesor;

public class CreadorProfesor
{
	public static void crearProfesor(String login, String password) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		HashMap<String, Profesor> profesores=LPS.getProfesores();
		
		for (Profesor profesor: profesores.values())
		{
			if (profesor.getLogin().equals(login))
			{
				throw new Exception ("Ya existe un profesor con ese login");
			}
		}
 	
        Profesor nuevoProfesor = new Profesor(login, password);  
		LPS.addProfesor(nuevoProfesor);
            
	}

}
