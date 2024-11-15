package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

public class QuizTest
{
	//Clase que prueba las funciones internas de quiz
	
	private static Quiz quiztest;
	private static List<String> objetivos;
	private static List<PreguntaQuiz> preguntas;
	@BeforeEach
 	void init( ) throws Exception
    {
		objetivos = new LinkedList<String>();
		objetivos.add("Saber diferentes tipos de datos");
		objetivos.add("Aprender loops");
		objetivos.add("Aprender estructuras");
		
		CaminoAprendizaje camino = new CaminoAprendizaje("CaminoTest", "Como crear caminos", objetivos, 1, "Prof999");
		preguntas = new LinkedList<PreguntaQuiz>();
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

		int[] fechaLim= new int[]{0,1,0};

		quiztest=new Quiz("Quiz Test", "Esto es un quiz donde te preguntan que tipo de variable es más indicado", objetivos, 1.5, 20, fechaLim, 
				false, 2, preguntas, "Prof999", camino, false, 0);

		
    }
	
	@Test
	public void preguntasCorrectasTest()
	{
		assertEquals(2, quiztest.getPreguntas().size(), "No se crearon el numero correcto de preguntas");
	}
	


}
