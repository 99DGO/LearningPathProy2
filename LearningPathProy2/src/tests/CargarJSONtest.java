package tests;

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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import controllers.Inscriptor;
import controllers.LearningPathSystem;
import creadores.CreadorAR;
import creadores.CreadorCamino;
import creadores.CreadorEstudiante;
import creadores.CreadorExamen;
import creadores.CreadorProfesor;
import datosEstudiantes.DatosEstudianteActividad;
import persistencia.ActividadesPersistencia;
import persistencia.CaminosPersistencia;
import persistencia.CentralPersistencia;
import traductores.TraductorActividad;
import traductores.TraductorCamino;
import traductores.TraductorEstudiante;
import traductores.TraductorProfesor;

public class CargarJSONtest {

    
	@Test
	public void testCargarPersitenciaCaminos()
	{
		LearningPathSystem LPS =LearningPathSystem.getInstance();
		
		try 
		{
			CentralPersistencia.cargarCaminosActividadesDatosEstudiante(true);
		} 
		catch (Exception e) 
		{
    		fail("No se cargo, tiro"+e.getMessage()); 
		}
		
		assertEquals( 2, LPS.getCaminos().keySet().size(), "No se cargaron el numero correcto de caminos" );
		
		String idCamino=null;
		try 
		{
			idCamino = TraductorCamino.getIDfromNombre("Python123");
		} 
		catch (Exception e) {
    		fail("No se encontro el ID del camino con el nombre"); 
		}
		
		CaminoAprendizaje camino= LPS.getCaminoIndividual(idCamino);
    	assertEquals("Python123", camino.getTitulo(), "El nombre del camino no se guardo bien" );
    	
    	List<Actividad> actividades = camino.getActividades();
    	assertEquals(2, actividades.size(), "Las actividades no se guardaron bien" );

    	Actividad actividad =actividades.getFirst();
    	HashMap<String, DatosEstudianteActividad>  hashDatosEst= actividad.getDatosEstudiantes();
    	assertEquals(1, hashDatosEst.size(), "Los datos del estudiante no se guardaron bien" );

	
	}
	
	/*
	**
	
	@Test
	public void testCargarPersistenciaCentral()
	{
		String idCamino=null;
		
		try 
		{
			idCamino = TraductorCamino.getIDfromNombre("Python123");
		} 
		catch (Exception e) {
    		fail("No se encontro el ID del camino con el nombre"); 
		}
		
		
		String pathCamino=("datosTests/Caminos/"+idCamino+"/");		
    	String contentCamino=null;
		try 
		{
			File caminoFile = new File(pathCamino+idCamino+".json");
			assertTrue("No existe el archivo del camino", caminoFile.exists());
			contentCamino = new String(Files.readAllBytes(Paths.get(pathCamino+idCamino+".json")));
		} 
		catch (IOException e)
		{
			fail("No se encontro el archivo de la actividad");
			e.printStackTrace();
		}
    	
		assertFalse("No se consiguio el content del camino", contentCamino==null);
		
    	JSONObject jCamino = new JSONObject(contentCamino);
    
    	CaminoAprendizaje camino=null;
		try 
		{
			camino = CaminosPersistencia.cargarCamino(jCamino, pathCamino);
		} catch (IOException e) 
		{
			fail("No deberia sacar exception");
			e.printStackTrace();
		}
		
    	assertEquals("Python123", camino.getTitulo(), "El nombre de la actividad no se guardo bien" );
    	
	}
	
*/
}
