package co.edu.unbosque.beans;

import java.io.Serializable;
import java.util.ArrayList;

import co.edu.unbosque.model.Admin;
import co.edu.unbosque.model.DocPDF;
import co.edu.unbosque.model.UsuarioActual;
import co.edu.unbosque.service.LibroService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named(value = "librobean")

@ViewScoped
public class LibroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	ArrayList<DocPDF> listaLibros = new ArrayList<>();

	public LibroBean() {
		cargarLibros();
	}

	public void cargarLibros() {
		listaLibros = LibroService.doGetAll("http://localhost:8081/doc/pdf/getall");
	}
	
	public void eliminar(DocPDF libro) {
		LibroService.doDelete("http://localhost:8081/doc/pdf/eliminar?id=" + libro.getId());
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

		return UsuarioActual.getUsuarioActual() instanceof Admin;
	}
}
