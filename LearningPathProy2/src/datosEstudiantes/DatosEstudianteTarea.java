package datosEstudiantes;


import org.json.JSONObject;

public class DatosEstudianteTarea extends DatosEstudianteActividad {
	private String metodoEntrega=null;
	
	public DatosEstudianteTarea(String IDEstudiante,String metodoEntrega) {
		super(IDEstudiante);
		this.metodoEntrega = metodoEntrega;
		this.type=DatosEstudianteActividad.TAREADATO;
	}
	
	
	public DatosEstudianteTarea(String IDEstudiante, String estado, String fechaInicio, String fechaFinal,
			String metodoEntrega,  String id) {
		super(IDEstudiante, estado, fechaInicio, fechaFinal, id);
		this.metodoEntrega = metodoEntrega;
		this.type=DatosEstudianteActividad.TAREADATO;
	}


	public String getMetodoEntrega() {
		return metodoEntrega;
	}
	
	public void setMetodoEntrega(String metodoEntrega) {
		this.metodoEntrega = metodoEntrega;
	}
	
	
	public JSONObject salvarEnJSON()
	{
		JSONObject jDatosEst = new JSONObject();
		jDatosEst=this.addInfoJSONGeneral(jDatosEst);
		
		jDatosEst.put("metodoEntrega", this.metodoEntrega);
		
		return jDatosEst;
	}

	
	
}
