package tests;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;
import creadores.CreadorCamino;
import creadores.CreadorProfesor;
import persistencia.ActividadesPersistencia;
import persistencia.CaminosPersistencia;
import persistencia.CentralPersistencia;
import traductores.TraductorActividad;
import traductores.TraductorCamino;
import traductores.TraductorProfesor;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class CaminoTest {
	
	private LearningPathSystem LPS =LearningPathSystem.getInstance();

	static boolean deleteDirectory(File directoryToBeDeleted) 
	{
	    File[] allContents = directoryToBeDeleted.listFiles();
	    if (allContents != null) 
	    {
	        for (File file : allContents) 
	        {
	            deleteDirectory(file);
	        }
	    }
	    return directoryToBeDeleted.delete();
	}
	
	@BeforeAll
	// Esto configura un setup que se hace una sola vez al comenzar el primer test
	// y despues nunca mas
	static void init() throws Exception 
	{
		File fileCaminosDirectorio = new File("/LearningPathProy2/datosTests/Caminos/CaminosDirectorio.txt");
		
		//Leo el archivo
		try (BufferedReader br = new BufferedReader(new FileReader(fileCaminosDirectorio))) 
		{		        
			
		    String line;
		    //Recorro el directorio para borrar las carpetas
		    while ((line = br.readLine()) != null) 
		    {
		    	File carpetaCamino = new File("/LearningPathProy2/datosTests/Caminos/"+line);
		       
		        deleteDirectory(carpetaCamino);
		    	
		    }
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		

	}
	
	@Test
	@Order(1)
	public void testCrearCamino()
	{
		String IDprof=null;
		try
		{
			CreadorProfesor.crearProfesor("KakashiCaminoTest", "Kakashi123", "Kakashi Hatake");
			IDprof = TraductorProfesor.getIDfromLogin("KakashiCaminoTest");
		}
		catch (Exception e)
		{
			fail("Error en la creación de profesor. Chequear otras pruebas"+e.getMessage());
			e.printStackTrace();
		}
		
		List<String> objetivos = new LinkedList<String>();
		objetivos.add("Saber diferentes tipos de datos");
		objetivos.add("Aprender loops");
		objetivos.add("Aprender estructuras");
		
		try
		{
			CreadorCamino.crearCaminoCero("Python123", "Un curso para saber los basicos de python", objetivos, 2, IDprof);
			CreadorCamino.crearCaminoCero("Java123", "Un curso para saber los basicos de java", objetivos, 3, IDprof);
		}
		catch (Exception e)
		{
			fail("No se guardaron los caminos por error: "+e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	@Order(3)
	public void testGuardarPersitencia()
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
    		CaminosPersistencia.GuardarCaminoSingular(camino, "/LearningPathProy2/datosTest/Caminos/");
    	}
    	catch (Exception e)
    	{
    		fail("No se creo el objeto del archivo"); 
    	}
    	

	}
	

	 
}
