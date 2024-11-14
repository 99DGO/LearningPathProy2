package usuarios;

import java.util.UUID;

import org.json.JSONObject;

public abstract class Usuario {
	public static final String ESTUDIANTE = "Estudiante";
	public static final String PROFESOR = "Profesor";
	
	private String nombre;
	private String login;
	private String password;
	private String type;
	private final String ID;
	
	public Usuario(String login, String password, String type, String ID, String nombre) {
		this.nombre = nombre;
		this.login = login;
		this.password = password;
		this.type = type;
		this.ID=ID;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public String getID() {
		return ID;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public JSONObject addInfoGeneralJSON(JSONObject jEstudiante) {

		jEstudiante.put("login", this.login);
		jEstudiante.put("nombre", this.nombre);
		jEstudiante.put("password", this.password);
		jEstudiante.put("type", this.type);
		jEstudiante.put("ID", this.ID);

		return jEstudiante;
	}
	
}
