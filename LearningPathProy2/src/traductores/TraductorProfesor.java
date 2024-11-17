package traductores;

import java.util.HashMap;

import caminosActividades.CaminoAprendizaje;
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
	
	public static String getNombreFromID(String idProfesor) throws Exception
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		HashMap<String, Profesor> profesores = LPS.getProfesores();
		
		Profesor profesor= profesores.get(idProfesor);
		
		if (profesor==null)
		{
			throw new Exception ("No se encontro el profesor");
		}
		
		String nombre= profesor.getNombre();
		
		if (nombre==null)
		{
			throw new Exception ("No se encontro el profesor");
		}
		
		return nombre;
		
	}
	
	/*
	 * Retorna un hashmap que contiene como llave el id del camino y como value le nombre del camino
	 */
	public static HashMap<String, String> verCaminosCreados(String idProfesor) throws Exception
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		HashMap<String, Profesor> profesores = LPS.getProfesores();
		Profesor profesor= profesores.get(idProfesor);
		
		if (profesor==null)
		{
			throw new Exception ("No se encontro el profesor");
		}
		
		HashMap<String, String> caminosCreados=new HashMap<String, String>();
		
		for (CaminoAprendizaje camino: profesor.getCaminos())
		{
			caminosCreados.put(camino.getID(), camino.getTitulo());
		}
		
		return caminosCreados;
		
		
	}

}
