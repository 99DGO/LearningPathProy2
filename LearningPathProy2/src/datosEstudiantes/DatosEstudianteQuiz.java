package datosEstudiantes;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import envios.EnvioQuiz;
import caminosActividades.PreguntaQuiz;
import caminosActividades.Quiz;

public class DatosEstudianteQuiz extends DatosEstudianteActividad {
	private double calificacion;
	private EnvioQuiz envioQuiz;
	
	public DatosEstudianteQuiz(String IDEstudiante) {
		super(IDEstudiante);
		this.envioQuiz = new EnvioQuiz();
		this.calificacion = 0.0;
		this.type=DatosEstudianteActividad.QUIZDATO;


	}
	
	
	
	public DatosEstudianteQuiz(String IDEstudiante, String estado, String fechaInicio, String fechaFinal,
			double calificacion, EnvioQuiz envioQuiz, String id) {
		super(IDEstudiante, estado, fechaInicio, fechaFinal, id);
		this.calificacion = calificacion;
		this.envioQuiz = envioQuiz;
		this.type=DatosEstudianteActividad.QUIZDATO;

	}

	

	public void setEnvioQuiz(EnvioQuiz envioQuiz) 
	{
		this.envioQuiz = envioQuiz;
		double calificacion=envioQuiz.calcularCalificacionQuiz();
		this.calificacion=calificacion;
	}



	public double getCalificacion() {
		return calificacion;
	}
	

	
	public EnvioQuiz getEnvioQuiz() {
        return envioQuiz;
    }
	
	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}
	
	public void updateCalificacion()
	{
		this.calificacion=this.envioQuiz.getCalificacion();
	}
	
	public JSONObject salvarEnJSON()
	{
		JSONObject jDatosEst = new JSONObject();
		jDatosEst=this.addInfoJSONGeneral(jDatosEst);
		
		jDatosEst.put("calificacion", this.calificacion);
		
		HashMap<PreguntaQuiz, Integer> respuestas = this.envioQuiz.getRespuestas();
		List<JSONObject> jRespuestasList = new LinkedList<JSONObject>();
		
		for (PreguntaQuiz preguntaObjeto: respuestas.keySet())
		{
			JSONObject jRespuesta = preguntaObjeto.getJSONObject();
			jRespuesta.put("respuestaUsuario", respuestas.get(preguntaObjeto));
			jRespuestasList.add(jRespuesta);
		}
		
		jDatosEst.put( "envio", new JSONArray(jRespuestasList));
		
		return jDatosEst;
	}
	
}
