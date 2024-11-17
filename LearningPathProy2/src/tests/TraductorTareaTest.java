package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import caminosActividades.ActividadRecurso;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Tarea;
import controllers.Inscriptor;
import controllers.LearningPathSystem;
import senders.ExamenSender;
import senders.TareaSender;
import traductores.TraductorEstudiante;
import traductores.TraductorExamen;
import traductores.TraductorTarea;
import usuarios.Estudiante;
import usuarios.Profesor;

public class TraductorTareaTest 
{
	private static String idEstudiante;
	private static String idActividad;
	private static String idCamino;
	private static CaminoAprendizaje camino;
	private static Tarea tarea;
	private static LearningPathSystem LPS;
	private static Profesor profesor;
	private static String idActividadSecundaria;
	private static Estudiante estudiante;
	

	@BeforeEach
 	void init( ) 
    {
		try
		{
			LearningPathSystem.resetLPS(); 
			LPS=LearningPathSystem.getInstance();
			
			profesor = new Profesor("Aizawa999", "Aizawa 123", "Aizawa Shouta");
			LPS.addProfesor(profesor);
						
			List<String> objetivos = new LinkedList<String>();
			objetivos.add("Saber la diferencia entre distintos tipos de cuervos.");
			objetivos.add("Poder sustentar porque los cuervos son tan increibles.");
			objetivos.add("Volverse fan de los cuervos.");
			
			camino = new CaminoAprendizaje("El maravilloso mundo de los cuervos", "Esto es un curso que te enseña lo increible que son los cuervos",
					objetivos, 1.5, profesor.getID());
	
			LPS.addCamino(camino);
			idCamino=camino.getID();

	
			int[] fechaLim= new int[]{0,1,0};
			
			tarea = new Tarea("Tarea Test", "Esto es una tarea sobre cuervos", objetivos, 1.5, 20, 
					fechaLim, true, "Tome fotos de cuervos.", profesor.getID(), camino, 0);
			idActividad=tarea.getID();
	
			estudiante= new Estudiante("Trey999", "Trey123", "Trey Clover");
			Estudiante estudiante2= new Estudiante("Cater999", "Cater123", "Cater Diamond");
			Estudiante estudiante3= new Estudiante("Riddle999", "Riddle123", "Riddle Rosehearts");
			LPS.addEstudiante(estudiante);
			LPS.addEstudiante(estudiante2);
			LPS.addEstudiante(estudiante3);
			
			idEstudiante= TraductorEstudiante.getIDfromLogin("Trey999");
			String idEstudiante2= TraductorEstudiante.getIDfromLogin("Cater999");
			String idEstudiante3= TraductorEstudiante.getIDfromLogin("Riddle999");
			
			
			Inscriptor.inscribirseCamino(idCamino, idEstudiante2);
			Inscriptor.inscribirseCamino(idCamino, idEstudiante3);
			Inscriptor.inscribirseCamino(idCamino, idEstudiante);
			Inscriptor.iniciarActivad(idCamino, idActividad, idEstudiante);
			Inscriptor.iniciarActivad(idCamino, idActividad, idEstudiante2);
			Inscriptor.iniciarActivad(idCamino, idActividad, idEstudiante3);
			TareaSender.addMetodoEntregaTarea(idCamino, idActividad, idEstudiante2, "Por un sueño");
			TareaSender.addMetodoEntregaTarea(idCamino, idActividad, idEstudiante, "Atado a la pata de un cuervo");
			
    	}
		catch (Exception e) 
		{
			fail("Error en el setup: "+e.getMessage());
		}
    }
	
    @AfterEach
    void tearDown( ) throws Exception
    {
    }
    
    @Test
    public void retornarInstruccionesTest()
    {
    	try 
    	{
			assertEquals("Tome fotos de cuervos.", TraductorTarea.retornarInstrucciones(idCamino, idActividad),
				"No se retorno las instrudcciones bien");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
    }
    
    @Test
    public void retornarListaEstudiantesEnviosTest()
    {
    	try 
    	{
			assertEquals(2, TraductorTarea.retornarListaEstudiantesEntrega(idCamino, idActividad).size(),
					"No retorno el numero de estudiantes con envios correcto");
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
    }
    
    @Test
    public void retornarEnvioIndividualTest()
    {    	
    	try 
    	{
    		assertEquals("Atado a la pata de un cuervo", TraductorTarea.retornarEntregaIndividual(idCamino, idActividad, idEstudiante),
    				"No se retorno el metodo de entrega bien");
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
    }

}
