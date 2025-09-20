package co.edu.unbosque.sistemaentrenamientoGPC_back.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Problema {
	
	@Column(unique = false, name = "nombre")
	private String nombre;
	
	@Column(unique = false, name = "dificultad")
	private String dificultad;

	@Column(unique = false, name = "tema")
	private String tema;
	@Column(unique = false, name = "jues")
	private String juez;
	
	
	
	public Problema() {
		// TODO Auto-generated constructor stub
	}



	public Problema(String nombre, String dificultad, String tema, String juez) {
		super();
		this.nombre = nombre;
		this.dificultad = dificultad;
		this.tema = tema;
		this.juez = juez;
	}



	@Override
	public int hashCode() {
		return Objects.hash(dificultad, juez, nombre, tema);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Problema other = (Problema) obj;
		return Objects.equals(dificultad, other.dificultad) && Objects.equals(juez, other.juez)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(tema, other.tema);
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getDificultad() {
		return dificultad;
	}



	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}



	public String getTema() {
		return tema;
	}



	public void setTema(String tema) {
		this.tema = tema;
	}



	public String getJuez() {
		return juez;
	}



	public void setJuez(String juez) {
		this.juez = juez;
	}



	@Override
	public String toString() {
		return "Problema [nombre=" + nombre + ", dificultad=" + dificultad + ", tema=" + tema + ", juez=" + juez + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
