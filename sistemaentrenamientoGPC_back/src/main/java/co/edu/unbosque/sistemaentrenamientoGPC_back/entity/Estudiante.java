package co.edu.unbosque.sistemaentrenamientoGPC_back.entity;

import java.util.Objects;

import jakarta.persistence.Entity;

/**
 * Clase Estudiante que representa a un estudiante perteneciente al GPC(grupo de
 * programación competitiva) Hereda de la clase Usuario
 */
@Entity
public class Estudiante extends Usuario {

	/**
	 * Atributo que se refiere al nivel en el que se encuentra el estudiante.
	 */
	private String nivelCompe;
	/**
	 * Atributo que indica el semestre en el que se encuentra el estudiante.
	 */
	private int semestre;

	/**
	 * Constructor vacio de la clase Estudiante
	 */
	public Estudiante() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor con parametros (atributos) de la clase Estudiante, excepto el id
	 * 
	 * @param nivelCompe
	 * @param semestre
	 */
	public Estudiante(String nivelCompe, int semestre) {
		super();
		this.nivelCompe = nivelCompe;
		this.semestre = semestre;
	}

	/**
	 * Constructor con parametros (atributos) de la clase Estudiante, incluyendo los
	 * atributos heredados de Usuario
	 * 
	 * @param nombre
	 * @param correo
	 * @param edad
	 * @param contrasena
	 * @param nivelCompe
	 * @param semestre
	 */
	public Estudiante(String nombre, String correo, int edad, String contrasena, String nivelCompe, int semestre) {
		super(nombre, correo, edad, contrasena);
		this.nivelCompe = nivelCompe;
		this.semestre = semestre;
	}

	/**
	 * Constructor con parametros (atributos) heredados de Usuario
	 * 
	 * @param nombre
	 * @param correo
	 * @param edad
	 * @param contrasena
	 */
	public Estudiante(String nombre, String correo, int edad, String contrasena) {
		super(nombre, correo, edad, contrasena);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Metodo hashCode de la clase Estudiante
	 * 
	 * @return int con el hashCode del estudiante
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(nivelCompe, semestre);
		return result;
	}

	/**
	 * Metodo equals de la clase Estudiante
	 * 
	 * @param obj
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estudiante other = (Estudiante) obj;
		return Objects.equals(nivelCompe, other.nivelCompe) && semestre == other.semestre;
	}

	/**
	 * Metodo toString de la clase Estudiante
	 * 
	 * @return String con la informacion del estudiante
	 */
	@Override
	public String toString() {
		return "Estudiante [nivelCompe=" + nivelCompe + ", semestre=" + semestre + "]";
	}

	/**
	 * Metodo getter del atributo nivelCompe
	 * 
	 * @return nivelCompe
	 */
	public String getNivelCompe() {
		return nivelCompe;
	}

	/**
	 * Metodo setter del atributo nivelCompe
	 * 
	 * @param nivelCompe
	 */
	public void setNivelCompe(String nivelCompe) {
		this.nivelCompe = nivelCompe;
	}

	/**
	 * Getter del atributo semestre
	 * 
	 * @return semestre
	 */
	public int getSemestre() {
		return semestre;
	}

	/**
	 * Setter del atributo semestre
	 * 
	 * @param semestre
	 */
	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

}