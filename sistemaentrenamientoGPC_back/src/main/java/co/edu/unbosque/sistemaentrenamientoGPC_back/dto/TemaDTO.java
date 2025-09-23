package co.edu.unbosque.sistemaentrenamientoGPC_back.dto;

import java.util.Objects;


/**
 * Clase DTO que representa un tema dentro del sistema.
 * Contiene información como el nombre del tema, su tipo y el contenido asociado.
 */
public class TemaDTO {

	/** Nombre o título del tema. */
	private String tema;

	/** Tipo o categoría del tema (por ejemplo: teórico, práctico, mixto). */
	private String tipo;

	/** Contenido textual o multimedia del tema. */
	private String contenido;

	/**
	 * Constructor vacío requerido para serialización/deserialización.
	 */
	public TemaDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor que inicializa todos los campos del tema.
	 * 
	 * @param tema      Nombre del tema
	 * @param tipo      Tipo o categoría del tema
	 * @param contenido Contenido asociado al tema
	 */
	public TemaDTO(String tema, String tipo, String contenido) {
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
		return Objects.hash(contenido, tema, tipo);
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
		TemaDTO other = (TemaDTO) obj;
		return Objects.equals(contenido, other.contenido) &&
		       Objects.equals(tema, other.tema) &&
		       Objects.equals(tipo, other.tipo);
	}

	/**
	 * Obtiene el nombre del tema.
	 * 
	 * @return nombre del tema
	 */
	public String getTema() {
		return tema;
	}

	/**
	 * Establece el nombre del tema.
	 * 
	 * @param tema nuevo nombre del tema
	 */
	public void setTema(String tema) {
		this.tema = tema;
	}

	/**
	 * Obtiene el tipo o categoría del tema.
	 * 
	 * @return tipo del tema
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Establece el tipo o categoría del tema.
	 * 
	 * @param tipo nuevo tipo del tema
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Obtiene el contenido del tema.
	 * 
	 * @return contenido del tema
	 */
	public String getContenido() {
		return contenido;
	}

	/**
	 * Establece el contenido del tema.
	 * 
	 * @param contenido nuevo contenido del tema
	 */
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	/**
	 * Representación en texto del objeto TemaDTO.
	 * 
	 * @return cadena con los valores del tema
	 */
	@Override
	public String toString() {
		return "TemaDTO [tema=" + tema + ", tipo=" + tipo + ", contenido=" + contenido + "]";
	}
}