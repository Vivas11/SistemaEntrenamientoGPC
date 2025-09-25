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

/**
 * Bean de vista para la organización y gestión de los distintos temas del
 * temario. Agrupa los objetos {@link Tema} por categoría (básica, búsqueda,
 * grafos, etc.) para facilitar su renderizado en la interfaz y permite crear y
 * eliminar temas mediante el consumo de servicios REST.
 */
@Named(value = "temarioBean")
@ViewScoped
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
	 * Asigna el tema cuyos detalles se mostrarán en la interfaz.
	 * Este método se utiliza para seleccionar un tema específico y cargar su información
	 * en la vista de detalles.
	 *
	 * @param tema Tema seleccionado para mostrar sus detalles.
	 */
	public void verDetalles(Tema tema) {
	    this.temaSeleccionado = tema;
	}

	/**
	 * Obtiene la lista completa de temas disponibles.
	 *
	 * @return Lista de todos los temas registrados.
	 */
	public ArrayList<Tema> getTemas() {
	    return temas;
	}

	/**
	 * Establece la lista completa de temas.
	 *
	 * @param temas Lista de temas a asignar.
	 */
	public void setTemas(ArrayList<Tema> temas) {
	    this.temas = temas;
	}

	/**
	 * Obtiene la lista de temas pertenecientes a la categoría de teoría básica.
	 *
	 * @return Lista de temas de teoría básica.
	 */
	public ArrayList<Tema> getTeoriaBasica() {
	    return teoriaBasica;
	}

	/**
	 * Establece la lista de temas de teoría básica.
	 *
	 * @param teoriaBasica Lista de temas de teoría básica a asignar.
	 */
	public void setTeoriaBasica(ArrayList<Tema> teoriaBasica) {
	    this.teoriaBasica = teoriaBasica;
	}

	/**
	 * Obtiene la lista de temas relacionados con algoritmos de búsqueda.
	 *
	 * @return Lista de temas de búsqueda.
	 */
	public ArrayList<Tema> getBusqueda() {
	    return busqueda;
	}

	/**
	 * Establece la lista de temas de búsqueda.
	 *
	 * @param busqueda Lista de temas de búsqueda a asignar.
	 */
	public void setBusqueda(ArrayList<Tema> busqueda) {
	    this.busqueda = busqueda;
	}

	/**
	 * Obtiene la lista de temas relacionados con algoritmos de ordenamiento.
	 *
	 * @return Lista de temas de ordenamiento.
	 */
	public ArrayList<Tema> getOrdenamiento() {
	    return ordenamiento;
	}

	/**
	 * Establece la lista de temas de ordenamiento.
	 *
	 * @param ordenamiento Lista de temas de ordenamiento a asignar.
	 */
	public void setOrdenamiento(ArrayList<Tema> ordenamiento) {
	    this.ordenamiento = ordenamiento;
	}

	/**
	 * Obtiene la lista de temas relacionados con programación dinámica.
	 *
	 * @return Lista de temas de programación dinámica.
	 */
	public ArrayList<Tema> getProgramacionDinamica() {
	    return programacionDinamica;
	}

	/**
	 * Establece la lista de temas de programación dinámica.
	 *
	 * @param programacionDinamica Lista de temas de programación dinámica a asignar.
	 */
	public void setProgramacionDinamica(ArrayList<Tema> programacionDinamica) {
	    this.programacionDinamica = programacionDinamica;
	}

	/**
	 * Obtiene la lista de temas relacionados con estructuras de datos.
	 *
	 * @return Lista de temas de estructuras de datos.
	 */
	public ArrayList<Tema> getEstructurasDatos() {
	    return estructurasDatos;
	}

	/**
	 * Establece la lista de temas de estructuras de datos.
	 *
	 * @param estructurasDatos Lista de temas de estructuras de datos a asignar.
	 */
	public void setEstructurasDatos(ArrayList<Tema> estructurasDatos) {
	    this.estructurasDatos = estructurasDatos;
	}

	/**
	 * Obtiene la lista de temas relacionados con matemáticas.
	 *
	 * @return Lista de temas de matemáticas.
	 */
	public ArrayList<Tema> getMatematicas() {
	    return matematicas;
	}

	/**
	 * Establece la lista de temas de matemáticas.
	 *
	 * @param matematicas Lista de temas de matemáticas a asignar.
	 */
	public void setMatematicas(ArrayList<Tema> matematicas) {
	    this.matematicas = matematicas;
	}

	/**
	 * Obtiene la lista de temas relacionados con operaciones bitwise.
	 *
	 * @return Lista de temas de bitwise.
	 */
	public ArrayList<Tema> getBitwise() {
	    return bitwise;
	}

	/**
	 * Establece la lista de temas de bitwise.
	 *
	 * @param bitwise Lista de temas de bitwise a asignar.
	 */
	public void setBitwise(ArrayList<Tema> bitwise) {
	    this.bitwise = bitwise;
	}

	/**
	 * Obtiene la lista de temas relacionados con manipulación de cadenas.
	 *
	 * @return Lista de temas de cadenas.
	 */
	public ArrayList<Tema> getCadenas() {
	    return cadenas;
	}

	/**
	 * Establece la lista de temas de cadenas.
	 *
	 * @param cadenas Lista de temas de cadenas a asignar.
	 */
	public void setCadenas(ArrayList<Tema> cadenas) {
	    this.cadenas = cadenas;
	}

	/**
	 * Obtiene la lista de temas relacionados con geometría.
	 *
	 * @return Lista de temas de geometría.
	 */
	public ArrayList<Tema> getGeometria() {
	    return geometria;
	}

	/**
	 * Establece la lista de temas de geometría.
	 *
	 * @param geometria Lista de temas de geometría a asignar.
	 */
	public void setGeometria(ArrayList<Tema> geometria) {
	    this.geometria = geometria;
	}

	/**
	 * Obtiene la lista de temas relacionados con grafos.
	 *
	 * @return Lista de temas de grafos.
	 */
	public ArrayList<Tema> getGrafos() {
	    return grafos;
	}

	/**
	 * Establece la lista de temas de grafos.
	 *
	 * @param grafos Lista de temas de grafos a asignar.
	 */
	public void setGrafos(ArrayList<Tema> grafos) {
	    this.grafos = grafos;
	}

	/**
	 * Obtiene el nombre del tema actualmente seleccionado o en edición.
	 *
	 * @return Nombre del tema.
	 */
	public String getTema() {
	    return tema;
	}

	/**
	 * Establece el nombre del tema.
	 *
	 * @param tema Nombre del tema a asignar.
	 */
	public void setTema(String tema) {
	    this.tema = tema;
	}

	/**
	 * Obtiene la categoría o tipo del tema actualmente seleccionado o en edición.
	 *
	 * @return Categoría del tema.
	 */
	public String getTipo() {
	    return tipo;
	}

	/**
	 * Establece la categoría o tipo del tema.
	 *
	 * @param tipo Categoría del tema a asignar.
	 */
	public void setTipo(String tipo) {
	    this.tipo = tipo;
	}

	/**
	 * Obtiene el contenido descriptivo del tema actualmente seleccionado o en edición.
	 *
	 * @return Contenido del tema.
	 */
	public String getContenido() {
	    return contenido;
	}

	/**
	 * Establece el contenido descriptivo del tema.
	 *
	 * @param contenido Contenido del tema a asignar.
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
