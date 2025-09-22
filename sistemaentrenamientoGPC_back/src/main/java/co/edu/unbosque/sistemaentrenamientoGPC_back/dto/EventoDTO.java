package co.edu.unbosque.sistemaentrenamientoGPC_back.dto;

import java.time.LocalDate;
import java.util.Objects;


public class EventoDTO {
	
	private Long id;
	private String nombre;
	private LocalDate fecha;
	private String descripcion ;
	
	
	public EventoDTO() {
		// TODO Auto-generated constructor stub
	}


	public EventoDTO(String nombre, LocalDate fecha, String descripcion) {
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


	public LocalDate getFecha() {
		return fecha;
	}


	public void setFecha(LocalDate fecha) {
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


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	
	

}
