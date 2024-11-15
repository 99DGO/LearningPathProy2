package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import caminosActividades.Actividad;
import caminosActividades.ActividadRecurso;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Encuesta;
import caminosActividades.Examen;
import caminosActividades.OpcionQuiz;
import caminosActividades.PreguntaQuiz;
import caminosActividades.Quiz;
import caminosActividades.Tarea;
import controllers.LearningPathSystem;
import creadores.ChooserClonadorActividad;
import creadores.CreadorAR;
import creadores.CreadorEncuesta;
import creadores.CreadorExamen;
import creadores.CreadorQuiz;
import creadores.CreadorTarea;
import usuarios.Profesor;

public class CreadoresActividadesTests 
{
	
	private static CaminoAprendizaje nuevoCamino;
	private static List<String> objetivos;
	private static List<PreguntaQuiz> preguntasQuiz;
	private static List<String> preguntasString;
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
		objetivos.add("Saber la diferencia entre distintos tipos de cuervos.");
		objetivos.add("Poder sustentar porque los cuervos son tan increibles.");
		objetivos.add("Volverse fan de los cuervos.");
		
		camino = new CaminoAprendizaje("El maravilloso mundo de los cuervos", "Esto es un curso que te enseña lo increible que son los cuervos",
				objetivos, 0, 10, profesor.getID());
		nuevoCamino = new CaminoAprendizaje("El maravilloso mundo de los zorros", "Mismo curso que cuervos pero con zorros", objetivos, 0, 10, profesor.getID());

		LPS.addCamino(nuevoCamino);
		LPS.addCamino(camino);
		
		preguntasQuiz = new LinkedList<PreguntaQuiz>();
		OpcionQuiz opcion1 = new OpcionQuiz("int", "Porque es un entero lo que necesitas representar", true);
		OpcionQuiz opcion2 = new OpcionQuiz("double", "Porque no puedes tener decimales de cuervos", false);
		OpcionQuiz opcion3 = new OpcionQuiz("float", "Porque no puedes tener decimales de cuervos", false);
		OpcionQuiz opcion4 = new OpcionQuiz("string", "Porque es un numero lo que necesitas", false);

		PreguntaQuiz pregunta1= new PreguntaQuiz("Si quiero representar el número de cuervos que tengo, que tipo de variable debería usar?", 1, 4);
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
		
		preguntasString= new LinkedList<String>();
		preguntasString.add("¿Cuales son las caracteristicas princiaples de los cuervos?");
		preguntasString.add("¿Que otro animal es parecido e igual de increible que los cuervos?");
		preguntasString.add("¿Por que son mejores los cuervos que otros pajaros?");

		
    }
	
    @AfterEach
    void tearDown( ) throws Exception
    {
    }
	
	@Test
	public void crearQuizCeroTest()
	{
		try 
		{
			CreadorQuiz.crearQuizCero(camino.getID(),"Quiz Test", "Esto es un quiz donde te preguntan que tipo de variable es más indicado", objetivos, 1.5, 20, fechaLim, 
					false, 3, preguntasQuiz, profesor.getID(), false, 0);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
		
		assertEquals(1, camino.getActividades().size(), "No se crearon el numero correcto de actividades");
		
		Quiz quiz= (Quiz) camino.getActividades().getFirst();
		
		assertEquals(2, quiz.getPreguntas().size(), "No se crearon el correcto numero de preguntas");
		assertEquals(3, quiz.getCalificacionMin(), "No se puso la calificacion minima correcta");
		assertEquals(1.5, quiz.getDificultad(), "No se guardo bien la dificultad");
		assertEquals("Quiz Test", quiz.getNombre(), "No se guardo bien le nombre");

		
		try
		{
			CreadorQuiz.clonarQuiz(quiz, profesor.getID(), camino.getID(), 20);
			fail("Deberia sacar error por posicion incorrecta");
		}
		catch (Exception e)
		{
			assertEquals("La posicion indicada no existe", e.getMessage(), "No se saco el string correcto de excepcion");
		}
		
		try
		{
			CreadorQuiz.clonarQuiz(quiz, profesor.getID(), camino.getID(), 0);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("No deberia sacar error la clonación de un quiz: " + e.getMessage());
		}

		Quiz quizClonado = (Quiz) camino.getActividades().getFirst();
		
		assertFalse(quiz==quizClonado, "No se guardo en la posicion correcta la tarea clonada, o en vez de clonar se creo la misma tarea.");
		assertEquals(2, camino.getActividades().size(), "No se crearon el numero correcto de actividades");
	}
	
	@Test
	public void crearARCeroTest()
	{
		try 
		{
			CreadorAR.crearARCero(camino.getID(),"Lectura Test", "Esto es una lectura de tipos de variables", objetivos, 1.5, 20, fechaLim, 
					false, "https://www.w3schools.com/python/python_variables.asp", "Leer el articulo.",  profesor.getID(), 0);

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
		
		assertEquals(1, camino.getActividades().size(), "No se crearon el numero correcto de actividades");
		
		ActividadRecurso AR= (ActividadRecurso) camino.getActividades().getFirst();
		
		assertEquals("Leer el articulo.", AR.getInstrucciones(), "No se guardaron las instrucciones correctas");
		assertEquals(1.5, AR.getDificultad(), "No se guardo bien la dificultad");
		assertEquals("Lectura Test", AR.getNombre(), "No se guardo bien le nombre");

		try
		{
			CreadorAR.clonarAR(AR, profesor.getID(), camino.getID(), 20);
			fail("Deberia sacar error por posicion incorrecta");
		}
		catch (Exception e)
		{
			assertEquals("La posicion indicada no existe", e.getMessage(), "No se saco el string correcto de excepcion");
		}
		
		try
		{
			CreadorAR.clonarAR(AR, profesor.getID(), camino.getID(), 0);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("No deberia sacar error la clonación de un actividad recurso: " + e.getMessage());
		}

		ActividadRecurso ARClonado = (ActividadRecurso) camino.getActividades().getFirst();
		
		assertFalse(AR==ARClonado, "No se guardo en la posicion correcta la tarea clonada, o en vez de clonar se creo la misma tarea.");
		assertEquals(2, camino.getActividades().size(), "No se crearon el numero correcto de actividades");
	}
	
	@Test
	public void crearExamenCeroTest()
	{
		try 
		{
			CreadorExamen.crearExamenCero(camino.getID(),"Examen Test", "Esto es un examen sobre cuervos.", objetivos, 1.5, 20, 
					fechaLim, false, 3, preguntasString, profesor.getID(), 0);

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
		
		assertEquals(1, camino.getActividades().size(), "No se crearon el numero correcto de actividades");
		
		Examen examen= (Examen) camino.getActividades().getFirst();
		
		assertEquals(3, examen.getPreguntasAbiertas().size(), "No se crearon el correcto numero de preguntas");
		assertEquals(3, examen.getCalificacionMin(), "No se puso la calificacion minima correcta");
		assertEquals(1.5, examen.getDificultad(), "No se guardo bien la dificultad");
		assertEquals("Examen Test", examen.getNombre(), "No se guardo bien le nombre");


		try
		{
			CreadorExamen.clonarExamen(examen, profesor.getID(), camino.getID(), 20);
			fail("Deberia sacar error por posicion incorrecta");
		}
		catch (Exception e)
		{
			assertEquals("La posicion indicada no existe", e.getMessage(), "No se saco el string correcto de excepcion");
		}
		
		try
		{
			CreadorExamen.clonarExamen(examen, profesor.getID(), camino.getID(), 0);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("No deberia sacar error la clonación de un examen: " + e.getMessage());
		}

		Examen examenClonado = (Examen) camino.getActividades().getFirst();
		
		assertFalse(examenClonado==examen, "No se guardo en la posicion correcta la tarea clonada, o en vez de clonar se creo la misma tarea.");
		assertEquals(2, camino.getActividades().size(), "No se crearon el numero correcto de actividades");
	}
	
	@Test
	public void crearEncuestaCeroTest()
	{
		try 
		{
			CreadorEncuesta.crearEncuestaCero(camino.getID(),"Encuesta Test", "Esto es una encuesta sobre cuervos", objetivos, 1.5, 20, 
					fechaLim, true, preguntasString, profesor.getID(), 0);

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
		
		assertEquals(1, camino.getActividades().size(), "No se crearon el numero correcto de actividades");
		
		Encuesta encuesta= (Encuesta) camino.getActividades().getFirst();
		
		assertEquals(3, encuesta.getPreguntasAbiertas().size(), "No se crearon el correcto numero de preguntas");
		assertEquals(1.5, encuesta.getDificultad(), "No se guardo bien la dificultad");
		assertEquals("Encuesta Test", encuesta.getNombre(), "No se guardo bien le nombre");


		try
		{
			CreadorEncuesta.clonarEncuesta(encuesta, profesor.getID(), camino.getID(), 20);
			fail("Deberia sacar error por posicion incorrecta");
		}
		catch (Exception e)
		{
			assertEquals("La posicion indicada no existe", e.getMessage(), "No se saco el string correcto de excepcion");
		}
		
		try
		{
			CreadorEncuesta.clonarEncuesta(encuesta, profesor.getID(), camino.getID(), 0);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("No deberia sacar error la clonación de una encuesta: "+e.getMessage());
		}

		Encuesta encuestaClonada = (Encuesta) camino.getActividades().getFirst();
		
		assertFalse(encuestaClonada==encuesta, "No se guardo en la posicion correcta la tarea clonada, o en vez de clonar se creo la misma tarea.");
		assertEquals(2, camino.getActividades().size(), "No se crearon el numero correcto de actividades");
	}
	
	@Test
	public void crearTareaTest()
	{
		try 
		{
			CreadorTarea.crearTareaCero(camino.getID(),"Tarea Test", "Esto es una tarea sobre cuervos", objetivos, 1.5, 20, 
					fechaLim, true, "Tome fotos de cuervos.", profesor.getID(), 0);

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
		
		assertEquals(1, camino.getActividades().size(), "No se crearon el numero correcto de actividades");
		
		Tarea tarea= (Tarea) camino.getActividades().getFirst();
		
		assertEquals("Tome fotos de cuervos.", tarea.getInstrucciones(), "No se crearon las instrucciones correctas");
		assertEquals(1.5, tarea.getDificultad(), "No se guardo bien la dificultad");
		assertEquals("Tarea Test", tarea.getNombre(), "No se guardo bien le nombre");
		
		try
		{
			CreadorTarea.clonarTarea(tarea, profesor.getID(), camino.getID(), -1);
			fail("Deberia sacar error por posicion incorrecta");
		}
		catch (Exception e)
		{
			assertEquals("La posicion indicada no existe", e.getMessage(), "No se saco el string correcto de excepcion");
		}
		
		try
		{
			CreadorTarea.clonarTarea(tarea, profesor.getID(), camino.getID(), 0);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("No deberia sacar error la clonación de una tarea: "+e.getMessage());
		}

		Tarea tareaClonada = (Tarea) camino.getActividades().getFirst();
		
		assertFalse(tareaClonada==tarea, "No se guardo en la posicion correcta la tarea clonada, o en vez de clonar se creo la misma tarea.");
		assertEquals(2, camino.getActividades().size(), "No se crearon el numero correcto de actividades");
		

	}

	@Test
	public void chooserClonadorTareaTest()
	{
		
		Actividad actividadOriginal=null;
		
		try 
		{
			CreadorTarea.crearTareaCero(camino.getID(),"Tarea Test", "Esto es una tarea sobre cuervos", objetivos, 1.5, 20, 
					fechaLim, true, "Tome fotos de cuervos.", profesor.getID(), 0);

			actividadOriginal = camino.getActividades().getFirst();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
		
		try
		{
			ChooserClonadorActividad.ClonarActividad(camino.getID(), actividadOriginal.getId(), profesor.getID(), nuevoCamino.getID(), 0);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
		assertEquals(Actividad.TAREA, nuevoCamino.getActividades().getFirst().getType(), "No se clono una tarea");

	}
	
	@Test
	public void chooserClonadorExamenTest()
	{
		
		Actividad actividadOriginal=null;
		
		try 
		{
			CreadorExamen.crearExamenCero(camino.getID(),"Examen Test", "Esto es un examen sobre cuervos.", objetivos, 1.5, 20, 
					fechaLim, false, 3, preguntasString, profesor.getID(), 0);

			actividadOriginal = camino.getActividades().getFirst();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
		
		try
		{
			ChooserClonadorActividad.ClonarActividad(camino.getID(), actividadOriginal.getId(), profesor.getID(), nuevoCamino.getID(), 0);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
		assertEquals(Actividad.EXAMEN, nuevoCamino.getActividades().getFirst().getType(), "No se clono un examen");

	}
	
	@Test
	public void chooserClonadorQuizTest()
	{
		
		Actividad actividadOriginal=null;
		
		try 
		{
			CreadorQuiz.crearQuizCero(camino.getID(),"Quiz Test", "Esto es un quiz donde te preguntan que tipo de variable es más indicado", objetivos, 1.5, 20, fechaLim, 
					false, 3, preguntasQuiz, profesor.getID(), false, 0);

			actividadOriginal = camino.getActividades().getFirst();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
		
		try
		{
			ChooserClonadorActividad.ClonarActividad(camino.getID(), actividadOriginal.getId(), profesor.getID(), nuevoCamino.getID(), 0);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
		assertEquals(Actividad.QUIZ, nuevoCamino.getActividades().getFirst().getType(), "No se clono un quiz");

	}
	
	@Test
	public void chooserClonadorEncuestaTest()
	{
		
		Actividad actividadOriginal=null;
		
		try 
		{
			CreadorEncuesta.crearEncuestaCero(camino.getID(),"Encuesta Test", "Esto es una encuesta sobre cuervos", objetivos, 1.5, 20, 
					fechaLim, true, preguntasString, profesor.getID(), 0);
			
			actividadOriginal = camino.getActividades().getFirst();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
		
		try
		{
			ChooserClonadorActividad.ClonarActividad(camino.getID(), actividadOriginal.getId(), profesor.getID(), nuevoCamino.getID(), 0);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
		assertEquals(Actividad.ENCUESTA, nuevoCamino.getActividades().getFirst().getType(), "No se clono una encuesta");

	}

	@Test
	public void chooserClonadorARTest()
	{
		
		Actividad actividadOriginal=null;
		
		try 
		{
			CreadorAR.crearARCero(camino.getID(),"Lectura Test", "Esto es una lectura de tipos de variables", objetivos, 1.5, 20, fechaLim, 
					false, "https://www.w3schools.com/python/python_variables.asp", "Leer el articulo.",  profesor.getID(), 0);
			
			actividadOriginal = camino.getActividades().getFirst();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage()); 
		}
		
		try
		{
			ChooserClonadorActividad.ClonarActividad(camino.getID(), actividadOriginal.getId(), profesor.getID(), nuevoCamino.getID(), 0);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
		assertEquals(Actividad.ACTIVIDADRECURSO, nuevoCamino.getActividades().getFirst().getType(), "No se clono una actividad de recurso");

	}



}
