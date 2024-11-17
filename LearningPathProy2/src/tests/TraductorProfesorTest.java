package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controllers.LearningPathSystem;
import creadores.CreadorCamino;
import creadores.CreadorProfesor;
import traductores.TraductorCamino;
import traductores.TraductorProfesor;

public class TraductorProfesorTest 
{
	private static String IDprof;
	@BeforeAll
 	static void init( ) throws Exception
    {
		LearningPathSystem.resetLPS();
		CreadorProfesor.crearProfesor("Dire999", "Dire123", "Dire Crowley");
		IDprof = TraductorProfesor.getIDfromLogin("Dire999");
		
		List<String> objetivos = new LinkedList<String>();
		objetivos.add("Saber diferentes tipos de datos");
		objetivos.add("Aprender loops");
		objetivos.add("Aprender estructuras");
		
		CreadorCamino.crearCaminoCero("Python123", "Un curso para saber los basicos de python", objetivos, 2, IDprof);
		CreadorCamino.crearCaminoCero("Jaca123", "Un curso para saber los basicos de java", objetivos, 3, IDprof);
		
		String idCamino = TraductorCamino.getIDfromNombre("Python123");
    }
	
	@Test
	public void getNombreFromIDTest()
	{
		try 
		{
			assertEquals("Dire Crowley", TraductorProfesor.getNombreFromID(IDprof), "No retorno bien el nombre");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No deberia salir error");
		}
	}
	
	@Test
	public void verCaminosCreadosTest()
	{
		try 
		{
			assertEquals(2, TraductorProfesor.verCaminosCreados(IDprof).size(), "No retorno bien el nombre");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No deberia salir error");
		}
	}

}
