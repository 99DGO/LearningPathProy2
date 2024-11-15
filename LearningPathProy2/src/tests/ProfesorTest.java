package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
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

public class ProfesorTest  
{
	@BeforeAll
 	static void init( ) throws Exception
    {
		LearningPathSystem.resetLPS();
		CreadorProfesor.crearProfesor("Dire999", "Dire123", "Dire Crowley");
		CreadorProfesor.crearProfesor("Divus999", "Divus123", "Divus Crewel");
		String IDprof = TraductorProfesor.getIDfromLogin("Divus999");
		
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
		
    }
 	
    @AfterEach
    void tearDown( ) throws Exception
    {
    }

    
    @Test
    @Order(1)
    public void crearProfesorTest()
    {
    	LearningPathSystem LPS = LearningPathSystem.getInstance();
    	
    	assertEquals(2, LPS.getProfesores().size(), "No se guardaron los profesores en el LPS");
    }
    
    
    @Test
    @Order(2)
    public void guardarProfesorPersistenciaTest()
    {
    	LearningPathSystem LPS = LearningPathSystem.getInstance();
	
    	assertEquals(2, LPS.getProfesores().size(), "No se guardaron los profesores en el LPS");
    	
    	try 
    	{
    		CentralPersistencia.guardarProfesores(true);

		} catch (Exception e) 
    	{
			e.printStackTrace();
			fail("No deberia sacar error "+e.getMessage());
		}
    }

}
