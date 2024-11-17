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
import caminosActividades.Tarea;
import controllers.LearningPathSystem;
import editores.EditorTarea;
import usuarios.Profesor;

public class EditorTareaTest 
{
	private static String idActividad;
	private static String idActividadSecundaria;
	private static String idCamino;
	private static CaminoAprendizaje camino;
	private static ActividadRecurso AR;
	private static LearningPathSystem LPS;
	private static Profesor profesor;
	private static Tarea tarea;
	
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
			
			tarea = new Tarea("Tarea Test", "Esto es una tarea sobre cuervos", objetivos, 1.5, 20, 
					fechaLim, true, "Tome fotos de cuervos.", profesor.getID(), camino, 0);
			idActividad=tarea.getId();
						
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
    public void editInstruccion()
    {
    	try 
    	{
			EditorTarea.editInstrucciones( "Tome una foto de un cuervo y haga una pintura", idCamino, idActividadSecundaria);
			fail("Deberia sacar error por tipo de actividad equivocado");
		} 
    	catch (Exception e) 
    	{
    		assertEquals("La actividad pasada no fue una tarea.", e.getMessage(), "No saco la exception correcta.");
    	}
    
    	try 
    	{
    		EditorTarea.editInstrucciones("Tome una foto de un cuervo y haga una pintura", idCamino, idActividad);
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
    	
    	assertEquals("Tome una foto de un cuervo y haga una pintura", tarea.getInstrucciones(), "No se edito bien las instrucciones.");
     	assertEquals(2, camino.getVersion(), "No se guardo bien la version");
    }
    
    @Test
    public void editAddDelActividadSigFracaso()
    {
    	try 
    	{
			EditorTarea.editAddActividadSigFracaso(idCamino, idActividadSecundaria, "Lectura Test");
			fail("Deberia sacar error por tipo de actividad equivocado");
		} 
    	catch (Exception e) 
    	{
    		assertEquals("La actividad pasada no fue una tarea.", e.getMessage(), "No saco la exception correcta.");
    	}
    	
       	try 
    	{
       		EditorTarea.editAddActividadSigFracaso(idCamino, idActividad, "Lectura Test");
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}	
       	
       	assertTrue(tarea.getActividadesSigFracaso().contains("Lectura Test"), "No se añadio bien la actividad siguiente fracaso");
     	assertEquals(2, camino.getVersion(), "No se guardo bien la version");

     	
       	try 
    	{
			EditorTarea.editDelActividadSigFracaso(idCamino, idActividadSecundaria, 1);
			fail("Deberia sacar error por tipo de actividad equivocado");
		} 
    	catch (Exception e) 
    	{
    		assertEquals("La actividad pasada no fue una tarea.", e.getMessage(), "No saco la exception correcta.");
    	}
       	try 
    	{
       		EditorTarea.editDelActividadSigFracaso(idCamino, idActividad, 1);
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
    	
    	assertFalse(tarea.getActividadesSigFracaso().contains("Lectura Test"), "No se borro bien la actividad siguiente fracaso");
     	assertEquals(3, camino.getVersion(), "No se guardo bien la version");
    }
	

}
