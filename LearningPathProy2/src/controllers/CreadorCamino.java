package controllers;

import java.util.List;

import caminosActividades.CaminoAprendizaje;
import usuarios.Profesor;

public class CreadorCamino 
{
	public static void crearCaminoCero(String titulo, String descripcion, List<String> objetivos, double dificultad, 
			String IDprofesor) throws Exception 
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		Profesor profesor=(Profesor) LPS.getUsuarioIndividal(IDprofesor);
		
		if (!(LPS.getCaminoIndividual(titulo)==null))
		{
			throw new Exception ("Ya existe un camino con ese titulo");
		}
		else
		{
			CaminoAprendizaje camino= new CaminoAprendizaje(titulo, descripcion, objetivos, dificultad, 
					 profesor.getID());
			profesor.addCamino(camino);
			LPS.addCamino(camino);
		}
		
	}
	

	public static void clonarCamino(String IDcaminoOG, String tituloCamino,  String IDprofesor) throws Exception 
	{

		LearningPathSystem LPS= LearningPathSystem.getInstance();
		Profesor profesor=(Profesor) LPS.getUsuarioIndividal(IDprofesor);
		CaminoAprendizaje caminoOG= LPS.getCaminoIndividual(IDcaminoOG);
		
		if (!(LPS.getCaminoIndividual(tituloCamino).equals(null)) )
		{
			throw new Exception ("Ya existe un camino con ese titulo");
		}
		else
		{
			CaminoAprendizaje camino= new CaminoAprendizaje(caminoOG, tituloCamino, profesor.getID());
			profesor.addCamino(camino);
			LPS.addCamino(camino);
		}
	}

}
