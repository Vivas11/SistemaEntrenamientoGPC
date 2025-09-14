package co.edu.unbosque.sistemaentrenamientoGPC_back.dto;

import java.util.Objects;

import co.edu.unbosque.sistemaentrenamientoGPC_back.entity.Usuario;

public class EstudianteDTO extends Usuario{
	
	private String nivelCompe;
	private int semestre;
	
	
	public EstudianteDTO() {
		// TODO Auto-generated constructor stub
	}


	public EstudianteDTO(String nivelCompe, int semestre) {
		super();
		this.nivelCompe = nivelCompe;
		this.semestre = semestre;
	}


	public EstudianteDTO(String nombre, String correo, int edad, String nivelCompe, int semestre) {
		super(nombre, correo, edad);
		this.nivelCompe = nivelCompe;
		this.semestre = semestre;
	}


	public EstudianteDTO(String nombre, String correo, int edad) {
		super(nombre, correo, edad);
		// TODO Auto-generated constructor stub
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(nivelCompe, semestre);
		return result;
	}


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


	public String getNivelCompe() {
		return nivelCompe;
	}


	public void setNivelCompe(String nivelCompe) {
		this.nivelCompe = nivelCompe;
	}


	public int getSemestre() {
		return semestre;
	}


	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}


	@Override
	public String toString() {
		return "EstudianteDTO [nivelCompe=" + nivelCompe + ", semestre=" + semestre + "]";
	}
	
	
	
	
	

}
