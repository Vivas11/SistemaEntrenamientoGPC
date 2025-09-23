package co.edu.unbosque.sistemaentrenamientoGPC_back.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.sistemaentrenamientoGPC_back.dto.EventoDTO;
import co.edu.unbosque.sistemaentrenamientoGPC_back.entity.Evento;
import co.edu.unbosque.sistemaentrenamientoGPC_back.repository.EventoRepository;
/**
 * Servicio encargado de gestionar operaciones CRUD para la entidad {@code Evento} usando {@code EventoDTO}.
 * Implementa la interfaz {@code CRUDOperation<EventoDTO>} y utiliza {@link ModelMapper} para la conversión entre entidades y DTOs.
 */
@Service
public class EventoService implements CRUDOperation<EventoDTO> {

	/** Repositorio JPA para acceder a datos de eventos. */
	@Autowired   
	private EventoRepository eventoRepo;

	/** Utilidad para mapear entre entidades y DTOs. */
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Constructor vacío requerido por Spring.
	 */
	public EventoService() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Crea un nuevo evento en la base de datos.
	 * 
	 * @param newData DTO con los datos del nuevo evento
	 * @return 0 si la operación fue exitosa
	 */
	@Override
	public int create(EventoDTO newData) {
		Evento entity = modelMapper.map(newData, Evento.class);
		eventoRepo.save(entity);
		return 0;
	}

	/**
	 * Obtiene todos los eventos registrados.
	 * 
	 * @return lista de {@code EventoDTO}
	 */
	@Override
	public List<EventoDTO> getAll() {
		List<Evento> entityList = eventoRepo.findAll();
	    List<EventoDTO> dtoList = new ArrayList<>();
	    entityList.forEach((entity) -> {
	        EventoDTO dto = modelMapper.map(entity, EventoDTO.class);
	        dtoList.add(dto);
	    });
	    return dtoList;
	}

	/**
	 * Elimina un evento por su ID.
	 * 
	 * @param id identificador del evento
	 * @return 0 si se eliminó correctamente, 1 si no existe
	 */
	@Override
	public int deleteById(Long id) {
		if (eventoRepo.existsById(id)) {
			eventoRepo.deleteById(id);
			return 0;
		}
		return 1;
	}

	/**
	 * Actualiza los datos de un evento por su ID.
	 * 
	 * @param id identificador del evento
	 * @param newData DTO con los nuevos datos
	 * @return 0 si se actualizó correctamente, 1 si no se encontró
	 */
	@Override
	public int updateById(Long id, EventoDTO newData) {
		Optional<Evento> opt = eventoRepo.findById(id);
		if (opt.isPresent()) {
			Evento entity = opt.get();
			entity.setNombre(newData.getNombre());
	        entity.setFecha(newData.getFecha());
	        entity.setDescripcion(newData.getDescripcion());
			eventoRepo.save(entity);
			return 0;
		}
		return 1;
	}

	/**
	 * Cuenta la cantidad total de eventos registrados.
	 * 
	 * @return número total de eventos
	 */
	@Override
	public Long count() {
		Long tamano = (long) getAll().size();
		return tamano;
	}

	/**
	 * Verifica si existe un evento por su ID.
	 * 
	 * @param id identificador del evento
	 * @return {@code false} (implementación pendiente)
	 */
	@Override
	public boolean exist(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
}