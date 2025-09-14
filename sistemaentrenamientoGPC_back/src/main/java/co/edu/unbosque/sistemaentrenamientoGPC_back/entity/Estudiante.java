package co.edu.unbosque.sistemaentrenamientoGPC_back.entity;

import java.util.Objects;




import jakarta.persistence.Entity;


@Entity

public class Estudiante extends Usuario{
	
	
	
	private String nivelCompe;
	private int semestre;
	
	
	public Estudiante() {
		// TODO Auto-generated constructor stub
	}


	public Estudiante(String nivelCompe, int semestre) {
		super();
		this.nivelCompe = nivelCompe;
		this.semestre = semestre;
	}


	public Estudiante(String nombre, String correo, int edad, String nivelCompe, int semestre) {
		super(nombre, correo, edad);
		this.nivelCompe = nivelCompe;
		this.semestre = semestre;
	}


	public Estudiante(String nombre, String correo, int edad) {
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
		Estudiante other = (Estudiante) obj;
		return Objects.equals(nivelCompe, other.nivelCompe) && semestre == other.semestre;
	}


	@Override
	public String toString() {
		return "Estudiante [nivelCompe=" + nivelCompe + ", semestre=" + semestre + "]";
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
	
	
	
	
	

	
	
	
	
	
	
	

}
