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
public class EventBean implements Serializable {

	private Date fecha;
	private String nombre;
	private String descripcion;

	private ArrayList<Evento> eventos = new ArrayList<>();

	private ScheduleModel eventModel;
	private ScheduleEvent<?> event = new DefaultScheduleEvent<>();

	@PostConstruct
	public void init() {
		cargarEventos();
		inicializarEventos();
	}

	public void cargarEventos() {
		eventos = EventoService.doGetAll("http://localhost:8081/evento/getall");
		if (eventos == null) {
			eventos = new ArrayList<>();
		}
	}

	public void inicializarEventos() {
		eventModel = new DefaultScheduleModel();

		for (Evento evento : eventos) {
			LocalDate fecha = evento.getFecha();
			LocalDateTime fechaIni = fecha.atTime(9, 0);
			LocalDateTime fechaFin = fecha.atTime(10, 0);

			eventModel.addEvent(DefaultScheduleEvent.builder().title(evento.getNombre()).startDate(fechaIni)
					.endDate(fechaFin).description(evento.getDescripcion()).editable(false) // ❌ no se pueden mover
					.resizable(false) // ❌ no se pueden redimensionar
					.build());
		}
	}

	public void agregarEvento() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		String json = "{";
		json += "\"nombre\":\"" + nombre + "\",";
		json += "\"fecha\":\"" + localDate.format(formatter) + "\",";
		json += "\"descripcion\":\"" + descripcion + "\"}";

		System.out.println(json);

		String exito = EventoService.doPostJson(json);
		String[] data = exito.split("\n");
		System.out.println(data[0] + "" + data[1]);
		showStickyLogin(data[0], data[1]);
		cargarEventos();
		inicializarEventos();

	}

	public void eliminarEvento(DefaultScheduleEvent<?> evento) {
		Evento encontrado = eventos.stream().filter(e -> e.getNombre().equals(evento.getTitle())).findFirst()
				.orElse(null);

		System.out.println("Intentando eliminar: " + encontrado);
		System.out.println("ID recibido: " + encontrado.getId());
		String respuesta = EventoService.doDelete("http://localhost:8081/evento/eliminar?id=" + encontrado.getId());
		String[] data = respuesta.split("\n");
		if (data[0].equals("204")) {
			showStickyLogin(data[0], "problema eliminado");
			eventos.remove(encontrado);
			cargarEventos();
			inicializarEventos();
			return;
		} else {
			showStickyLogin(data[0], "no se ha podido eliminar el problema.");
			cargarEventos();
			inicializarEventos();
			return;
		}
	}

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
			System.out.println("Error en crear cuenta");
			System.out.println("Status code: " + code);
			System.out.println("reason: " + content);
			FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error Critico", "Error," + "comuniquese con el administrador"));
		}
	}

	// Guardar evento nuevo (ejemplo básico)
	public void crearEvento() {
		event = new DefaultScheduleEvent<>();
	}

	public void onEventSelect(SelectEvent<ScheduleEvent<?>> selectEvent) {
		event = selectEvent.getObject();
	}

	// Getters & Setters
	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public ScheduleEvent<?> getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent<?> event) {
		this.event = event;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}
	
	public boolean getAdminIniciada() {
		if (UsuarioActual.getUsuarioActual() == null)
			return false;

		return UsuarioActual.getUsuarioActual() instanceof Admin
				|| UsuarioActual.getUsuarioActual() instanceof Profesor;
	}
	
}
