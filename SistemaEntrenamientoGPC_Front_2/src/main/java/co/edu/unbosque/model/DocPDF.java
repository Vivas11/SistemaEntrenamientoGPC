package co.edu.unbosque.model;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.Objects;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 * Representa un documento PDF (extiende {@link Doc}) incluyendo el contenido
 * propiamente dicho codificado en Base64 para su transporte/almacenamiento.
 */
public class DocPDF extends Doc {

	/** Contenido del PDF codificado en Base64. */
	private String contenidoPdf;

	/** Constructor vacío requerido por frameworks. */
	public DocPDF() {
	}

	/**
	 * Constructor parcial.
	 * 
	 * @param id           Identificador.
	 * @param contenidoPdf Contenido codificado en Base64.
	 */
	public DocPDF(Long id, String contenidoPdf) {
		super();
		setId(id);
		this.contenidoPdf = contenidoPdf;
	}

	/**
	 * Constructor completo con contenido.
	 * 
	 * @param nombre       Nombre del documento.
	 * @param descripcion  Descripción del documento.
	 * @param imagen       Imagen asociada.
	 * @param contenidoPdf Contenido codificado en Base64.
	 */
	public DocPDF(String nombre, String descripcion, String imagen, String contenidoPdf) {
		super(nombre, descripcion, imagen);
		this.contenidoPdf = contenidoPdf;
	}

	/**
	 * Constructor sin contenido (puede añadirse posteriormente).
	 * 
	 * @param nombre      Nombre del documento.
	 * @param descripcion Descripción del documento.
	 * @param imagen      Imagen asociada.
	 */
	public DocPDF(String nombre, String descripcion, String imagen) {
		super(nombre, descripcion, imagen);
	}

	/**
	 * @return Contenido PDF codificado.
	 */
	public String getContenidoPdf() {
		return contenidoPdf;
	}

	/**
	 * @param contenidoPdf Contenido codificado en Base64.
	 */
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

	/**
	 * Construye un recurso descargable para PrimeFaces a partir del contenido
	 * Base64.
	 * 
	 * @return {@link StreamedContent} listo para descargar o null si no hay
	 *         contenido.
	 */
	public StreamedContent getPdf() {
		if (contenidoPdf == null)
			return null;
		byte[] bytes = Base64.getDecoder().decode(contenidoPdf);
		return DefaultStreamedContent.builder().name(getNombre() + ".pdf").contentType("application/pdf")
				.stream(() -> new ByteArrayInputStream(bytes)).build();
	}
}
