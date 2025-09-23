package co.edu.unbosque.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import co.edu.unbosque.model.Evento;
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
    }

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

    public void agregarEvento() {
		try {
			Evento nuevoEvento = new Evento();
			nuevoEvento.setFecha(LocalDate.parse(new java.text.SimpleDateFormat("yyyy-MM-dd").format(fecha)));
			nuevoEvento.setNombre(nombre);
			nuevoEvento.setDescripcion(descripcion);

			boolean exito = EventoService.doPostJson(nuevoEvento);

			if (exito) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Evento agregado exitosamente");
				FacesContext.getCurrentInstance().addMessage(null, message);
				cargarEventos();
				inicializarEventos();
			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo agregar el evento");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al agregar el evento");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
    
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
}
