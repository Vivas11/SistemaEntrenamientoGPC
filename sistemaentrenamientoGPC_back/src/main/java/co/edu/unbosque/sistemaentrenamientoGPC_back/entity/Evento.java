package co.edu.unbosque.sistemaentrenamientoGPC_back.entity;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
/**
 * Entidad que representa un evento dentro del sistema.
 * Contiene atributos como identificador, nombre, fecha y descripción.
 * Se mapea a una tabla en la base de datos mediante JPA.
 */
@Entity
public class Evento {

	/** Identificador único del evento, generado automáticamente. */
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

	/** Nombre del evento. */
	@Column(unique = false, name = "nombre")
	private String nombre;

	/** Fecha en la que se realiza el evento. */
	@Column(unique = false, name = "fecha")
	private LocalDate fecha;

	/** Descripción del evento. */
	@Column(unique = false, name = "descripcion")
	private String descripcion;

	/**
	 * Constructor vacío requerido por JPA.
	 */
	public Evento() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor que inicializa los campos principales del evento.
	 * 
	 * @param nombre      Nombre del evento
	 * @param fecha       Fecha del evento
	 * @param descripcion Descripción del evento
	 */
	public Evento(String nombre, LocalDate fecha, String descripcion) {
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
		return Objects.hash(descripcion, fecha, id, nombre);
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
		Evento other = (Evento) obj;
		return Objects.equals(descripcion, other.descripcion) &&
		       Objects.equals(fecha, other.fecha) &&
		       Objects.equals(id, other.id) &&
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

	/** @return el identificador del evento */
	public Long getId() {
		return id;
	}

	/** @param id nuevo identificador del evento */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Representación textual del objeto Evento.
	 * 
	 * @return cadena con los valores principales del evento
	 */
	@Override
	public String toString() {
		return "Evento [nombre=" + nombre + ", fecha=" + fecha + ", descripcion=" + descripcion + "]";
	}
}
