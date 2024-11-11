package tests;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controllers.LearningPathSystem;
import creadores.CreadorEstudiante;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;


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
    public void crearEstudianteTest()
    {
    	try 
    	{
			CreadorEstudiante.crearEstudiante("Trey999", "Trey123", "Trey Clover");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	LearningPathSystem LPS = LearningPathSystem.getInstance();
    	
    	assertEquals(1, LPS.getEstudiantes().size(), "No se guardo el estudiante en el LPS");
    }

}
