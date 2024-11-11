package tests;

import static org.junit.Assert.fail;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test; 

import controllers.Inscriptor;
import controllers.LearningPathSystem;
import creadores.CreadorAR;
import creadores.CreadorCamino;
import creadores.CreadorEstudiante;
import creadores.CreadorExamen;
import creadores.CreadorProfesor;
import persistencia.CentralPersistencia;
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
	
		
	
	}
}
