package envios;

import java.util.HashMap;

public abstract class Envio<K, V> {

	protected HashMap<K, V> respuestas=new HashMap<K,V>();
	
	 public Envio() {
		 this.respuestas = new HashMap<>(); 
	 }
	 
	 
	 public Envio(HashMap<K, V> respuestas) 
	 {
		this.respuestas = respuestas;
	}


	public abstract HashMap<K, V> getRespuestas();
	 	   
}
