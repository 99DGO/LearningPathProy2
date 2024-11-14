package datosEstudiantes;


import org.json.JSONObject;

public class DatosEstudianteAR  extends DatosEstudianteActividad {
	
	public DatosEstudianteAR(String IDEstudiante) {
		super(IDEstudiante);
		this.type=DatosEstudianteActividad.ARDATO;
	}
	
	
	public DatosEstudianteAR(String IDEstudiante, String estado, String fechaInicio, String fechaFinal, String id) {
		super(IDEstudiante, estado, fechaInicio, fechaFinal, id);
		this.type=DatosEstudianteActividad.ARDATO;
	}


	public void finalizarAR() throws Exception 
	{
		finalizarActividad();
		setEstado(DatosEstudianteActividad.EXITOSO);
	}
	
	public JSONObject salvarEnJSON()
	{
		JSONObject jDatosEst = new JSONObject();
		jDatosEst=this.addInfoJSONGeneral(jDatosEst);
		
		return jDatosEst;
	}

}
