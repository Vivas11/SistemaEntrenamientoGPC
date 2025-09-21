package co.edu.unbosque.sistemaentrenamientoGPC_back.dto;

import java.util.Date;
import java.util.Objects;


public class EventoDTO {
	
	
	private String nombre;
	private Date fecha;
	private String descripcion ;
	
	
	public EventoDTO() {
		// TODO Auto-generated constructor stub
	}


	public EventoDTO(String nombre, Date fecha, String descripcion) {
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
		EventoDTO other = (EventoDTO) obj;
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
		return "EventoDTO [nombre=" + nombre + ", fecha=" + fecha + ", descripcion=" + descripcion + "]";
	}
	
	
	

}
