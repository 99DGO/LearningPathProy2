package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import caminosActividades.ActividadRecurso;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Examen;
import controllers.Inscriptor;
import controllers.LearningPathSystem;
import traductores.TraductorAR;
import traductores.TraductorEstudiante;
import usuarios.Estudiante;
import usuarios.Profesor;

public class TraductorARTest 
{
	private static String idEstudiante;
	private static String idActividad;
	private static String idCamino;
	private static CaminoAprendizaje camino;
	private static ActividadRecurso AR;
	private static LearningPathSystem LPS;
	private static Profesor profesor;
	private static HashMap<String, String> respuestas;
	private static String idActividadSecundaria;
	private static Estudiante estudiante;
	

	@BeforeEach
 	void init( ) 
    {
		try
		{
			LearningPathSystem.resetLPS(); 
			LPS=LearningPathSystem.getInstance();
			
			profesor = new Profesor("Aizawa999", "Aizawa 123", "Aizawa Shouta");
			LPS.addProfesor(profesor);
						
			List<String> objetivos = new LinkedList<String>();
			objetivos.add("Saber la diferencia entre distintos tipos de cuervos.");
			objetivos.add("Poder sustentar porque los cuervos son tan increibles.");
			objetivos.add("Volverse fan de los cuervos.");
			
			camino = new CaminoAprendizaje("El maravilloso mundo de los cuervos", "Esto es un curso que te enseña lo increible que son los cuervos",
					objetivos, 1.5, profesor.getID());
	
			LPS.addCamino(camino);
			idCamino=camino.getID();

	
			int[] fechaLim= new int[]{0,1,0};
			
			AR= new ActividadRecurso("Lectura Test", "Esto es una lectura de tipos de variables", objetivos, 1.5, 20, fechaLim, 
					false, "https://www.w3schools.com/python/python_variables.asp", "Leer el articulo.", profesor.getID(), camino, 0);
			idActividad=AR.getID();
			

    	}
		catch (Exception e) 
		{
			fail("Error en el setup: "+e.getMessage());
		}
    }
	
	@Test
	public void retornarInstruccionesRecursoTest()
	{
		try 
		{
			String[] instruccionesRecurso=TraductorAR.retornarInstruccionesRecurso(idCamino, idActividad);
			assertEquals("Leer el articulo.", instruccionesRecurso[0], "No retorno bien las instrucciones.");
			assertEquals("https://www.w3schools.com/python/python_variables.asp", instruccionesRecurso[1], "No retorno bien el recurso.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
	}

}
