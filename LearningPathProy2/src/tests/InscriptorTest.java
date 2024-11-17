package tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import caminosActividades.ActividadRecurso;
import caminosActividades.CaminoAprendizaje;
import controllers.Inscriptor;
import controllers.LearningPathSystem;
import creadores.CreadorAR;
import creadores.CreadorCamino;
import creadores.CreadorEstudiante;
import creadores.CreadorProfesor;
import traductores.TraductorCamino;
import traductores.TraductorEstudiante;
import traductores.TraductorProfesor;
import usuarios.Estudiante;
import usuarios.Profesor;

public class InscriptorTest 
{
	private static LearningPathSystem LPS; 
	private static String idEstudiante;
	private static String idCamino;
	private static String idActividad;
	private static ActividadRecurso AR;
	private static Estudiante estudiante;
	private static CaminoAprendizaje camino;
	
	@BeforeEach
	void init( ) throws Exception
    {		
		LearningPathSystem.resetLPS(); 
		LPS=LearningPathSystem.getInstance();
		
		Profesor profesor = new Profesor("Aizawa999", "Aizawa 123", "Aizawa Shouta");
		LPS.addProfesor(profesor);
		
		List<String> objetivos = new LinkedList<String>();
		objetivos.add("Saber la diferencia entre distintos tipos de cuervos.");
		objetivos.add("Poder sustentar porque los cuervos son tan increibles.");
		objetivos.add("Volverse fan de los cuervos.");
		
		camino = new CaminoAprendizaje("El maravilloso mundo de los cuervos", "Esto es un curso que te enseña lo increible que son los cuervos",
				objetivos, 1.5, profesor.getID());
		
		LPS.addCamino(camino);

		idCamino = camino.getID();
		

		List<String> preguntasString= new LinkedList<String>();
		preguntasString.add("¿Cuales son las caracteristicas princiaples de los cuervos?");
		preguntasString.add("¿Que otro animal es parecido e igual de increible que los cuervos?");
		preguntasString.add("¿Por que son mejores los cuervos que otros pajaros?");
		
		int [] fechaLim= new int[] {0,0,0};
		
		AR= new ActividadRecurso("Lectura Test", "Esto es una lectura de tipos de variables", objetivos, 1.5, 20, fechaLim, 
				false, "https://www.w3schools.com/python/python_variables.asp", "Leer el articulo.", profesor.getID(), camino, 0);
		idActividad=AR.getID();
		
		estudiante= new Estudiante("Trey999", "Trey123", "Trey Clover");
		LPS.addEstudiante(estudiante);
		
		idEstudiante= TraductorEstudiante.getIDfromLogin("Trey999");
    } 
	
    @AfterEach
    void tearDown( ) throws Exception
    {
    }
	
	@Test
	public void inscribirseCaminoTest()
	{
		try 
		{
			Inscriptor.inscribirseCamino(idCamino, idEstudiante);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No odeberia sacar error: "+e.getMessage());
		}
		
		assertEquals(1, camino.getActividades().getFirst().getDatosEstudiantes().size(), "No se creo bien el dato del estudiante en la actividad.");
	}
	
	@Test
	public void iniciarActividadTest()
	{
		
		try 
		{
			Inscriptor.iniciarActivad(idCamino, idActividad, idEstudiante);
			fail("Deberia sacar error de que ya que no se ha inscrito al camino");
		} 
		catch(Exception e)
		{
			assertEquals("No estas inscrito en este camino", e.getMessage(), "No saco la exception correcta");
		}
		
		try 
		{
			Inscriptor.inscribirseCamino(idCamino, idEstudiante);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No odeberia sacar error: "+e.getMessage());
		}
		
		try 
		{
			Inscriptor.iniciarActivad(idCamino, idActividad, idEstudiante);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No odeberia sacar error: "+e.getMessage());
		}
		
		assertEquals(AR.getId(), estudiante.getIdActividadActiva(), "No se guardo el id de actividad activa correcto");
		assertEquals(true, estudiante.isActividadActiva(), "No se puso el booleano de id de actividad activa");

		
		try 
		{
			Inscriptor.iniciarActivad(idCamino, idActividad, idEstudiante);
			fail("Deberia sacar error de que ya se inicio una actividad");
		} 
		catch(Exception e)
		{
			assertEquals("No se puede iniciar una nueva actividad porque ya hay una iniciada", e.getMessage(), "No saco la exception correcta");
		}
	}

}
