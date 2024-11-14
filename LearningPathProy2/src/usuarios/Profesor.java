package usuarios;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import caminosActividades.CaminoAprendizaje;

public class Profesor extends Usuario{
	private List<CaminoAprendizaje> caminos;
	
	public Profesor(String login, String password, String nombre) {
		super(login, password, Usuario.PROFESOR ,Usuario.PROFESOR+"-"+login, nombre);
		this.caminos=new ArrayList<CaminoAprendizaje>();
	}
	
	public Profesor(String login, String password, String type, List<CaminoAprendizaje> caminos, String nombre) {
		super(login, password, type, Usuario.PROFESOR+"-"+login, nombre);
		this.caminos = caminos;
	}

	public List<CaminoAprendizaje> getCaminos() {
		return caminos;
	}
	
	public void addCamino(CaminoAprendizaje camino)
	{
		caminos.add(camino);
	}

	public JSONObject salvarJSON()
	{
		JSONObject jProf= new JSONObject();
		jProf=addInfoGeneralJSON(jProf);
		
		List<String> idsCaminos = new LinkedList<String>();
		for (CaminoAprendizaje camino : this.caminos)
		{
			idsCaminos.add(camino.getID());
		}
		
		jProf.put("caminos", new JSONArray(idsCaminos));
		
		return jProf;
	}
	
	
}
