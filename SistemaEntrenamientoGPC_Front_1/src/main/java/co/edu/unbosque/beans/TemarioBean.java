package co.edu.unbosque.beans;

import java.io.Serializable;
import java.util.ArrayList;

import co.edu.unbosque.model.Admin;
import co.edu.unbosque.model.Profesor;
import co.edu.unbosque.model.Tema;
import co.edu.unbosque.model.UsuarioActual;
import co.edu.unbosque.service.ProblemaService;
import co.edu.unbosque.service.TemaService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named(value = "temarioBean")
@ViewScoped
/**
 * Bean de vista para la organización y gestión de los distintos temas del
 * temario. Agrupa los objetos {@link Tema} por categoría (básica, búsqueda,
 * grafos, etc.) para facilitar su renderizado en la interfaz y permite crear y
 * eliminar temas mediante el consumo de servicios REST.
 */
public class TemarioBean implements Serializable{

	private static final long serialVersionUID = 1L;
	/** Lista completa de temas recuperados del servicio. */
	private ArrayList<Tema> temas;
	/** Temas de teoría básica. */
	private ArrayList<Tema> teoriaBasica;
	/** Temas de búsqueda. */
	private ArrayList<Tema> busqueda;
	/** Temas de ordenamiento. */
	private ArrayList<Tema> ordenamiento;
	/** Temas de programación dinámica. */
	private ArrayList<Tema> programacionDinamica;
	/** Temas sobre estructuras de datos. */
	private ArrayList<Tema> estructurasDatos;
	/** Temas de matemáticas. */
	private ArrayList<Tema> matematicas;
	/** Temas de operaciones bitwise. */
	private ArrayList<Tema> bitwise;
	/** Temas de cadenas (string). */
	private ArrayList<Tema> cadenas;
	/** Temas de geometría. */
	private ArrayList<Tema> geometria;
	/** Temas de grafos. */
	private ArrayList<Tema> grafos;

	/** Tema actualmente seleccionado (para mostrar detalles). */
	private Tema temaSeleccionado;

	/** Nombre del tema a crear. */
	private String tema;
	/** Tipo/categoría del tema a crear. */
	private String tipo;
	/** Contenido (texto) del tema a crear. */
	private String contenido;

	/**
	 * Constructor que inicializa las colecciones y dispara la primera carga de
	 * temas.
	 */
	public TemarioBean() {
		temas = new ArrayList<>();
		teoriaBasica = new ArrayList<>();
		busqueda = new ArrayList<>();
		ordenamiento = new ArrayList<>();
		programacionDinamica = new ArrayList<>();
		estructurasDatos = new ArrayList<>();
		matematicas = new ArrayList<>();
		bitwise = new ArrayList<>();
		cadenas = new ArrayList<>();
		geometria = new ArrayList<>();
		grafos = new ArrayList<>();
		cargarTemas();
	}

	/**
	 * Recupera todos los temas desde el servicio y los distribuye según su tipo
	 * en las listas correspondientes para facilitar su visualización agrupada.
	 */
	public void cargarTemas() {
		temas = TemaService.doGetAll("http://localhost:8081/tema/getall");
		if (temas == null) {
			temas = new ArrayList<>();
		}
		// Limpiar listas previas antes de reclasificar
		teoriaBasica.clear();
		busqueda.clear();
		ordenamiento.clear();
		programacionDinamica.clear();
		estructurasDatos.clear();
		matematicas.clear();
		bitwise.clear();
		cadenas.clear();
		geometria.clear();
		grafos.clear();

		for (Tema t : temas) {
			switch (t.getTipo()) {
			case "basica":
				teoriaBasica.add(t);
				break;
			case "busqueda":
				busqueda.add(t);
				break;
			case "ordenamiento":
				ordenamiento.add(t);
				break;
			case "dinamica":
				programacionDinamica.add(t);
				break;
			case "estructuras":
				estructurasDatos.add(t);
				break;
			case "matematicas":
				matematicas.add(t);
				break;
			case "bitwise":
				bitwise.add(t);
				break;
			case "cadenas":
				cadenas.add(t);
				break;
			case "geometria":
				geometria.add(t);
				break;
			case "grafos":
				grafos.add(t);
				break;
			default:
				break;
			}
		}
	}

	/**
	 * @return Tema actualmente seleccionado.
	 */
	public Tema getTemaSeleccionado() {
		return temaSeleccionado;
	}

	/**
	 * Selecciona un tema para mostrar detalles.
	 * 
	 * @param temaSeleccionado Tema a seleccionar.
	 */
	public void seleccionarTema(Tema temaSeleccionado) {
		this.temaSeleccionado = temaSeleccionado;
	}

	/**
	 * Crea un nuevo tema construyendo manualmente un JSON y enviándolo al
	 * servicio REST. Luego de una respuesta exitosa, recarga las listas y limpia
	 * el formulario.
	 */
	public void crearTema() {
		String safeContenido = contenido.replace("\n", "\\n").replace("\r", "\\r");
		String json = "{";
		json += "\"tema\":\"" + tema + "\",";
		json += "\"tipo\":\"" + tipo + "\",";
		json += "\"contenido\":\"" + safeContenido + "\"}";
		String respuesta = TemaService.doPostJson(json);
		String[] data = respuesta.split("\n");
		showStickyLogin(data[0], data[1]);
		cargarTemas();
		tema = "";
		tipo = "";
		contenido = "";
	}


	/**
	 * Elimina un tema dado su identificador usando el servicio REST.
	 * 
	 * @param tema Tema a eliminar.
	 */
	public void eliminarTema(Tema tema) {
		String respuesta = ProblemaService.doDelete("http://localhost:8081/tema/eliminar?id=" + tema.getId());
		String[] data = respuesta.split("\n");
		if (data[0].equals("204")) {
			showStickyLogin(data[0], "tema eliminado");
			cargarTemas();
			return;
		} else {
			showStickyLogin(data[0], "no se ha podido eliminar el tema.");
			cargarTemas();
			return;
		}
	}

	/**
	 * Muestra mensajes de retroalimentación según el código devuelto por el
	 * servicio REST.
	 * 
	 * @param code    Código de estado.
	 * @param content Mensaje adicional.
	 */
	public void showStickyLogin(String code, String content) {
		if (code.equals("201")) {
			FacesContext.getCurrentInstance().addMessage("sticky-key",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Hecho", ", se ha creado el tema"));
		} else if (code.equals("406")) {
			FacesContext.getCurrentInstance().addMessage("sticky-key",
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", content));
		} else if (code.equals("204")) {
			FacesContext.getCurrentInstance().addMessage("sticky-key",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Hecho", ", se ha eliminado el tema"));
		} else {
			FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error Critico", "Error,comuniquese con el administrador"));
		}
	}

	/**
	 * Asigna el tema cuyos detalles deben mostrarse.
	 * 
	 * @param tema Tema seleccionado.
	 */
	public void verDetalles(Tema tema) {
		this.temaSeleccionado = tema;
	}

	/**
	 * @return Lista completa de temas.
	 */
	public ArrayList<Tema> getTemas() {
		return temas;
	}

	/**
	 * @param temas Lista completa de temas.
	 */
	public void setTemas(ArrayList<Tema> temas) {
		this.temas = temas;
	}

	/**
	 * @return Lista de temas de teoría básica.
	 */
	public ArrayList<Tema> getTeoriaBasica() {
		return teoriaBasica;
	}

	/**
	 * @param teoriaBasica Lista de teoría básica.
	 */
	public void setTeoriaBasica(ArrayList<Tema> teoriaBasica) {
		this.teoriaBasica = teoriaBasica;
	}

	/**
	 * @return Lista de temas de búsqueda.
	 */
	public ArrayList<Tema> getBusqueda() {
		return busqueda;
	}

	/**
	 * @param busqueda Lista de búsqueda.
	 */
	public void setBusqueda(ArrayList<Tema> busqueda) {
		this.busqueda = busqueda;
	}

	/**
	 * @return Lista de temas de ordenamiento.
	 */
	public ArrayList<Tema> getOrdenamiento() {
		return ordenamiento;
	}

	/**
	 * @param ordenamiento Lista de ordenamiento.
	 */
	public void setOrdenamiento(ArrayList<Tema> ordenamiento) {
		this.ordenamiento = ordenamiento;
	}

	/**
	 * @return Lista de programación dinámica.
	 */
	public ArrayList<Tema> getProgramacionDinamica() {
		return programacionDinamica;
	}

	/**
	 * @param programacionDinamica Lista de programación dinámica.
	 */
	public void setProgramacionDinamica(ArrayList<Tema> programacionDinamica) {
		this.programacionDinamica = programacionDinamica;
	}

	/**
	 * @return Lista de estructuras de datos.
	 */
	public ArrayList<Tema> getEstructurasDatos() {
		return estructurasDatos;
	}

	/**
	 * @param estructurasDatos Lista de estructuras de datos.
	 */
	public void setEstructurasDatos(ArrayList<Tema> estructurasDatos) {
		this.estructurasDatos = estructurasDatos;
	}

	/**
	 * @return Lista de matemáticas.
	 */
	public ArrayList<Tema> getMatematicas() {
		return matematicas;
	}

	/**
	 * @param matematicas Lista de matemáticas.
	 */
	public void setMatematicas(ArrayList<Tema> matematicas) {
		this.matematicas = matematicas;
	}

	/**
	 * @return Lista de bitwise.
	 */
	public ArrayList<Tema> getBitwise() {
		return bitwise;
	}

	/**
	 * @param bitwise Lista de bitwise.
	 */
	public void setBitwise(ArrayList<Tema> bitwise) {
		this.bitwise = bitwise;
	}

	/**
	 * @return Lista de cadenas.
	 */
	public ArrayList<Tema> getCadenas() {
		return cadenas;
	}

	/**
	 * @param cadenas Lista de cadenas.
	 */
	public void setCadenas(ArrayList<Tema> cadenas) {
		this.cadenas = cadenas;
	}

	/**
	 * @return Lista de geometría.
	 */
	public ArrayList<Tema> getGeometria() {
		return geometria;
	}

	/**
	 * @param geometria Lista de geometría.
	 */
	public void setGeometria(ArrayList<Tema> geometria) {
		this.geometria = geometria;
	}

	/**
	 * @return Lista de grafos.
	 */
	public ArrayList<Tema> getGrafos() {
		return grafos;
	}

	/**
	 * @param grafos Lista de grafos.
	 */
	public void setGrafos(ArrayList<Tema> grafos) {
		this.grafos = grafos;
	}

	/**
	 * @return Nombre del tema en edición/creación.
	 */
	public String getTema() {
		return tema;
	}

	/**
	 * @param tema Nombre del tema.
	 */
	public void setTema(String tema) {
		this.tema = tema;
	}

	/**
	 * @return Tipo/categoría del tema.
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo Categoría del tema.
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return Contenido descriptivo del tema.
	 */
	public String getContenido() {
		return contenido;
	}

	/**
	 * @param contenido Contenido del tema.
	 */
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	/**
	 * Indica si el usuario autenticado posee rol de administrador o profesor.
	 * 
	 * @return true si es Admin o Profesor.
	 */
	public boolean getAdminIniciada() {
		if (UsuarioActual.getUsuarioActual() == null)
			return false;
		return UsuarioActual.getUsuarioActual() instanceof Admin
				|| UsuarioActual.getUsuarioActual() instanceof Profesor;
	}

}
