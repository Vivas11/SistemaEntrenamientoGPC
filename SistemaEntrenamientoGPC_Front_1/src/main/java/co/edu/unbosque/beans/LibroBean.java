package co.edu.unbosque.beans;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

import co.edu.unbosque.model.Admin;
import co.edu.unbosque.model.DocPDF;
import co.edu.unbosque.model.Profesor;
import co.edu.unbosque.model.UsuarioActual;
import co.edu.unbosque.service.LibroService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named(value = "librobean")
@ViewScoped
/**
 * Bean de vista responsable de la administración de documentos PDF ("Libros").
 * Permite listar, crear, descargar y eliminar documentos almacenados a través
 * de un servicio REST.
 */
public class LibroBean implements Serializable {

	/** Serial UID. */
	private static final long serialVersionUID = 1L;

	/** Nombre del libro a crear. */
	private String nombre;
	/** Descripción del libro a crear. */
	private String descripcion;
	/** Archivo PDF subido por el usuario. */
	private UploadedFile pdf;
	/** Imagen asociada al libro (carátula). */
	private UploadedFile img;

	/** Libro seleccionado para ver/descargar. */
	private DocPDF libroSeleccionado;
	/** Recurso de streaming para descarga del PDF. */
	private StreamedContent pdfDownload;

	/** Lista de libros obtenidos del servicio. */
	private ArrayList<DocPDF> listaLibros = new ArrayList<>();

	/**
	 * Selecciona un libro de la lista para mostrar detalles o habilitar su
	 * descarga.
	 * 
	 * @param libro Libro seleccionado.
	 */
	public void seleccionar(DocPDF libro) {
		this.libroSeleccionado = libro;
	}

	/**
	 * Genera un recurso descargable del PDF asociado al libro seleccionado.
	 * 
	 * @return {@link StreamedContent} listo para descarga o {@code null} si no hay
	 *         libro válido seleccionado.
	 */
	public StreamedContent getPdfDownload() {
		if (libroSeleccionado != null && libroSeleccionado.getPdf() != null) {
			byte[] bytes = Base64.getDecoder().decode(libroSeleccionado.getContenidoPdf());
			return DefaultStreamedContent.builder()
					.name(libroSeleccionado.getNombre() + ".pdf")
					.contentType("application/pdf")
					.stream(() -> new ByteArrayInputStream(bytes))
					.build();
		}
		return null;
	}

	/**
	 * Constructor que inicializa la lista de libros consultando el servicio.
	 */
	public LibroBean() {
		cargarLibros();
	}

	/**
	 * Recupera todos los libros desde el servicio REST.
	 */
	public void cargarLibros() {
		listaLibros = LibroService.doGetAll("http://localhost:8081/doc/pdf/getall");
	}

	/**
	 * Muestra mensajes contextualizados dependiendo del código devuelto por el
	 * servicio REST tras una operación.
	 * 
	 * @param code    Código de estado (200/204/406/etc.).
	 * @param content Mensaje a mostrar.
	 */
	public void showStickyLogin(String code, String content) {
		if (code.equals("204")) {
			FacesContext.getCurrentInstance().addMessage("sticky-key",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Hecho", content));
		} else if (code.equals("406")) {
			FacesContext.getCurrentInstance().addMessage("sticky-key",
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", content));
		} else {
			FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error Critico", "Error al crear,comuniquese con el administrador"));
		}
	}

	/**
	 * Elimina un libro del repositorio remoto y actualiza la lista local.
	 * 
	 * @param libro Libro a eliminar.
	 */
	public void eliminar(DocPDF libro) {
		String respuesta = LibroService.doDelete("http://localhost:8081/doc/pdf/eliminar?id=" + libro.getId());
		String[] data = respuesta.split("\n");
		if (data[0].equals("204")) {
			showStickyLogin(data[0], "Libro eliminado");
			listaLibros.remove(libro);
			cargarLibros();
			return;
		} else {
			showStickyLogin(data[0], "no se ha podido eliminar el libro.");
			cargarLibros();
			return;
		}
	}

    /**
     * Envía al servicio los datos y archivos para crear un nuevo libro (multipart
     * form-data). Tras la creación recarga la lista.
     */
    public void guardarNuevoLibro() {
        try {
            String url = "http://localhost:8081/doc/pdf/crear";
            String respuesta = LibroService.doPostMultipart(url, nombre, descripcion, img, pdf);
            String[] data = respuesta.split("\n");
    		showStickyLogin(data[0], data[1]);
            cargarLibros();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	/**
	 * @return Lista de libros gestionados por el bean.
	 */
	public ArrayList<DocPDF> getListaLibros() {
		return listaLibros;
	}

	/**
	 * @param listaLibros Lista de libros a establecer.
	 */
	public void setListaLibros(ArrayList<DocPDF> listaLibros) {
		this.listaLibros = listaLibros;
	}

	/**
	 * Indica si el usuario autenticado es administrador o profesor.
	 * 
	 * @return true si hay sesión y el rol es Admin o Profesor.
	 */
	public boolean getAdminIniciada() {
		if (UsuarioActual.getUsuarioActual() == null)
			return false;
		return UsuarioActual.getUsuarioActual() instanceof Admin
				|| UsuarioActual.getUsuarioActual() instanceof Profesor;
	}

	/**
	 * @return Nombre del libro a crear.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre Nombre del libro.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return Descripción del libro.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion Descripción del libro.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return Archivo PDF subido.
	 */
	public UploadedFile getPdf() {
		return pdf;
	}

	/**
	 * @param pdf Archivo PDF a establecer.
	 */
	public void setPdf(UploadedFile pdf) {
		this.pdf = pdf;
	}

	/**
	 * @return Imagen asociada.
	 */
	public UploadedFile getImg() {
		return img;
	}

	/**
	 * @param img Imagen asociada.
	 */
	public void setImg(UploadedFile img) {
		this.img = img;
	}

	/**
	 * @return Libro actualmente seleccionado.
	 */
	public DocPDF getLibroSeleccionado() {
		return libroSeleccionado;
	}

	/**
	 * @param libroSeleccionado Libro seleccionado.
	 */
	public void setLibroSeleccionado(DocPDF libroSeleccionado) {
		this.libroSeleccionado = libroSeleccionado;
	}

	/**
	 * @param pdfDownload Recurso de descarga ya construido.
	 */
	public void setPdfDownload(StreamedContent pdfDownload) {
		this.pdfDownload = pdfDownload;
	}

}
