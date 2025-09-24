package co.edu.unbosque.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Representa un evento programado, con nombre, fecha y descripción.
 */
public class Evento {

	/** Identificador del evento. */
	private Long id;
	/** Nombre del evento. */
	private String nombre;
	/** Fecha del evento. */
	private LocalDate fecha;
	/** Descripción del evento. */
	private String descripcion;

	/** Constructor vacío requerido por frameworks. */
	public Evento() {
		// TODO Auto-generated constructor stub
	}


	public Evento(String nombre, LocalDate fecha, String descripcion) {
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

	/**
	 * @return Nombre del evento.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre Nombre del evento.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return Fecha del evento.
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * @param fecha Fecha del evento.
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return Descripción del evento.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion Descripción del evento.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return Identificador del evento.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id Identificador del evento.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "EventoDTO [nombre=" + nombre + ", fecha=" + fecha + ", descripcion=" + descripcion + "]";
	}
}
