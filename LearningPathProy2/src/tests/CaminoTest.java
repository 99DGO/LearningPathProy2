package tests;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;
import creadores.CreadorCamino;
import creadores.CreadorProfesor;
import persistencia.CaminosPersistencia;
import persistencia.CentralPersistencia;
import traductores.TraductorCamino;
import traductores.TraductorProfesor;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class CaminoTest {
	
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
    		CaminosPersistencia.GuardarCaminoSingular(camino, "datosTest/Caminos/");
    	}
    	catch (Exception e)
    	{
    		fail("No se creo el objeto del archivo"); 
    	}
    	

	}
	 
}
