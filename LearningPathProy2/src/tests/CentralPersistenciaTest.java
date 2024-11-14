package tests;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.OpcionQuiz;
import caminosActividades.PreguntaQuiz;
import controllers.Inscriptor;
import controllers.LearningPathSystem;
import creadores.CreadorAR;
import creadores.CreadorCamino;
import creadores.CreadorEstudiante;
import creadores.CreadorExamen;
import creadores.CreadorProfesor;
import creadores.CreadorQuiz;
import datosEstudiantes.DatosEstudianteActividad;
import persistencia.ActividadesPersistencia;
import persistencia.CaminosPersistencia;
import persistencia.CentralPersistencia;
import persistencia.metodosAuxPersistencia;
import traductores.TraductorCamino;
import traductores.TraductorEstudiante;
import traductores.TraductorProfesor;

public class CentralPersistenciaTest 
{

	@BeforeAll
    static void setup( ) throws Exception
    {
		metodosAuxPersistencia.cleanDatos(true);
		LearningPathSystem.resetLPS();
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }
	
 
	@Test
	public void testCleanDatos() 
	{
		try 
		{
			metodosAuxPersistencia.cleanDatos(true);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("Saco error la función clean datos"+e.getMessage());
		}
		

		int lines=999;
		try 
		{
			lines = metodosAuxPersistencia.contadorLineasCaminosDirectorio(true);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("Saco error la función contar lineas"+e.getMessage());
		}
		
		int numFolders=999;
		try
		{
			numFolders=metodosAuxPersistencia.contadorFoldersCaminos(true);
		}
		catch (Exception e)
		{
			
		}
		assertEquals(0, lines, "No se borro el directorio");
		assertEquals(2, numFolders, "No se borraron las carpetas");
	}

	@Test
	public void testGuardarPersitenciaCaminos()
	{
		LearningPathSystem LPS =LearningPathSystem.getInstance();

		try
		{
			CreadorProfesor.crearProfesor("KakashiCentralPersistenciaTest", "Kakashi123", "Kakashi Hatake");
			String IDprof = TraductorProfesor.getIDfromLogin("KakashiCentralPersistenciaTest");
			
			//Creo objetivos de camino
			List<String> objetivos = new LinkedList<String>();
			objetivos.add("Saber diferentes tipos de datos");
			objetivos.add("Aprender loops");
			objetivos.add("Aprender estructuras");
			
			CreadorCamino.crearCaminoCero("Python123", "Un curso para saber los basicos de python", objetivos, 2, 120, IDprof);
			CreadorCamino.crearCaminoCero("Jaca123", "Un curso para saber los basicos de java", objetivos, 3, 125, IDprof);
			
			String idCamino = TraductorCamino.getIDfromNombre("Python123");
			
			//Creo objetivos de actividad
			List<String> objetivosActividad = new LinkedList<String>();
			objetivosActividad.add("Saber que es un int");
			objetivosActividad.add("Saber que es un double");
			objetivosActividad.add("Saber que es un float");
			
			List<String> preguntasExamen = new LinkedList<String>();
			preguntasExamen.add("Cual es la diferencia entre double y float?");
			preguntasExamen.add("Se puede pasar de un double a un int o de un int a un double? Por que o por que no?");
			preguntasExamen.add("Diga cual es su tipo de variable favorita y justifique.");
			
			int [] fechaLim2= new int[] {0,0,0};
			CreadorAR.crearARCero(idCamino, "Lectura de variables", "Una lectura para saber los tipos de variable", objetivosActividad, 
					2, 20, fechaLim2, true, "https://www.w3schools.com/python/python_datatypes.asp", "Lea el articulo", IDprof,0);
			
			int[] fechaLim= new int[]{0,1,0};
			CreadorExamen.crearExamenCero(idCamino, "Tipos de variables examen", "Comprobar que el estudiante sabe diferenciar variables numericos", 
					objetivosActividad, 2.5, 30, fechaLim, true, 3, preguntasExamen, IDprof,1);
			
			//Creo el quiz
			List<PreguntaQuiz> preguntas = new LinkedList<PreguntaQuiz>();
			OpcionQuiz opcion1 = new OpcionQuiz("int", "Porque es un entero", true);
			OpcionQuiz opcion2 = new OpcionQuiz("double", "Porque no es un decimal", false);
			OpcionQuiz opcion3 = new OpcionQuiz("float", "Porque no es un decimal", false);
			OpcionQuiz opcion4 = new OpcionQuiz("string", "Porque es un numero", false);

			PreguntaQuiz pregunta1= new PreguntaQuiz("Si quiero representar el número de vacas que tengo, que tipo de variable debería usar?", 0, 4);
			pregunta1.setOpcion(0, opcion1);
			pregunta1.setOpcion(1, opcion2);
			pregunta1.setOpcion(2, opcion3);
			pregunta1.setOpcion(3, opcion4);
			preguntas.add(pregunta1);
			
			OpcionQuiz opcion1B = new OpcionQuiz("Paloma", "Porque son sucias", false);
			OpcionQuiz opcion2B = new OpcionQuiz("Cuervo", "Porque son hermosos e inteligentes", true);
			OpcionQuiz opcion3B = new OpcionQuiz("Pechirrojo", "Tierno pero es muy pequeño", false);
			OpcionQuiz opcion4B = new OpcionQuiz("Vaca", "La vaca no es un pajaro", false);
			
			PreguntaQuiz pregunta2= new PreguntaQuiz("Cual es el mejor pajaro?", 1, 4);
			pregunta2.setOpcion(0, opcion1B);
			pregunta2.setOpcion(1, opcion2B);
			pregunta2.setOpcion(2, opcion3B);
			pregunta2.setOpcion(3, opcion4B);
			preguntas.add(pregunta2);

			CreadorQuiz.crearQuizCero(idCamino, "Quiz de asignación variables", "Esto es un quiz donde te preguntan que tipo de variable es más indicado", 
					objetivosActividad, 1.5, 15, fechaLim, false, 3, preguntas, IDprof, false, 2);
			
			CreadorEstudiante.crearEstudiante("Trey999", "Trey123", "Trey Clover");
			String IDEstudiante= TraductorEstudiante.getIDfromLogin("Trey999");
			
			CreadorEstudiante.crearEstudiante("Cater999", "Cater123", "Cater Diamond");
			String IDEstudiante2= TraductorEstudiante.getIDfromLogin("Cater999");
	
			Inscriptor.inscribirseCamino(idCamino, IDEstudiante);
			Inscriptor.inscribirseCamino(idCamino, IDEstudiante2);

		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("No se pudo crear el setup. Chequear otras pruebas antes de esta"+e.getMessage());
		}
				
		try 
		{
			CentralPersistencia.guardarCaminosActividadesDatosEstudiante(true);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
    		fail("No se guardo, tiro"+e.getMessage()); 
		}	
	
	}
	


}
