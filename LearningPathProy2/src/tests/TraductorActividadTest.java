package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import caminosActividades.ActividadRecurso;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Examen;
import caminosActividades.OpcionQuiz;
import caminosActividades.PreguntaQuiz;
import caminosActividades.Quiz;
import controllers.LearningPathSystem;
import traductores.TraductorActividad;
import usuarios.Profesor;

public class TraductorActividadTest 
{
	private static String idActividadAR;
	private static String idActividadQuiz;
	private static String idCamino;
	private static CaminoAprendizaje camino;
	private static ActividadRecurso AR;
	private static LearningPathSystem LPS;
	private static Profesor profesor;
	
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

			AR= new ActividadRecurso("Lectura Test", "Esto es una lectura de tipos de variables", objetivos, 1.5, 20, fechaLim, 
					false, "https://www.w3schools.com/python/python_variables.asp", "Leer el articulo.", profesor.getID(), camino, 0);	
			idActividadAR=AR.getId();
			
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

			Quiz quiz= new Quiz("Quiz de asignación variables", "Esto es un quiz donde te preguntan que tipo de variable es más indicado", 
					objetivos, 1.5, 15, fechaLim, false, 3, preguntas, profesor.getID(), camino, false, 0);
			idActividadQuiz=quiz.getId();


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
    public void verInfoGeneralActividadTest()
    {
    	try
    	{
			TraductorActividad.verInfoGeneralActividad(idCamino, idActividadAR);
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			fail ("No deberia sacar error: "+e.getMessage());
		}
    	
    	try
    	{
			TraductorActividad.verInfoGeneralActividad(idCamino, idActividadQuiz);
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			fail ("No deberia sacar error: "+e.getMessage());
		}
    }
    
    @Test
    public void getTipoQuizTest()
    {
    	try
    	{
			boolean verdaderoFalso= TraductorActividad.getTipoQuiz(idCamino, idActividadQuiz);
			assertFalse(verdaderoFalso, "No retorno el tipo de quiz correcto");
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			fail ("No deberia sacar error: "+e.getMessage());
		}
    	
    }

}
