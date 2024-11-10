package caminosActividades;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import datosEstudiantes.DatosEstudianteActividad;


public class Examen extends ActividadCalificable{
	private List<String> preguntasAbiertas;
	
	//Constructor normal
	public Examen(String nombre, String descripcion, List<String> objetivos, double dificultad, int duracion,
			int[] fechaLim, boolean obligatoria, double calificacionMin, List<String> preguntasAbiertas, String creadorLogin, CaminoAprendizaje camino) {
		super(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria, calificacionMin, creadorLogin, camino);
		this.preguntasAbiertas = preguntasAbiertas;
		this.type=EXAMEN;

	}
	
	//Constructor para clonar
	public Examen(String nombre, String descripcion, List<String> objetivos, double dificultad, int duracion,
			int[] fechaLim, boolean obligatoria, double calificacionMin, String creadorLogin,
			List<String> preguntasAbiertas, CaminoAprendizaje camino) {
		super(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria, calificacionMin,
				creadorLogin, camino);
		this.preguntasAbiertas = preguntasAbiertas;
	}

	//Constructor para cargar
	public Examen(String nombre, String descripcion, List<String> objetivos, double dificultad, int duracion,
			int[] fechaLim, boolean obligatoria, double rating, int ratingsTotales, List<String> resenias,
			String creadorLogin, String type, HashMap<String, DatosEstudianteActividad> datosEstudiantes,
			double calificacionMin, List<Actividad> actividadesSigFracaso, List<String> preguntasAbiertas, String id) {
		super(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria, rating, ratingsTotales, resenias, creadorLogin, type, datosEstudiantes,
				calificacionMin, actividadesSigFracaso, id);
		this.preguntasAbiertas = preguntasAbiertas;
	}

	public Examen(String creadorLogin, Examen ActividadOG, CaminoAprendizaje camino)
	{
		super(creadorLogin, ActividadOG, camino);
		
		Iterator<String> it1 = ActividadOG.getPreguntasAbiertas().iterator(); 
    	
    	while (it1.hasNext())
    	{
    		this.preguntasAbiertas.add(it1.next());
    	}
    	
		this.type=EXAMEN;
	}
	
	public void addPregunta(String pregunta)
	{
		this.preguntasAbiertas.add(pregunta);
	}
	
	public void delPregunta(String pregunta)
	{
		this.preguntasAbiertas.remove(pregunta);
	}
	
	public void delPregunta(int pos)
	{
		this.preguntasAbiertas.remove(pos);
	}

	public List<String> getPreguntasAbiertas() {
		return preguntasAbiertas;
	}
	
	public JSONObject salvarEnJSON()
	{
        JSONObject jobject = new JSONObject( );
        
        jobject=this.addInfoJSONObject(jobject);
        jobject=this.addInfoCalificableJSON(jobject);
        
        JSONArray preguntasArray= new JSONArray(this.preguntasAbiertas);
        jobject.put("preguntasAbiertas", preguntasArray);


        return jobject;
	}
	

}
