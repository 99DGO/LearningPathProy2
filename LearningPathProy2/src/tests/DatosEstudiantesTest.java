package tests;

import static org.junit.Assert.fail;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import datosEstudiantes.DatosEstudianteExamen;
import controllers.Inscriptor;
import controllers.LearningPathSystem;
import creadores.CreadorAR;
import creadores.CreadorCamino;
import creadores.CreadorEstudiante;
import creadores.CreadorExamen;
import creadores.CreadorProfesor;
import persistencia.ActividadesPersistencia;
import persistencia.CentralPersistencia;
import persistencia.DatosEstudiantesPersistencia;
import traductores.TraductorCamino;
import traductores.TraductorEstudiante;
import traductores.TraductorProfesor;

public class DatosEstudiantesTest 
{
	private LearningPathSystem LPS =LearningPathSystem.getInstance();

	@BeforeAll
	// Esto configura un setup que se hace una sola vez al comenzar el primer test
	// y despues nunca mas
	static void init() throws Exception 
	{
		CreadorProfesor.crearProfesor("Kakashi", "Kakashi123");
		String IDprof = TraductorProfesor.getIDfromLogin("Kakashi");
		
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
		
		
		CreadorEstudiante.crearEstudiante("TreyClover", "Trey123");
		String IDEstudiante= TraductorEstudiante.getIDfromLogin("TreyClover");

		Inscriptor.inscribirseCamino(idCamino, IDEstudiante);

	}

	@Test
	public void testPersitencia()
	{
		String idCamino=null;
		String IDEstudiante=null;
		

		try 
		{
			IDEstudiante= TraductorEstudiante.getIDfromLogin("TreyClover");
			idCamino = TraductorCamino.getIDfromNombre("Python123");

		} 
		catch (Exception e) {
    		fail("No se encontro el ID del estudiante con el login"); 
		}
	
    	try
    	{
    		CaminoAprendizaje camino = LPS.getCaminoIndividual(idCamino);
    		Actividad actividad;
    		
    		List<Actividad> actividades = camino.getActividades();
    		DatosEstudianteExamen datosEst = new DatosEstudianteExamen(IDEstudiante);
    		
    		DatosEstudiantesPersistencia.guardarDatosEstudiante(datosEst, idCamino, actividades.getFirst().getId(), "datosTests/Caminos/");
  
    	}
    	catch (Exception e)
    	{
    		System.out.println(e);
    		fail("No se creo el objeto del archivo"); 
    	}
    	

	}

}
