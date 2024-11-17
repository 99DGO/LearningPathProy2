package envios;

import java.util.HashMap;

public class EnvioEncuesta extends Envio<String, String>{

	/*
	 * El hashmap de respuestas contiene como llave la pregunta y como valor la respuesta del estudiante
	 */
	public EnvioEncuesta(HashMap<String, String> respuestas) 
	{
		this.respuestas = respuestas;
	}

	public EnvioEncuesta()
	{
		super ();
	}
	

	public HashMap<String, String> getRespuestas() {
		return respuestas;
	}

	
}
