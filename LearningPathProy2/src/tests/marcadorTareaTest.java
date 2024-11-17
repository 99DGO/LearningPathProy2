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
import caminosActividades.Examen;
import caminosActividades.Tarea;
import controllers.Inscriptor;
import controllers.LearningPathSystem;
import datosEstudiantes.DatosEstudianteAR;
import datosEstudiantes.DatosEstudianteActividad;
import datosEstudiantes.DatosEstudianteTarea;
import marcadoresActividades.marcadorAR;
import marcadoresActividades.marcadorTarea;
import senders.TareaSender;
import traductores.TraductorEstudiante;
import usuarios.Estudiante;
import usuarios.Profesor;

public class marcadorTareaTest 
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
			
			estudiante= new Estudiante("Trey999", "Trey123", "Trey Clover");
			LPS.addEstudiante(estudiante);
			idActividad=tarea.getId();
			
			idEstudiante= TraductorEstudiante.getIDfromLogin("Trey999");
			
			ActividadRecurso AR= new ActividadRecurso("Lectura Test", "Esto es una lectura de tipos de variables", objetivos, 1.5, 20, fechaLim, 
					false, "https://www.w3schools.com/python/python_variables.asp", "Leer el articulo.", profesor.getID(), camino, 0);
			idActividadSecundaria=AR.getID();
			
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
    public void marcarExitosoTest()
    {
    	try 
    	{
			marcadorTarea.marcarTareaExito(idCamino, idActividadSecundaria, idEstudiante, true);
			fail("Deberia sacar error por tipo de actividad incorrecto");
		} 
    	catch (Exception e) 
    	{
			assertEquals("La actividad pasada no es una tarea.", e.getMessage(), "Exception incorrecta");
		}
    	
    	try 
    	{
			marcadorTarea.marcarTareaExito(idCamino, idActividad, idEstudiante, true);
			fail("Deberia sacar error por no haberse inscrito al camino");
		} 
    	catch (Exception e) 
    	{
			assertEquals("No se ha inscrito a este camino el estudiante", e.getMessage(), "Exception incorrecta");
		}
    	
    	try
    	{
    		Inscriptor.inscribirseCamino(idCamino, idEstudiante);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		fail("Fallo por errores en las inscripciones. Chequear otras pruebas: "+e.getMessage());
    	}
    	
    	try 
    	{
			marcadorTarea.marcarTareaExito(idCamino, idActividad, idEstudiante, true);
			fail("Deberia sacar error por no haber hecho un envio");
		} 
    	catch (Exception e) 
    	{
			assertEquals("El estudiante no ha hecho un envio para esta tarea.", e.getMessage(), "Exception incorrecta");
		}
    	
    	try
    	{
    		Inscriptor.iniciarActivad(idCamino, idActividad, idEstudiante);
    		TareaSender.addMetodoEntregaTarea(idCamino, idActividad, idEstudiante, "Mandado por medio de un sueño");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		fail("Fallo por errores en las inscripciones. Chequear otras pruebas: "+e.getMessage());
    	}
    	
    	try
    	{
			marcadorTarea.marcarTareaExito(idCamino, idActividad, idEstudiante, true);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		fail("No deberia salir error: "+e.getMessage());
    	}
    	
    	DatosEstudianteTarea datoEst=null;
		try 
		{
			datoEst = (DatosEstudianteTarea) tarea.getDatoEstudianteIndFromIDEstudiante(idEstudiante);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
    		fail("No deberia salir error: "+e.getMessage());
		}
    	
    	assertEquals(DatosEstudianteActividad.EXITOSO, datoEst.getEstado(), "No se actualizo bien el estado del dato del estudiante"); 
    }

    @Test
    public void marcarNoExitosoTest()
    {
    	try
    	{
    		Inscriptor.inscribirseCamino(idCamino, idEstudiante);
    		Inscriptor.iniciarActivad(idCamino, idActividad, idEstudiante);
    		TareaSender.addMetodoEntregaTarea(idCamino, idActividad, idEstudiante, "Mandado por medio de un sueño");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		fail("Fallo por errores en las inscripciones. Chequear otras pruebas: "+e.getMessage());
    	}

    	
    	try
    	{
			marcadorTarea.marcarTareaExito(idCamino, idActividad, idEstudiante, false);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		fail("No deberia salir error: "+e.getMessage());
    	}
    	
    	DatosEstudianteTarea datoEst=null;
		try 
		{
			datoEst = (DatosEstudianteTarea) tarea.getDatoEstudianteIndFromIDEstudiante(idEstudiante);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
    		fail("No deberia salir error: "+e.getMessage());
		}
    	
    	assertEquals(DatosEstudianteActividad.NOEXITOSO, datoEst.getEstado(), "No se actualizo bien el estado del dato del estudiante"); 

    }
}
