package datosEstudiantes;

import java.util.Date;

public class DatosEstudianteTarea extends DatosEstudianteActividad {
	private String metodoEntrega;
	
	public DatosEstudianteTarea(String IDEstudiante,String metodoEntrega) {
		super(IDEstudiante);
		this.metodoEntrega = metodoEntrega;
	}
	
	
	public DatosEstudianteTarea(String IDEstudiante, String estado, String fechaInicio, String fechaFinal,
			String metodoEntrega,  String id) {
		super(IDEstudiante, estado, fechaInicio, fechaFinal, id);
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
