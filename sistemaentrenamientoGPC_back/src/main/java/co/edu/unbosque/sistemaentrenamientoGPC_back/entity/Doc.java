package co.edu.unbosque.sistemaentrenamientoGPC_back.entity;

import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

/**
 * Clase base abstracta que representa un documento genérico en el sistema.
 * Incluye atributos comunes como nombre, descripción e imagen en formato binario.
 * Se utiliza como superclase para entidades especializadas que extienden su funcionalidad.
 */
@Entity
@Table(name = "Doc")
public abstract class Doc {

	/** Identificador único del documento, generado automáticamente. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** Nombre del documento. */
	private String nombre;

	/** Descripción del documento. */
	private String descripcion;

	/** Imagen asociada al documento, almacenada como BLOB. */
	@Lob
	private byte[] imagen; // Ahora es un arreglo de bytes para guardar la imagen como BLOB

	/**
	 * Constructor vacío requerido por JPA.
	 */
	public Doc() {
	}

	/**
	 * Constructor que inicializa los campos nombre, descripción e imagen.
	 * 
	 * @param nombre      Nombre del documento
	 * @param descripcion Descripción del documento
	 * @param imagen      Imagen en formato binario
	 */
	public Doc(String nombre, String descripcion, byte[] imagen) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.imagen = imagen;
	}

	/** @return el identificador del documento */
	public Long getId() {
		return id;
	}

	/** @param id el nuevo identificador del documento */
	public void setId(Long id) {
		this.id = id;
	}

	/** @return el nombre del documento */
	public String getNombre() {
		return nombre;
	}

	/** @param nombre el nuevo nombre del documento */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/** @return la descripción del documento */
	public String getDescripcion() {
		return descripcion;
	}

	/** @param descripcion la nueva descripción del documento */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/** @return la imagen del documento como arreglo de bytes */
	public byte[] getImagen() {
		return imagen;
	}

	/** @param imagen la nueva imagen del documento en formato binario */
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	/**
	 * Calcula el código hash del documento basado en sus atributos.
	 * 
	 * @return valor hash del objeto
	 */
	@Override
	public int hashCode() {
		return Objects.hash(descripcion, id, imagen, nombre);
	}

	/**
	 * Compara este documento con otro para verificar igualdad.
	 * 
	 * @param obj objeto a comparar
	 * @return true si son iguales, false en caso contrario
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doc other = (Doc) obj;
		return Objects.equals(descripcion, other.descripcion) &&
		       Objects.equals(id, other.id) &&
		       Objects.deepEquals(imagen, other.imagen) &&
		       Objects.equals(nombre, other.nombre);
	}

	/**
	 * Representación textual del documento.
	 * 
	 * @return cadena con los valores principales del documento
	 */
	@Override
	public String toString() {
		return "Libro [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
}