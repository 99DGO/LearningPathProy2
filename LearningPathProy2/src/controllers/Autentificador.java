package controllers;

import creadores.CreadorProfesor;
import creadores.CreadorEstudiante;
import usuarios.Estudiante;
import usuarios.Profesor;
import usuarios.Usuario;

public class Autentificador {
	private static Autentificador autentificador = null;
    private LearningPathSystem LPS;

	private Autentificador(LearningPathSystem LPS) {
		this.LPS = LPS;
	}

	public static Autentificador getInstance(LearningPathSystem LPS) {
		if (autentificador == null)
			autentificador = new Autentificador(LPS);
		return autentificador;
	}

	public Usuario autentificar(String ID, String contrasena) throws Exception {
		Usuario usuario = null;
		usuario = LPS.getEstudianteIndividual(ID);
		if (usuario == null)
			usuario = LPS.getProfesorIndividual(ID);
		if (usuario != null) {
			if (usuario.getPassword().equals(contrasena))
				return usuario;
			else
				throw new Exception("Usuario o contraseña incorrecta. \n");
		} else
			throw new Exception("Usuario no encontrado. \n");
	}

	public boolean registrarUsuario(Usuario usuario) {
		if (usuario instanceof Estudiante) {
			if (LPS.getEstudianteIndividual(usuario.getID()) == null) {
				try {
					CreadorEstudiante.crearEstudiante(LPS, usuario.getLogin(), usuario.getPassword(), usuario.getNombre());
				} catch (Exception e) {
					e.getMessage();
					e.printStackTrace();
				}
				return true;
			}
		} else if (usuario instanceof Profesor) {
			if (LPS.getProfesorIndividual(usuario.getID()) == null) {
				try {
					CreadorProfesor.crearProfesor(LPS, usuario.getLogin(), usuario.getPassword(), usuario.getNombre());
				} catch (Exception e) {
					e.getMessage();
					e.printStackTrace();
				}

				return true;
			}
		}
		return false;
	}

	public void eliminarUsuario(String ID) {
		if (LPS.getEstudianteIndividual(ID) != null) {
			try {
				CreadorEstudiante.eliminarEstudiante(LPS, ID);
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
		} else if (LPS.getProfesorIndividual(ID) != null) {
			try {
                CreadorProfesor.eliminarProfesor(LPS, ID);
            } catch (Exception e) {
                e.getMessage();
                e.printStackTrace();
            }
		}
	}

	public void modificarContrasena(String ID, String contrasena) {
		if (LPS.getEstudianteIndividual(ID) != null) {
			LPS.getEstudianteIndividual(ID).setPassword(contrasena);
		} else if (LPS.getProfesorIndividual(ID) != null) {
			LPS.getProfesorIndividual(ID).setPassword(contrasena);
		}
	}

	public void modificarNombre(String ID, String nombre) {
		if (LPS.getEstudianteIndividual(ID) != null) {
			LPS.getEstudianteIndividual(ID).setNombre(nombre);
		} else if (LPS.getProfesorIndividual(ID) != null) {
			LPS.getProfesorIndividual(ID).setNombre(nombre);
		}
	}
}
