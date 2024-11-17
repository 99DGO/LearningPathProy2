package caminosActividades;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import creadores.CreadorAR;
import creadores.CreadorEncuesta;
import creadores.CreadorExamen;
import creadores.CreadorQuiz;
import creadores.CreadorTarea;
import persistencia.ActividadesPersistencia;


public class CaminoAprendizaje {
	
	private String titulo;
	private String descripcion;
	private List<String> objetivos;
	private double dificultad;
	private int duracion;
	private String fechaCreacion;
	private double rating;
	private int ratingsTotales;
	private int version;
	private String fechaModificacion;
	private int numActividadesObligatorias;
	private List<Actividad> actividades; 
	private String creadorID;
	private final String ID;

//Constructor normal
	public CaminoAprendizaje(String titulo, String descripcion, List<String> objetivos, double dificultad, String creadorID) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.objetivos = objetivos;
		this.dificultad = dificultad;
		this.duracion = 0;
		this.fechaCreacion= new Date().toString();
		this.fechaModificacion=new Date().toString();
		this.creadorID=creadorID;
		this.actividades=new ArrayList<Actividad>();
		
		this.ratingsTotales=0;
		this.version=1;
		this.numActividadesObligatorias=0;
		this.ID="Camino"+UUID.randomUUID().toString();

	}
	
//Constructor clonador
	public CaminoAprendizaje(CaminoAprendizaje caminoOG, String creadorID, String titulo) throws Exception
	{
		this.titulo= titulo;
		this.descripcion= caminoOG.getDescripcion();
		this.dificultad= caminoOG.getDificultad();
		this.numActividadesObligatorias=caminoOG.getNumActividadesObligatorias();
		this.fechaModificacion=new Date().toString();

		this.actividades=new ArrayList<Actividad>();
		this.objetivos = new ArrayList<String>();
		this.ratingsTotales=0;
		this.version=1;
		
		this.fechaCreacion= new Date().toString();

		for (String objetivo : caminoOG.getObjetivos()) {
	        this.objetivos.add(objetivo);
	    }
    	
    	
    	//Copia de actividades
    	Iterator<Actividad> it2 = caminoOG.getActividades().iterator(); 
    	Actividad actividad;
    	int i =0;
    	
    	while (it2.hasNext())
    	{
    		Actividad act2 = it2.next();
    		if (act2 .getType().equals(Actividad.ENCUESTA))
    		{
    			actividad=new Encuesta (creadorID, (Encuesta) act2 , this, i);
    		}
    		
    		else if (act2 .getType().equals(Actividad.ACTIVIDADRECURSO))
    		{
    			actividad=new ActividadRecurso (creadorID, (ActividadRecurso) act2, this, i );
    		}
    		
    		else if (act2 .getType().equals(Actividad.EXAMEN))
    		{
    			actividad=new Examen (creadorID, (Examen) act2, this, i );
    		}
    		
    		else if (act2 .getType().equals(Actividad.QUIZ))
    		{
				if (((Quiz) act2).isVerdaderoFalso()) {
					actividad = new Quiz(creadorID, (Quiz) act2, this, true, i);
				} else {
					actividad = new Quiz(creadorID, (Quiz) act2, this, false, i);
				}
    		}
    		
    		else
    		{
    			actividad= new Tarea (creadorID, (Tarea) act2, this, i);
    		}
    		
    		i+=1;
    	}
    			
		this.creadorID=creadorID;
		this.ID="Camino"+UUID.randomUUID().toString();
	}

//Constructor cargar
	public CaminoAprendizaje(String titulo, String descripcion, List<String> objetivos, double dificultad, int duracion,
			String fechaCreacion, double rating, int ratingsTotales, int version, String fechaModificacion,
			int numActividadesObligatorias, List<Actividad> actividades, String creadorID, String id) {
		super();
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.objetivos = objetivos;
		this.dificultad = dificultad;
		this.duracion = duracion;
		this.fechaCreacion = fechaCreacion;
		this.rating = rating;
		this.ratingsTotales = ratingsTotales;
		this.version = version;
		this.fechaModificacion = fechaModificacion;
		this.numActividadesObligatorias = numActividadesObligatorias;
		this.actividades = actividades;
		this.creadorID = creadorID;
		this.ID=id;
	}

	
	public String getID() {
		return ID;
	}

	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getCreadorID()
	{
		return this.creadorID;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public double getRating() {
		return rating;
	}

	public int getRatingsTotales() {
		return ratingsTotales;
	}

	public int getVersion() {
		return version;
	}

	public String getFechaModificacion() {
		return fechaModificacion;
	}

	public int getNumActividadesObligatorias() {
		return this.numActividadesObligatorias;
	}

	public List<Actividad> getActividades() {
		return actividades;
	}
	
	public void addRating(double ratingNuevo)
	{
		double sumatoriaPrev=this.rating*this.ratingsTotales;
		this.ratingsTotales+=1;
		this.rating=(sumatoriaPrev+ratingNuevo)/this.ratingsTotales;
	}
	
	public void addObjetivo(String objetivo)
	{
		this.objetivos.add(objetivo);
	}
	
	/**
	 * @param Actividad actividad, int posicion
	 * Añade una actividad en el camino en la posicion indicada
	 * Actualiza la duracion del camino en total
	 * añade al contador de obligatorias si es obligatoria
	 */
	public void addActividad(Actividad actividad, int pos) throws Exception
	{
		if (pos < 0 || pos > this.actividades.size())
		{
			throw new Exception ("La posicion indicada no existe");
		}
		else
		{
			this.actividades.add(pos, actividad);
			this.duracion+=actividad.getDuracion();
		}
		
		if (actividad.isObligatoria())
		{
			this.numActividadesObligatorias+=1;
		}
	}
	
	/**
	 * @param Actividad actividad
	 * Añade una actividad al final del camino
	 * Actualiza la duracion del camino en total
	 * 	 * añade al contador de obligatorias si es obligatoria
	 */
	public void addActividad(Actividad actividad)
	{
		this.actividades.add(actividad);
		this.duracion+=actividad.getDuracion();
		
		if (actividad.isObligatoria())
		{
			this.numActividadesObligatorias+=1;
		}
	}
	
	public void delActividad(int pos)
	{
		this.actividades.remove(pos);
	}
	
	public void delObjetivo(int pos)
	{
		this.objetivos.remove(pos);
	}

	public void cambiarPosActividad(String idActividad, int newPos) throws Exception
	{

		if(newPos<this.actividades.size() && (newPos>=0))
		{
			Iterator<Actividad> it1 = actividades.iterator();
			int posIterator=0;
			
			while (it1.hasNext())
			{
				Actividad actividad=it1.next();
				
				if (actividad.getId().equals(idActividad))
				{
					actividades.remove(posIterator);
					actividades.set(newPos, actividad);
				}
			}
		} 
		else
		{
			throw new Exception ("La posicion de la actividad no es valida");
		}
	}


	
    /**
     * Salva este objeto de tipo camino dentro de un objeto JSONObject para que ese objeto se almacene en un archivo
     * @return El objeto JSON con toda la información del camino
     */
	
    public JSONObject salvarEnJSON( )
    {
    	
        JSONObject jobject = new JSONObject( );
        jobject.put( "titulo", this.titulo );
        jobject.put( "descripcion", this.descripcion );
        jobject.put("dificultad", this.dificultad);
        jobject.put("duracion", this.duracion);
        jobject.put("rating", this.rating);
        jobject.put("fechaCreacion", this.fechaCreacion);
        jobject.put("ratingsTotales", this.ratingsTotales);
        jobject.put("version", this.version);
        jobject.put("fechaModificacion", this.fechaModificacion);
        jobject.put("numActividadesObligatorias", this.numActividadesObligatorias);
        jobject.put( "creadorID", this.creadorID );
        jobject.put("id", this.ID);
        
     
        List<String> listaIDsActividades= new LinkedList<String>();
        for (Actividad actividad: this.actividades)
        {
        	listaIDsActividades.add(actividad.getId());
        }
        
        JSONArray objetivosArray= new JSONArray(this.objetivos);
        JSONArray actividadesID= new JSONArray(listaIDsActividades);

        
        jobject.put( "actividades", actividadesID );
        
        jobject.put( "objetivos", objetivosArray );
        
        return jobject;
        
    }
  
}
