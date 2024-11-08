package datosEstudiantes;

import java.util.Date;

public class DatosEstudianteAR  extends DatosEstudianteActividad {
	
	public DatosEstudianteAR(String loginEstudiante) {
		super(loginEstudiante);
	}
	
	
	public DatosEstudianteAR(String loginEstudiante, String estado, Date fechaInicio, Date fechaFinal, String id) {
		super(loginEstudiante, estado, fechaInicio, fechaFinal, id);
	}


	public void finalizarAR() throws Exception 
	{
		finalizarActividad();
		setEstado(DatosEstudianteActividad.EXITOSO);
	}

}
