package caminosActividades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import datosEstudiantes.DatosEstudianteActividad;

public abstract class Actividad {
	public static final String ENCUESTA="Encuesta";
	public static final String EXAMEN="Examen";
	public static final String QUIZ="Quiz";
	public static final String TAREA="Tarea";
	public static final String ACTIVIDADRECURSO="Actividad recurso";

	private String nombre;
	private String descripcion;
	private List<String> objetivos;
	private double dificultad;
	private int duracion;
	private int[] fechaLim;
	private boolean obligatoria;
	private List<Actividad> actividadesPrereqs;
	private List<Actividad> actividadesSigExitoso;
	private double rating;
	private int ratingsTotales;
	private List<String> resenias;
	private String creadorID;
	private final String ID;
	
	protected String type;
	protected HashMap<String, DatosEstudianteActividad> datosEstudiantes;
	
	//Constructor normal
	public Actividad(String nombre, String descripcion, List<String> objetivos, double dificultad, int duracion, int[] fechaLim,
			boolean obligatoria, String creadorID, CaminoAprendizaje camino, int pos) throws Exception
	{
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.objetivos = objetivos;
		this.dificultad = dificultad;
		this.fechaLim = fechaLim;
		this.duracion=duracion;
		this.obligatoria = obligatoria;
		this.creadorID=creadorID;
		this.datosEstudiantes = new HashMap<>();
		this.actividadesPrereqs=new ArrayList<Actividad>();
		this.actividadesSigExitoso=new ArrayList<Actividad>();
		this.ID="Actividad"+UUID.randomUUID().toString();
		camino.addActividad(this, pos);
	}
	
	/**Constructor para clonar
	 * No copia actividades prerequisitos o actividades fracasos siguientes
	 * @param creadorID
	 * @param ActividadOG
	 */
	public Actividad(String creadorID, Actividad ActividadOG, CaminoAprendizaje camino, int pos) throws Exception
	{
		this.nombre = ActividadOG.getNombre();
		this.descripcion = ActividadOG.getDescripcion();
		this.dificultad = ActividadOG.getDificultad();
		this.fechaLim = ActividadOG.getFechaLim();
		this.duracion=ActividadOG.getDuracion();
		this.obligatoria = ActividadOG.isObligatoria();
		this.datosEstudiantes = new HashMap<>();
		
		this.creadorID=creadorID;
		this.actividadesPrereqs=new ArrayList<Actividad>();
		this.actividadesSigExitoso=new ArrayList<Actividad>();
		
		Iterator<String> it1 = ActividadOG.getObjetivos().iterator(); 
    	
    	while (it1.hasNext())
    	{
    		this.objetivos.add(it1.next());
    	}
		this.ID="Actividad"+UUID.randomUUID().toString();
	
		camino.addActividad(this, pos);

	}
	

	
	//Constructor para cargar?
	public Actividad(String nombre, String descripcion, List<String> objetivos, double dificultad, int duracion,
			int[] fechaLim, boolean obligatoria, double rating, int ratingsTotales, List<String> resenias,
			String creadorID, String type, HashMap<String, DatosEstudianteActividad> datosEstudiantes, String id) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.objetivos = objetivos;
		this.dificultad = dificultad;
		this.duracion = duracion;
		this.fechaLim = fechaLim;
		this.obligatoria = obligatoria;
		this.rating = rating;
		this.ratingsTotales = ratingsTotales;
		this.resenias = resenias;
		this.creadorID = creadorID;
		this.type = type;
		this.datosEstudiantes = datosEstudiantes;
		this.ID=id;
	}

	public String getCreadorID()
	{
		return this.creadorID;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<String> getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(List<String> objetivos) {
		this.objetivos = objetivos;
	}
	

	public double getDificultad() {
		return dificultad;
	}

	public void setDificultad(double dificultad) {
		this.dificultad = dificultad;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int[] getFechaLim() {
		return fechaLim;
	}

	public void setFechaLim(int[] fechaLim) {
		this.fechaLim = fechaLim;
	}

	public boolean isObligatoria() {
		return obligatoria;
	}

	public void setObligatoria(boolean obligatoria) {
		this.obligatoria = obligatoria;
	}

	public List<Actividad> getActividadesPrereqs() {
		return actividadesPrereqs;
	}

	public List<Actividad> getActividadesSigExitoso() {
		return actividadesSigExitoso;
	}

	public double getRating() {
		return rating;
	}

	public List<String> getResenias() {
		return resenias;
	}
	
	public void addRating(double ratingNuevo)
	{
		double sumatoriaPrev=this.rating*this.ratingsTotales;
		this.ratingsTotales+=1;
		this.rating=(sumatoriaPrev+ratingNuevo)/this.ratingsTotales;
	}

	public void addResenia(String resenia) 
	{
		this.resenias.add(resenia);
	}
	
	public void addActividadSiguienteExitosa(Actividad actividad)
	{
		this.actividadesSigExitoso.add(actividad);
	}
	
	public void addActividadPrereq(Actividad actividad)
	{
		this.actividadesPrereqs.add(actividad);
	}
	
	public void addObjetivo(String objetivo)
	{
		this.objetivos.add(objetivo);
	}
	
	public void delActividadPrereq(Actividad actividad)
	{
		this.actividadesPrereqs.remove(actividad);
	}
	
	public void delActividadPrereq(int pos)
	{
		this.actividadesPrereqs.remove(pos);
	}
	
	public void delObjetivo(String objetivo)
	{
		this.objetivos.remove(objetivo);
	}
	
	public void delObjetivo(int pos)
	{
		this.objetivos.remove(pos);
	}
	
	public void delActividadSiguienteExitosa(Actividad actividad)
	{
		this.actividadesSigExitoso.remove(actividad);
	}
	
	public void delActividadSiguienteExitosa(int pos)
	{
		this.actividadesSigExitoso.remove(pos);
	}
	
	/**
	 * @param loginEstudiante
	 */
	public void putDatoEstudiante(DatosEstudianteActividad dato)
	{
		this.datosEstudiantes.put(dato.getID(), dato );
	}
	
	public DatosEstudianteActividad getDatoEstudianteIndividual(String IDestudiante)
	{
		return this.datosEstudiantes.get(IDestudiante);
	}
	
	public HashMap<String, DatosEstudianteActividad> getDatosEstudiantes()
	{
		return this.datosEstudiantes;
	}
	/**
	 * @param actividadesPrereqs the actividadesPrereqs to set
	 */
	public void setActividadesPrereqs(List<Actividad> actividadesPrereqs) {
		this.actividadesPrereqs = actividadesPrereqs;
	}

	/**
	 * @param actividadesSigExitoso the actividadesSigExitoso to set
	 */
	public void setActividadesSigExitoso(List<Actividad> actividadesSigExitoso) {
		this.actividadesSigExitoso = actividadesSigExitoso;
	}

	public String getId() 
	{
		return ID;
	}
	
	public JSONObject addInfoJSONObject(JSONObject jobject)
	{
		jobject.put("nombre", this.nombre);
		jobject.put("descripcion", this.descripcion);
        jobject.put("dificultad", this.dificultad);
        jobject.put("duracion", this.duracion);
        jobject.put("rating", this.rating);
        jobject.put("obligatoria", this.obligatoria);
        jobject.put("ratingsTotales", this.ratingsTotales);
        jobject.put( "creadorID", this.creadorID );
        jobject.put("id", this.ID);
        jobject.put("type", this.type);
        
        JSONArray objetivosArray= new JSONArray(this.objetivos);
        jobject.put("objetivos", objetivosArray);
        
        JSONArray fechaLimArray= new JSONArray(this.fechaLim);
        jobject.put("fechaLim", fechaLimArray);
        
        JSONArray reseniasArray= new JSONArray(this.resenias);
        jobject.put("resenias", reseniasArray);

        List<String> listaIDsActividadesPrereqs= new LinkedList<String>();
        for (Actividad actividad: this.actividadesPrereqs)
        {
        	listaIDsActividadesPrereqs.add(actividad.getId());
        }
        JSONArray actividadesPrereqsArray = new JSONArray (listaIDsActividadesPrereqs);
        jobject.put("actividadesPrereqs", actividadesPrereqsArray);
        
        
        List<String> listaIDsActividadesSigExitoso= new LinkedList<String>();
        for (Actividad actividad: this.actividadesPrereqs)
        {
        	listaIDsActividadesSigExitoso.add(actividad.getId());
        }
        JSONArray actividadesSigExitosoArray = new JSONArray (listaIDsActividadesSigExitoso);
        jobject.put("actividadesSigExitoso", actividadesSigExitosoArray);
        
        
        JSONArray datosEstudiantesArray = new JSONArray (this.datosEstudiantes.keySet());
        jobject.put("actividadesSigExitoso", datosEstudiantesArray);
   
		return jobject;
	}

	public abstract JSONObject salvarEnJSON();
}
