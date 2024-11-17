package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import caminosActividades.ActividadRecurso;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Examen;
import controllers.LearningPathSystem;
import editores.EditorActividadGeneral;
import usuarios.Profesor;

public class EditorActividadGeneralTest 
{
	private static String idActividad;
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
			idActividad=AR.getID();
			
			
			List<String> preguntasString= new LinkedList<String>();
			preguntasString.add("¿Cuales son las caracteristicas princiaples de los cuervos?");
			preguntasString.add("¿Que otro animal es parecido e igual de increible que los cuervos?");
			preguntasString.add("¿Por que son mejores los cuervos que otros pajaros?");
			
			Examen examen = new Examen("Examen Test", "Esto es un examen sobre cuervos", objetivos, 1.5, 20, 
					fechaLim, true, 3, preguntasString, profesor.getID(), camino, 0);

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
    public void editAddObjetivoTest()
    {
    	try
    	{
			EditorActividadGeneral.editAddObjetivo(idCamino, "cuervos", "hola");
			fail("Deberia sacar error por inexistencia de actividad");
    	}
    	catch (Exception e)
    	{
    		assertEquals("No se encontro la actividad", e.getMessage(), "No saco exception correcta");
    	}
    	
    	try 
    	{
			EditorActividadGeneral.editAddObjetivo(idCamino, idActividad, "Proteger a los cuervos");
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
    	
    	assertEquals(4, AR.getObjetivos().size(), "No se añadio bien un objetivo");
     	assertEquals(2, camino.getVersion(), "No se guardo bien la version");
    }
    
    @Test
    public void editDelObjetivoTest()
    {
    	try
    	{
			EditorActividadGeneral.editDelObjetivo(idCamino, "cuervos", 1);
			fail("Deberia sacar error por inexistencia de actividad");
    	}
    	catch (Exception e)
    	{
    		assertEquals("No se encontro la actividad", e.getMessage(), "No saco exception correcta");
    	}
    	
    	try 
    	{
			EditorActividadGeneral.editDelObjetivo(idCamino, idActividad, 3);
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
    	
    	assertFalse(AR.getObjetivos().contains("Volverse fan de los cuervos."), "No se borro bien un objetivo");
     	assertEquals(2, camino.getVersion(), "No se guardo bien la version");
    }
	
    @Test
    public void editAddDelActividadPrereq()
    {
       	try 
    	{
			EditorActividadGeneral.editAddActividadPrereq(idCamino, idActividad, "Examen Test");
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}	
       	
       	assertTrue(AR.getActividadesPrereqs().contains("Examen Test"), "No se añadio bien la actividad pre-requisito");
     	assertEquals(2, camino.getVersion(), "No se guardo bien la version");

       	try 
    	{
			EditorActividadGeneral.editDelActividadPrereq(idCamino, idActividad, 1);
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
    	
    	assertFalse(AR.getActividadesPrereqs().contains("Examen Test"), "No se borro bien la actividad pre-requisito");
     	assertEquals(3, camino.getVersion(), "No se guardo bien la version");
    }
    
    @Test
    public void editAddDelActividadSigExitoso()
    {
       	try 
    	{
			EditorActividadGeneral.editAddActividadSiguienteExitosa(idCamino, idActividad, "Examen Test");
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}	
       	
       	assertTrue(AR.getActividadesSigExitoso().contains("Examen Test"), "No se añadio bien la actividad siguiente");
     	assertEquals(2, camino.getVersion(), "No se guardo bien la version");

       	try 
    	{
			EditorActividadGeneral.editDelActividadSiguienteExitosa(idCamino, idActividad, 1);
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
    	
    	assertFalse(AR.getActividadesSigExitoso().contains("Examen Test"), "No se borro bien la actividad siguiente");
     	assertEquals(3, camino.getVersion(), "No se guardo bien la version");
    }
    
    @Test
    public void editDescripcionTest()
    {
    	try
    	{
			EditorActividadGeneral.editDescripcion(idCamino, "cuervos", "hola");
			fail("Deberia sacar error por inexistencia de actividad");
    	}
    	catch (Exception e)
    	{
    		assertEquals("No se encontro la actividad", e.getMessage(), "No saco exception correcta");
    	}
    	
     	try 
    	{
			EditorActividadGeneral.editDescripcion(idCamino, idActividad, "Esto es el MEJOR curso en existencia.");
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}	
     	
     	assertEquals("Esto es el MEJOR curso en existencia.", AR.getDescripcion(), "No se edito bien la descripcion");
     	assertEquals(2, camino.getVersion(), "No se guardo bien la version");

    }
    
    @Test
    public void editDificultadTest()
    {
    	try
    	{
			EditorActividadGeneral.editDificultad(idCamino, "cuervos", 100);
			fail("Deberia sacar error por inexistencia de actividad");
    	}
    	catch (Exception e)
    	{
    		assertEquals("No se encontro la actividad", e.getMessage(), "No saco exception correcta");
    	}
    	
     	try 
    	{
			EditorActividadGeneral.editDificultad(idCamino, idActividad, 2);
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}	
     	
     	assertEquals(2, AR.getDificultad(), "No se edito bien la dificultad");
     	assertEquals(2, camino.getVersion(), "No se guardo bien la version");
    }
    
    @Test
    public void editDuracionTest()
    {
    	try
    	{
			EditorActividadGeneral.editDuracion(idCamino, "cuervos", 100);
			fail("Deberia sacar error por inexistencia de actividad");
    	}
    	catch (Exception e)
    	{
    		assertEquals("No se encontro la actividad", e.getMessage(), "No saco exception correcta");
    	}
    	
     	try 
    	{
			EditorActividadGeneral.editDuracion(idCamino, idActividad, 100);
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}	
     	
     	assertEquals(100, AR.getDuracion(), "No se edito bien la duracion");
     	assertEquals(2, camino.getVersion(), "No se guardo bien la version");
    }
    
    @Test
    public void editFechaLimTest()
    {
    	int[] fechaLimNueva= new int[] {9,9,9};
    	try
    	{
			EditorActividadGeneral.editFechaLim(idCamino, "cuervos", fechaLimNueva);
			fail("Deberia sacar error por inexistencia de actividad");
    	}
    	catch (Exception e)
    	{
    		assertEquals("No se encontro la actividad", e.getMessage(), "No saco exception correcta");
    	}
    	
     	try 
    	{
			EditorActividadGeneral.editFechaLim(idCamino, idActividad, fechaLimNueva);
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}	
     	
     	assertEquals(fechaLimNueva, AR.getFechaLim(), "No se edito bien la fecha limite");
     	assertEquals(2, camino.getVersion(), "No se guardo bien la version");
    }
    
    @Test
    public void editNombreTest()
    {
    	try
    	{
			EditorActividadGeneral.editNombre(idCamino, "cuervos", "El mejor curso en exitencia");
			fail("Deberia sacar error por inexistencia de actividad");
    	}
    	catch (Exception e)
    	{
    		assertEquals("No se encontro la actividad", e.getMessage(), "No saco exception correcta");
    	}
    	
     	try 
    	{
			EditorActividadGeneral.editNombre(idCamino, idActividad, "El mejor curso en exitencia");
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}	
     	
     	assertEquals("El mejor curso en exitencia", AR.getNombre(), "No se edito bien el nombre");
     	assertEquals(2, camino.getVersion(), "No se guardo bien la version");
    }
    
    @Test
    public void editObligatoriaTest()
    {
    	try
    	{
			EditorActividadGeneral.editObligatoria(idCamino, "cuervos", true);
			fail("Deberia sacar error por inexistencia de actividad");
    	}
    	catch (Exception e)
    	{
    		assertEquals("No se encontro la actividad", e.getMessage(), "No saco exception correcta");
    	}
    	
     	try 
    	{
			EditorActividadGeneral.editObligatoria(idCamino, idActividad, true);
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}	
     	
     	assertEquals(true, AR.isObligatoria(), "No se edito bien si es obligatoria");
     	assertEquals(2, camino.getVersion(), "No se guardo bien la version");
    }
}
