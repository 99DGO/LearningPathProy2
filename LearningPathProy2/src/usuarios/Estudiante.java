package usuarios;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import caminosActividades.CaminoAprendizaje;

public class Estudiante extends Usuario {
	
	private List<CaminoAprendizaje> historialCaminos;
	private List<String> intereses;
	private boolean actividadActiva=false;
	//Este string de nombreCaminoActividadActiva contendra el nombre del camino tambien si se inicia la actividad
	private String nombreCaminoActividadActiva="Ninguna";
	private String idActividadActiva="";
	
	public Estudiante(String login, String password, String nombre) 
	{
		super(login, password, Usuario.ESTUDIANTE, Usuario.ESTUDIANTE+"-"+login, nombre);
		this.historialCaminos=new ArrayList<CaminoAprendizaje>();
		this.intereses=new ArrayList<String>();
		intereses.add("_");
	}
	
	
	public Estudiante(String login, String password, String type, List<CaminoAprendizaje> historialCaminos, List<String> intereses,
			boolean actividadActiva, String nombre, String nombreCaminoActividadActiva, String idActividadActiva )
	{
		super(login, password, type, Usuario.ESTUDIANTE+"-"+login, nombre);
		this.historialCaminos = historialCaminos;
		this.intereses = intereses;
		this.actividadActiva = actividadActiva;
		this.nombreCaminoActividadActiva=nombreCaminoActividadActiva;
		this.idActividadActiva=idActividadActiva;
	}


	public void addCamino(CaminoAprendizaje camino)
	{
		this.historialCaminos.add(camino);
	}
	
	public void addInteres(String interes)
	{
		this.intereses.add(interes);
		this.intereses.remove("_");
	}
	
	public void delInteres(String interes)
	{
		this.intereses.remove(interes);
	}
	
	public void setActividadActiva(boolean bool)
	{
		this.actividadActiva=bool;
	}

	public List<CaminoAprendizaje> getHistorialCaminos() {
		return historialCaminos;
	}

	public List<String> getIntereses() {
		return intereses;
	}

	public boolean isActividadActiva() {
		return actividadActiva;
	}

	

	public String getNombreCaminoActividadActiva() 
	{
		return nombreCaminoActividadActiva;
	}


	public void setNombreCaminoActividadActiva(String nombreCaminoActividadActiva) 
	{
		this.nombreCaminoActividadActiva = nombreCaminoActividadActiva;
	}

	

	public String getIdActividadActiva() {
		return idActividadActiva;
	}


	public void setIdActividadActiva(String idActividadActiva) {
		this.idActividadActiva = idActividadActiva;
	}


	public JSONObject salvarJSON() 
	{
		JSONObject jEstudiante = new JSONObject();
		
		jEstudiante=addInfoGeneralJSON(jEstudiante);
		
		jEstudiante.put("actividadActiva", this.actividadActiva);
		jEstudiante.put("idActividadActiva", this.idActividadActiva);
		jEstudiante.put("nombreCaminoActividadActiva", this.nombreCaminoActividadActiva);
		
		JSONArray intereses = new JSONArray(this.intereses);
		jEstudiante.put("intereses", intereses);
		
		List<String> idsCaminos = new LinkedList<String>();
		for (CaminoAprendizaje camino : this.historialCaminos)
		{
			idsCaminos.add(camino.getID());
		}
		
		jEstudiante.put("historialCaminos", new JSONArray(idsCaminos));
				
		return jEstudiante;
	}
	
	
	

}
