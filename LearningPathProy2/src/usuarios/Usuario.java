package usuarios;

import java.util.UUID;

public abstract class Usuario {
	public static final String ESTUDIANTE = "Estudiante";
	public static final String PROFESOR = "Profesor";
	
	private String login;
	private String password;
	private String type;
	private final String ID;
	
	public Usuario(String login, String password, String type, String ID) {
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

	
	
}
