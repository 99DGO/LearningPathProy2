package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import caminosActividades.CaminoAprendizaje;
import caminosActividades.OpcionQuiz;
import caminosActividades.PreguntaQuiz;
import caminosActividades.Quiz;
import controllers.LearningPathSystem;
import creadores.CreadorQuiz;
import usuarios.Profesor;

public class CreadoresTest {
	
	private static Quiz quiztest;
	private static List<String> objetivos;
	private static List<PreguntaQuiz> preguntasQuiz;
	private static CaminoAprendizaje camino;
	private static int[] fechaLim;
	private static LearningPathSystem LPS;
	private static Profesor profesor;
	

	@BeforeEach
 	void init( ) throws Exception
    {
		LearningPathSystem.resetLPS();
		LPS=LearningPathSystem.getInstance();
		
		profesor = new Profesor("Aizawa999", "Aizawa 123", "Aizawa Shouta");
		LPS.addProfesor(profesor);
		
		objetivos = new LinkedList<String>();
		objetivos.add("Saber diferentes tipos de datos");
		objetivos.add("Aprender loops");
		objetivos.add("Aprender estructuras");
		
		camino = new CaminoAprendizaje("CaminoTest", "Como crear caminos", objetivos, 0, 10, profesor.getID());
		
		LPS.addCamino(camino);
		
		preguntasQuiz = new LinkedList<PreguntaQuiz>();
		OpcionQuiz opcion1 = new OpcionQuiz("int", "Porque es un entero", true);
		OpcionQuiz opcion2 = new OpcionQuiz("double", "Porque no es un decimal", false);
		OpcionQuiz opcion3 = new OpcionQuiz("float", "Porque no es un decimal", false);
		OpcionQuiz opcion4 = new OpcionQuiz("string", "Porque es un numero", false);

		PreguntaQuiz pregunta1= new PreguntaQuiz("Si quiero representar el número de vacas que tengo, que tipo de variable debería usar?", 1, 4);
		pregunta1.setOpcion(1, opcion1);
		pregunta1.setOpcion(2, opcion2);
		pregunta1.setOpcion(3, opcion3);
		pregunta1.setOpcion(4, opcion4);
		preguntasQuiz.add(pregunta1);
		
		OpcionQuiz opcion1B = new OpcionQuiz("Paloma", "Porque son sucias", false);
		OpcionQuiz opcion2B = new OpcionQuiz("Cuervo", "Porque son hermosos e inteligentes", true);
		OpcionQuiz opcion3B = new OpcionQuiz("Pechirrojo", "Tierno pero es muy pequeño", false);
		OpcionQuiz opcion4B = new OpcionQuiz("Vaca", "La vaca no es un pajaro", false);
		
		PreguntaQuiz pregunta2= new PreguntaQuiz("Cual es el mejor pajaro?", 2, 4);
		pregunta2.setOpcion(1, opcion1B);
		pregunta2.setOpcion(2, opcion2B);
		pregunta2.setOpcion(3, opcion3B);
		pregunta2.setOpcion(4, opcion4B);
		preguntasQuiz.add(pregunta2);

		fechaLim= new int[]{0,1,0};

		
    }
	
	@Test
	public void crearQuizCeroTest()
	{
		try 
		{
			CreadorQuiz.crearQuizCero(camino.getID(),"Quiz Test", "Esto es un quiz donde te preguntan que tipo de variable es más indicado", objetivos, 1.5, 20, fechaLim, 
					false, 2, preguntasQuiz, profesor.getID(), false, 0);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
		
		assertEquals(1, camino.getActividades().size(), "No se crearon el numero correcto de actividades");
	}

}
