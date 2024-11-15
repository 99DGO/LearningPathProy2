package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import caminosActividades.ActividadRecurso;
import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;
import controllers.Reseñador;
import creadores.CreadorCamino;
import traductores.TraductorCamino;
import usuarios.Profesor;

public class ReseñadorTest 
{

	private static LearningPathSystem LPS;
	private static List<String> objetivos;
	private static String idCamino;
	private static String idActividad;
	private static CaminoAprendizaje camino;
	private static ActividadRecurso AR;

	
	@BeforeEach
	void init()
	{
		try
		{
			LearningPathSystem.resetLPS(); 
			LPS=LearningPathSystem.getInstance();
			int[] fechaLim=new int[] {0,1,0};
			
			Profesor profesor = new Profesor("Aizawa999", "Aizawa 123", "Aizawa Shouta");
			LPS.addProfesor(profesor);
			
			objetivos = new LinkedList<String>();
			objetivos.add("Saber la diferencia entre distintos tipos de cuervos.");
			objetivos.add("Poder sustentar porque los cuervos son tan increibles.");
			objetivos.add("Volverse fan de los cuervos.");
			
			
			CreadorCamino.crearCaminoCero("El maravilloso mundo de los cuervos", 
					"Esto es un curso que te enseña lo increible que son los cuervos", 
					objetivos, 0.5, profesor.getID());
			
			idCamino=TraductorCamino.getIDfromNombre("El maravilloso mundo de los cuervos");
			camino = LPS.getCaminoIndividual(idCamino);

			//Creo las actividades para añadirlas al camino original para que luego sean clonadas
			AR = new ActividadRecurso("Lectura Cuervos", "Lectura de cuervos", objetivos, 1, 20, fechaLim, 
					false, "https://www.audubon.org/es/guia-de-aves/ave/cuervo-comun", "Lee el articulo", profesor.getID(), camino, 0);
			
			idActividad=AR.getId();
			
		}
		catch (Exception e)
		{
			fail("Error en el setup: "+e.getMessage());

		}
	}

	@Test
	public void dejarRatingCaminoTest()
	{
		try
		{
			Reseñador.dejarRatingCamino(5, idCamino);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
		
		assertEquals(5, camino.getRating(), "No se sumo bien el rating al camino");
		
		try
		{
			Reseñador.dejarRatingCamino(4, idCamino);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
		
		assertEquals(4.5, camino.getRating(), "No se sumo bien el rating al camino");
		assertEquals(2, camino.getRatingsTotales(), "No se añadio bien el rating al camino");

	}
	
	@Test
	public void dejarReseniaTest()
	{
		try 
		{
			Reseñador.dejarResenia("Increible lectura! Pero muy larga.", idCamino, idActividad);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
	
		assertTrue(AR.getResenias().contains("Increible lectura! Pero muy larga."), "No se añadio bien el contenido de la reseña");
		assertEquals(1, AR.getResenias().size(), "No se añadio bien la reseña");
	}
	
	@Test
	public void dejarRatingActividadTest()
	{
		try 
		{
			Reseñador.dejarRatingActividad(5, idCamino, idActividad);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
		
		assertEquals(5, AR.getRating(), "No se sumo bien el rating a la actividad");
		
		try 
		{
			Reseñador.dejarRatingActividad(4, idCamino, idActividad);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
		
		assertEquals(4.5, AR.getRating(), "No se sumo bien el rating a la actividad");
		assertEquals(2, AR.getRatingsTotales(), "No se añadio bien el rating a la actividad");


	}

}
