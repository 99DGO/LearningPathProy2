package envios;

import java.util.HashMap;

public class EnvioExamen extends Envio<String, String>{
	private double calificacion;


	public EnvioExamen(HashMap<String, String> respuestas, double calificacion) 
	{
		this.respuestas = respuestas;
		this.calificacion=calificacion;
	}
	
	public EnvioExamen()
	{
		super ();
	}
	
	public EnvioExamen(HashMap<String, String> respuestas) 
	{
		this.respuestas = respuestas;
	}
	
	
	public HashMap<String, String> getRespuestas() {
		return respuestas;
	}
	
    public double getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}

}
