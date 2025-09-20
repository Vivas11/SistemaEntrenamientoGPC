package co.edu.unbosque.sistemaentrenamientoGPC_back.entity;

import java.sql.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
@Entity
public class Evento {
	
	
	@Column(unique = false, name = "nombre")
	private String nombre;
	
	@Column(unique = false, name = "fecha")
	private Date fecha; 

	@Column(unique = false, name = "descripcion")
	private String descripcion ;
	
	 public Evento() {
		// TODO Auto-generated constructor stub
	}

	 public Evento(String nombre, Date fecha, String descripcion) {
		super();
		this.nombre = nombre;
		this.fecha = fecha;
		this.descripcion = descripcion;
	 }

	 @Override
	 public int hashCode() {
		return Objects.hash(descripcion, fecha, nombre);
	 }

	 @Override
	 public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		return Objects.equals(descripcion, other.descripcion) && Objects.equals(fecha, other.fecha)
				&& Objects.equals(nombre, other.nombre);
	 }

	 public String getNombre() {
		 return nombre;
	 }

	 public void setNombre(String nombre) {
		 this.nombre = nombre;
	 }

	 public Date getFecha() {
		 return fecha;
	 }

	 public void setFecha(Date fecha) {
		 this.fecha = fecha;
	 }

	 public String getDescripcion() {
		 return descripcion;
	 }

	 public void setDescripcion(String descripcion) {
		 this.descripcion = descripcion;
	 }

	 @Override
	 public String toString() {
		return "Evento [nombre=" + nombre + ", fecha=" + fecha + ", descripcion=" + descripcion + "]";
	 }
	 
	 
	
	
	

}
