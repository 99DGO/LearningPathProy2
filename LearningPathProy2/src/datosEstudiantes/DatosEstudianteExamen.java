package datosEstudiantes;

import java.util.Date;

public class DatosEstudianteExamen extends DatosEstudianteActividad {
	private double calificacion;
	
	public DatosEstudianteExamen(String IDEstudiante) {
		super(IDEstudiante);
		calificacion = 0.0;
	}
		
	
	public DatosEstudianteExamen(String IDEstudiante, String estado, String fechaInicio, String fechaFinal,
			double calificacion,  String id) {
		super(IDEstudiante, estado, fechaInicio, fechaFinal, id);
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
