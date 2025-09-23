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
public class TemarioBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private ArrayList<Tema> temas;
	private ArrayList<Tema> teoriaBasica;
	private ArrayList<Tema> busqueda;
	private ArrayList<Tema> ordenamiento;
	private ArrayList<Tema> programacionDinamica;
	private ArrayList<Tema> estructurasDatos;
	private ArrayList<Tema> matematicas;
	private ArrayList<Tema> bitwise;
	private ArrayList<Tema> cadenas;
	private ArrayList<Tema> geometria;
	private ArrayList<Tema> grafos;

	private Tema temaSeleccionado;

	private String tema;
	private String tipo;
	private String contenido;

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

	public void cargarTemas() {
		temas = TemaService.doGetAll("http://localhost:8081/tema/getall");
		 if (temas == null) {
			 temas = new ArrayList<>();
		    }
		for (Tema tema : temas) {
			switch (tema.getTipo()) {
			case "basica": {
				teoriaBasica.add(tema);
				break;
			}
			case "busqueda": {
				busqueda.add(tema);
				break;
			}
			case "ordenamiento": {
				ordenamiento.add(tema);
				break;
			}
			case "dinamica": {
				programacionDinamica.add(tema);
				break;
			}
			case "estructuras": {
				estructurasDatos.add(tema);
				break;
			}
			case "matematicas": {
				matematicas.add(tema);
				break;
			}
			case "bitwise": {
				bitwise.add(tema);
				break;
			}
			case "cadenas": {
				cadenas.add(tema);
				break;
			}
			case "geometria": {
				geometria.add(tema);
				break;
			}
			case "grafos": {
				grafos.add(tema);
				break;
			}
			default:
				break;
			}
		}
	}

	public Tema getTemaSeleccionado() {
		return temaSeleccionado;
	}

	public void seleccionarTema(Tema temaSeleccionado) {
		this.temaSeleccionado = temaSeleccionado;
	}

	public void crearTema() {
		String safeContenido = contenido.replace("\n", "\\n").replace("\r", "\\r");
		
	    String json = "{";
	    json += "\"tema\":\"" + tema + "\",";
	    json += "\"tipo\":\"" + tipo + "\",";  
	    json += "\"contenido\":\"" + safeContenido + "\"}";

	    String respuesta = TemaService.doPostJson(json);

	    String[] data = respuesta.split("\n");
	    System.out.println(data[0] + " " + data[1]);
	    showStickyLogin(data[0], data[1]);

	    cargarTemas();

	    tema = "";
	    tipo = "";
	    contenido = "";
	}


	public void eliminarTema(Tema tema) {
		System.out.println("Intentando eliminar: " + tema);
		System.out.println("ID recibido: " + tema.getId());
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
			System.out.println("Error en crear cuenta");
			System.out.println("Status code: " + code);
			System.out.println("reason: " + content);
			FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error Critico", "Error," + "comuniquese con el administrador"));
		}
	}

	public void verDetalles(Tema tema) {
		this.temaSeleccionado = tema;
	}

	public ArrayList<Tema> getTemas() {
		return temas;
	}

	public void setTemas(ArrayList<Tema> temas) {
		this.temas = temas;
	}

	public ArrayList<Tema> getTeoriaBasica() {
		return teoriaBasica;
	}

	public void setTeoriaBasica(ArrayList<Tema> teoriaBasica) {
		this.teoriaBasica = teoriaBasica;
	}

	public ArrayList<Tema> getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(ArrayList<Tema> busqueda) {
		this.busqueda = busqueda;
	}

	public ArrayList<Tema> getOrdenamiento() {
		return ordenamiento;
	}

	public void setOrdenamiento(ArrayList<Tema> ordenamiento) {
		this.ordenamiento = ordenamiento;
	}

	public ArrayList<Tema> getProgramacionDinamica() {
		return programacionDinamica;
	}

	public void setProgramacionDinamica(ArrayList<Tema> programacionDinamica) {
		this.programacionDinamica = programacionDinamica;
	}

	public ArrayList<Tema> getEstructurasDatos() {
		return estructurasDatos;
	}

	public void setEstructurasDatos(ArrayList<Tema> estructurasDatos) {
		this.estructurasDatos = estructurasDatos;
	}

	public ArrayList<Tema> getMatematicas() {
		return matematicas;
	}

	public void setMatematicas(ArrayList<Tema> matematicas) {
		this.matematicas = matematicas;
	}

	public ArrayList<Tema> getBitwise() {
		return bitwise;
	}

	public void setBitwise(ArrayList<Tema> bitwise) {
		this.bitwise = bitwise;
	}

	public ArrayList<Tema> getCadenas() {
		return cadenas;
	}

	public void setCadenas(ArrayList<Tema> cadenas) {
		this.cadenas = cadenas;
	}

	public ArrayList<Tema> getGeometria() {
		return geometria;
	}

	public void setGeometria(ArrayList<Tema> geometria) {
		this.geometria = geometria;
	}

	public ArrayList<Tema> getGrafos() {
		return grafos;
	}

	public void setGrafos(ArrayList<Tema> grafos) {
		this.grafos = grafos;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public boolean getAdminIniciada() {
		if (UsuarioActual.getUsuarioActual() == null)
			return false;

		return UsuarioActual.getUsuarioActual() instanceof Admin
				|| UsuarioActual.getUsuarioActual() instanceof Profesor;
	}

}
