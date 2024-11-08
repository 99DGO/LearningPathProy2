package datosEstudiantes;

import java.util.Date;

import envios.EnvioEncuesta;

public class DatosEstudianteEncuesta extends DatosEstudianteActividad {
	
	private EnvioEncuesta envio;
	
	public DatosEstudianteEncuesta(String loginEstudiante) {
		super(loginEstudiante);
	}
	
	
	public DatosEstudianteEncuesta(String loginEstudiante, String estado, Date fechaInicio, Date fechaFinal,  String id) {
		super(loginEstudiante, estado, fechaInicio, fechaFinal, id);
	}



	public void finalizarEncuesta() throws Exception 
	{
		finalizarActividad();
		setEstado(DatosEstudianteActividad.EXITOSO);
	}
	

}
