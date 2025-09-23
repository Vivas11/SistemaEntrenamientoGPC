package co.edu.unbosque.sistemaentrenamientoGPC_back.dto;

import java.util.Arrays;

import co.edu.unbosque.sistemaentrenamientoGPC_back.entity.Doc;

/**
 * Clase DTO que extiende de {@link Doc} para incluir contenido PDF adicional.
 * Se utiliza para representar documentos que contienen tanto metadatos como el archivo PDF en sí.
 */
public class DocPDFDTO extends Doc {

	/** Contenido binario del archivo PDF asociado al documento. */
	private byte[] contenidoPdf;

	/** Constructor vacío requerido para serialización/deserialización. */
	public DocPDFDTO() {
	}

	/**
	 * Constructor que inicializa el documento con su ID y contenido PDF.
	 * 
	 * @param id            Identificador del documento
	 * @param contenidoPdf  Contenido binario del archivo PDF
	 */
	public DocPDFDTO(Long id, byte[] contenidoPdf) {
		super();
		this.contenidoPdf = contenidoPdf;
	}

	/**
	 * Constructor que inicializa el documento con nombre, descripción, imagen y contenido PDF.
	 * 
	 * @param nombre        Nombre del documento
	 * @param descripcion   Descripción del documento
	 * @param imagen        Imagen asociada al documento
	 * @param contenidoPdf  Contenido binario del archivo PDF
	 */
	public DocPDFDTO(String nombre, String descripcion, byte[] imagen, byte[] contenidoPdf) {
		super(nombre, descripcion, imagen);
		this.contenidoPdf = contenidoPdf;
	}

	/**
	 * Constructor que inicializa el documento con nombre, descripción e imagen.
	 * 
	 * @param nombre      Nombre del documento
	 * @param descripcion Descripción del documento
	 * @param imagen      Imagen asociada al documento
	 */
	public DocPDFDTO(String nombre, String descripcion, byte[] imagen) {
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
		DocPDFDTO other = (DocPDFDTO) obj;
		return Arrays.equals(contenidoPdf, other.contenidoPdf);
	}
}
