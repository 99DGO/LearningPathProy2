package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import caminosActividades.ActividadRecurso;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Encuesta;
import controllers.Inscriptor;
import controllers.LearningPathSystem;
import senders.EncuestaSender;
import traductores.TraductorEncuesta;
import traductores.TraductorEstudiante;
import usuarios.Estudiante;
import usuarios.Profesor;

public class TraductorEncuestaTest
{
	private static String idEstudiante;
	private static String idActividad;
	private static String idCamino;
	private static CaminoAprendizaje camino;
	private static Encuesta encuesta;
	private static LearningPathSystem LPS;
	private static Profesor profesor;
	private static HashMap<String, String> respuestas;
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
			
			List<String> preguntasString= new LinkedList<String>();
			preguntasString.add("¿Cuales son las caracteristicas princiaples de los cuervos?");
			preguntasString.add("¿Que otro animal es parecido e igual de increible que los cuervos?");
			preguntasString.add("¿Por que son mejores los cuervos que otros pajaros?");
			
			encuesta = new Encuesta("Encuesta Test", "Esto es una tarea sobre cuervos", objetivos, 1.5, 20, 
					fechaLim, true, preguntasString, profesor.getID(), camino, 0);
			idActividad=encuesta.getId();

			estudiante= new Estudiante("Trey999", "Trey123", "Trey Clover");
			Estudiante estudiante2= new Estudiante("Cater999", "Cater123", "Cater Diamond");
			Estudiante estudiante3= new Estudiante("Riddle999", "Riddle123", "Riddle Rosehearts");
			LPS.addEstudiante(estudiante);
			LPS.addEstudiante(estudiante2);
			LPS.addEstudiante(estudiante3);
			
			idEstudiante= TraductorEstudiante.getIDfromLogin("Trey999");
			String idEstudiante2= TraductorEstudiante.getIDfromLogin("Cater999");
			String idEstudiante3= TraductorEstudiante.getIDfromLogin("Riddle999");
			
			respuestas=new HashMap<String, String>();
			respuestas.put("¿Cuales son las caracteristicas princiaples de los cuervos?", "Que son increibles");
			respuestas.put("¿Que otro animal es parecido e igual de increible que los cuervos?", "Ninguno alcanza la grandeza de los cuervos");
			respuestas.put("¿Por que son mejores los cuervos que otros pajaros?", "Porque es una verdad universal");
			
			Inscriptor.inscribirseCamino(idCamino, idEstudiante2);
			Inscriptor.inscribirseCamino(idCamino, idEstudiante3);
			Inscriptor.inscribirseCamino(idCamino, idEstudiante);
			Inscriptor.iniciarActivad(idCamino, idActividad, idEstudiante);
			Inscriptor.iniciarActivad(idCamino, idActividad, idEstudiante2);
			Inscriptor.iniciarActivad(idCamino, idActividad, idEstudiante3);
			EncuestaSender.sendEnvioEncuesta(idCamino, idActividad, idEstudiante2, respuestas);
			EncuestaSender.sendEnvioEncuesta(idCamino, idActividad, idEstudiante, respuestas);
		
    	}
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("Error en el setup: "+e.getMessage());
		}
    }
	
	
    @AfterEach
    void tearDown( ) throws Exception
    {
    }
    
    @Test
    public void retornarPreguntasTest()
    {
    	try 
    	{
			assertEquals(3, TraductorEncuesta.retornarPreguntas(idCamino, idActividad).size(), "No retorno numero de preguntas correcto");
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
			assertEquals(2, TraductorEncuesta.retornarListaEstudiantesEnvios(idCamino, idActividad).size(), "No retorno el numero de estudiantes con envios correcto");
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
			TraductorEncuesta.retornarEnvioIndividual(idCamino, idActividad, idEstudiante);
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
    }

}
