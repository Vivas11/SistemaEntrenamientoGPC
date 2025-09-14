package co.edu.unbosque.sistemaentrenamientoGPC_back.dto;

import java.util.Objects;

import co.edu.unbosque.sistemaentrenamientoGPC_back.entity.Usuario;

/**
 * Clase EstudianteDTO que extiende de Usuario 
 * Es un Data Transfer Object para la entidad Estudiante
 */
public class EstudianteDTO extends Usuario {

	/**
	 * Atributo que se refiere al nivel en el que se encuentra el estudiante.
	 */
	private String nivelCompe;
	/**
	 * Atributo que indica el semestre en el que se encuentra el estudiante.
	 */
	private int semestre;

	/**
	 * Constructor vacio de la clase EstudianteDTO
	 */
	public EstudianteDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor con parametros (atributos) de la clase EstudianteDTO, excepto el id
	 * 
	 * @param nivelCompe
	 * @param semestre
	 */
	public EstudianteDTO(String nivelCompe, int semestre) {
		super();
		this.nivelCompe = nivelCompe;
		this.semestre = semestre;
	}

	/**
	 * Constructor con parametros (atributos) de la clase EstudianteDTO, incluyendo los
	 * atributos heredados de Usuario
	 * 
	 * @param nombre
	 * @param correo
	 * @param edad
	 * @param nivelCompe
	 * @param semestre
	 */
	public EstudianteDTO(String nombre, String correo, int edad, String contrasena, String nivelCompe, int semestre) {
		super(nombre, correo, edad, contrasena);
		this.nivelCompe = nivelCompe;
		this.semestre = semestre;
	}
	

	/**
	 * Constructor con parametros (atributos) heredados de UsuarioDTO
	 * @param nombre
	 * @param correo
	 * @param edad
	 */
	public EstudianteDTO(String nombre, String correo, int edad, String contrasena) {
		super(nombre, correo, edad, contrasena);
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * Metodo hashCode de la clase Estudiante DTO
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
	 * Metodo equals de la clase EstudianteDTO
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
		EstudianteDTO other = (EstudianteDTO) obj;
		return Objects.equals(nivelCompe, other.nivelCompe) && semestre == other.semestre;
	}

	/**
	 * Metodo toString de la clase EstudianteDTO
	 * @return String con la informacion del estudiante
	 */
	@Override
	public String toString() {
		return "Estudiante [nivelCompe=" + nivelCompe + ", semestre=" + semestre + "]";
	}

	/**
	 * Metodo getter del atributo nivelCompe
	 * @return nivelCompe
	 */
	public String getNivelCompe() {
		return nivelCompe;
	}

	/**
	 * Metodo setter del atributo nivelCompe
	 * @param nivelCompe
	 */
	public void setNivelCompe(String nivelCompe) {
		this.nivelCompe = nivelCompe;
	}

	/**
	 * Getter del atributo semestre
	 * @return semestre
	 */
	public int getSemestre() {
		return semestre;
	}

	/**
	 * Setter del atributo semestre
	 * @param semestre
	 */
	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

}