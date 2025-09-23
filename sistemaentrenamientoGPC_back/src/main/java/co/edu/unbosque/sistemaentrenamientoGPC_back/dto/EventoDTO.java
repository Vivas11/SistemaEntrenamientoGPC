package co.edu.unbosque.sistemaentrenamientoGPC_back.dto;

import java.util.Date;
import java.util.Objects;


/**
 * Clase DTO que representa un evento con nombre, fecha y descripción.
 * Se utiliza para transferir datos relacionados con eventos en el sistema.
 */
public class EventoDTO {

	/** Nombre del evento. */
	private String nombre;

	/** Fecha en la que se realiza el evento. */
	private Date fecha;

	/** Descripción del evento. */
	private String descripcion;

	/**
	 * Constructor vacío requerido para serialización/deserialización.
	 */
	public EventoDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor que inicializa todos los campos del evento.
	 * 
	 * @param nombre      Nombre del evento
	 * @param fecha       Fecha del evento
	 * @param descripcion Descripción del evento
	 */
	public EventoDTO(String nombre, Date fecha, String descripcion) {
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

	/**
	 * Obtiene el nombre del evento.
	 * 
	 * @return nombre del evento
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del evento.
	 * 
	 * @param nombre nuevo nombre del evento
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene la fecha del evento.
	 * 
	 * @return fecha del evento
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Establece la fecha del evento.
	 * 
	 * @param fecha nueva fecha del evento
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Obtiene la descripción del evento.
	 * 
	 * @return descripción del evento
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Establece la descripción del evento.
	 * 
	 * @param descripcion nueva descripción del evento
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Representación en texto del objeto EventoDTO.
	 * 
	 * @return cadena con los valores del evento
	 */
	@Override
	public String toString() {
		return "EventoDTO [nombre=" + nombre + ", fecha=" + fecha + ", descripcion=" + descripcion + "]";
	}
}
