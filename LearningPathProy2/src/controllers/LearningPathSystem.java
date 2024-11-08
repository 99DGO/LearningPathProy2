package controllers;

import caminosActividades.CaminoAprendizaje;
import java.util.HashMap;
import usuarios.Usuario;

public class LearningPathSystem {
	
	private HashMap<String, Usuario> Usuarios;
	private HashMap<String, CaminoAprendizaje> Caminos;
	private static LearningPathSystem LPS=null;
	
	private LearningPathSystem()
	{
		
	}
	
	public static LearningPathSystem getInstance()
	{
		 if (LPS == null)
	            LPS = new LearningPathSystem();
	 
	     return LPS;
		
	}
	public HashMap<String, Usuario> getUsuarios() {
		return Usuarios;
	}
	
	public void setUsuarios(HashMap<String, Usuario> usuarios) {
		Usuarios = usuarios;
	}
	
	public HashMap<String, CaminoAprendizaje> getCaminos() {
		return Caminos;
	}
	
	public void setCaminos(HashMap<String, CaminoAprendizaje> caminos) {
		Caminos = caminos;
	}
	
	public Usuario getUsuarioIndividal(String login)
	{
		return this.Usuarios.get(login);
	}
	

	public void addCamino(CaminoAprendizaje camino)
	{
		this.Caminos.put(camino.getTitulo(), camino);
	}
	
	public void addUsuario(Usuario usuario)
	{
		this.Usuarios.put(usuario.getLogin(), usuario);
	}
	
	public CaminoAprendizaje getCaminoIndividual(String titulo)
	{
		return this.Caminos.get(titulo);
	}
	
	
	public void delCamino(CaminoAprendizaje camino)
	{
		this.Caminos.remove(camino.getTitulo());
	}
	

}
