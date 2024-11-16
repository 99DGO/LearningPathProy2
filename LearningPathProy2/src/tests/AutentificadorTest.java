package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.Autentificador;
import controllers.LearningPathSystem;
import usuarios.Estudiante;
import usuarios.Profesor;

public class AutentificadorTest 
{
	private static Estudiante estudiante;
	private static LearningPathSystem LPS; 
	private static Profesor profesor;
	private static Autentificador autenti;


	@BeforeEach
	void init( ) throws Exception
    {	
		LearningPathSystem.resetLPS();
		
		LPS=LearningPathSystem.getInstance();
		autenti=Autentificador.getInstance(LPS);
				
		estudiante= new Estudiante("Trey999", "Trey123", "Trey Clover");
		LPS.addEstudiante(estudiante);
		
		profesor = new Profesor("Gojo999", "Gojo123", "Gojo Satoru");
		LPS.addProfesor(profesor);
    }
	
    @AfterEach
    void tearDown( ) throws Exception
    {
    }
    
	@Test
	public void autentificarTest()
	{
		try 
		{
			autenti.autentificar("Gojo999", "Gojo123");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No deberia sacar error al autentificar profesor: "+e.getMessage());
		}
		
		try 
		{
			autenti.autentificar("Trey999", "Trey123");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No deberia sacar error al autentificar estudiante: "+e.getMessage());
		}
		
		try
		{
			autenti.autentificar("Trey999", "Gojo123");
			fail("Deberia sacar error por contraseña incorrecta");
		}
		catch (Exception e)
		{
			assertEquals("Usuario o contraseña incorrecta. \n", e.getMessage(), "No saco excepcion correcta.");
		}
		
		try
		{
			autenti.autentificar("Random", "Gojo123");
			fail("Deberia sacar error por usuario no encontrado");
		}
		catch (Exception e)
		{
			assertEquals("Usuario no encontrado. \n", e.getMessage(), "No saco excepcion correcta.");
		}
	}
}
