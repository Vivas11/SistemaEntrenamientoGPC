package co.edu.unbosque.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import co.edu.unbosque.model.Admin;
import co.edu.unbosque.model.Evento;
import co.edu.unbosque.model.Profesor;
import co.edu.unbosque.model.UsuarioActual;
import co.edu.unbosque.service.EventoService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named("eventbean")
@ViewScoped
/**
 * Backing bean encargado de gestionar los eventos que se muestran en el
 * componente Schedule de PrimeFaces.
 * <p>
 * Funcionalidades principales:
 * <ul>
 *   <li>Consumir el servicio REST para listar, crear y eliminar eventos.</li>
 *   <li>Construir el {@link ScheduleModel} que alimenta el calendario.</li>
 *   <li>Controlar la interacción de selección en el calendario.</li>
 *   <li>Mostrar mensajes (FacesMessage) al usuario sobre las acciones realizadas.</li>
 * </ul>
 * El bean es {@code @ViewScoped} para mantener el estado del calendario mientras
 * el usuario permanezca en la misma vista.
 */
public class EventBean implements Serializable {

	/** Serial version UID para la serialización del bean. */
	private static final long serialVersionUID = 1L;
	/** Fecha seleccionada para crear un nuevo evento. */
	private Date fecha;
	/** Nombre (título) del evento a crear. */
	private String nombre;
	/** Descripción del evento a crear. */
	private String descripcion;

	/** Lista interna de eventos recuperados desde el servicio REST. */
	private ArrayList<Evento> eventos = new ArrayList<>();

	/** Modelo que contiene los eventos para el componente Schedule. */
	private ScheduleModel eventModel;
	/** Evento actualmente seleccionado en el calendario. */
	private ScheduleEvent<?> event = new DefaultScheduleEvent<>();

	/**
	 * Constructor por defecto. Carga los eventos desde el servicio e inicializa el
	 * modelo del calendario.
	 */
	public EventBean() {
		cargarEventos();
		inicializarEventos();
	}

	/**
	 * Consume el servicio REST para recuperar todos los eventos. En caso de que
	 * el servicio retorne {@code null}, se inicializa una lista vacía para evitar
	 * {@link NullPointerException} posteriores.
	 */
	public void cargarEventos() {
		eventos = EventoService.doGetAll("http://localhost:8081/evento/getall");
		if (eventos == null) {
			eventos = new ArrayList<>();
		}
	}

	/**
	 * Reconstruye el {@link ScheduleModel} con los eventos disponibles. Cada
	 * evento se fija en un rango horario por defecto (09:00 - 10:00) ya que el
	 * modelo de dominio sólo provee la fecha. Se marcan como no editables ni
	 * redimensionables.
	 */
	public void inicializarEventos() {
		eventModel = new DefaultScheduleModel();

		for (Evento evento : eventos) {
			LocalDate fecha = evento.getFecha();
			LocalDateTime fechaIni = fecha.atTime(9, 0);
			LocalDateTime fechaFin = fecha.atTime(10, 0);

			eventModel.addEvent(DefaultScheduleEvent.builder()
					.title(evento.getNombre())
					.startDate(fechaIni)
					.endDate(fechaFin)
					.description(evento.getDescripcion())
					.editable(false)
					.resizable(false)
					.build());
		}
	}

	/**
	 * Crea un nuevo evento consumiendo el servicio REST. Construye manualmente un
	 * JSON simple con los datos capturados en el formulario y, tras la respuesta
	 * del servicio, recarga el modelo del calendario.
	 */
	public void agregarEvento() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		String json = "{";
		json += "\"nombre\":\"" + nombre + "\",";
		json += "\"fecha\":\"" + localDate.format(formatter) + "\",";
		json += "\"descripcion\":\"" + descripcion + "\"}";

		String exito = EventoService.doPostJson(json);
		String[] data = exito.split("\n");
		showStickyLogin(data[0], data[1]);
		cargarEventos();
		inicializarEventos();
	}

	/**
	 * Elimina un evento seleccionado del calendario. Se busca el objeto
	 * {@link Evento} asociado (por título) y se invoca el servicio REST para
	 * eliminarlo. Al finalizar se reconstruye el modelo y se muestra mensaje al
	 * usuario.
	 * 
	 * @param evento Evento de PrimeFaces seleccionado para eliminar.
	 */
	public void eliminarEvento(DefaultScheduleEvent<?> evento) {
		Evento encontrado = eventos.stream()
				.filter(e -> e.getNombre().equals(evento.getTitle()))
				.findFirst()
				.orElse(null);

		if (encontrado == null) {
			showStickyLogin("404", "Evento no encontrado");
			return;
		}

		String respuesta = EventoService.doDelete("http://localhost:8081/evento/eliminar?id=" + encontrado.getId());
		String[] data = respuesta.split("\n");
		if (data[0].equals("204")) {
			showStickyLogin(data[0], "evento eliminado");
			eventos.remove(encontrado);
			cargarEventos();
			inicializarEventos();
			return;
		} else {
			showStickyLogin(data[0], "no se ha podido eliminar el evento.");
			cargarEventos();
			inicializarEventos();
			return;
		}
	}

	/**
	 * Muestra mensajes al usuario dependiendo del código de estado devuelto por el
	 * servicio REST tras una operación CRUD de eventos.
	 * 
	 * @param code    Código de estado (ej. 201, 204, 406).
	 * @param content Mensaje adicional devuelto por el servicio.
	 */
	public void showStickyLogin(String code, String content) {
		if (code.equals("201")) {
			FacesContext.getCurrentInstance().addMessage("sticky-key",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Hecho", ", se ha creado el evento"));
		} else if (code.equals("406")) {
			FacesContext.getCurrentInstance().addMessage("sticky-key",
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", content));
		} else if (code.equals("204")) {
			FacesContext.getCurrentInstance().addMessage("sticky-key",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Hecho", ", se ha eliminado el evento"));
		} else {
			FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error Critico", "Error,comuniquese con el administrador"));
		}
	}

	/**
	 * Inicializa un nuevo objeto {@link ScheduleEvent} vacío. Útil para limpiar
	 * un formulario antes de crear un evento.
	 */
	public void crearEvento() {
		event = new DefaultScheduleEvent<>();
	}

	/**
	 * Maneja la selección de un evento existente en el calendario para, por
	 * ejemplo, mostrar sus detalles.
	 * 
	 * @param selectEvent Evento de selección de PrimeFaces.
	 */
	public void onEventSelect(SelectEvent<ScheduleEvent<?>> selectEvent) {
		event = selectEvent.getObject();
	}

	// Getters & Setters
	/**
	 * @return Modelo de eventos para el calendario.
	 */
	public ScheduleModel getEventModel() {
		return eventModel;
	}

	/**
	 * @return Evento actualmente seleccionado.
	 */
	public ScheduleEvent<?> getEvent() {
		return event;
	}

	/**
	 * @param event Evento seleccionado a establecer en el bean.
	 */
	public void setEvent(ScheduleEvent<?> event) {
		this.event = event;
	}

	/**
	 * @return Fecha seleccionada para un nuevo evento.
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha Fecha a asociar al nuevo evento.
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return Nombre (título) del nuevo evento.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre Nombre (título) del nuevo evento.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return Descripción del nuevo evento.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion Descripción del nuevo evento.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @param eventModel Modelo de eventos a establecer.
	 */
	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}
	
	/**
	 * Indica si el usuario autenticado posee rol de administrador o profesor.
	 * 
	 * @return {@code true} si hay un usuario autenticado y es Admin o Profesor; de
	 *         lo contrario {@code false}.
	 */
	public boolean getAdminIniciada() {
		if (UsuarioActual.getUsuarioActual() == null)
			return false;
		return UsuarioActual.getUsuarioActual() instanceof Admin
				|| UsuarioActual.getUsuarioActual() instanceof Profesor;
	}
	
}
