package usuarios;

import java.util.ArrayList;
import java.util.List;

import caminosActividades.CaminoAprendizaje;

public class Profesor extends Usuario{
	private List<CaminoAprendizaje> caminos;
	
	public Profesor(String login, String password) {
		super(login, password, Usuario.PROFESOR+"-"+login);
		this.caminos=new ArrayList<CaminoAprendizaje>();
	}
	
	public Profesor(String login, String password, String type, List<CaminoAprendizaje> caminos) {
		super(login, password, type);
		this.caminos = caminos;
	}

	public List<CaminoAprendizaje> getCaminos() {
		return caminos;
	}
	
	public void addCamino(CaminoAprendizaje camino)
	{
		caminos.add(camino);
	}
	
	
}
