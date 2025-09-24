package co.edu.unbosque.beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.Admin;
import co.edu.unbosque.model.Estudiante;
import co.edu.unbosque.model.Profesor;
import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.service.AdministradorService;
import co.edu.unbosque.service.EstudianteService;
import co.edu.unbosque.service.ProblemaService;
import co.edu.unbosque.service.ProfesorService;

@Named("usuariosBean")
@ViewScoped
public class UsuarioBean implements Serializable {

	private ArrayList<Usuario> usuarios;
	private ArrayList<Admin> admins;
	private ArrayList<Profesor> profesores;
	private ArrayList<Estudiante> estudiantes;

	private Usuario usuarioSeleccionado;

	public UsuarioBean() {
		cargarUsuarios();
	}

	@PostConstruct
	public void cargarUsuarios() {
		// Simulación: Cargar lista de usuarios de base de datos o servicio
		usuarios = new ArrayList<>();
		admins = AdministradorService.doGetAll("http://localhost:8081/admin/getall");
		profesores = ProfesorService.doGetAll("http://localhost:8081/profesor/getall");
		estudiantes = EstudianteService.doGetAll("http://localhost:8081/estudiante/getall");
		
		usuarios.addAll(admins);
		usuarios.addAll(profesores);
		usuarios.addAll(estudiantes);
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	// ✅ Devuelve el tipo de usuario de forma legible
	public String getTipoUsuario(Usuario u) {
		if (u instanceof Admin)
			return "Administrador";
		if (u instanceof Profesor)
			return "Profesor";
		if (u instanceof Estudiante)
			return "Estudiante";
		return "Usuario";
	}

	// ✅ Acción para eliminar un usuario
	public void eliminarUsuario(Usuario u) {
		usuarios.remove(u);
		String respuesta = "";
		switch (getTipoUsuario(u)) {
		case "Administrador": {

			respuesta = ProblemaService.doDelete("http://localhost:8081/admin/eliminar?id=" + u.getId());
			break;
		}
		case "Profesor": {

			respuesta = ProblemaService.doDelete("http://localhost:8081/profesor/eliminar?id=" + u.getId());
			break;
		}
		case "Estudiante": {

			respuesta = ProblemaService.doDelete("http://localhost:8081/estudiante/eliminar?id=" + u.getId());
			break;
		}
		default:
			respuesta = "400 Error";
			break;
		}
		
		String[] data = respuesta.split("\n");
		if (data[0].equals("204")) {
			showStickyLogin(data[0], "usuario eliminado");
			cargarUsuarios();
			return;
		} else {
			showStickyLogin(data[0], "no se ha podido eliminar el usuario.");
			cargarUsuarios();
			return;
		}
	}
	
	public void showStickyLogin(String code, String content) {
		if (code.equals("201")) {
			FacesContext.getCurrentInstance().addMessage("sticky-key",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Hecho", ", se ha creado el usuario"));
		} else if (code.equals("406")) {
			FacesContext.getCurrentInstance().addMessage("sticky-key",
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", content));
		} else if (code.equals("204")) {
			FacesContext.getCurrentInstance().addMessage("sticky-key",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Hecho", ", se ha eliminado el usuario"));
		} else {
			System.out.println("Error en crear cuenta");
			System.out.println("Status code: " + code);
			System.out.println("reason: " + content);
			FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error Critico", "Error," + "comuniquese con el administrador"));
		}
	}

	// ✅ Acción para seleccionar usuario y mostrarlo en un diálogo
	public void seleccionarUsuario(Usuario u) {
		this.usuarioSeleccionado = u;
	}

	public ArrayList<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(ArrayList<Admin> admins) {
		this.admins = admins;
	}

	public ArrayList<Profesor> getProfesores() {
		return profesores;
	}

	public void setProfesores(ArrayList<Profesor> profesores) {
		this.profesores = profesores;
	}

	public ArrayList<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
}