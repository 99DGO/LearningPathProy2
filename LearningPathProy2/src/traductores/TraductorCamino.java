package traductores;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;
import usuarios.Profesor;

public class TraductorCamino 
{
	/*
	 * Retorna un hashMap donde las llaves son los IDs de los caminos y los valores son los nombres de ese camino.
	 */
	public static HashMap<String, String> verTodosCaminos ()
	{
		HashMap<String, String> caminosToReturn = new HashMap<String, String>();
		
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		HashMap<String, CaminoAprendizaje> hashCaminosLPS=LPS.getCaminos();
		CaminoAprendizaje camino;
		
		for (String IDCamino : hashCaminosLPS.keySet())
		{
			camino=hashCaminosLPS.get(IDCamino);
			caminosToReturn.put(IDCamino, camino.getTitulo());
		}
		
		return caminosToReturn;
	}
	
	/*
	 * Retorna un hashMap donde las llaves son los atributos del camino y los valores son la información de ese atributo en String.
	 *No retorna las actividades
	 */
	public static  HashMap<String, String> verInfoGeneralCamino(String IDCamino)
	{
		HashMap<String, String> infoCamino=new HashMap<String, String>();
		
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		CaminoAprendizaje camino=LPS.getCaminoIndividual(IDCamino);
		
		infoCamino.put("Titulo", (camino.getTitulo()+"\n"));
		infoCamino.put("Descripcion", (camino.getDescripcion()+"\n"));
		infoCamino.put("Dificultad", (String.valueOf(camino.getDificultad())+"\n"));

		
		Iterator<String> it1 = camino.getObjetivos().iterator();
		String objetivos="";
		int numObjetivo=0;
		
		while (it1.hasNext())
		{
			objetivos.concat(String.valueOf(numObjetivo)+")"+it1.next()+"\n");
			numObjetivo+=1;
		}
		
		infoCamino.put("Objetivos", objetivos+"\n");

		infoCamino.put("Rating: ", String.valueOf(camino.getRating())+"\n");
		
		return infoCamino;
	}
	
	/*
	 * Retorna las actividades de un camino en una lista de arreglo de String.
	 * Cada arreglo es de tamaño dos, con el primer valor siendo el IDActividad y el segundo el título de la actividad
	 * La lista esta en el mismo orden que en el camino
	 */
	public static  List<String[]> verActividadesCamino(String IDCamino)
	{
		List<String[]> listToReturn = new LinkedList<String[]>();
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		CaminoAprendizaje camino=LPS.getCaminoIndividual(IDCamino);
		
		Iterator<Actividad> it2 = camino.getActividades().iterator();
		Actividad actividadIterator;
		String[] arrayActividad; 
		
		while (it2.hasNext())
		{
			actividadIterator=it2.next();
			arrayActividad= new String[] {actividadIterator.getId(), actividadIterator.getNombre()};
			
			listToReturn.add(arrayActividad);
		}

		return listToReturn;
		
	}
	
	public static String getIDfromNombre(String titulo) throws Exception
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		HashMap<String, CaminoAprendizaje> caminos = LPS.getCaminos();
		String IDtoReturn=null;
		
		for (String IDCamino: caminos.keySet())
		{
			CaminoAprendizaje caminoIterator=caminos.get(IDCamino);
			
			if (caminoIterator.getTitulo().equals(titulo))
			{
				IDtoReturn= IDCamino;
			}
		}
		
		if (IDtoReturn==null)
		{
			throw new Exception("No se encontro el camino");
		}
		else
		{
			return IDtoReturn;
		}
	}
}
