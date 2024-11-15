package tests;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import controllers.Inscriptor;
import controllers.LearningPathSystem;
import creadores.CreadorAR;
import creadores.CreadorCamino;
import creadores.CreadorEstudiante;
import creadores.CreadorExamen;
import creadores.CreadorProfesor;
import persistencia.CentralPersistencia;
import traductores.TraductorCamino;
import traductores.TraductorEstudiante;
import traductores.TraductorProfesor;
import usuarios.Estudiante;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class EstudianteTest 
{
 	@BeforeAll
 	static void init( ) throws Exception
    {
 		LearningPathSystem.resetLPS();
		CreadorProfesor.crearProfesor("KakashiCentralPersistenciaTest", "Kakashi123", "Kakashi Hatake");
		String IDprof = TraductorProfesor.getIDfromLogin("KakashiCentralPersistenciaTest");
		
		List<String> objetivos = new LinkedList<String>();
		objetivos.add("Saber diferentes tipos de datos");
		objetivos.add("Aprender loops");
		objetivos.add("Aprender estructuras");
		
		CreadorCamino.crearCaminoCero("Python123", "Un curso para saber los basicos de python", objetivos, 2, IDprof);
		CreadorCamino.crearCaminoCero("Jaca123", "Un curso para saber los basicos de java", objetivos, 3, IDprof);
		
		String idCamino = TraductorCamino.getIDfromNombre("Python123");
		
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
		
	  	try 
    	{
			CreadorEstudiante.crearEstudiante("Trey999", "Trey123", "Trey Clover");
			CreadorEstudiante.crearEstudiante("Cater999", "Cater123", "Cater Diamond");

		} catch (Exception e) 
    	{
			e.printStackTrace();
			fail("Deberia poder crearse los estudiantes "+e.getMessage());
		}
	  	
    	String IDestudiante1=null;
    	String IDestudiante2=null;
    	String IDCamino=null;
    	
    	try
    	{
    		IDestudiante1= TraductorEstudiante.getIDfromLogin("Cater999");
    		IDestudiante2= TraductorEstudiante.getIDfromLogin("Trey999");
    		IDCamino=TraductorCamino.getIDfromNombre("Python123");

    	}
    	catch (Exception e)
    	{
    		fail("Error en el setup"+e.getMessage());
    	}
    	
    	try 
    	{
			Inscriptor.inscribirseCamino(IDCamino, IDestudiante1);
			Inscriptor.inscribirseCamino(IDCamino, IDestudiante2);


		} catch (Exception e) 
    	{
			e.printStackTrace();
			fail("Deberia poder inscribirse los estudiantes "+e.getMessage());
		}
    }
 	
    @BeforeEach
    void setUp( ) throws Exception
    {
        
    }
    
    @Test
    @Order(1)
    public void crearEstudianteTest()
    {
    	LearningPathSystem LPS = LearningPathSystem.getInstance(); 
    	
    	assertEquals(2, LPS.getEstudiantes().size(), "No se guardaron los estudiante en el LPS");
    }
    
    @Test
    @Order(2)
    public void inscribirEstudiante()
    {
    	String IDestudiante1=null;
    	String IDestudiante2=null;
    	String IDCamino=null;
    	
    	try
    	{
    		IDestudiante1= TraductorEstudiante.getIDfromLogin("Cater999");
    		IDestudiante2= TraductorEstudiante.getIDfromLogin("Trey999");
    		IDCamino=TraductorCamino.getIDfromNombre("Python123");

    	}
    	catch (Exception e)
    	{
    		fail("Error en el setup"+e.getMessage());
    	}
    	
    	LearningPathSystem LPS = LearningPathSystem.getInstance();

		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDCamino);
		Actividad actividad =camino.getActividades().getFirst();
		Estudiante estudiante1=LPS.getEstudianteIndividual(IDestudiante1);
		
		assertEquals(1, estudiante1.getHistorialCaminos().size(), "No se añadio el camino al estudiante");
		assertEquals(2, actividad.getDatosEstudiantes().size(), "No se añadieron los datos de los estudiantes al camino");
    }
    
    @Test
    @Order(3)
    public void guardarEstudiante()
    {
    	LearningPathSystem LPS = LearningPathSystem.getInstance();
	
    	
    	assertEquals(2, LPS.getEstudiantes().size(), "No se guardaron los estudiante en el LPS");
    	
    	try 
    	{
    		CentralPersistencia.guardarEstudiantes(true);

		} catch (Exception e) 
    	{
			e.printStackTrace();
			fail("No deberia sacar error "+e.getMessage());
		}
    }
    

}
