package co.edu.unbosque.beans;

import java.util.ArrayList;

import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.model.UsuarioActual;
import co.edu.unbosque.service.AdministradorService;
import co.edu.unbosque.service.EstudianteService;
import co.edu.unbosque.service.ProfesorService;
import co.edu.unbosque.util.AESUtil;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named(value = "loginbean")

@RequestScoped
public class LogInBean {

	private String user;
	private String password;
	private Usuario sesionIniciada;
	
	ArrayList<Usuario> listUsers = new ArrayList<>();
	
	public LogInBean() {
		cargarUsuarios();
	}

	public void iniciarSesion() {
		for (Usuario usuario : listUsers) {
			String usuarioN = AESUtil.decrypt(usuario.getNombre());
			String contrasenaN = AESUtil.decrypt(usuario.getContrasena());
			
			if(usuarioN.equals(user) && contrasenaN.equals(password)) {
				showStickyLogin("200","Sesion inicaiada exitosamente");
				UsuarioActual.setUsuarioActual(usuario);
				user = "";
				password = "";
				return;
			}
		}
		showStickyLogin("401","Credenciales invalidas");
		password = "";
		
	}
	
	public void cargarUsuarios() {
		listUsers.addAll(AdministradorService.doGetAll("http://localhost:8081/admin/getall"));
		listUsers.addAll(EstudianteService.doGetAll("http://localhost:8081/estudiante/getall"));
		listUsers.addAll(ProfesorService.doGetAll("http://localhost:8081/profesor/getall"));
	}
	
	public void showStickyLogin(String code, String content) {
		if (code.equals("200")) {
			FacesContext.getCurrentInstance().addMessage("sticky-key",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Hecho", content));
		} else if (code.equals("401")) {
			FacesContext.getCurrentInstance().addMessage("sticky-key",
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", content));
		} else {
			System.out.println("Error en crear cuenta");
			System.out.println("Status code: " + code);
			System.out.println("reason: " + content);
			FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error Critico", "Error al crear," + "comuniquese con el administrador"));
		}
	}

	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Usuario> getListUsers() {
		return listUsers;
	}

	public void setListUsers(ArrayList<Usuario> listUsers) {
		this.listUsers = listUsers;
	}

	public boolean getSesionIniciada() {
		return UsuarioActual.getUsuarioActual() !=null;
	}

	public void setSesionIniciada(Usuario sesionIniciada) {
		this.sesionIniciada = sesionIniciada;
	}

	
}
