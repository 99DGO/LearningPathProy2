package tests;

import static org.junit.Assert.fail;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;
import creadores.CreadorCamino;
import creadores.CreadorProfesor;
import persistencia.CaminosPersistencia;
import traductores.TraductorCamino;
import traductores.TraductorProfesor;

public class CentralPersistenciaTest 
{
	private LearningPathSystem LPS =LearningPathSystem.getInstance();

	@BeforeAll
	// Esto configura un setup que se hace una sola vez al comenzar el primer test
	// y despues nunca mas
	static void init() throws Exception 
	{
		CreadorProfesor.crearProfesor("Kakashi", "Kakashi123");
		String IDprof = TraductorProfesor.getIDfromLogin("Kakashi");
		
		List<String> objetivos = new LinkedList<String>();
		objetivos.add("Saber diferentes tipos de datos");
		objetivos.add("Aprender loops");
		objetivos.add("Aprender estructuras");
		
		CreadorCamino.crearCaminoCero("Python123", "Un curso para saber los basicos de python", objetivos, 2, IDprof);
		CreadorCamino.crearCaminoCero("Jaca123", "Un curso para saber los basicos de java", objetivos, 3, IDprof);

	}

	@Test
	public void testPersitencia()
	{
		String idCamino=null;
		
		try 
		{
			idCamino = TraductorCamino.getIDfromNombre("Python123");
		} 
		catch (Exception e) {
    		fail("No se encontro el ID del camino con el nombre"); 
		}
	
    	try
    	{
    		CaminoAprendizaje camino = LPS.getCaminoIndividual(idCamino);
    		CaminosPersistencia.GuardarCaminoSingular(camino);
    	}
    	catch (Exception e)
    	{
    		fail("No se creo el objeto del archivo"); 
    	}
    	

	}
	

}
