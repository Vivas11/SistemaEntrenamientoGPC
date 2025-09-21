package co.edu.unbosque.sistemaentrenamientoGPC_back.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Problema {
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
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
		return Objects.hash(dificultad, id, juez, nombre, tema);
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
		return Objects.equals(dificultad, other.dificultad) && Objects.equals(id, other.id)
				&& Objects.equals(juez, other.juez) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(tema, other.tema);
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
	
	
	



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	@Override
	public String toString() {
		return "Problema [nombre=" + nombre + ", dificultad=" + dificultad + ", tema=" + tema + ", juez=" + juez + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
