package traductores;

import java.util.HashMap;

import controllers.LearningPathSystem;
import usuarios.Profesor;

public class TraductorProfesor 
{
	public static String getIDfromLogin(String login) throws Exception
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		HashMap<String, Profesor> profesores = LPS.getProfesores();
		String IDtoReturn=null;
		
		for (String IDProfesor: profesores.keySet())
		{
			Profesor profesorIterator=profesores.get(IDProfesor);
			
			if (profesorIterator.getLogin().equals(login))
			{
				IDtoReturn= IDProfesor;
			}
		}
		
		if (IDtoReturn==null)
		{
			throw new Exception("No se encontro el login");
		}
		else
		{
			return IDtoReturn;
		}
	}

}
