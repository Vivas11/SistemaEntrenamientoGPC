package co.edu.unbosque.sistemaentrenamientoGPC_back.entity;

import java.util.Arrays;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;

/**
 * Entidad que representa un documento con contenido PDF adicional.
 * Extiende la clase abstracta {@link Doc} para incluir el archivo PDF como BLOB.
 * Se utiliza para almacenar documentos enriquecidos con contenido binario.
 */
@Entity
public class DocPDF extends Doc {

	/** Contenido binario del archivo PDF asociado al documento. */
	@Lob
	private byte[] contenidoPdf;

	/**
	 * Constructor vacío requerido por JPA.
	 */
	public DocPDF() {
	}

	/**
	 * Constructor que inicializa el documento con su ID y contenido PDF.
	 * 
	 * @param id           Identificador del documento
	 * @param contenidoPdf Contenido binario del archivo PDF
	 */
	public DocPDF(Long id, byte[] contenidoPdf) {
		super();
		this.contenidoPdf = contenidoPdf;
	}

	/**
	 * Constructor que inicializa el documento con nombre, descripción, imagen y contenido PDF.
	 * 
	 * @param nombre       Nombre del documento
	 * @param descripcion  Descripción del documento
	 * @param imagen       Imagen asociada en formato binario
	 * @param contenidoPdf Contenido binario del archivo PDF
	 */
	public DocPDF(String nombre, String descripcion, byte[] imagen, byte[] contenidoPdf) {
		super(nombre, descripcion, imagen);
		this.contenidoPdf = contenidoPdf;
	}

	/**
	 * Constructor que inicializa el documento con nombre, descripción e imagen.
	 * 
	 * @param nombre      Nombre del documento
	 * @param descripcion Descripción del documento
	 * @param imagen      Imagen asociada en formato binario
	 */
	public DocPDF(String nombre, String descripcion, byte[] imagen) {
		super(nombre, descripcion, imagen);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Obtiene el contenido binario del archivo PDF.
	 * 
	 * @return contenidoPdf como arreglo de bytes
	 */
	public byte[] getContenidoPdf() {
		return contenidoPdf;
	}

	/**
	 * Establece el contenido binario del archivo PDF.
	 * 
	 * @param contenidoPdf arreglo de bytes que representa el PDF
	 */
	public void setContenidoPdf(byte[] contenidoPdf) {
		this.contenidoPdf = contenidoPdf;
	}

	/**
	 * Calcula el código hash del objeto, incluyendo el contenido PDF.
	 * 
	 * @return valor hash del objeto
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(contenidoPdf);
		return result;
	}

	/**
	 * Compara este objeto con otro para verificar igualdad.
	 * 
	 * @param obj objeto a comparar
	 * @return true si son iguales, false en caso contrario
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		DocPDF other = (DocPDF) obj;
		return Arrays.equals(contenidoPdf, other.contenidoPdf);
	}
}