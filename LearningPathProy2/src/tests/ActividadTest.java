package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;
import creadores.CreadorAR;
import creadores.CreadorCamino;
import creadores.CreadorExamen;
import creadores.CreadorProfesor;
import creadores.CreadorTarea;
import persistencia.ActividadesPersistencia;
import persistencia.CaminosPersistencia;
import persistencia.CentralPersistencia;
import traductores.TraductorActividad;
import traductores.TraductorCamino;
import traductores.TraductorProfesor;

public class ActividadTest {
	
	/*
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
		File fileCaminosDirectorio = new File("LearningPathProy2/datosTests/Caminos/CaminosDirectorio.txt");
		
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
			fail("Error en el set up, file not found");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			fail("Error en el set up, IOException");

		}
		
		CreadorProfesor.crearProfesor("Kakashi", "Kakashi123", "Kakashi Hatake");
		String IDprof = TraductorProfesor.getIDfromLogin("Kakashi");

		List<String> objetivos = new LinkedList<String>();
		objetivos.add("Saber diferentes tipos de datos");
		objetivos.add("Aprender loops");
		objetivos.add("Aprender estructuras");
		
		CreadorCamino.crearCaminoCero("Python123", "Un curso para saber los basicos de python", objetivos, 2, IDprof);
		CreadorCamino.crearCaminoCero("Jaca123", "Un curso para saber los basicos de java", objetivos, 3, IDprof);	

	}

	@Test
	@Order(1)
	public void testCreacionActividad()
	{
		String idCamino=null;
		String IDProf=null;

		try
		{
			idCamino = TraductorCamino.getIDfromNombre("Python123");
			IDProf = TraductorProfesor.getIDfromLogin("Kakashi");
		}
		catch (Exception e)
		{
			fail("Error sacando el id de caminos. Chequear otras pruebas");
			e.printStackTrace();
		}


		List<String> objetivosActividad = new LinkedList<String>();
		objetivosActividad.add("Saber que es un int");
		objetivosActividad.add("Saber que es un double");
		objetivosActividad.add("Saber que es un float");
		
		List<String> preguntasExamen = new LinkedList<String>();
		preguntasExamen.add("Cual es la diferencia entre double y float?");
		preguntasExamen.add("Se puede pasar de un double a un int o de un int a un double? Por que o por que no?");
		preguntasExamen.add("Diga cual es su tipo de variable favorita y justifique.");
		
		int [] fechaLim2= new int[] {0,0,0};
		try 
		{
			CreadorAR.crearARCero(idCamino, "Lectura de variables", "Una lectura para saber los tipos de variable", objetivosActividad, 
					2, 20, fechaLim2, true, "https://www.w3schools.com/python/python_datatypes.asp", "Lea el articulo", IDProf,0);
		}
		catch (Exception e)
		{
			fail("Error creando la actividad recurso");
			e.printStackTrace();
		}
		
		int[] fechaLim= new int[]{0,1,0};
		try 
		{
			CreadorExamen.crearExamenCero(idCamino, "Tipos de variables examen", "Comprobar que el estudiante sabe diferenciar variables numericos", 
					objetivosActividad, 2.5, 30, fechaLim, true, 3, preguntasExamen, IDProf,100);
			fail("Deberia salir error por posicion invalida");
		} 
		catch (Exception e) 
		{
			
		}
		
		try 
		{
			CreadorExamen.crearExamenCero(idCamino, "Tipos de variables examen", "Comprobar que el estudiante sabe diferenciar variables numericos", 
					objetivosActividad, 2.5, 30, fechaLim, true, 3, preguntasExamen, IDProf,1);
		} 
		catch (Exception e) 
		{
			fail("Error creando el examen");
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
    		
    		List<Actividad> actividades = camino.getActividades();
    		
    		ActividadesPersistencia.guardarActividad(actividades.get(0), idCamino, "/LearningPathProy2/datosTest/Caminos/");
    	}
    	catch (Exception e)
    	{
    		fail("No se creo el objeto del archivo"); 
    	}
    	
	}
	*/


}
