package caminosActividades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import datosEstudiantes.DatosEstudianteActividad;

public class Tarea extends Actividad{

	private String instrucciones;
	private List<String> actividadesSigFracaso;
	
	//Constructor normal
	public Tarea(String nombre, String descripcion, List<String> objetivos, double dificultad, int duracion,
			int[] fechaLim, boolean obligatoria, String instrucciones, String creadorLogin, 
			CaminoAprendizaje camino, int pos)throws Exception
	{
		super(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria, creadorLogin, camino, pos);
		this.instrucciones = instrucciones;
		this.type=TAREA;

		this.actividadesSigFracaso=new ArrayList<String>();
	}
	
	/**Constructo clonar 
	 * No copia actividades siguientes exitososas ni las de fracaso
	 * @param creadorLogin
	 * @param ActividadOG
	 */
	public Tarea(String creadorLogin, Tarea ActividadOG, CaminoAprendizaje camino, int pos)throws Exception
	{
		super(creadorLogin, ActividadOG, camino, pos);
		this.type=TAREA;

		this.actividadesSigFracaso=new ArrayList<String>();
		
	}
	
	
	//Constructor para cargar
	public Tarea(String nombre, String descripcion, List<String> objetivos, double dificultad, int duracion,
			int[] fechaLim, boolean obligatoria, double rating, int ratingsTotales, List<String> resenias,
			String creadorLogin, String type, HashMap<String, DatosEstudianteActividad> datosEstudiantes,
			String instrucciones, List<String> actividadesSigFracaso, String id, List<String> actividadesPrereqs, 
			List<String> actividadesSigExitoso)
	{
		super(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria,  rating, ratingsTotales, resenias, 
				creadorLogin, type, datosEstudiantes, id, actividadesPrereqs, actividadesSigExitoso);
		this.instrucciones = instrucciones;
		this.actividadesSigFracaso = actividadesSigFracaso;
	}

	public String getInstrucciones() {
		return instrucciones;
	}


	public void setInstrucciones(String instrucciones) {
		this.instrucciones = instrucciones;
	}


	public List<String> getActividadesSigFracaso() {
		return actividadesSigFracaso;
	}
	
	public void addActividadSigFracaso(String IDactividad)
	{
		this.actividadesSigFracaso.add(IDactividad);
	}
	
	public void delActividadSigFracaso(String IDactividad) 
	{
		this.actividadesSigFracaso.remove(IDactividad);
	}
	
	public void delActividadSigFracaso(int pos) 
	{
		this.actividadesSigFracaso.remove(pos);
	}
	
	public JSONObject salvarEnJSON()
	{
        JSONObject jobject = new JSONObject( );
        
        jobject=this.addInfoJSONObject(jobject);
        
        jobject.put("instrucciones", this.instrucciones);

        JSONArray actividadesSigFracasoArray = new JSONArray (this.actividadesSigFracaso);
        jobject.put("actividadesSigFracaso", actividadesSigFracasoArray);
        
        return jobject;
	}
	
}
