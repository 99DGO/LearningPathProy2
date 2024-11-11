package datosEstudiantes;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import envios.EnvioExamen;

public class DatosEstudianteExamen extends DatosEstudianteActividad {
	private double calificacion;
	private EnvioExamen envio;
	
	public DatosEstudianteExamen(String IDEstudiante) {
		super(IDEstudiante);
		calificacion = 0.0;
		this.type=DatosEstudianteActividad.EXAMENDATO;
	}
		
	
	public DatosEstudianteExamen(String IDEstudiante, String estado, String fechaInicio, String fechaFinal,
			double calificacion,  String id) {
		super(IDEstudiante, estado, fechaInicio, fechaFinal, id);
		this.calificacion = calificacion;
		this.type=DatosEstudianteActividad.EXAMENDATO;

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
