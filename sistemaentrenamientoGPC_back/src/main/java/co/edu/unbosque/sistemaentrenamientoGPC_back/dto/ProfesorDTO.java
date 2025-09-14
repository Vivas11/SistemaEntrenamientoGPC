package co.edu.unbosque.sistemaentrenamientoGPC_back.dto;

import java.util.Objects;

import co.edu.unbosque.sistemaentrenamientoGPC_back.entity.Usuario;

/**
 * Clase ProfesorDTO que extiende de Usuario 
 * Es un Data Transfer Object para la entidad Profesor
 */
public class ProfesorDTO  extends Usuario{
	
	/**
	 * Atributo cargo de la clase ProfesorDTO
	 */
	private String cargo;
	/**
	 * Atributo que indica si el profesor es Entrenador o no
	 */
	private boolean esEntrenador;
	
	
	/**
	 * Constructor vacio de la clase ProfesorDTO
	 */
	public ProfesorDTO() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * Constructor con parametros (atributos) de la clase ProfesorDTO, excepto el id
	 * @param cargo
	 * @param esEntrenador
	 */
	public ProfesorDTO(String cargo, boolean esEntrenador) {
		super();
		this.cargo = cargo;
		this.esEntrenador = esEntrenador;
	}


	/**
	 * Constructor con parametros (atributos) de la clase ProfesorDTO, incluyendo los atributos heredados de Usuario
	 * @param nombre
	 * @param correo
	 * @param edad
	 * @param contrasena
	 * @param cargo
	 * @param esEntrenador
	 */
	
	public ProfesorDTO(String nombre, String correo, int edad, String contrasena, String cargo, boolean esEntrenador) {
		super(nombre, correo, edad, contrasena);
		this.cargo = cargo;
		this.esEntrenador = esEntrenador;
	}
	


	/**
	 * Constructor con parametros (atributos) heredados de Usuario
	 * @param nombre
	 * @param correo
	 * @param edad
	 * @param contrasena
	 */
	
	public ProfesorDTO(String nombre, String correo, int edad, String contrasena) {
		super(nombre, correo, edad, contrasena);
		// TODO Auto-generated constructor stub
	}


	/**
	 * Metodo hashCode de la clase ProfesorDTO
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
	 * Metodo equals de la clase ProfesorDTO
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfesorDTO other = (ProfesorDTO) obj;
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