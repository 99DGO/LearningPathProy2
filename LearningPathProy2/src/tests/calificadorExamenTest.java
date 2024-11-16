package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import caminosActividades.ActividadRecurso;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Examen;
import controllers.Inscriptor;
import controllers.LearningPathSystem;
import datosEstudiantes.DatosEstudianteActividad;
import datosEstudiantes.DatosEstudianteExamen;
import marcadoresActividades.calificadorExamen;
import senders.ExamenSender;
import traductores.TraductorEstudiante;
import usuarios.Estudiante;
import usuarios.Profesor;

public class calificadorExamenTest 
{
	private static String idEstudiante;
	private static String idActividad;
	private static String idCamino;
	private static CaminoAprendizaje camino;
	private static Examen examen;
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
			
			examen = new Examen("Tarea Test", "Esto es una tarea sobre cuervos", objetivos, 1.5, 20, 
					fechaLim, true, 3, preguntasString, profesor.getID(), camino, 0);
			idActividad=examen.getId();

			estudiante= new Estudiante("Trey999", "Trey123", "Trey Clover");
			LPS.addEstudiante(estudiante);
			
			idEstudiante= TraductorEstudiante.getIDfromLogin("Trey999");
			
			ActividadRecurso AR= new ActividadRecurso("Lectura Test", "Esto es una lectura de tipos de variables", objetivos, 1.5, 20, fechaLim, 
					false, "https://www.w3schools.com/python/python_variables.asp", "Leer el articulo.", profesor.getID(), camino, 0);
			idActividadSecundaria=AR.getID();
			
			respuestas=new HashMap<String, String>();
			respuestas.put("¿Cuales son las caracteristicas princiaples de los cuervos?", "Que son increibles");
			respuestas.put("¿Que otro animal es parecido e igual de increible que los cuervos?", "Ninguno alcanza la grandeza de los cuervos");
			respuestas.put("¿Por que son mejores los cuervos que otros pajaros?", "Porque es una verdad universal");
		
    		Inscriptor.inscribirseCamino(idCamino, idEstudiante);
    		Inscriptor.iniciarActivad(idCamino, idActividad, idEstudiante);

    	}
		catch (Exception e) 
		{
			fail("Error en el setup: "+e.getMessage());
		}
    }

	@Test
	public void calificarExamenAprobadoTest()
	{
		try 
		{
			calificadorExamen.calificarExamen(idCamino, idActividad, idEstudiante, 2);
			fail("Deberia sacar error por no haber hecho envio el estudiante ");
		} 
		catch (Exception e) 
		{
	    	assertEquals("El estudiante no ha hecho un envio para este examen.", e.getMessage(), "No saco la exception correcta");
		}
		
		try 
		{
			ExamenSender.sendEnvioExamen(idCamino, idActividad, idEstudiante, respuestas);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
    		fail("No deberia salir error: "+e.getMessage());
		}
    	
		try 
		{
			calificadorExamen.calificarExamen(idCamino, idActividad, idEstudiante, 2);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
		
	 	DatosEstudianteExamen datoEst=null;
		try 
		{
			datoEst = (DatosEstudianteExamen) examen.getDatoEstudianteIndFromIDEstudiante(idEstudiante);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
    		fail("No deberia salir error: "+e.getMessage());
		}
    	
    	assertEquals(2, datoEst.getCalificacion(), "No se actualizo la calificacion bien");
    	assertEquals(DatosEstudianteActividad.NOEXITOSO, datoEst.getEstado(), "No se actualizo el estado del dato del estudiante");
    	
	    	
	}
	
	@Test
	public void calificarExamenTest()
	{

		try 
		{
			ExamenSender.sendEnvioExamen(idCamino, idActividad, idEstudiante, respuestas);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
    		fail("No deberia salir error: "+e.getMessage());
		}
    	
		try 
		{
			calificadorExamen.calificarExamen(idCamino, idActividad, idEstudiante, 5);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
		
	 	DatosEstudianteExamen datoEst=null;
		try 
		{
			datoEst = (DatosEstudianteExamen) examen.getDatoEstudianteIndFromIDEstudiante(idEstudiante);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
    		fail("No deberia salir error: "+e.getMessage());
		}
    	
    	assertEquals(5, datoEst.getCalificacion(), "No se actualizo la calificacion bien");
    	assertEquals(DatosEstudianteActividad.EXITOSO, datoEst.getEstado(), "No se actualizo el estado del dato del estudiante");
    	
	    	
	}
}
