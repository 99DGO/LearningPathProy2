package envios;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import caminosActividades.OpcionQuiz;
import caminosActividades.PreguntaQuiz;
import caminosActividades.Quiz;

public class EnvioQuiz extends Envio<PreguntaQuiz, Integer> {
	private double calificacion;

	public EnvioQuiz() 
	{
		super();
	}

	public EnvioQuiz(HashMap<PreguntaQuiz, Integer> respuestas, double calificacion) {
		super(respuestas);
		this.calificacion = calificacion;
	}

	public EnvioQuiz(HashMap<PreguntaQuiz, Integer> respuestas) 
	{
		super(respuestas);
	}
	
	public void setRespuestas(HashMap<PreguntaQuiz, Integer> respuestas)
	{
		this.respuestas=respuestas;
	}
	
	
	public double getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}

	public HashMap<PreguntaQuiz, Integer> getRespuestas() {
		return respuestas;
	}

	public double calcularCalificacionQuiz() 
	{
		Set<PreguntaQuiz> preguntasQuiz = this.respuestas.keySet();
		
		int totalPreguntas = preguntasQuiz.size();
		int respuestasCorrectas = 0;

		for (PreguntaQuiz pregunta : preguntasQuiz) 
		{
			Integer respuestaCorrecta = pregunta.getRespuesta();
			Integer respuestaUsuario = this.respuestas.get(pregunta);

			if (respuestaUsuario != null && respuestaUsuario.equals(respuestaCorrecta)) 
			{
				respuestasCorrectas++;
			}
		}

		double calificacionCalculada= (respuestasCorrectas / (double) totalPreguntas)*5;
		this.calificacion=calificacionCalculada;
		
		return calificacionCalculada;
	}


}
