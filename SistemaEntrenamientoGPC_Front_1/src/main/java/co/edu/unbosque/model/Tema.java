package co.edu.unbosque.model;

import java.util.Objects;


/**
 * DTO (Data Transfer Object) que representa un tema de programación o contenido académico.
 * Contiene información sobre el nombre del tema, su tipo y el contenido asociado.
 */
public class Tema {

	/** Identificador único del tema. */
	private Long id;

	/** Nombre del tema. */
	private String tema;

	/** Tipo de tema (por ejemplo: teoría, práctica, algoritmo, etc.). */
	private String tipo;

	/** Contenido textual o descripción del tema. */
	private String contenido;

	/**
	 * Constructor vacío requerido para serialización/deserialización.
	 */
	public Tema() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor con parámetros para inicializar un nuevo tema.
	 *
	 * @param tema      nombre del tema
	 * @param tipo      tipo del tema
	 * @param contenido contenido asociado al tema
	 */
	public Tema(String tema, String tipo, String contenido) {
		super();
		this.tema = tema;
		this.tipo = tipo;
		this.contenido = contenido;
	}

	/**
	 * Calcula el hash del objeto basado en sus atributos clave.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(contenido, tema, tipo);
	}

	/**
	 * Compara si dos objetos {@code TemaDTO} son equivalentes.
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
		       Objects.equals(tema, other.tema) &&
		       Objects.equals(tipo, other.tipo);
	}

	/** @return el nombre del tema */
	public String getTema() {
		return tema;
	}

	/** @param tema establece el nombre del tema */
	public void setTema(String tema) {
		this.tema = tema;
	}

	/** @return el tipo del tema */
	public String getTipo() {
		return tipo;
	}

	/** @param tipo establece el tipo del tema */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/** @return el contenido del tema */
	public String getContenido() {
		return contenido;
	}

	/** @param contenido establece el contenido del tema */
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	/**
	 * Representación textual del objeto {@code TemaDTO}.
	 */
	@Override
	public String toString() {
		return "TemaDTO [tema=" + tema + ", tipo=" + tipo + ", contenido=" + contenido + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}