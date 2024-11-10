package caminosActividades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import datosEstudiantes.DatosEstudianteActividad;


public class Encuesta extends Actividad{
	
	private List<String> preguntasAbiertas;
	
	//Constructor normal
	public Encuesta(String nombre, String descripcion, List<String> objetivos, double dificultad, int duracion,
			int[] fechaLim, boolean obligatoria, List<String> preguntasAbiertas, String creadorLogin, 
			CaminoAprendizaje camino, int pos) throws Exception
	{
		super(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria, creadorLogin, camino, pos);
		this.preguntasAbiertas = preguntasAbiertas;
		this.type=ENCUESTA;

	}
	
	//Constructor para clonar
	public Encuesta(String creadorLogin, Encuesta ActividadOG, CaminoAprendizaje camino, int pos) throws Exception
	{
		super(creadorLogin, ActividadOG, camino, pos);
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
	
	public JSONObject salvarEnJSON()
	{
        JSONObject jobject = new JSONObject( );
        
        jobject=this.addInfoJSONObject(jobject);
        
        JSONArray preguntasArray= new JSONArray(this.preguntasAbiertas);
        jobject.put("preguntasAbiertas", preguntasArray);

        return jobject;
	}

}
