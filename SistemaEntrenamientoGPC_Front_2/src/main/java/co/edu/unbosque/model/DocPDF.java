package co.edu.unbosque.model;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import co.edu.unbosque.service.LibroService;
import jakarta.faces.context.FacesContext;

public class DocPDF extends Doc {


	private String contenidoPdf;

	public DocPDF() {
	}

	public DocPDF(Long id, String contenidoPdf) {
		super();
		this.contenidoPdf = contenidoPdf;
	}

	public DocPDF(String nombre, String descripcion, String imagen,  String contenidoPdf) {
		super(nombre, descripcion, imagen);
		this.contenidoPdf = contenidoPdf;
	}

	public DocPDF(String nombre, String descripcion, String imagen) {
		super(nombre, descripcion, imagen);
		// TODO Auto-generated constructor stub
	}


	public String getContenidoPdf() {
		return contenidoPdf;
	}

	public void setContenidoPdf(String contenidoPdf) {
		this.contenidoPdf = contenidoPdf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(contenidoPdf);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		DocPDF other = (DocPDF) obj;
		return Objects.equals(contenidoPdf, other.contenidoPdf);
	}
	
	
	public StreamedContent getPdf() {
	    if (contenidoPdf == null) return null;
	    byte[] bytes = Base64.getDecoder().decode(contenidoPdf);
	    return DefaultStreamedContent.builder()
	            .name(getNombre() + ".pdf")
	            .contentType("application/pdf")
	            .stream(() -> new ByteArrayInputStream(bytes))
	            .build();
	}
}
