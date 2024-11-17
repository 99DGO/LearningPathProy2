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

import caminosActividades.Actividad;
import caminosActividades.ActividadRecurso;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Examen;
import controllers.LearningPathSystem;
import editores.EditorCamino;
import usuarios.Profesor;

public class EditorCaminoTest 
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
			
			CaminoAprendizaje camino2 = new CaminoAprendizaje("El maravilloso mundo de los zorros", "Esto es un curso que te enseña lo increible que son los zorros",
					objetivos, 1.5, profesor.getID());
	
			LPS.addCamino(camino);
			LPS.addCamino(camino2);
			idCamino=camino.getID();
	
			int[] fechaLim= new int[]{0,1,0};

			AR= new ActividadRecurso("Lectura Test", "Esto es una lectura de tipos de variables", objetivos, 1.5, 20, fechaLim, 
					false, "https://www.w3schools.com/python/python_variables.asp", "Leer el articulo.", profesor.getID(), camino, 0);
			
			List<String> preguntasString= new LinkedList<String>();
			preguntasString.add("¿Cuales son las caracteristicas princiaples de los cuervos?");
			preguntasString.add("¿Que otro animal es parecido e igual de increible que los cuervos?");
			preguntasString.add("¿Por que son mejores los cuervos que otros pajaros?");
			
			Examen examen = new Examen("Examen Test", "Esto es un examen sobre cuervos", objetivos, 1.5, 20, 
					fechaLim, true, 3, preguntasString, profesor.getID(), camino, 0);
			idActividad=examen.getID();
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
    public void cambiarPosActividadTest()
    {    	
    	try
    	{
			EditorCamino.cambiarPosActividad(idCamino, idActividad, 1);
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
    	
    	assertEquals(Actividad.EXAMEN, LPS.getCaminoIndividual(idCamino).getActividades().getFirst().getType(), "No se cambio bien la posicion");
    }
    
    @Test
    public void addObjetivoTest()
    {
    	try 
    	{
			EditorCamino.editAddObjetivo(idCamino, "Ser una persona más culta.");
		} 
    	catch (Exception e)
    	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
    	
    	assertTrue(LPS.getCaminoIndividual(idCamino).getObjetivos().contains("Ser una persona más culta."), "No se añado bien el objetivo");

    }
    
    @Test
    public void delObjetivoTest()
    {
    	try 
    	{
    		EditorCamino.editDelObjetivo(idCamino, 3);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
    	
    	assertFalse(LPS.getCaminoIndividual(idCamino).getObjetivos().contains("Volverse fan de los cuervos."), "No se añado bien el objetivo");

    }
    
    @Test
    public void delActividadTest()
    {
    	try 
    	{
    		EditorCamino.editDelActividad(idCamino, 2);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
    	
    	assertEquals(1, LPS.getCaminoIndividual(idCamino).getActividades().size(), "No se borro bien la actividad");
    }
    
    @Test
    public void editDescripcionTest()
    {
    	try 
    	{
			EditorCamino.editDescripcion(idCamino, "El mejor curso del mundo.");
		} 
    	catch (Exception e)
    	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
    	
    	assertEquals("El mejor curso del mundo.", LPS.getCaminoIndividual(idCamino).getDescripcion(), "No se cambio bien la descripcion");
    	
    }
    
    @Test
    public void editDificultadTest()
    {
    	try 
    	{
			EditorCamino.editDificultad(idCamino, 2);
		} 
    	catch (Exception e)
    	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
    	
    	assertEquals(2, LPS.getCaminoIndividual(idCamino).getDificultad(), "No se cambio bien la dificultad");

    }
    
    @Test
    public void editTituloTest()
    {
    	try
    	{
    		EditorCamino.editTitulo(idCamino, "El deslumbrante mundo de los cuervos");
    	}
    	catch (Exception e)
    	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
    	}
    	
    	assertEquals("El deslumbrante mundo de los cuervos", LPS.getCaminoIndividual(idCamino).getTitulo(), "No se cambio bien el titulo");
    	
       	try
    	{
    		EditorCamino.editTitulo(idCamino, "El maravilloso mundo de los zorros");
			fail("Deberia sacar error por titulo repetido");
    	}
    	catch (Exception e)
    	{
			assertEquals("Ya existe un camino con ese titulo",e.getMessage(), "Exception incorrecta");
    	}
    }





}
