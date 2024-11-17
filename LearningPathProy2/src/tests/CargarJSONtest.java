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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Quiz;
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
import usuarios.Estudiante;
import usuarios.Profesor;

public class CargarJSONtest {

    @BeforeEach
    void setUp()
    {
    	LearningPathSystem.resetLPS();
    }
    
    @AfterEach
    void tearDown( ) throws Exception
    {
    }

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
			e.printStackTrace();
    		fail("No se cargo, tiro"+e.getMessage()); 
		}
		
		assertEquals( 2, LPS.getCaminos().keySet().size(), "No se cargaron el numero correcto de caminos" );
		
		String idCamino=null;
		try 
		{
			idCamino = TraductorCamino.getIDfromNombre("Python123");
		} 
		catch (Exception e) 
		{
    		fail("No se encontro el ID del camino con el nombre"); 
		}
		
		CaminoAprendizaje camino= LPS.getCaminoIndividual(idCamino);
    	assertEquals("Python123", camino.getTitulo(), "El nombre del camino no se guardo bien" );
    	
    	List<Actividad> actividades = camino.getActividades();
    	
    	/*
    	for (Actividad act: actividades)
    	{
    		System.out.println(act.getNombre());	
    	}
		*/
    	
    	assertEquals(5, actividades.size(), "Las actividades no se guardaron bien" );
    	

		
    	Actividad actividad =actividades.getFirst();
    	HashMap<String, DatosEstudianteActividad>  hashDatosEst= actividad.getDatosEstudiantes();
    	assertEquals(2, hashDatosEst.size(), "Los datos del estudiante no se guardaron bien" );
    	
    	Quiz quiz=null;
    	try
    	{
        	quiz = (Quiz) actividades.get(2);
    	}
    	catch (Exception e) 
    	{
    		fail("No se guardo correctamente la posición de las actividades");
    	}
    	
    	assertEquals("Quiz de asignación variables", quiz.getNombre(), "No se guardo bien el nombre");
    	assertEquals(2, quiz.getPreguntas().size(), "No se guardaron bien las preguntas");

	
	}
	
	@Test
	public void testCargarPersitenciaEstudiantes()
	{
		LearningPathSystem LPS =LearningPathSystem.getInstance();
		try 
		{
			CentralPersistencia.cargarEstudiantes(true);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
    		fail("No se cargo, tiro"+e.getMessage()); 
		}
		
		assertEquals( 2, LPS.getEstudiantes().keySet().size(), "No se cargaron el numero correcto de estudiantes" );
		
		String idEstudiante=null;
		try 
		{
			idEstudiante = TraductorEstudiante.getIDfromLogin("Cater999");
		} 
		catch (Exception e) {
    		fail("No se encontro el ID del camino con el nombre"); 
		}
		
		Estudiante estudiante= LPS.getEstudianteIndividual(idEstudiante);
    	assertEquals("Cater Diamond", estudiante.getNombre(), "El nombre del estudainte no se guardo bien" );
    	assertEquals("Cater123", estudiante.getPassword(), "La contraseña del estudainte no se guardo bien" );

    	
    	List<CaminoAprendizaje> caminos = estudiante.getHistorialCaminos();
    	assertEquals(1, caminos.size(), "Los caminos no se guardaron bien" );
	
	}
	
	@Test
	public void testCargarPersitenciaProfesores()
	{
		LearningPathSystem LPS =LearningPathSystem.getInstance();
		try 
		{
			CentralPersistencia.cargarProfesores(true);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
    		fail("No se cargo, tiro"+e.getMessage()); 
		}
		
		assertEquals( 2, LPS.getProfesores().keySet().size(), "No se cargaron el numero correcto de profesores" );
		
		String idProfesor=null;
		try 
		{
			idProfesor = TraductorProfesor.getIDfromLogin("Divus999");
		} 
		catch (Exception e) {
    		fail("No se encontro el ID del camino con el nombre"); 
		}
		
		Profesor profesor= LPS.getProfesorIndividual(idProfesor);
    	assertEquals("Divus Crewel", profesor.getNombre(), "El nombre del profesor no se guardo bien" );
    	assertEquals("Divus123", profesor.getPassword(), "La contraseña del profesor no se guardo bien" );

    	
    	List<CaminoAprendizaje> caminos = profesor.getCaminos();
    	assertEquals(2, caminos.size(), "Los caminos no se guardaron bien" );
	
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
