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
public class LibroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre;
	private String descripcion;
	private UploadedFile pdf;
	private UploadedFile img;

	private DocPDF libroSeleccionado;
	private StreamedContent pdfDownload;

	private ArrayList<DocPDF> listaLibros = new ArrayList<>();

	public void seleccionar(DocPDF libro) {
		this.libroSeleccionado = libro;
	}

	public StreamedContent getPdfDownload() {
		if (libroSeleccionado != null && libroSeleccionado.getPdf() != null) {

			byte[] bytes = Base64.getDecoder().decode(libroSeleccionado.getContenidoPdf());
			return DefaultStreamedContent.builder().name(libroSeleccionado.getNombre() + ".pdf")
					.contentType("application/pdf").stream(() -> new ByteArrayInputStream(bytes)).build();
		}
		return null;
	}

	public LibroBean() {
		cargarLibros();
	}

	public void cargarLibros() {
		listaLibros = LibroService.doGetAll("http://localhost:8081/doc/pdf/getall");
	}

	public void showStickyLogin(String code, String content) {
		if (code.equals("204")) {
			FacesContext.getCurrentInstance().addMessage("sticky-key",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Hecho", content));
		} else if (code.equals("406")) {
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

	public void guardarNuevoLibro() {
		try {
			String url = "http://localhost:8081/doc/pdf/crear";
			System.out.println(url);
			String respuesta = LibroService.doPostMultipart(url, nombre, descripcion, img, // UploadedFile imagen
					pdf // UploadedFile pdf
			);
			System.out.println("Respuesta servidor: " + respuesta);
			cargarLibros();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<DocPDF> getListaLibros() {
		return listaLibros;
	}

	public void setListaLibros(ArrayList<DocPDF> listaLibros) {
		this.listaLibros = listaLibros;
	}

	public boolean getAdminIniciada() {
		if (UsuarioActual.getUsuarioActual() == null)
			return false;

		return UsuarioActual.getUsuarioActual() instanceof Admin
				|| UsuarioActual.getUsuarioActual() instanceof Profesor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public UploadedFile getPdf() {
		return pdf;
	}

	public void setPdf(UploadedFile pdf) {
		this.pdf = pdf;
	}

	public UploadedFile getImg() {
		return img;
	}

	public void setImg(UploadedFile img) {
		this.img = img;
	}

	public DocPDF getLibroSeleccionado() {
		return libroSeleccionado;
	}

	public void setLibroSeleccionado(DocPDF libroSeleccionado) {
		this.libroSeleccionado = libroSeleccionado;
	}

	public void setPdfDownload(StreamedContent pdfDownload) {
		this.pdfDownload = pdfDownload;
	}

}
