package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.LearningPathSystem;
import creadores.CreadorEstudiante;
import creadores.CreadorProfesor;
import traductores.TraductorEstudiante;
import traductores.TraductorProfesor;
import usuarios.Estudiante;
import usuarios.Profesor;

public class CreadoresUsuariosTests 
{
	private static LearningPathSystem LPS;

	@BeforeEach
	void init( ) throws Exception
	{
		LearningPathSystem.resetLPS();
		LPS=LearningPathSystem.getInstance();
	}
	
	@AfterEach
	void tearDown( ) throws Exception
	{
		
	}
	
	@Test
	public void crearEstudianteTest()
	{
		String idEst=null;
		Estudiante estudiante;
		
		try 
		{
			CreadorEstudiante.crearEstudiante("Trey999", "Trey123", "Trey Clover");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
		
		assertEquals(1, LPS.getEstudiantes().size(), "No se guardo el estudiante en el LPS");
		
		try
		{
			idEst=TraductorEstudiante.getIDfromLogin("Trey999");	
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("No deberia sacar error. No se guardo bien el login: "+e.getMessage());
		}
		
		estudiante=LPS.getEstudianteIndividual(idEst);
		
		assertEquals("Trey123", estudiante.getPassword(), "No se guardo bien la contraseña");
		assertEquals("Trey Clover", estudiante.getNombre(), "No se guardo bien el nombre");

		try 
		{
			CreadorEstudiante.crearEstudiante("Trey999", "Trey123", "Trey Clover");
			fail("Deberia sacar error por tener el mismo login.");
		} 
		catch (Exception e) 
		{
			assertEquals("Ya existe un estudiante con ese login", e.getMessage(), "Deberia sacar error de tener el mismo login.");
		}
	}
	
	@Test
	public void crearProfesorTest()
	{
		String idProf=null;
		Profesor profesor;
		
		try 
		{
			CreadorProfesor.crearProfesor("Gojo999", "Gojo123", "Gojo Satoru");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
		
		assertEquals(1, LPS.getProfesores().size(), "No se guardo el profesor en el LPS");
		
		try
		{
			idProf=TraductorProfesor.getIDfromLogin("Gojo999");	
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("No deberia sacar error. No se guardo bien el login: "+e.getMessage());
		}
		
		profesor=LPS.getProfesorIndividual(idProf);
		
		assertEquals("Gojo123", profesor.getPassword(), "No se guardo bien la contraseña");
		assertEquals("Gojo Satoru", profesor.getNombre(), "No se guardo bien el nombre");

		
		try 
		{
			CreadorProfesor.crearProfesor("Gojo999", "Gojo123", "Gojo Satoru");
			fail("Deberia sacar error por tener el mismo login.");
		} 
		catch (Exception e) 
		{
			assertEquals("Ya existe un profesor con ese login", e.getMessage(), "Deberia sacar error de tener el mismo login.");
		}
	}
}
