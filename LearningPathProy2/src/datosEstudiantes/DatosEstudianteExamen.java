package datosEstudiantes;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import envios.EnvioExamen;

public class DatosEstudianteExamen extends DatosEstudianteActividad {
	private double calificacion;
	private EnvioExamen envio=new EnvioExamen();
	
	public DatosEstudianteExamen(String IDEstudiante) {
		super(IDEstudiante);
		this.calificacion = -1;
		this.type=DatosEstudianteActividad.EXAMENDATO;
		this.envio=new EnvioExamen();
	}
		
	
	public DatosEstudianteExamen(String IDEstudiante, String estado, String fechaInicio, String fechaFinal,
			double calificacion,  String id, EnvioExamen envio) {
		super(IDEstudiante, estado, fechaInicio, fechaFinal, id);
		this.calificacion = calificacion;
		this.type=DatosEstudianteActividad.EXAMENDATO;
		this.envio=envio;

	}


	public double getCalificacion() {
		return calificacion;
	}
	
	public void setCalificacion(double calificacion) 
	{
		this.calificacion = calificacion;
		this.envio.setCalificacion(calificacion);
	}
	
	public EnvioExamen getEnvio()
	{
		return this.envio;
	}
	
	
	public void setEnvio(EnvioExamen envio) {
		this.envio = envio;
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
		jDatosEst.put("calificacion", this.envio.getCalificacion());

		
		return jDatosEst;
	}
	
	
}
