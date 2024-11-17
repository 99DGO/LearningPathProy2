package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import caminosActividades.CaminoAprendizaje;
import controllers.CreatorValidator;
import controllers.LearningPathSystem;
import usuarios.Profesor;

public class CreatorValidatorTest 
{
	
	private static LearningPathSystem LPS; 
	private static String idProf;
	private static String idProf2;
	private static String idCamino;
	private static CaminoAprendizaje camino;
	
	@BeforeEach
	void init( ) throws Exception
    {		
		LearningPathSystem.resetLPS(); 
		LPS=LearningPathSystem.getInstance();
		
		Profesor profesor = new Profesor("Aizawa999", "Aizawa123", "Aizawa Shouta");
		LPS.addProfesor(profesor);
		idProf=profesor.getID();
		
		Profesor profesor2 = new Profesor("Gojo999", "Gojo123", "Gojo Satoru");
		LPS.addProfesor(profesor2);
		idProf2=profesor2.getID();
		
		List<String> objetivos = new LinkedList<String>();
		objetivos.add("Saber la diferencia entre distintos tipos de cuervos.");
		objetivos.add("Poder sustentar porque los cuervos son tan increibles.");
		objetivos.add("Volverse fan de los cuervos.");
		
		camino = new CaminoAprendizaje("El maravilloso mundo de los cuervos", "Esto es un curso que te enseña lo increible que son los cuervos",
				objetivos, 1.5, profesor.getID());
		
		LPS.addCamino(camino);

		idCamino = camino.getID();
	
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }
	
    @Test
    public void profCreadorCheckerTest()
    {
    	try 
    	{
			CreatorValidator.profCreadorChecker(idCamino, idProf);
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			fail("Si deberia ser el profesor el creador");
		}
    	
    	try 
    	{
			CreatorValidator.profCreadorChecker(idCamino, idProf2);
			fail("No deberia ser el profesor el creador");
		} 
    	catch (Exception e) 
    	{
    		assertEquals("El profesor no es creador", e.getMessage(), "No saco la exception correcta.");
		}
    }
}
