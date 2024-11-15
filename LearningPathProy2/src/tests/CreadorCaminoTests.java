package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;
import creadores.CreadorCamino;
import creadores.CreadorProfesor;
import traductores.TraductorCamino;
import traductores.TraductorProfesor;
import usuarios.Profesor;

public class CreadorCaminoTests 
{
	String profesorID;
	private static LearningPathSystem LPS;
	private static Profesor profesor;
	List<String> objetivos = new LinkedList<String>();
	
	@BeforeEach
	void init( ) throws Exception
    {
		LearningPathSystem.resetLPS();
		LPS=LearningPathSystem.getInstance();
		
		profesor = new Profesor("Gojo999", "Gojo123", "Gojo Satoru");
		LPS.addProfesor(profesor);
		profesorID=profesor.getID();
		
		objetivos.add("Saber la diferencia entre distintos tipos de cuervos.");
		objetivos.add("Poder sustentar porque los cuervos son tan increibles.");
		objetivos.add("Volverse fan de los cuervos.");

    }
	
    @AfterEach
    void tearDown( ) throws Exception
    {
    }
    
	@Test
	public void crearCaminoCeroTest()
	{
		String idCamino=null;
		
		try
		{
			CreadorCamino.crearCaminoCero("El maravilloso mundo de los cuervos", 
					"Esto es un curso que te enseña lo increible que son los cuervos", 
					objetivos, 0.5, 30, profesorID);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No deberia salir error: "+e.getMessage());
		}
		
		assertEquals(1, LPS.getCaminos().keySet().size(), "No se guardo bien el camino en el LPS.");
		
		try 
		{
			idCamino=TraductorCamino.getIDfromNombre("El maravilloso mundo de los cuervos");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No deberia salir error: "+e.getMessage());
		}
		
		
		CaminoAprendizaje camino = LPS.getCaminoIndividual(idCamino);
		
		if (camino==null)
		{
			fail("No se puede obtener el camino. No se guardaron bien el id y titulo del camino");
		}
		
		assertEquals(0.5, camino.getDificultad(), "No se creo bien la dificultad del camino.");
		assertEquals(30, camino.getDuracion(), "No se creo bien la duracion del camino.");
		assertEquals("Esto es un curso que te enseña lo increible que son los cuervos", camino.getDescripcion(), 
				"No se creo bien la descripcion del camino.");


	}

	@Test
	public void clonarCaminoTest()
	{
		String idCaminoOG= null; 
		String idCaminoClonado= null;
		
		try
		{
			CreadorCamino.crearCaminoCero("El maravilloso mundo de los cuervos", 
					"Esto es un curso que te enseña lo increible que son los cuervos", 
					objetivos, 0.5, 30, profesorID);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No deberia salir error: "+e.getMessage());
		}
		
		try 
		{
			idCaminoOG=TraductorCamino.getIDfromNombre("El maravilloso mundo de los cuervos");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No deberia salir error: "+e.getMessage());
		}
		
		try 
		{
			CreadorCamino.clonarCamino(idCaminoOG, "El maravilloso mundo de los zorros", profesorID);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No deberia salir error: "+e.getMessage());
		}
		
		assertEquals(2, LPS.getCaminos().keySet().size(), "No se guardo bien el camino clonado en el LPS.");
		
		try 
		{
			idCaminoClonado=TraductorCamino.getIDfromNombre("El maravilloso mundo de los zorros");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No deberia salir error al buscar el camino clonado por nombre: "+e.getMessage());
		}
		
		CaminoAprendizaje caminoClonado = LPS.getCaminoIndividual(idCaminoClonado);
		
		if (caminoClonado==null)
		{
			fail("No se puede obtener el camino. No se guardaron bien el id y titulo del camino clonado");
		}
		
		assertEquals(0.5, caminoClonado.getDificultad(), "No se creo bien la dificultad del camino clonado.");
		assertEquals(30, caminoClonado.getDuracion(), "No se creo bien la duracion del camino clonado.");
		assertEquals("Esto es un curso que te enseña lo increible que son los cuervos", caminoClonado.getDescripcion(), 
				"No se creo bien la descripcion del camino clonado.");
	}
}
