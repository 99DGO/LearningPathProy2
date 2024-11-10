package datosEstudiantes;

import java.util.Date;

import envios.EnvioEncuesta;

public class DatosEstudianteEncuesta extends DatosEstudianteActividad {
	
	private EnvioEncuesta envio;
	
	public DatosEstudianteEncuesta(String IDEstudiante) {
		super(IDEstudiante);
	}
	
	
	public DatosEstudianteEncuesta(String IDEstudiante, String estado, String fechaInicio, String fechaFinal,  String id) {
		super(IDEstudiante, estado, fechaInicio, fechaFinal, id);
	}



	public void finalizarEncuesta() throws Exception 
	{
		finalizarActividad();
		setEstado(DatosEstudianteActividad.EXITOSO);
	}
	

}
