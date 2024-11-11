package datosEstudiantes;


import org.json.JSONObject;

import envios.EnvioQuiz;
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



	public double getCalificacion() {
		return calificacion;
	}
	
	public void finalizarQuiz(Quiz quiz) {
		finalizarActividad();
	}
	
	public EnvioQuiz getEnvioQuiz() {
        return envioQuiz;
    }
	
	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}
	
	
	public JSONObject salvarEnJSON()
	{
		JSONObject jDatosEst = new JSONObject();
		jDatosEst=this.addInfoJSONGeneral(jDatosEst);
		
		jDatosEst.put("calificaion", this.calificacion);
		
		return jDatosEst;
	}
	
}
