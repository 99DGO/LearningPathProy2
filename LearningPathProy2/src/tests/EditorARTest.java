package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import caminosActividades.ActividadRecurso;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Examen;
import controllers.LearningPathSystem;
import editores.EditorAR;
import usuarios.Profesor;

public class EditorARTest
{
	private static String idActividad;
	private static String idActividadSecundaria;
	private static String idCamino;
	private static CaminoAprendizaje camino;
	private static ActividadRecurso AR;
	private static LearningPathSystem LPS;
	private static Profesor profesor;
	
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
			
			List<String> preguntasString= new LinkedList<String>();
			preguntasString.add("¿Cuales son las caracteristicas princiaples de los cuervos?");
			preguntasString.add("¿Que otro animal es parecido e igual de increible que los cuervos?");
			preguntasString.add("¿Por que son mejores los cuervos que otros pajaros?");
			
			Examen examen = new Examen("Examen Test", "Esto es un examen sobre cuervos", objetivos, 1.5, 20, 
					fechaLim, true, 3, preguntasString, profesor.getID(), camino, 0);
			idActividadSecundaria=examen.getID();
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
    public void editRecursoTest()
    {
    	try 
    	{
			EditorAR.editRecurso("El recurso sera mandado telekineticamente", idCamino, idActividadSecundaria);
			fail("Deberia sacar error por tipo de actividad equivocado");
		} 
    	catch (Exception e) 
    	{
    		assertEquals("La actividad pasada no fue una actividad de recurso.", e.getMessage(), "No saco la exception correcta.");
    	}
    
    	try 
    	{
			EditorAR.editRecurso("El recurso sera mandado telekineticamente", idCamino, idActividad);
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
    	
    	assertEquals("El recurso sera mandado telekineticamente", AR.getRecurso(), "No se edito bien el recurso.");
    }
    
    @Test
    public void editInstruccionTest()
    {
    	try 
    	{
			EditorAR.editInstrucciones( "Dormir con el recurso al lado", idCamino, idActividadSecundaria);
			fail("Deberia sacar error por tipo de actividad equivocado");
		} 
    	catch (Exception e) 
    	{
    		assertEquals("La actividad pasada no fue una actividad de recurso.", e.getMessage(), "No saco la exception correcta.");
    	}
    
    	try 
    	{
			EditorAR.editInstrucciones("Dormir con el recurso al lado", idCamino, idActividad);
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
    	
    	assertEquals("Dormir con el recurso al lado", AR.getInstrucciones(), "No se edito bien las instrucciones.");
    }
	

}
