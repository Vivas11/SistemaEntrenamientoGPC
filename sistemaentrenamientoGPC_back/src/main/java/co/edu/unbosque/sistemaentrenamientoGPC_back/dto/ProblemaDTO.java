package co.edu.unbosque.sistemaentrenamientoGPC_back.dto;

import java.util.Objects;

/**
 * DTO que representa un problema de programación dentro del sistema.
 * Contiene atributos como identificador, nombre, dificultad, tema y juez.
 */
public class ProblemaDTO {

	/** Identificador único del problema. */
	private Long id;

	/** Nombre del problema. */
	private String nombre;

	/** Nivel de dificultad del problema. */
	private String dificultad;

	/** Tema relacionado con el problema. */
	private String tema;

	/** Juez o plataforma donde se encuentra el problema. */
	private String juez;

	/**
	 * Constructor vacío requerido para serialización/deserialización.
	 */
	public ProblemaDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor que inicializa los campos principales del problema.
	 * 
	 * @param nombre      Nombre del problema
	 * @param dificultad  Nivel de dificultad
	 * @param tema        Tema relacionado
	 * @param juez        Plataforma o juez del problema
	 */
	public ProblemaDTO(String nombre, String dificultad, String tema, String juez) {
		super();
		this.nombre = nombre;
		this.dificultad = dificultad;
		this.tema = tema;
		this.juez = juez;
	}

	/**
	 * Calcula el código hash del objeto basado en sus atributos.
	 * 
	 * @return valor hash del objeto
	 */
	@Override
	public int hashCode() {
		return Objects.hash(dificultad, juez, nombre, tema);
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
		ProblemaDTO other = (ProblemaDTO) obj;
		return Objects.equals(dificultad, other.dificultad) &&
		       Objects.equals(juez, other.juez) &&
		       Objects.equals(nombre, other.nombre) &&
		       Objects.equals(tema, other.tema);
	}

	/** @return el nombre del problema */
	public String getNombre() {
		return nombre;
	}

	/** @param nombre nuevo nombre del problema */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/** @return la dificultad del problema */
	public String getDificultad() {
		return dificultad;
	}

	/** @param dificultad nueva dificultad del problema */
	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}

	/** @return el tema del problema */
	public String getTema() {
		return tema;
	}

	/** @param tema nuevo tema del problema */
	public void setTema(String tema) {
		this.tema = tema;
	}

	/** @return el juez o plataforma del problema */
	public String getJuez() {
		return juez;
	}

	/** @param juez nuevo juez o plataforma del problema */
	public void setJuez(String juez) {
		this.juez = juez;
	}

	/**
	 * Representación textual del objeto ProblemaDTO.
	 * 
	 * @return cadena con los valores principales del problema
	 */
	@Override
	public String toString() {
		return "ProblemaDTO [nombre=" + nombre + ", dificultad=" + dificultad + ", tema=" + tema + ", juez=" + juez + "]";
	}

	/** @return el identificador del problema */
	public Long getId() {
		return id;
	}

	/** @param id nuevo identificador del problema */
	public void setId(Long id) {
		this.id = id;
	}
}
