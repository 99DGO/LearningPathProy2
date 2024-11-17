package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import caminosActividades.ActividadRecurso;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Encuesta;
import caminosActividades.OpcionQuiz;
import caminosActividades.PreguntaQuiz;
import controllers.LearningPathSystem;
import editores.EditorEncuesta;
import editores.EditorExamen;
import usuarios.Profesor;

public class EditorEncuestaTest 
{

	private static String idCamino;
	private static LearningPathSystem LPS;
	private static CaminoAprendizaje camino;
	private static String idActividad;
	private static String idActividadSecundaria;
	private static Encuesta encuesta;
	
	
	@BeforeEach
 	void init( ) 
    {
		try
		{
			LearningPathSystem.resetLPS(); 
			LPS=LearningPathSystem.getInstance();
			
			Profesor profesor = new Profesor("Aizawa999", "Aizawa 123", "Aizawa Shouta");
			LPS.addProfesor(profesor);
			
			List<String> objetivos = new LinkedList<String>();
			objetivos.add("Saber la diferencia entre distintos tipos de cuervos.");
			objetivos.add("Poder sustentar porque los cuervos son tan increibles.");
			objetivos.add("Volverse fan de los cuervos.");
			
			camino = new CaminoAprendizaje("El maravilloso mundo de los cuervos", "Esto es un curso que te enseña lo increible que son los cuervos",
					objetivos, 1.5, profesor.getID());
			idCamino=camino.getID();
	
			LPS.addCamino(camino);
			
			int[] fechaLim= new int[]{0,1,0};
			
			List<String> preguntasString= new LinkedList<String>();
			preguntasString.add("¿Cuales son las caracteristicas princiaples de los cuervos?");
			preguntasString.add("¿Que otro animal es parecido e igual de increible que los cuervos?");
			preguntasString.add("¿Por que son mejores los cuervos que otros pajaros?");
			
			encuesta = new Encuesta("Encuesta Test", "Esto es una tarea sobre cuervos", objetivos, 1.5, 20, fechaLim, true, preguntasString, 
					profesor.getID(), camino, 0);
			idActividad=encuesta.getID();
			
			ActividadRecurso AR= new ActividadRecurso("Lectura Test", "Esto es una lectura de tipos de variables", objetivos, 1.5, 20, fechaLim, 
					false, "https://www.w3schools.com/python/python_variables.asp", "Leer el articulo.", profesor.getID(), camino, 0);
			idActividadSecundaria=AR.getID();
    	}
		catch (Exception e) 
		{
			fail("Error en el setup: "+e.getMessage());
		}
    }
	
    @AfterEach
    void tearDown( ) throws Exception
    {
    }
    
	
   @Test
   public void editAddDelPreguntaTest()
   {
	   	
	   	try 
	   	{
			EditorEncuesta.editAddPregunta("¿Por que deberia ser el siguiente presidente un cuervo?", idCamino, idActividadSecundaria);
			fail("Deberia sacar error por tipo de actividad equivocado");
		} 
	   	catch (Exception e) 
	   	{
	   		assertEquals("La actividad pasada no fue una encuesta.", e.getMessage(), "No saco la exception correcta.");
	   	}
	   	
	    try 
	   	{
	    	EditorEncuesta.editAddPregunta("¿Por que deberia ser el siguiente presidente un cuervo?", idCamino, idActividad);
	   	} 
	   	catch (Exception e) 
	   	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}	
      	
      	assertTrue(encuesta.getPreguntasAbiertas().contains("¿Por que deberia ser el siguiente presidente un cuervo?"), "No se añadio bien la pregunta");
    	assertEquals(2, camino.getVersion(), "No se guardo bien la version");
    	
	    try 
	   	{
	    	EditorEncuesta.editDelPregunta(4, idCamino, idActividadSecundaria);
			fail("Deberia sacar error por tipo de actividad equivocado");
		} 
	   	catch (Exception e) 
	   	{
	   		assertEquals("La actividad pasada no fue una encuesta.", e.getMessage(), "No saco la exception correcta.");
	   	}
      	try 
      	{
      		EditorEncuesta.editDelPregunta(4, idCamino, idActividad);
		} 
	   	catch (Exception e) 
	   	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
	   	
	   	assertFalse(encuesta.getPreguntasAbiertas().contains("¿Por que deberia ser el siguiente presidente un cuervo?"), "No se borro bien la  pregunta");
	    	assertEquals(3, camino.getVersion(), "No se guardo bien la version");
   }
	   

}
