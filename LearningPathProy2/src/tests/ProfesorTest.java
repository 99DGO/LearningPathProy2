package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import controllers.LearningPathSystem;
import creadores.CreadorEstudiante;
import creadores.CreadorProfesor;
import persistencia.CentralPersistencia;

public class ProfesorTest 
{
	@BeforeAll
 	static void init( ) throws Exception
    {
 		
    }
 	
    @AfterEach
    void tearDown( ) throws Exception
    {
    }

    
    @Test
    @Order(1)
    public void crearEstudianteTest()
    {
    	try 
    	{
			CreadorProfesor.crearProfesor("Divus99", "Divus123", "Divus Crewel");
			CreadorProfesor.crearProfesor("Dire999", "Dire123", "Dire Crowley");

		} catch (Exception e) 
    	{
			e.printStackTrace();
			fail("Deberia poder crearse los profesores "+e.getMessage());
		}
    	LearningPathSystem LPS = LearningPathSystem.getInstance();
    	
    	assertEquals(2, LPS.getProfesores().size(), "No se guardaron los estudiante en el LPS");
    }
    
    @Test
    @Order(2)
    public void guardarEstudiante()
    {
    	try 
    	{
    		CentralPersistencia.guardarProfesores(true);

		} catch (Exception e) 
    	{
			e.printStackTrace();
			fail("No deberia sacar error "+e.getMessage());
		}
    }

}
