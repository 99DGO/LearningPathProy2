package datosEstudiantes;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import envios.EnvioEncuesta;

public class DatosEstudianteEncuesta extends DatosEstudianteActividad {
	
	private EnvioEncuesta envio;
	
	public DatosEstudianteEncuesta(String IDEstudiante) {
		super(IDEstudiante);
		this.type=DatosEstudianteActividad.ENCUESTADATO;
	}
	
	
	public DatosEstudianteEncuesta(String IDEstudiante, String estado, String fechaInicio, String fechaFinal,  String id) {
		super(IDEstudiante, estado, fechaInicio, fechaFinal, id);
		this.type=DatosEstudianteActividad.ENCUESTADATO;

	}



	public void finalizarEncuesta() throws Exception 
	{
		finalizarActividad();
		setEstado(DatosEstudianteActividad.EXITOSO);
	}
	
	
	public JSONObject salvarEnJSON()
	{
		JSONObject jDatosEst = new JSONObject();
		jDatosEst=this.addInfoJSONGeneral(jDatosEst);
		JSONArray jRespuestas;
		
		List<String> preguntasRespuestas = new LinkedList<String>();
		
		for (String pregunta : this.envio.getRespuestas().keySet())
		{
			String preguntaRespuestaInd = pregunta+"999_999"+this.envio.getRespuestas().get(pregunta);
			preguntasRespuestas.add(preguntaRespuestaInd);
		}
		
		jRespuestas= new JSONArray(preguntasRespuestas);
		jDatosEst.put("envio", jRespuestas);

		
		return jDatosEst;
	}
	

}
