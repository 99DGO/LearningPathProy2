package caminosActividades;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

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
	private String creadorLogin;
	private final String ID;

//Constructor normal
	public CaminoAprendizaje(String titulo, String descripcion, List<String> objetivos, double dificultad, String creadorLogin) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.objetivos = objetivos;
		this.dificultad = dificultad;
		this.fechaCreacion= new Date().toString();
		this.creadorLogin=creadorLogin;
		this.actividades=new ArrayList<Actividad>();
		
		this.ratingsTotales=0;
		this.version=1;
		this.numActividadesObligatorias=0;
		this.ID="Camino"+UUID.randomUUID().toString();

	}
	
//Constructor clonador
	public CaminoAprendizaje(CaminoAprendizaje caminoOG, String creadorLogin, String titulo)
	{
		this.titulo= titulo;
		this.descripcion= caminoOG.getDescripcion();
		this.dificultad= caminoOG.getDificultad();
		this.duracion=caminoOG.getDuracion();
		this.numActividadesObligatorias=caminoOG.getNumActividadesObligatorias();

		this.actividades=new ArrayList<Actividad>();

		this.ratingsTotales=0;
		this.version=1;
		
		this.fechaCreacion= new Date().toString();

    	Iterator<String> it1 = caminoOG.getObjetivos().iterator(); 
    	
    	while (it1.hasNext())
    	{
    		this.objetivos.add(it1.next());
    	}
    	
    	
    	//Copia de actividades
    	Iterator<Actividad> it2 = caminoOG.getActividades().iterator(); 
    	Actividad actividad;
    	
    	while (it2.hasNext())
    	{
    		Actividad act2 = it2.next();
    		if (act2 .getType().equals(Actividad.ENCUESTA))
    		{
    			actividad=new Encuesta (creadorLogin, (Encuesta) act2 , this);
    		}
    		
    		else if (act2 .getType().equals(Actividad.ACTIVIDADRECURSO))
    		{
    			actividad=new ActividadRecurso (creadorLogin, (ActividadRecurso) act2, this );
    		}
    		
    		else if (act2 .getType().equals(Actividad.EXAMEN))
    		{
    			actividad=new Examen (creadorLogin, (Examen) act2, this );
    		}
    		
    		else if (act2 .getType().equals(Actividad.QUIZ))
    		{
    			actividad=new Quiz (creadorLogin, (Quiz) act2, this );
    		}
    		
    		else
    		{
    			actividad= new Tarea (creadorLogin, (Tarea) act2, this );
    		}
    		
    		this.actividades.add(actividad);
    	}
		
		this.creadorLogin=creadorLogin;
		this.ID="Actividad"+UUID.randomUUID().toString();
	}

//Constructor cargar
	public CaminoAprendizaje(String titulo, String descripcion, List<String> objetivos, double dificultad, int duracion,
			String fechaCreacion, double rating, int ratingsTotales, int version, String fechaModificacion,
			int numActividadesObligatorias, List<Actividad> actividades, String creadorLogin, String id) {
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
		this.creadorLogin = creadorLogin;
		this.ID=id;
	}

	
	public String getID() {
		return ID;
	}

	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getCreadorLogin()
	{
		return this.creadorLogin;
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

	
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getNumActividadesObligatorias() {
		return numActividadesObligatorias;
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
	public void addActividad(Actividad actividad, int pos)
	{
		this.actividades.add(pos, actividad);
		this.duracion+=actividad.getDuracion();
		
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


	/**
     * Crea un nuevo objeto de tipo a partir de un objeto JSON.
     * 
     * El objeto JSON debe tener dos atributos: nombreEmpresa (una cadena) y tamanoEmpresa (un número).
     * @param cliente El objeto JSON que contiene la información
     * @return El nuevo objeto inicializado con la información
     */
	/*
    public static ClienteCorporativo cargarDesdeJSON( JSONObject cliente )
    {
        String nombreEmpresa = cliente.getString( "nombreEmpresa" );
        int tam = cliente.getInt( "tamanoEmpresa" );
        return new ClienteCorporativo( nombreEmpresa, tam );
    }

*/ 
	
    /**
     * Salva este objeto de tipo ClienteCorporativo dentro de un objeto JSONObject para que ese objeto se almacene en un archivo
     * @return El objeto JSON con toda la información del cliente corporativo
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
        jobject.put( "creadorLogin", this.creadorLogin );
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
