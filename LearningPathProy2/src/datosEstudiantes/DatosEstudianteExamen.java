package datosEstudiantes;

import java.util.Date;

public class DatosEstudianteExamen extends DatosEstudianteActividad {
	private double calificacion;
	
	public DatosEstudianteExamen(String loginEstudiante) {
		super(loginEstudiante);
		calificacion = 0.0;
	}
		
	
	public DatosEstudianteExamen(String loginEstudiante, String estado, Date fechaInicio, Date fechaFinal,
			double calificacion,  String id) {
		super(loginEstudiante, estado, fechaInicio, fechaFinal, id);
		this.calificacion = calificacion;
	}


	public void finalizarExamen() throws Exception {
		finalizarActividad();
		setEstado(DatosEstudianteActividad.ENVIADO);
	}
	
	public double getCalificacion() {
		return calificacion;
	}
	
	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}
	
	
}
