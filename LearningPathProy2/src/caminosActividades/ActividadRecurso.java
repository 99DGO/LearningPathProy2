package caminosActividades;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import datosEstudiantes.DatosEstudianteActividad;

public class ActividadRecurso extends Actividad {

	private String recurso;
	private String instrucciones;

	//Constructor normal
	public ActividadRecurso(String nombre, String descripcion, List<String> objetivos, double dificultad, int duracion,
			int[] fechaLim, boolean obligatoria, String recurso, String instrucciones, String creadorID, 
			CaminoAprendizaje camino, int pos) throws Exception
	{
		super(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria, creadorID, camino, pos);
		this.recurso = recurso;
		this.instrucciones = instrucciones;
		this.type=ACTIVIDADRECURSO;
	}

	//Constructor para clonar
	public ActividadRecurso(String creadorLogin, ActividadRecurso ActividadOG, CaminoAprendizaje camino, int pos) throws Exception
	{
		super(creadorLogin, ActividadOG, camino, pos);
		
		this.recurso = ActividadOG.getRecurso();
		this.instrucciones = ActividadOG.getInstrucciones();
		this.type=ACTIVIDADRECURSO;

	}
	
	
//Constructor para cargar
	public ActividadRecurso(String nombre, String descripcion, List<String> objetivos, double dificultad, int duracion,
			int[] fechaLim, boolean obligatoria, double rating, int ratingsTotales, List<String> resenias,
			String creadorLogin, String type, HashMap<String, DatosEstudianteActividad> datosEstudiantes,
			String recurso, String instrucciones, String id, List<String> actividadesPrereqs, List<String> actividadesSigExitoso) 
	{
		super(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria, rating, ratingsTotales, resenias, 
				creadorLogin, type, datosEstudiantes, id, actividadesPrereqs, actividadesSigExitoso);
		this.recurso = recurso;
		this.instrucciones = instrucciones;
	}

	public String getRecurso() {
		return recurso;
	}

	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}

	public String getInstrucciones() {
		return instrucciones;
	}

	public void setInstrucciones(String instrucciones) {
		this.instrucciones = instrucciones;
	}
	
	public JSONObject salvarEnJSON()
	{
        JSONObject jobject = new JSONObject( );
        
        jobject=this.addInfoJSONObject(jobject);
        
        jobject.put("instrucciones", this.instrucciones);
        jobject.put("recurso", this.recurso);
   
        return jobject;
	}
	
}
