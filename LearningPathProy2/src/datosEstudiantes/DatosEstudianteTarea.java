package datosEstudiantes;

import java.util.Date;

public class DatosEstudianteTarea extends DatosEstudianteActividad {
	private String metodoEntrega;
	
	public DatosEstudianteTarea(String loginEstudiante,String metodoEntrega) {
		super(loginEstudiante);
		this.metodoEntrega = metodoEntrega;
	}
	
	
	public DatosEstudianteTarea(String loginEstudiante, String estado, Date fechaInicio, Date fechaFinal,
			String metodoEntrega,  String id) {
		super(loginEstudiante, estado, fechaInicio, fechaFinal, id);
		this.metodoEntrega = metodoEntrega;
	}


	public String getMetodoEntrega() {
		return metodoEntrega;
	}
	
	public void setMetodoEntrega(String metodoEntrega) {
		this.metodoEntrega = metodoEntrega;
	}
	
	public void finalizarTarea() throws Exception {
		finalizarActividad();
		setEstado(DatosEstudianteActividad.ENVIADO);
	}
	
}
