package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import caminosActividades.OpcionQuiz;
import caminosActividades.PreguntaQuiz;
import caminosActividades.Quiz;
import controllers.LearningPathSystem;
import editores.EditorExamen;
import editores.EditorQuiz;
import traductores.TraductorEstudiante;
import usuarios.Estudiante;
import usuarios.Profesor;

public class EditorQuizTest 
{
	private static LearningPathSystem LPS; 
	private static String idCamino;
	private static String idActividad;
	private static ActividadRecurso AR;
	private static CaminoAprendizaje camino;
	private static Profesor profesor;
	private static Quiz quiz;
	private static String idActividadSecundaria;
	private static PreguntaQuiz pregunta3;
	
	@BeforeEach
	void init( ) throws Exception
    {	
		LPS.resetLPS();
		LearningPathSystem LPS =LearningPathSystem.getInstance();

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
				
		
		
		//Creo el quiz
		List<PreguntaQuiz> preguntas = new LinkedList<PreguntaQuiz>();
		OpcionQuiz opcion1 = new OpcionQuiz("int", "Porque es un entero", true);
		OpcionQuiz opcion2 = new OpcionQuiz("double", "Porque no es un decimal", false);
		OpcionQuiz opcion3 = new OpcionQuiz("float", "Porque no es un decimal", false);
		OpcionQuiz opcion4 = new OpcionQuiz("string", "Porque es un numero", false);

		PreguntaQuiz pregunta1= new PreguntaQuiz("Si quiero representar el número de vacas que tengo, que tipo de variable debería usar?", 1, 4);
		pregunta1.setOpcion(1, opcion1);
		pregunta1.setOpcion(2, opcion2);
		pregunta1.setOpcion(3, opcion3);
		pregunta1.setOpcion(4, opcion4);
		preguntas.add(pregunta1);
		
		OpcionQuiz opcion1B = new OpcionQuiz("Paloma", "Porque son sucias", false);
		OpcionQuiz opcion2B = new OpcionQuiz("Cuervo", "Porque son hermosos e inteligentes", true);
		OpcionQuiz opcion3B = new OpcionQuiz("Pechirrojo", "Tierno pero es muy pequeño", false);
		OpcionQuiz opcion4B = new OpcionQuiz("Vaca", "La vaca no es un pajaro", false);
		
		PreguntaQuiz pregunta2= new PreguntaQuiz("Cual es el mejor pajaro?", 2, 4);
		pregunta2.setOpcion(1, opcion1B);
		pregunta2.setOpcion(2, opcion2B);
		pregunta2.setOpcion(3, opcion3B);
		pregunta2.setOpcion(4, opcion4B);
		preguntas.add(pregunta2);
		
		pregunta3= new PreguntaQuiz("Si quiero representar el número de cuervos, no vacas, que tengo, que tipo de variable debería usar?", 1, 4);
		pregunta3.setOpcion(1, opcion1);
		pregunta3.setOpcion(2, opcion2);
		pregunta3.setOpcion(3, opcion3);
		pregunta3.setOpcion(4, opcion4);		

		quiz= new Quiz("Quiz de asignación variables", "Esto es un quiz donde te preguntan que tipo de variable es más indicado", 
				objetivos, 1.5, 15, fechaLim, false, 3, preguntas, profesor.getID(), camino, false, 0);
		idActividad=quiz.getId();


		ActividadRecurso AR= new ActividadRecurso("Lectura Test", "Esto es una lectura de tipos de variables", objetivos, 1.5, 20, fechaLim, 
				false, "https://www.w3schools.com/python/python_variables.asp", "Leer el articulo.", profesor.getID(), camino, 0);
		idActividadSecundaria=AR.getID();
				
	}
	
    @AfterEach
    void tearDown( ) throws Exception
    {
    }
    
    @Test
    public void editAddDelActividadSigFracaso()
    {
    	try 
    	{
			EditorQuiz.editAddActividadSigFracaso(idCamino, idActividadSecundaria, "Lectura Test");
			fail("Deberia sacar error por tipo de actividad equivocado");
		} 
    	catch (Exception e) 
    	{
    		assertEquals("La actividad pasada no fue un quiz.", e.getMessage(), "No saco la exception correcta.");
    	}
    	
       	try 
    	{
       		EditorQuiz.editAddActividadSigFracaso(idCamino, idActividad, "Lectura Test");
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}	
       	
       	assertTrue(quiz.getActividadesSigFracaso().contains("Lectura Test"), "No se añadio bien la actividad siguiente fracaso");
     	assertEquals(2, camino.getVersion(), "No se guardo bien la version");

     	
       	try 
    	{
       		EditorQuiz.editDelActividadSigFracaso(idCamino, idActividadSecundaria, 1);
			fail("Deberia sacar error por tipo de actividad equivocado");
		} 
    	catch (Exception e) 
    	{
    		assertEquals("La actividad pasada no fue un quiz.", e.getMessage(), "No saco la exception correcta.");
    	}
       	try 
    	{
       		EditorQuiz.editDelActividadSigFracaso(idCamino, idActividad, 1);
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
    	
    	assertFalse(quiz.getActividadesSigFracaso().contains("Lectura Test"), "No se borro bien la actividad siguiente fracaso");
     	assertEquals(3, camino.getVersion(), "No se guardo bien la version");
    }
	
   @Test
   public void editAddDelPreguntaTest()
   {
	   	try 
	   	{
	   		EditorQuiz.editAddPregunta(pregunta3, idCamino, idActividadSecundaria);
			fail("Deberia sacar error por tipo de actividad equivocado");
		} 
	   	catch (Exception e) 
	   	{
	   		assertEquals("La actividad pasada no fue un quiz.", e.getMessage(), "No saco la exception correcta.");
	   	}
	   	
	    try 
	   	{
	    	EditorQuiz.editAddPregunta(pregunta3,  idCamino, idActividad);
	   	} 
	   	catch (Exception e) 
	   	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}	
      	
      	assertTrue(quiz.getPreguntas().contains(pregunta3), "No se añadio bien la pregunta");
    	assertEquals(2, camino.getVersion(), "No se guardo bien la version");

	    	
	    try 
	   	{
	    	EditorQuiz.editDelPregunta(3, idCamino, idActividadSecundaria);
			fail("Deberia sacar error por tipo de actividad equivocado");
		} 
	   	catch (Exception e) 
	   	{
	   		assertEquals("La actividad pasada no fue un quiz.", e.getMessage(), "No saco la exception correcta.");
	   	}
      	try 
      	{
      		EditorQuiz.editDelPregunta(3, idCamino, idActividad);
		} 
	   	catch (Exception e) 
	   	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
      	
	   	assertFalse(quiz.getPreguntas().contains(pregunta3), "No se borro bien la  pregunta");
	    assertEquals(3, camino.getVersion(), "No se guardo bien la version");
   }
   
   
   @Test
   public void editCalificacionMinTest()
   {
	   	try 
    	{
	   		EditorQuiz.editCalificacionMin(idCamino, idActividadSecundaria, 4);
			fail("Deberia sacar error por tipo de actividad equivocado");
		} 
    	catch (Exception e) 
    	{
    		assertEquals("La actividad pasada no fue un quiz.", e.getMessage(), "No saco la exception correcta.");
    	}
    
    	try 
    	{
    		EditorQuiz.editCalificacionMin(idCamino, idActividad, 4);
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
    	
    	assertEquals(4, quiz.getCalificacionMin(), "No se edito bien las instrucciones.");
     	assertEquals(2, camino.getVersion(), "No se guardo bien la version");
     	
   }

}
