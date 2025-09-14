package co.edu.unbosque.sistemaentrenamientoGPC_back.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name= "Usuario")
public abstract class Usuario {
	
	
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id; 
	@Column(unique = false, name = "nombre")
	private String nombre;
	@Column(unique = true, name = "correo")
	private String correo;
	private int edad;
	
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}


	public Usuario(String nombre, String correo, int edad) {
		super();
		this.nombre = nombre;
		this.correo = correo;
		this.edad = edad;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}


	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", correo=" + correo + ", edad=" + edad + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(correo, edad, id, nombre);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(correo, other.correo) && edad == other.edad && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre);
	}
	
	
	
	
	
	
	
	

}
