package datosEstudiantes;

import java.util.Date;
import java.util.UUID;

import org.json.JSONObject;

public abstract class DatosEstudianteActividad {
	public static final String EXITOSO = "Exitoso";
	public static final String ENVIADO = "Enviado";
	public static final String PENDIENTE = "Pendiente";
	public static final String NOEXITOSO = "No exitoso";
	
	public static final String ARDATO= "DatosActividadRecurso";
	public static final String ENCUESTADATO = "DatosEncuesta";
	public static final String EXAMENDATO = "DatosExamen";
	public static final String QUIZDATO = "DatosQuiz";
	public static final String TAREADATO = "DatosTarea";
	
	private String IDEstudiante;
	private String estado;
	private String fechaInicio;
	private String fechaFinal;
	private final String id;
	protected String type;

	//Constructor normal
	public DatosEstudianteActividad(String IDEstudiante) {
		this.IDEstudiante = IDEstudiante;
		this.estado = PENDIENTE;
		this.fechaFinal = "No finalizada";  //Se asigna cuando se finalice la actividad
		this.fechaInicio="No iniciada";
		this.id="DatoEstudiante_"+UUID.randomUUID().toString();
	} 
	
	
	//Constructor para cargar
	public DatosEstudianteActividad(String IDEstudiante, String estado, String fechaInicio, String fechaFinal, String id) {
		super();
		this.IDEstudiante = IDEstudiante;
		this.estado = estado;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.id=id;
	}



	public String getIDEstudiante() {
		return IDEstudiante;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public String getFechaInicio() {
		return fechaInicio;
	}
	
	public String getFechaFinal() throws Exception {
		if (this.fechaFinal == null) {
            throw new Exception("No se ha finalizado la actividad todavía.");
        }
        return this.fechaFinal;
	}
	
	public void setEstado(String nuevoEstado) throws Exception 
	{
		if (nuevoEstado.equals(EXITOSO) || nuevoEstado.equals(ENVIADO) || nuevoEstado.equals(PENDIENTE) || nuevoEstado.equals(NOEXITOSO)) 
		{
	        this.estado = nuevoEstado;
	    } 
		else 
		{
	        throw new Exception("Estado no válido. Los estados válidos son: Exitoso, Enviado, Pendiente y No exitoso");
	    }
	}
	
	public void finalizarActividad() {
		this.fechaFinal = new Date().toString();
	}
	
	public void setFechaFinal() {
		this.fechaFinal = new Date().toString();
	}
	
	public void setFechaInicio() {
		this.fechaInicio = new Date().toString();
	}
	
	public String getID()
	{
		return this.id;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public JSONObject addInfoJSONGeneral(JSONObject jDatosEst)
	{
		jDatosEst.put("IDEstudiante", this.IDEstudiante);
		jDatosEst.put("estado", this.estado);
		jDatosEst.put("fechaInicio", this.fechaInicio);
		jDatosEst.put("fechaFinal", this.fechaFinal);
		jDatosEst.put("id", this.id);
		jDatosEst.put("type", this.type);

		return jDatosEst;
	}
	
	public abstract JSONObject salvarEnJSON();

}
