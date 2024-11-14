package envios;

import java.util.HashMap;

public abstract class Envio<K, V> {

	protected HashMap<K, V> respuestas;
	
	 public Envio() {
		 this.respuestas = new HashMap<>(); 
	 }
	 
	 
	 public Envio(HashMap<K, V> respuestas) {
		super();
		this.respuestas = respuestas;
	}


	public abstract HashMap<K, V> getRespuestas();
	 
	public abstract void agregarRespuesta(K pregunta, V respuesta);
	   
}
