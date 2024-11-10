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
	private List<Actividad> actividadesSigFracaso;
	
	//Constructor normal
	public Tarea(String nombre, String descripcion, List<String> objetivos, double dificultad, int duracion,
			int[] fechaLim, boolean obligatoria, String instrucciones, String creadorLogin, CaminoAprendizaje camino) {
		super(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria, creadorLogin, camino);
		this.instrucciones = instrucciones;
		this.type=TAREA;

		this.actividadesSigFracaso=new ArrayList<Actividad>();
	}
	
	/**Constructo clonar 
	 * No copia actividades siguientes exitososas ni las de fracaso
	 * @param creadorLogin
	 * @param ActividadOG
	 */
	public Tarea(String creadorLogin, Tarea ActividadOG, CaminoAprendizaje camino)
	{
		super(creadorLogin, ActividadOG, camino);
		this.type=TAREA;

		this.actividadesSigFracaso=new ArrayList<Actividad>();
		
	}
	
	
	//Constructor para cargar
	public Tarea(String nombre, String descripcion, List<String> objetivos, double dificultad, int duracion,
			int[] fechaLim, boolean obligatoria, double rating, int ratingsTotales, List<String> resenias,
			String creadorLogin, String type, HashMap<String, DatosEstudianteActividad> datosEstudiantes,
			String instrucciones, List<Actividad> actividadesSigFracaso, String id) {
		super(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria,  rating, ratingsTotales, resenias, 
				creadorLogin, type, datosEstudiantes, id);
		this.instrucciones = instrucciones;
		this.actividadesSigFracaso = actividadesSigFracaso;
	}

	public String getInstrucciones() {
		return instrucciones;
	}


	public void setInstrucciones(String instrucciones) {
		this.instrucciones = instrucciones;
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
	
	public JSONObject salvarEnJSON()
	{
        JSONObject jobject = new JSONObject( );
        
        jobject=this.addInfoJSONObject(jobject);
        
        jobject.put("instrucciones", this.instrucciones);

        List<String> listaIDsActividadesSigFracaso= new LinkedList<String>();
        for (Actividad actividad: this.actividadesSigFracaso)
        {
        	listaIDsActividadesSigFracaso.add(actividad.getId());
        }
        JSONArray actividadesSigFracasoArray = new JSONArray (listaIDsActividadesSigFracaso);
        jobject.put("actividadesSigFracaso", actividadesSigFracasoArray);
        
        return jobject;
	}
	
}
