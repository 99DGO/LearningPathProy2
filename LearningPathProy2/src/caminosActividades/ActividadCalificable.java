package caminosActividades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import datosEstudiantes.DatosEstudianteActividad;

public abstract class ActividadCalificable extends Actividad{
	protected double calificacionMin;
	protected List<String> actividadesSigFracaso;
	
	//Constructor normal
	public ActividadCalificable(String nombre, String descripcion, List<String> objetivos, double dificultad,
			int duracion, int[] fechaLim, boolean obligatoria, double calificacionMin, String creadorLogin,
			CaminoAprendizaje camino, int pos) throws Exception
	{
		
		super(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria, creadorLogin, camino, pos);
		this.calificacionMin = calificacionMin;
		this.actividadesSigFracaso=new ArrayList<String>();
	}
	
	
	//Constructor para cargar
	public ActividadCalificable(String nombre, String descripcion, List<String> objetivos, double dificultad, int duracion,
			int[] fechaLim, boolean obligatoria,  double rating, int ratingsTotales, List<String> resenias,
			String creadorLogin, String type, HashMap<String, DatosEstudianteActividad> datosEstudiantes,
			double calificacionMin, List<String> actividadesSigFracaso, String id, List<String> actividadesPrereqs,
			List<String> actividadesSigExitoso)
	{
		super(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria, rating, ratingsTotales, resenias,
				creadorLogin, type, datosEstudiantes, id, actividadesPrereqs,  actividadesSigExitoso);
		this.calificacionMin = calificacionMin;
		this.actividadesSigFracaso = actividadesSigFracaso;
	}

	//Constructor clonador
	public ActividadCalificable(String creadorLogin, ActividadCalificable ActividadOG, CaminoAprendizaje camino, int pos) throws Exception
	{
		super(creadorLogin, ActividadOG, camino, pos);
		this.calificacionMin=ActividadOG.getCalificacionMin();
		this.actividadesSigFracaso=new ArrayList<String>();

	}
	
	
	public double getCalificacionMin() {
		return calificacionMin;
	}

	public void setCalificacionMin(double calificacionMin) {
		this.calificacionMin = calificacionMin;
	}

	public List<String> getActividadesSigFracaso() {
		return actividadesSigFracaso;
	}
	
	public void addActividadSigFracaso(String actividad)
	{
		this.actividadesSigFracaso.add(actividad);
	}
	
	public void delActividadSigFracaso(String actividad)
	{
		this.actividadesSigFracaso.remove(actividad);
	}
	
	public void delActividadSigFracaso(int pos)
	{
		this.actividadesSigFracaso.remove(pos);
	}
	
	public JSONObject addInfoCalificableJSON(JSONObject jobject)
	{
   
        JSONArray actividadesSigFracasoArray = new JSONArray (this.actividadesSigFracaso);
        jobject.put("actividadesSigFracaso", actividadesSigFracasoArray);
        
        jobject.put("calificacionMin", this.calificacionMin);
        
		return jobject;
	}

}
