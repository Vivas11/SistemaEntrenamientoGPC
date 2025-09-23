package co.edu.unbosque.sistemaentrenamientoGPC_back.entity;


import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
/**
 * Entidad que representa un tema dentro del sistema.
 * Contiene atributos como identificador, nombre del tema, tipo y contenido.
 * Se mapea a una tabla en la base de datos mediante JPA.
 */
@Entity
public class Tema {

	/** Identificador único del tema, generado automáticamente. */
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

	/** Nombre o título del tema. */
	@Column(unique = false, name = "tema")
	private String tema;

	/** Tipo o categoría del tema. */
	@Column(unique = false, name = "tipo")

	private String tipo;

	/** Contenido asociado al tema. */
	@Column(unique = false, name = "contenido", length = 10000)
	private String contenido;

	/**
	 * Constructor vacío requerido por JPA.
	 */
	public Tema() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor que inicializa los campos principales del tema.
	 * 
	 * @param tema      Nombre del tema
	 * @param tipo      Tipo o categoría
	 * @param contenido Contenido del tema
	 */
	public Tema(String tema, String tipo, String contenido) {
		super();
		this.tema = tema;
		this.tipo = tipo;
		this.contenido = contenido;
	}

	/**
	 * Calcula el código hash del objeto basado en sus atributos.
	 * 
	 * @return valor hash del objeto
	 */
	@Override
	public int hashCode() {
		return Objects.hash(contenido, id, tema, tipo);
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tema other = (Tema) obj;
		return Objects.equals(contenido, other.contenido) &&
		       Objects.equals(id, other.id) &&
		       Objects.equals(tema, other.tema) &&
		       Objects.equals(tipo, other.tipo);
	}

	/** @return el nombre del tema */
	public String getTema() {
		return tema;
	}

	/** @param tema nuevo nombre del tema */
	public void setTema(String tema) {
		this.tema = tema;
	}

	/** @return el tipo del tema */
	public String getTipo() {
		return tipo;
	}

	/** @param tipo nuevo tipo del tema */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/** @return el contenido del tema */
	public String getContenido() {
		return contenido;
	}

	/** @param contenido nuevo contenido del tema */
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	/** @return el identificador del tema */
	public Long getId() {
		return id;
	}

	/** @param id nuevo identificador del tema */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Representación textual del objeto Tema.
	 * 
	 * @return cadena con los valores principales del tema
	 */
	@Override
	public String toString() {
		return "Tema [tema=" + tema + ", tipo=" + tipo + ", contenido=" + contenido + "]";
	}
}
