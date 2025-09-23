package co.edu.unbosque.sistemaentrenamientoGPC_back.dto;

import java.time.LocalDate;
import java.util.Objects;


/**
 * DTO que representa un evento dentro del sistema.
 * Contiene información como nombre, fecha, descripción e identificador.
 */
public class EventoDTO {

	/** Identificador único del evento. */
	private Long id;

	/** Nombre del evento. */
	private String nombre;

	/** Fecha en la que se realiza el evento. */
	private LocalDate fecha;

	/** Descripción del evento. */
	private String descripcion;

	/**
	 * Constructor vacío requerido para serialización/deserialización.
	 */
	public EventoDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor que inicializa los campos principales del evento.
	 * 
	 * @param nombre      Nombre del evento
	 * @param fecha       Fecha del evento
	 * @param descripcion Descripción del evento
	 */
	public EventoDTO(String nombre, LocalDate fecha, String descripcion) {
		super();
		this.nombre = nombre;
		this.fecha = fecha;
		this.descripcion = descripcion;
	}

	/**
	 * Calcula el código hash del objeto basado en sus atributos.
	 * 
	 * @return valor hash del objeto
	 */
	@Override
	public int hashCode() {
		return Objects.hash(descripcion, fecha, nombre);
	}

	/**
	 * Compara este objeto con otro para verificar igualdad.
	 * 
	 * @param obj objeto a comparar
	 * @return true si son iguales, false en caso contrario
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventoDTO other = (EventoDTO) obj;
		return Objects.equals(descripcion, other.descripcion) &&
		       Objects.equals(fecha, other.fecha) &&
		       Objects.equals(nombre, other.nombre);
	}

	/** @return el nombre del evento */
	public String getNombre() {
		return nombre;
	}

	/** @param nombre nuevo nombre del evento */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/** @return la fecha del evento */
	public LocalDate getFecha() {
		return fecha;
	}

	/** @param fecha nueva fecha del evento */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	/** @return la descripción del evento */
	public String getDescripcion() {
		return descripcion;
	}

	/** @param descripcion nueva descripción del evento */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Representación textual del objeto EventoDTO.
	 * 
	 * @return cadena con los valores principales del evento
	 */
	@Override
	public String toString() {
		return "EventoDTO [nombre=" + nombre + ", fecha=" + fecha + ", descripcion=" + descripcion + "]";
	}

	/** @return el identificador del evento */
	public Long getId() {
		return id;
	}

	/** @param id nuevo identificador del evento */
	public void setId(Long id) {
		this.id = id;
	}
}
