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
	private Integer respuesta;
	
	public PreguntaQuiz(String textoPregunta, Integer respuesta, int cantidadOpciones) {
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
	

	//Constructor para cargar
	public PreguntaQuiz(int cantidadOpciones, Map<Integer, OpcionQuiz> opciones, String textoPregunta,
			Integer respuesta) {
		this.cantidadOpciones = cantidadOpciones;
		this.opciones = opciones;
		this.textoPregunta = textoPregunta;
		this.respuesta = respuesta;
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

	public Integer getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(Integer respuesta) 
	{
		this.respuesta = respuesta;
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
		jPregunta.put("respuesta", respuesta.intValue());
	
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
