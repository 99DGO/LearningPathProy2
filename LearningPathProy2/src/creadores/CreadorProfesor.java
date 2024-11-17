package creadores;

import java.util.HashMap;

import controllers.LearningPathSystem;
import usuarios.Estudiante;
import usuarios.Profesor;

public class CreadorProfesor
{
	//public static void crearProfesor(LearningPathSystem LPS, String login, String password, String nombre) throws Exception
	public static void crearProfesor(String login, String password, String nombre) throws Exception

	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		HashMap<String, Profesor> profesores=LPS.getProfesores();
		
		for (Profesor profesor: profesores.values())
		{
			if (profesor.getLogin().equals(login))
			{
				throw new Exception ("Ya existe un usuario con ese login");
			}
		}
		
		for (Estudiante estudiante: LPS.getEstudiantes().values())
		{
			if (estudiante.getLogin().equals(login))
			{
				throw new Exception ("Ya existe un usuario con ese login"); 
			}
		}
 	
        Profesor nuevoProfesor = new Profesor(login, password, nombre);  
		LPS.addProfesor(nuevoProfesor);
            
	}
	
	public static void eliminarProfesor(String IDprofesor) throws Exception 
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		HashMap<String, Profesor> profesores = LPS.getProfesores();

		if (profesores.containsKey(IDprofesor)) 
		{
			profesores.remove(IDprofesor);
		} 
		else 
		{
			throw new Exception("No existe un profesor con ese ID");
		}
	}

}
