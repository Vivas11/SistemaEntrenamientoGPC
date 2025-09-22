package co.edu.unbosque.beans;

import java.io.Serializable;
import java.util.ArrayList;

import co.edu.unbosque.model.Admin;
import co.edu.unbosque.model.DocPDF;
import co.edu.unbosque.model.Problema;
import co.edu.unbosque.model.Profesor;
import co.edu.unbosque.model.UsuarioActual;
import co.edu.unbosque.service.EstudianteService;
import co.edu.unbosque.service.LibroService;
import co.edu.unbosque.service.ProblemaService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named(value = "problemabean")

@ViewScoped
public class ProblemaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombre;
	private int dificultad;
	private String tema;
	private String juez;

	ArrayList<Problema> problemas = new ArrayList<>();

	public ProblemaBean() {
		cargarProblemas();
	}

	public void cargarProblemas() {
		problemas = ProblemaService.doGetAll("http://localhost:8081/problema/getall");
	}

	public void crearProblema() {
		String json = "{";
		json += "\"nombre\":\"" + nombre + "\",";
		json += "\"dificultad\":" + dificultad + ",";
		json += "\"tema\":\"" + tema + "\",";
		json += "\"juez\":\"" + juez + "\"}";

		String respuesta = "";

		respuesta = ProblemaService.doPostJson(json);

		String[] data = respuesta.split("\n");
		System.out.println(data[0] + "" + data[1]);
		showStickyLogin(data[0], data[1]);

		cargarProblemas();

		nombre = "";
		dificultad = 0;
		tema = "";
		juez = "";
	}

	public void eliminarProblema(Problema problema) {
		System.out.println("Intentando eliminar: " + problema);
		System.out.println("ID recibido: " + problema.getId());
		String respuesta = ProblemaService.doDelete("http://localhost:8081/problema/eliminar?id=" + problema.getId());
		String[] data = respuesta.split("\n");
		if (data[0].equals("204")) {
			showStickyLogin(data[0], "problema eliminado");
			problemas.remove(problema);
			cargarProblemas();
			return;
		} else {
			showStickyLogin(data[0], "no se ha podido eliminar el problema.");
			cargarProblemas();
			return;
		}
	}

	public void showStickyLogin(String code, String content) {
		if (code.equals("201")) {
			FacesContext.getCurrentInstance().addMessage("sticky-key",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Hecho", ", se ha creado el problema"));
		} else if (code.equals("406")) {
			FacesContext.getCurrentInstance().addMessage("sticky-key",
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", content));
		} else if (code.equals("204")) {
			FacesContext.getCurrentInstance().addMessage("sticky-key",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Hecho", ", se ha eliminado el problema"));
		} else {
			System.out.println("Error en crear cuenta");
			System.out.println("Status code: " + code);
			System.out.println("reason: " + content);
			FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error Critico", "Error," + "comuniquese con el administrador"));
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDificultad() {
		return dificultad;
	}

	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getJuez() {
		return juez;
	}

	public void setJuez(String juez) {
		this.juez = juez;
	}

	public ArrayList<Problema> getProblemas() {
		return problemas;
	}

	public void setProblemas(ArrayList<Problema> problemas) {
		this.problemas = problemas;
	}

	public boolean getAdminIniciada() {
		if (UsuarioActual.getUsuarioActual() == null)
			return false;

		return UsuarioActual.getUsuarioActual() instanceof Admin
				|| UsuarioActual.getUsuarioActual() instanceof Profesor;
	}
}
