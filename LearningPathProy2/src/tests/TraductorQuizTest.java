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
import caminosActividades.OpcionQuiz;
import caminosActividades.PreguntaQuiz;
import caminosActividades.Quiz;
import controllers.LearningPathSystem;
import traductores.TraductorEstudiante;
import traductores.TraductorQuiz;
import usuarios.Estudiante;
import usuarios.Profesor;

public class TraductorQuizTest
{
	private static LearningPathSystem LPS; 
	private static String idEstudiante;
	private static String idCamino;
	private static String idActividad;
	private static ActividadRecurso AR;
	private static Estudiante estudiante;
	private static CaminoAprendizaje camino;
	private static Profesor profesor;
	private static Quiz quiz;
	private static String idActividadSecundaria;
	private static HashMap<String, Integer> respuestasEquivocada;
	private static HashMap<String, Integer> respuestasPerfectas;
	private static PreguntaQuiz pregunta1;
	
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

		pregunta1= new PreguntaQuiz("Si quiero representar el número de vacas que tengo, que tipo de variable debería usar?", 1, 4);
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

		quiz= new Quiz("Quiz de asignación variables", "Esto es un quiz donde te preguntan que tipo de variable es más indicado", 
				objetivos, 1.5, 15, fechaLim, false, 3, preguntas, profesor.getID(), camino, false, 0);
		idActividad=quiz.getId();
		
	}
	
    @AfterEach
    void tearDown( ) throws Exception
    {
    }

    @Test
    public void retornarPreguntasConRespuestaTest()
    {
    	try
    	{
    		assertEquals(2, TraductorQuiz.retornarPreguntasConRespuesta(idCamino, idActividad).size());
    	}
    	catch(Exception e )
    	{
    		e.printStackTrace();
    		fail("No deberia salir error: "+e.getMessage());
    	}
    }
    
    @Test
    public void retornarPreguntasSinRespuestaTest()
    {
    	try
    	{
    		assertEquals(2, TraductorQuiz.retornarPreguntasSinRespuesta(idCamino, idActividad).size());
    	}
    	catch(Exception e )
    	{
    		e.printStackTrace();
    		fail("No deberia salir error: "+e.getMessage());
    	}
    }
}
