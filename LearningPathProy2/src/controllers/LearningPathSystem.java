package controllers;

import caminosActividades.CaminoAprendizaje;
import java.util.HashMap;

import usuarios.Estudiante;
import usuarios.Profesor;
import usuarios.Usuario;

public class LearningPathSystem {
	
	private HashMap<String, Estudiante> estudiantes;
	private HashMap<String, Profesor>profesores;
	private HashMap<String, CaminoAprendizaje> caminos;
	private static LearningPathSystem LPS=null;
	
	private LearningPathSystem()
	{
		
	}
	
	public static LearningPathSystem getInstance()
	{
		 if (LPS == null)
	            LPS = new LearningPathSystem();
	 
	     return LPS;
	
	}
	
	
	public HashMap<String, Estudiante> getEstudiantes() {
		return estudiantes;
	}
	
	public Estudiante getEstudianteIndividual(String IDestudiante)
	{
		return estudiantes.get(IDestudiante);
	}

	public void setEstudiantes(HashMap<String, Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}

	public void setProfesores(HashMap<String, Profesor> profesores) {
		this.profesores = profesores;
	}

	public HashMap<String, Profesor> getProfesores() {
		return profesores;
	}
	
	public Profesor getProfesorIndividual(String IDprofesor)
	{
		return profesores.get(IDprofesor);
	}
	
	
	public void addProfesor(Profesor profesor)
	{
		this.profesores.put(profesor.getID(), profesor);
	}
	
	public void addEstudiante(Estudiante estudiante)
	{
		this.estudiantes.put(estudiante.getID(), estudiante);
		
	}
	public HashMap<String, CaminoAprendizaje> getCaminos() {
		return caminos;
	}
	
	public void setCaminos(HashMap<String, CaminoAprendizaje> caminos) {
		this.caminos = caminos;
	}
	

	public void addCamino(CaminoAprendizaje camino)
	{
		this.caminos.put(camino.getID(), camino);
	}
	
	
	public CaminoAprendizaje getCaminoIndividual(String titulo)
	{
		return this.caminos.get(titulo);
	}
	
	
	public void delCamino(CaminoAprendizaje camino)
	{
		this.caminos.remove(camino.getTitulo());
	}
	

}
