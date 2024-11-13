package traductores;

import java.util.HashMap;
import java.util.Iterator;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;

public class TraductorActividad {
	
	public static String getIDfromNombre(String idCamino, String nombre) throws Exception
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		CaminoAprendizaje camino =LPS.getCaminoIndividual(idCamino);
		String IDtoReturn=null;

		for (Actividad actividad: camino.getActividades())
		{
			
			if (actividad.getNombre().equals(nombre))
			{
				IDtoReturn= actividad.getId();
			}
		}
		
		if (IDtoReturn==null)
		{
			throw new Exception("No se encontro la actividad");
		}
		else
		{
			return IDtoReturn;
		}
	}
	
	public static HashMap<String, String> verInfoGeneralActividad(String idCamino, String idActividad)
	{
		HashMap<String, String> infoActividad = new HashMap<String, String>();
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		CaminoAprendizaje camino = LPS.getCaminoIndividual(idCamino);
		Actividad actividad = null;

		for (Actividad actividadIterator : camino.getActividades())
		{
			if (actividadIterator.getId().equals(idActividad))
			{
				actividad = actividadIterator;
			}
		}
		

		infoActividad.put("Nombre: ", actividad.getNombre() + "\n");
		infoActividad.put("ID: ", actividad.getId() + "\n");
		infoActividad.put("Tipo de actividad: ", actividad.getType() + "\n");
		infoActividad.put("Descripcion: ", actividad.getDescripcion() + "\n");
		infoActividad.put("Dificultad: ", String.valueOf(actividad.getDificultad()) + "\n");
		infoActividad.put("Duracion: ", String.valueOf(actividad.getDuracion()) + "\n");
		infoActividad.put("Fecha límite: ", actividad.getFechaLim() + "\n");
		infoActividad.put("ID Profesor creador: ", actividad.getCreadorID() + "\n");
		Iterator<String> it1 = actividad.getObjetivos().iterator();
		String objetivos="";
		int numObjetivo=0;
		
		while (it1.hasNext())
		{
			objetivos += String.valueOf(numObjetivo)+")"+it1.next()+"\n";
			numObjetivo+=1;
		}
		infoActividad.put("Objetivos", objetivos+"\n");
	    infoActividad.put("Rating: ", String.valueOf(actividad.getRating()) + "\n");
	    
	    Iterator<String> it2 = actividad.getActividadesPrereqs().iterator();
	    String actividadesPrereqs="";
	    int numActividad=0;
		while (it2.hasNext())
		{
			actividadesPrereqs += String.valueOf(numActividad) + ")" + it2.next() + "\n";
			numActividad += 1;
		}
		infoActividad.put("Actividades Prerequisitos", actividadesPrereqs + "\n");
		
		Iterator<String> it3 = actividad.getActividadesSigExitoso().iterator();
		String actividadesSigExitoso="";
		int numActividad2=0;
		while (it3.hasNext())
		{
			actividadesSigExitoso += String.valueOf(numActividad2) + ")" + it3.next() + "\n";
			numActividad2 += 1;
		}
		infoActividad.put("Actividades Siguientes Exitosas", actividadesSigExitoso + "\n");
		
		
		Iterator<String> it4 = actividad.getResenias().iterator();
		String resenias="";
		int numResenia=0;
		while (it4.hasNext())
		{
			resenias += String.valueOf(numResenia) + ")" + it4.next() + "\n";
			numResenia += 1;
		}
		infoActividad.put("Resenias", resenias + "\n");
		

		return infoActividad;
	}

}
