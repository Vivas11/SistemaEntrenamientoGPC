package co.edu.unbosque.sistemaentrenamientoGPC_back.entity;

import java.util.Objects;

import jakarta.persistence.Entity;

@Entity

public class Profesor  extends Usuario{
	
	/**
	 * Atributo cargo de la clase Profesor
	 */
	private String cargo;
	/**
	 * Atributo que indica si el profesor es Entrenador o no
	 */
	private boolean esEntrenador;
	
	
	/**
	 * Constructor vacio de la clase Profesor
	 */
	public Profesor() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * Constructor con parametros (atributos) de la clase Profesor, excepto el id
	 * @param cargo
	 * @param esEntrenador
	 */
	public Profesor(String cargo, boolean esEntrenador) {
		super();
		this.cargo = cargo;
		this.esEntrenador = esEntrenador;
	}


	/**
	 * Constructor con parametros (atributos) de la clase Profesor, incluyendo los atributos heredados de Usuario
	 * @param nombre
	 * @param correo
	 * @param edad
	 * @param cargo
	 * @param esEntrenador
	 */
	public Profesor(String nombre, String correo, int edad, String cargo, boolean esEntrenador) {
		super(nombre, correo, edad);
		this.cargo = cargo;
		this.esEntrenador = esEntrenador;
	}


	/**
	 * Constructor con parametros (atributos) heredados de Usuario
	 * @param nombre
	 * @param correo
	 * @param edad
	 */
	public Profesor(String nombre, String correo, int edad) {
		super(nombre, correo, edad);
		// TODO Auto-generated constructor stub
	}


	/**
	 * Metodo hashCode de la clase Profesor
	 * @return int con el codigo hash del usuario
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cargo, esEntrenador);
		return result;
	}


	/**
	 * Metodo equals de la clase Profesor
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesor other = (Profesor) obj;
		return Objects.equals(cargo, other.cargo) && esEntrenador == other.esEntrenador;
	}


	/**
	 * Getter del atributo cargo
	 * @return cargo
	 */
	public String getCargo() {
		return cargo;
	}


	/**
	 * Setter del atributo cargo
	 * @param cargo
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}


	/**
	 * Getter del atributo esEntrenador
	 * @return esEntrenador
	 */
	public boolean isEsEntrenador() {
		return esEntrenador;
	}


	/**
	 * Setter del atributo esEntrenador
	 * @param esEntrenador
	 */
	public void setEsEntrenador(boolean esEntrenador) {
		this.esEntrenador = esEntrenador;
	}


	/**
	 * Metodo toString de la clase Profesor
	 * @return String con la informacion del profesor
	 */
	@Override
	public String toString() {
		return "Profesor [cargo=" + cargo + ", esEntrenador=" + esEntrenador + "]";
	}
	
	
	

}
