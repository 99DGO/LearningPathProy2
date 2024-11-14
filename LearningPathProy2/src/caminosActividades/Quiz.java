package caminosActividades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import datosEstudiantes.DatosEstudianteActividad;

public class Quiz extends ActividadCalificable{
	private List<PreguntaQuiz> preguntas;
	private boolean verdaderoFalso; //true si es un quiz de verdadero o falso, false si es de opcion multiple
	
	//Constructor normal
	public Quiz(String nombre, String descripcion, List<String> objetivos, double dificultad, int duracion,
			int[] fechaLim, boolean obligatoria, double calificacionMin, List<PreguntaQuiz> preguntas, 
			String creadorLogin, CaminoAprendizaje camino, boolean verdaderoFalso, int pos) throws Exception
	{
		super(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria, calificacionMin, creadorLogin, camino, pos);
		this.preguntas = preguntas;
		this.type=QUIZ;
		this.verdaderoFalso = verdaderoFalso;
		
	}
	
	//Constructor para clonar
	public Quiz(String creadorID, Quiz ActividadOG, CaminoAprendizaje camino, boolean verdaderoFalso, int pos)throws Exception
	{
		super(creadorID, ActividadOG, camino, pos);
		this.type=QUIZ;
		this.verdaderoFalso = verdaderoFalso;

		this.preguntas=new ArrayList<PreguntaQuiz>();
		
		Iterator<PreguntaQuiz> it1 = ActividadOG.getPreguntas().iterator(); 
		PreguntaQuiz pregunta;
    	
    	while (it1.hasNext())
    	{
    		pregunta=new PreguntaQuiz(it1.next());
    		this.preguntas.add(pregunta);
    	}
	}
	
	//Constructor para cargar
	public Quiz(String nombre, String descripcion, List<String> objetivos, double dificultad, int duracion,
			int[] fechaLim, boolean obligatoria,  double rating, int ratingsTotales, List<String> resenias,
			String creadorLogin, String type, HashMap<String, DatosEstudianteActividad> datosEstudiantes,
			double calificacionMin, List<String> actividadesSigFracaso, List<PreguntaQuiz> preguntas, String id, 
			boolean verdaderoFalso, List<String> actividadesPrereqs, List<String> actividadesSigExitoso)
	{
		super(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria,  rating, ratingsTotales, resenias, creadorLogin, type, datosEstudiantes,
				calificacionMin, actividadesSigFracaso, id, actividadesPrereqs, actividadesSigExitoso);
		this.preguntas = preguntas;
		this.verdaderoFalso = verdaderoFalso;
	}

	public void addPregunta(PreguntaQuiz pregunta)
	{
		this.preguntas.add(pregunta);
	}
	
	public void delPregunta(PreguntaQuiz pregunta)
	{
		this.preguntas.remove(pregunta);
	}
	
	public void delPregunta(int pos)
	{
		this.preguntas.remove(pos);
	}

	public List<PreguntaQuiz> getPreguntas() {
		return preguntas;
	}
	
	public boolean isVerdaderoFalso() {
		return verdaderoFalso;
	}
	
	//TODO
	public JSONObject salvarEnJSON()
	{
        JSONObject jobject = new JSONObject( );
        
        jobject=this.addInfoJSONObject(jobject);
        jobject=this.addInfoCalificableJSON(jobject);
        jobject.put("verdaderoFalso", this.verdaderoFalso);
                
        List<JSONObject> listJPreguntas = new LinkedList<JSONObject>();
        
        for (PreguntaQuiz preguntaObjeto : this.preguntas)
        {
        	JSONObject jPreguntaSingular = preguntaObjeto.getJSONObject();
        	listJPreguntas.add(jPreguntaSingular);
        }
        
        JSONArray jPreguntasTotales = new JSONArray(listJPreguntas);

        jobject.put("preguntas", jPreguntasTotales);
        
        return jobject;
        
	}

}
