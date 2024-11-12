package caminosActividades;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class PreguntaQuiz {
	private int cantidadOpciones;
	private Map <Integer, OpcionQuiz> opciones;
	private String textoPregunta;
	private OpcionQuiz respuesta;
	
	public PreguntaQuiz(String textoPregunta, OpcionQuiz respuesta, int cantidadOpciones) {
		this.opciones = new HashMap<>();
		this.textoPregunta = textoPregunta;
		this.respuesta = respuesta;
		this.cantidadOpciones = cantidadOpciones;
	}
	
	public PreguntaQuiz(PreguntaQuiz preguntaOG)
	{
		Map<Integer, OpcionQuiz> opcionesOG = preguntaOG.getOpciones();
		this.opciones = clonerOpciones(opcionesOG);
		this.textoPregunta = preguntaOG.getTextoPregunta();
		this.respuesta = preguntaOG.getRespuesta();
		this.cantidadOpciones = preguntaOG.getCantidadOpciones();
	}

	private Map<Integer, OpcionQuiz> clonerOpciones(Map<Integer, OpcionQuiz> opcionesOG) {
		Map<Integer, OpcionQuiz> clonedOpciones = new HashMap<>(opcionesOG);
		return clonedOpciones;
	}

	public String getTextoPregunta() {
		return textoPregunta;
	}

	public void setTextoPregunta(String textoPregunta) {
		this.textoPregunta = textoPregunta;
	}

	public OpcionQuiz getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(OpcionQuiz respuesta) {
		if (this.getOpciones().containsValue(respuesta)) {
			if (respuesta.isCorrecta() == true)
				this.respuesta = respuesta;
			else
				throw new IllegalArgumentException("La respuesta de la pregunta debe ser correcta");
		}
		else
			throw new IllegalArgumentException("La respuesta de la pregunta debe ser una de las opciones");
	}

	public Map<Integer, OpcionQuiz> getOpciones() {
		return opciones;
	}
	
	public int getCantidadOpciones() {
		return cantidadOpciones;
	}
	
	public void setOpcion(int pos, OpcionQuiz opcion)
	{
		if (pos < 0 || pos >= this.cantidadOpciones)
			throw new IllegalArgumentException(
					"La posición de la opción debe ser mayor o igual a 0 y menor a la cantidad de opciones");
		else
			this.opciones.put(pos, opcion);
	}

	public JSONObject getJSONObject() 
	{
		JSONObject jPregunta = new JSONObject();
		
		jPregunta.put("cantidadOpciones", this.cantidadOpciones);
		jPregunta.put("textoPregunta", this.textoPregunta);
		
		JSONObject jRespuesta = this.respuesta.getJSONObject();
		
		jPregunta.put("respuesta", jRespuesta);
	
		List<JSONObject> listJOpciones= new LinkedList<JSONObject>();
		for (int numOpcion: this.opciones.keySet())
		{
			JSONObject jOpcionInd;
			jOpcionInd=this.opciones.get(numOpcion).getJSONObject();
			jOpcionInd.put("numOpcion", numOpcion);
			
			listJOpciones.add(jOpcionInd);
		}
		
		JSONArray jOpcionesArray = new JSONArray(listJOpciones);
		jPregunta.put("opciones", jOpcionesArray);
		
		return jPregunta;
	}


}
