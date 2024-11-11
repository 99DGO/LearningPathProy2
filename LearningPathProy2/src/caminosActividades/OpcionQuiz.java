package caminosActividades;

public class OpcionQuiz {
	private String texto;
	private String explicacion;
	private boolean correcta;

	public OpcionQuiz(String texto, String explicacion, boolean correcta) {
		this.texto = texto;
		this.explicacion = explicacion;
		this.correcta = correcta;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getExplicacion() {
		return explicacion;
	}

	public void setExplicacion(String explicacion) {
		this.explicacion = explicacion;
	}

	public boolean isCorrecta() {
		return correcta;
	}

	public void setCorrecta(boolean correcta) {
		this.correcta = correcta;
	}

}