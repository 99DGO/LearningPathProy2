package datosEstudiantes;

import java.util.Date;
import java.util.UUID;

public abstract class DatosEstudianteActividad {
	public static final String EXITOSO = "Exitoso";
	public static final String ENVIADO = "Enviado";
	public static final String PENDIENTE = "Pendiente";
	public static final String NOEXITOSO = "No exitoso";
	
	private String loginEstudiante;
	private String estado;
	private Date fechaInicio;
	private Date fechaFinal;
	private final String id;

	//Constructor normal
	public DatosEstudianteActividad(String loginEstudiante) {
		this.loginEstudiante = loginEstudiante;
		this.estado = PENDIENTE;
		this.fechaFinal = null;  //Se asigna cuando se finalice la actividad
		this.id="Actividad"+UUID.randomUUID().toString();
	} 
	
	
	//Constructor para cargar
	public DatosEstudianteActividad(String loginEstudiante, String estado, Date fechaInicio, Date fechaFinal, String id) {
		super();
		this.loginEstudiante = loginEstudiante;
		this.estado = estado;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.id=id;
	}



	public String getLoginEstudiante() {
		return loginEstudiante;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	
	public Date getFechaFinal() throws Exception {
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
		this.fechaFinal = new Date();
	}
	
	public void setFechaFinal() {
		this.fechaFinal = new Date();
	}
	
	public void setFechaInicio() {
		this.fechaInicio = new Date();
	}

}
