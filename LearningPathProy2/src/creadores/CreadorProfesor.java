package creadores;

import java.util.HashMap;

import controllers.LearningPathSystem;
import usuarios.Profesor;

public class CreadorProfesor
{
	public static void crearProfesor(LearningPathSystem LPS, String login, String password, String nombre) throws Exception
	{
		HashMap<String, Profesor> profesores=LPS.getProfesores();
		
		for (Profesor profesor: profesores.values())
		{
			if (profesor.getLogin().equals(login))
			{
				throw new Exception ("Ya existe un profesor con ese login");
			}
		}
 	
        Profesor nuevoProfesor = new Profesor(login, password, nombre);  
		LPS.addProfesor(nuevoProfesor);
            
	}
	
	public static void eliminarProfesor(LearningPathSystem LPS, String IDprofesor) throws Exception {
		HashMap<String, Profesor> profesores = LPS.getProfesores();

		if (profesores.containsKey(IDprofesor)) {
			profesores.remove(IDprofesor);
		} else {
			throw new Exception("No existe un profesor con ese ID");
		}
	}

}
