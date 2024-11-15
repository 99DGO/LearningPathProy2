package caminosActividades;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import datosEstudiantes.DatosEstudianteActividad;


public class Examen extends ActividadCalificable{
	private List<String> preguntasAbiertas=new LinkedList<String>();
	
	//Constructor normal
	public Examen(String nombre, String descripcion, List<String> objetivos, double dificultad, int duracion,
			int[] fechaLim, boolean obligatoria, double calificacionMin, List<String> preguntasAbiertas, String creadorLogin, 
			CaminoAprendizaje camino, int pos) throws Exception
	{
		super(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria, calificacionMin, creadorLogin, camino, pos);
		this.preguntasAbiertas = preguntasAbiertas;
		this.type=EXAMEN;

	}
	
	//Constructor ??
	public Examen(String nombre, String descripcion, List<String> objetivos, double dificultad, int duracion,
			int[] fechaLim, boolean obligatoria, double calificacionMin, String creadorLogin,
			List<String> preguntasAbiertas, CaminoAprendizaje camino, int pos) throws Exception
	{
		super(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria, calificacionMin,
				creadorLogin, camino, pos);
		this.preguntasAbiertas = preguntasAbiertas;
	}

	//Constructor para cargar
	public Examen(String nombre, String descripcion, List<String> objetivos, double dificultad, int duracion,
			int[] fechaLim, boolean obligatoria, double rating, int ratingsTotales, List<String> resenias,
			String creadorLogin, String type, HashMap<String, DatosEstudianteActividad> datosEstudiantes,
			double calificacionMin, List<String> actividadesSigFracaso, List<String> preguntasAbiertas, String id,
			List<String> actividadesPrereqs, List<String> actividadesSigExitoso) 
	{
		super(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria, rating, ratingsTotales, resenias, creadorLogin, type, datosEstudiantes,
				calificacionMin, actividadesSigFracaso, id, actividadesPrereqs, actividadesSigExitoso);
		this.preguntasAbiertas = preguntasAbiertas;
	}
	
	//Constructor para clonar
	public Examen(String creadorLogin, Examen ActividadOG, CaminoAprendizaje camino, int pos) throws Exception
	{
		super(creadorLogin, ActividadOG, camino, pos);
		
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
