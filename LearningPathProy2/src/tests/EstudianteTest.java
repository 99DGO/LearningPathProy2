package tests;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controllers.LearningPathSystem;
import creadores.CreadorEstudiante;
import persistencia.CentralPersistencia;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class EstudianteTest 
{
 	@BeforeAll
 	static void init( ) throws Exception
    {
 		
    }
 	
    @BeforeEach
    void setUp( ) throws Exception
    {
        
    }
    
    @Test
    @Order(1)
    public void crearEstudianteTest()
    {
    	try 
    	{
			CreadorEstudiante.crearEstudiante("Trey999", "Trey123", "Trey Clover");
			CreadorEstudiante.crearEstudiante("Cater999", "Trey123", "Cater Diamond");

		} catch (Exception e) 
    	{
			e.printStackTrace();
			fail("Deberia poder crearse los estudiantes "+e.getMessage());
		}
    	LearningPathSystem LPS = LearningPathSystem.getInstance();
    	
    	assertEquals(2, LPS.getEstudiantes().size(), "No se guardaron los estudiante en el LPS");
    }
    
    @Test
    @Order(2)
    public void guardarEstudiante()
    {
    	try 
    	{
    		CentralPersistencia.guardarEstudiantes(true);

		} catch (Exception e) 
    	{
			e.printStackTrace();
			fail("No deberia sacar error "+e.getMessage());
		}
    }

}
