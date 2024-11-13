package envios;

import java.util.HashMap;

public class EnvioEncuesta extends Envio<String, String>{

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

    public void agregarRespuesta(String pregunta, String respuesta) {
        respuestas.put(pregunta, respuesta);
    }
	
}
