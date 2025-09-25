package co.edu.unbosque.model;

import java.util.Objects;

/**
 * Clase base abstracta para documentos (por ejemplo, PDFs). Proporciona campos
 * comunes como identificador, nombre, descripción e imagen asociada.
 */
public abstract class Doc {

	/** Identificador único del documento. */
	private Long id;
	/** Nombre del documento. */
	private String nombre;
	/** Descripción breve del documento. */
	private String descripcion;
	/** Imagen asociada (puede almacenarse codificada). */
	private String imagen;

	/** Constructor vacío requerido por frameworks. */
	public Doc() {
	}

	/**
	 * Constructor completo.
	 * 
	 * @param nombre      Nombre del documento.
	 * @param descripcion Descripción del documento.
	 * @param imagen      Imagen asociada.
	 */
	public Doc(String nombre, String descripcion, String imagen) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.imagen = imagen;
	}

	/**
	 * @return Identificador único.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id Identificador único.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return Nombre del documento.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre Nombre del documento.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return Descripción del documento.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion Descripción del documento.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return Imagen asociada.
	 */
	public String getImagen() {
		return imagen;
	}

	/**
	 * @param imagen Imagen asociada.
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descripcion, id, imagen, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doc other = (Doc) obj;
		return Objects.equals(descripcion, other.descripcion) && Objects.equals(id, other.id)
				&& Objects.deepEquals(imagen, other.imagen) && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
}