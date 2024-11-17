package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import caminosActividades.ActividadRecurso;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Examen;
import controllers.Inscriptor;
import controllers.LearningPathSystem;
import marcadoresActividades.calificadorExamen;
import marcadoresActividades.marcadorAR;
import senders.ExamenSender;
import traductores.TraductorEstudiante;
import usuarios.Estudiante;
import usuarios.Profesor;

public class TraductorEstudianteTest 
{
	private static String idEstudiante;
	private static String idActividad;
	private static String idActividad3;
	private static String idCamino;
	private static CaminoAprendizaje camino;
	private static ActividadRecurso AR;
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
			
			Examen examen = new Examen("Examen Test", "Esto es una tarea sobre cuervos", objetivos, 1.5, 20, 
					fechaLim, true, 3, preguntasString, profesor.getID(), camino, 0);
			String idActividadExamen=examen.getId();

			estudiante= new Estudiante("Trey999", "Trey123", "Trey Clover");
			LPS.addEstudiante(estudiante);
			
			idEstudiante= TraductorEstudiante.getIDfromLogin("Trey999");
			
			AR= new ActividadRecurso("Lectura Test", "Esto es una lectura de tipos de variables", objetivos, 1.5, 20, fechaLim, 
					true, "https://www.w3schools.com/python/python_variables.asp", "Leer el articulo.", profesor.getID(), camino, 0);
			idActividad=AR.getID();
			
			ActividadRecurso ARNoOblig= new ActividadRecurso("Lectura Test2", "Esto es una lectura de tipos de variables", objetivos, 1.5, 20, fechaLim, 
					false, "https://www.w3schools.com/python/python_variables.asp", "Leer el articulo.", profesor.getID(), camino, 0);
			idActividad3=ARNoOblig.getID();

			respuestas=new HashMap<String, String>();
			respuestas.put("¿Cuales son las caracteristicas princiaples de los cuervos?", "Que son increibles");
			respuestas.put("¿Que otro animal es parecido e igual de increible que los cuervos?", "Ninguno alcanza la grandeza de los cuervos");
			respuestas.put("¿Por que son mejores los cuervos que otros pajaros?", "Porque es una verdad universal");
		
    		Inscriptor.inscribirseCamino(idCamino, idEstudiante);
    		Inscriptor.iniciarActivad(idCamino, idActividad, idEstudiante);
    		marcadorAR.marcarARTerminado(idCamino, idActividad, idEstudiante);
    		Inscriptor.iniciarActivad(idCamino, idActividadExamen, idEstudiante);
    		ExamenSender.sendEnvioExamen(idCamino, idActividadExamen, idEstudiante, respuestas);
    		calificadorExamen.calificarExamen(idCamino, idActividadExamen, idEstudiante, 1);
    		Inscriptor.iniciarActivad(idCamino, idActividad3, idEstudiante);
			

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
   public void getNombreFromIDTest()
   {
	  try 
	  {
		assertEquals("Trey Clover", TraductorEstudiante.getNombrefromID(idEstudiante), "No retorno el nombre correcto");
	  } 
	  catch (Exception e) 
	  {
		e.printStackTrace();
		fail("No deberia sacar error: "+ e.getMessage());
	  }
   }
   
   @Test
   public void verActividadActivaTest()
   {
	   try 
	   {
		   String act= TraductorEstudiante.verActividadActiva(idEstudiante);
		   assertTrue(act.contains("El maravilloso mundo de los cuervos"), "No dice camino");
		   assertTrue(act.contains("Lectura Test2"), "No dice actividad");
	   } 
	   catch (Exception e) 
	   {
		e.printStackTrace();
		fail("No deberia sacar error: "+ e.getMessage());
	   }
   }
   
   @Test
   public void getAvancesCaminosTest()
   {
	   try
	   {
		   HashMap<String,String> avances=TraductorEstudiante.getAvancesCaminos(idEstudiante);
		   assertEquals("50.0%", avances.get("El maravilloso mundo de los cuervos"), "No calculo porcentajes bien");
	   }
	   catch (Exception e) 
	   {
		e.printStackTrace();
		fail("No deberia sacar error: "+ e.getMessage());
	   }
	   
	   try
	   {
		   TraductorEstudiante.getAvancesCaminoIndividual(idEstudiante, idCamino);
	   }
	   catch (Exception e)
	   {
			e.printStackTrace();
			fail("No deberia sacar error: "+ e.getMessage());
	   }
   }

}
