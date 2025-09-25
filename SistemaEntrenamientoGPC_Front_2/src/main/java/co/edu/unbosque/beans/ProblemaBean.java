package co.edu.unbosque.beans;

import java.io.Serializable;
import java.util.ArrayList;

import co.edu.unbosque.model.Admin;
import co.edu.unbosque.model.Problema;
import co.edu.unbosque.model.Profesor;
import co.edu.unbosque.model.UsuarioActual;
import co.edu.unbosque.service.ProblemaService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named(value = "problemabean")
@ViewScoped
/**
 * Bean de vista para la gestión de problemas (ejercicios) de programación.
 * Permite listar, crear y eliminar problemas consumiendo servicios REST.
 */
public class ProblemaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	/** Nombre del problema. */
	private String nombre;
	/** Dificultad numérica del problema. */
	private int dificultad;
	/** Tema asociado al problema. */
	private String tema;
	/** Juez o plataforma (por ejemplo, UVA, Codeforces). */
	private String juez;

	/** Lista de problemas cargados desde el servicio. */
	ArrayList<Problema> problemas = new ArrayList<>();

	/**
	 * Constructor que inicia la carga inicial de problemas.
	 */
	public ProblemaBean() {
		cargarProblemas();
	}

	/**
	 * Recupera todos los problemas desde el servicio REST.
	 */
	public void cargarProblemas() {
		problemas = ProblemaService.doGetAll("http://localhost:8081/problema/getall");
	}

	/**
	 * Construye un JSON simple con los datos del formulario y solicita la creación
	 * de un nuevo problema mediante el servicio REST. Tras la creación, recarga la
	 * lista y limpia los campos del formulario.
	 */
	public void crearProblema() {
		String json = "{";
		json += "\"nombre\":\"" + nombre + "\",";
		json += "\"dificultad\":" + dificultad + ",";
		json += "\"tema\":\"" + tema + "\",";
		json += "\"juez\":\"" + juez + "\"}";

		String respuesta = ProblemaService.doPostJson(json);
		String[] data = respuesta.split("\n");
		showStickyLogin(data[0], data[1]);
		cargarProblemas();

		nombre = "";
		dificultad = 0;
		tema = "";
		juez = "";
	}

	/**
	 * Elimina un problema específico invocando el servicio REST correspondiente y
	 * actualiza la lista local.
	 * 
	 * @param problema Problema a eliminar.
	 */
	public void eliminarProblema(Problema problema) {
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

	/**
	 * Muestra mensajes contextualizados de acuerdo al código devuelto por el
	 * servicio REST.
	 * 
	 * @param code    Código de estado (201, 204, 406, etc.).
	 * @param content Mensaje adicional.
	 */
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
			FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error Critico", "Error,comuniquese con el administrador"));
		}
	}

	/**
	 * @return Nombre del problema.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre Nombre del problema.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return Nivel de dificultad.
	 */
	public int getDificultad() {
		return dificultad;
	}

	/**
	 * @param dificultad Nivel de dificultad.
	 */
	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}

	/**
	 * @return Tema del problema.
	 */
	public String getTema() {
		return tema;
	}

	/**
	 * @param tema Tema del problema.
	 */
	public void setTema(String tema) {
		this.tema = tema;
	}

	/**
	 * @return Juez/plataforma asociada.
	 */
	public String getJuez() {
		return juez;
	}

	/**
	 * @param juez Plataforma de referencia.
	 */
	public void setJuez(String juez) {
		this.juez = juez;
	}

	/**
	 * @return Lista de problemas.
	 */
	public ArrayList<Problema> getProblemas() {
		return problemas;
	}

	/**
	 * @param problemas Lista de problemas a establecer.
	 */
	public void setProblemas(ArrayList<Problema> problemas) {
		this.problemas = problemas;
	}

	/**
	 * Indica si el usuario autenticado es Admin o Profesor.
	 * 
	 * @return true si el rol es Admin/Profesor.
	 */
	public boolean getAdminIniciada() {
		if (UsuarioActual.getUsuarioActual() == null)
			return false;
		return UsuarioActual.getUsuarioActual() instanceof Admin
				|| UsuarioActual.getUsuarioActual() instanceof Profesor;
	}
}
