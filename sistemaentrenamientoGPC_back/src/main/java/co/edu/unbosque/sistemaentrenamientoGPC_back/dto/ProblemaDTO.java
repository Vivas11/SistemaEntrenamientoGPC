package co.edu.unbosque.sistemaentrenamientoGPC_back.dto;

import java.util.Objects;

/**
 * Clase DTO que representa un problema de programación o evaluación.
 * Contiene información básica como nombre, dificultad, tema y juez asociado.
 */
public class ProblemaDTO {

	/** Nombre del problema. */
	private String nombre;

	/** Nivel de dificultad del problema (por ejemplo: fácil, medio, difícil). */
	private String dificultad;

	/** Tema relacionado con el problema (por ejemplo: recursión, grafos, etc.). */
	private String tema;

	/** Juez o plataforma donde se encuentra el problema (por ejemplo: Codeforces, LeetCode). */
	private String juez;

	/**
	 * Constructor vacío requerido para serialización/deserialización.
	 */
	public ProblemaDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor que inicializa todos los campos del problema.
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

	/**
	 * Obtiene el nombre del problema.
	 * 
	 * @return nombre del problema
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del problema.
	 * 
	 * @param nombre nuevo nombre del problema
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene el nivel de dificultad del problema.
	 * 
	 * @return dificultad del problema
	 */
	public String getDificultad() {
		return dificultad;
	}

	/**
	 * Establece el nivel de dificultad del problema.
	 * 
	 * @param dificultad nueva dificultad
	 */
	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}

	/**
	 * Obtiene el tema del problema.
	 * 
	 * @return tema del problema
	 */
	public String getTema() {
		return tema;
	}

	/**
	 * Establece el tema del problema.
	 * 
	 * @param tema nuevo tema
	 */
	public void setTema(String tema) {
		this.tema = tema;
	}

	/**
	 * Obtiene el juez o plataforma del problema.
	 * 
	 * @return juez del problema
	 */
	public String getJuez() {
		return juez;
	}

	/**
	 * Establece el juez o plataforma del problema.
	 * 
	 * @param juez nuevo juez
	 */
	public void setJuez(String juez) {
		this.juez = juez;
	}

	/**
	 * Representación en texto del objeto ProblemaDTO.
	 * 
	 * @return cadena con los valores del problema
	 */
	@Override
	public String toString() {
		return "ProblemaDTO [nombre=" + nombre + ", dificultad=" + dificultad + ", tema=" + tema + ", juez=" + juez + "]";
	}
}
