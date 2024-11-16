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
import caminosActividades.OpcionQuiz;
import caminosActividades.PreguntaQuiz;
import caminosActividades.Tarea;
import controllers.Inscriptor;
import controllers.LearningPathSystem;
import datosEstudiantes.DatosEstudianteActividad;
import datosEstudiantes.DatosEstudianteTarea;
import senders.TareaSender;
import traductores.TraductorEstudiante;
import usuarios.Estudiante;
import usuarios.Profesor;

public class TareaSenderTest 
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
    public void addMetodoEntregaTareaTes()
    {
    	try 
    	{
			TareaSender.addMetodoEntregaTarea(idCamino, idActividadSecundaria, idEstudiante, "Mandado en una botella.");
			fail("Deberia sacar error por tipo de actividad incorrecto");
		} 
    	catch (Exception e) 
    	{
			assertEquals("El id pasado no es el de una tarea", e.getMessage(), "Exception incorrecta");
		}
    	
    	try 
    	{
			TareaSender.addMetodoEntregaTarea(idCamino, idActividad, idEstudiante, "Mandado en una botella.");
			fail("Deberia sacar error por no haber iniciado la actividad");
		} 
    	catch (Exception e) 
    	{
			assertEquals("No se ha inscrito a este camino", e.getMessage(), "Exception incorrecta");
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
			TareaSender.addMetodoEntregaTarea(idCamino, idActividad, idEstudiante, "Mandado en una botella.");
			fail("Deberia sacar error por no haber iniciado la actividad");
		} 
    	catch (Exception e) 
    	{
			assertEquals("No se ha iniciado esta actividad", e.getMessage(), "Exception incorrecta");
		}
    	
    	try
    	{
    		Inscriptor.iniciarActivad(idCamino, idActividad, idEstudiante);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		fail("Fallo por errores en las inscripciones. Chequear otras pruebas: "+e.getMessage());
    	}
    	
    	try
    	{
			TareaSender.addMetodoEntregaTarea(idCamino, idActividad, idEstudiante, "Mandado en una botella.");
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
    	
    	assertEquals("Mandado en una botella.", datoEst.getMetodoEntrega(), "No se añadio bien el metodo de entrega"); 
    	assertEquals(DatosEstudianteActividad.ENVIADO, datoEst.getEstado(), "No se actualizo bien el estado del dato del estudiante"); 
    	assertEquals(false, estudiante.isActividadActiva(), "No se puso false actividad activa" );

    	
    }

}
