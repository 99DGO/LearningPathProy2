package caminosActividades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import datosEstudiantes.DatosEstudianteActividad;

public abstract class ActividadCalificable extends Actividad{
	protected double calificacionMin;
	protected List<Actividad> actividadesSigFracaso;
	
	//Constructor normal
	public ActividadCalificable(String nombre, String descripcion, List<String> objetivos, double dificultad,
			int duracion, int[] fechaLim, boolean obligatoria, double calificacionMin, String creadorLogin,CaminoAprendizaje camino) {
		
		super(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria, creadorLogin, camino);
		this.calificacionMin = calificacionMin;
		this.actividadesSigFracaso=new ArrayList<Actividad>();
	}
	
	
	//Constructor para cargar
	public ActividadCalificable(String nombre, String descripcion, List<String> objetivos, double dificultad, int duracion,
			int[] fechaLim, boolean obligatoria,  double rating, int ratingsTotales, List<String> resenias,
			String creadorLogin, String type, HashMap<String, DatosEstudianteActividad> datosEstudiantes,
			double calificacionMin, List<Actividad> actividadesSigFracaso, String id) {
		super(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria, rating, ratingsTotales, resenias, creadorLogin, type, datosEstudiantes, id);
		this.calificacionMin = calificacionMin;
		this.actividadesSigFracaso = actividadesSigFracaso;
	}

	//Constructor clonador
	public ActividadCalificable(String creadorLogin, ActividadCalificable ActividadOG, CaminoAprendizaje camino)
	{
		super(creadorLogin, ActividadOG, camino);
		this.calificacionMin=ActividadOG.getCalificacionMin();
		this.actividadesSigFracaso=new ArrayList<Actividad>();

	}
	
	
	public double getCalificacionMin() {
		return calificacionMin;
	}

	public void setCalificacionMin(double calificacionMin) {
		this.calificacionMin = calificacionMin;
	}

	public List<Actividad> getActividadesSigFracaso() {
		return actividadesSigFracaso;
	}
	
	public void addActividadSigFracaso(Actividad actividad)
	{
		this.actividadesSigFracaso.add(actividad);
	}
	
	public void delActividadSigFracaso(Actividad actividad)
	{
		this.actividadesSigFracaso.remove(actividad);
	}
	
	public void delActividadSigFracaso(int pos)
	{
		this.actividadesSigFracaso.remove(pos);
	}

}
