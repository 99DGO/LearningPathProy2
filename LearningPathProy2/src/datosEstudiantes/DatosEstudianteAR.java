package datosEstudiantes;

import java.util.Date;

public class DatosEstudianteAR  extends DatosEstudianteActividad {
	
	public DatosEstudianteAR(String IDEstudiante) {
		super(IDEstudiante);
	}
	
	
	public DatosEstudianteAR(String IDEstudiante, String estado, String fechaInicio, String fechaFinal, String id) {
		super(IDEstudiante, estado, fechaInicio, fechaFinal, id);
	}


	public void finalizarAR() throws Exception 
	{
		finalizarActividad();
		setEstado(DatosEstudianteActividad.EXITOSO);
	}

}
