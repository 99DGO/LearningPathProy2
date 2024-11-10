package caminosActividades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import datosEstudiantes.DatosEstudianteActividad;


public class Encuesta extends Actividad{
	
	private List<String> preguntasAbiertas;
	
	//Constructor normal
	public Encuesta(String nombre, String descripcion, List<String> objetivos, double dificultad, int duracion,
			int[] fechaLim, boolean obligatoria, List<String> preguntasAbiertas, String creadorLogin, CaminoAprendizaje camino) {
		super(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria, creadorLogin, camino);
		this.preguntasAbiertas = preguntasAbiertas;
		this.type=ENCUESTA;

	}
	
	//Constructor para clonar
	public Encuesta(String creadorLogin, Encuesta ActividadOG, CaminoAprendizaje camino)
	{
		super(creadorLogin, ActividadOG, camino);
		this.type=ENCUESTA;
		this.preguntasAbiertas=new ArrayList<String>();

		Iterator<String> it1 = ActividadOG.getPreguntasAbiertas().iterator(); 
    	
    	while (it1.hasNext())
    	{
    		this.preguntasAbiertas.add(it1.next());
    	}
	}
	
	
	//Constructor para cargar
	public Encuesta(String nombre, String descripcion, List<String> objetivos, double dificultad, int duracion,
			int[] fechaLim, boolean obligatoria, double rating, int ratingsTotales, List<String> resenias,
			String creadorLogin, String type, HashMap<String, DatosEstudianteActividad> datosEstudiantes,
			List<String> preguntasAbiertas, String id) {
		super(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria, rating, ratingsTotales, resenias, 
				creadorLogin, type, datosEstudiantes, id);
		this.preguntasAbiertas = preguntasAbiertas;
	}

	public void addPregunta(String pregunta)
	{
		this.preguntasAbiertas.add(pregunta);
	}
	
	public List<String> getPreguntasAbiertas() {
		return preguntasAbiertas;
	}

	public void delPregunta(int pos)
	{
		this.preguntasAbiertas.remove(pos);
	}
	

}
