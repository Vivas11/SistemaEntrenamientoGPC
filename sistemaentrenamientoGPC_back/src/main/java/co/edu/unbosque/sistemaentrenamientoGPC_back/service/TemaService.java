package co.edu.unbosque.sistemaentrenamientoGPC_back.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.sistemaentrenamientoGPC_back.dto.TemaDTO;
import co.edu.unbosque.sistemaentrenamientoGPC_back.entity.Tema;
import co.edu.unbosque.sistemaentrenamientoGPC_back.repository.TemaRepository;
/**
 * Servicio encargado de gestionar operaciones CRUD para la entidad {@code Tema} usando {@code TemaDTO}.
 * Implementa la interfaz {@code CRUDOperation<TemaDTO>} y utiliza {@link ModelMapper} para la conversión entre entidades y DTOs.
 */
@Service
public class TemaService implements CRUDOperation<TemaDTO> {

	/** Repositorio JPA para acceder a datos de temas. */
	@Autowired 
	private TemaRepository temaRepo;

	/** Utilidad para mapear entre entidades y DTOs. */
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Constructor vacío requerido por Spring.
	 */
	public TemaService() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Crea un nuevo tema en la base de datos.
	 * 
	 * @param newData DTO con los datos del nuevo tema
	 * @return 0 si la operación fue exitosa
	 */
	@Override
	public int create(TemaDTO newData) {
		Tema entity = modelMapper.map(newData, Tema.class);
		temaRepo.save(entity);
		return 0;
	}

	/**
	 * Obtiene todos los temas registrados.
	 * 
	 * @return lista de {@code TemaDTO}
	 */
	@Override
	public List<TemaDTO> getAll() {
		List<Tema> entityList = temaRepo.findAll();
	    List<TemaDTO> dtoList = new ArrayList<>();
	    entityList.forEach((entity) -> {
	        TemaDTO dto = modelMapper.map(entity, TemaDTO.class);
	        dtoList.add(dto);
	    });
	    return dtoList;
	}

	/**
	 * Elimina un tema por su ID.
	 * 
	 * @param id identificador del tema
	 * @return 0 si se eliminó correctamente, 1 si no existe
	 */
	@Override
	public int deleteById(Long id) {
		if (temaRepo.existsById(id)) {
			temaRepo.deleteById(id);
			return 0;
		}
		return 1;
	}

	/**
	 * Actualiza los datos de un tema por su ID.
	 * 
	 * @param id identificador del tema
	 * @param newData DTO con los nuevos datos
	 * @return 0 si se actualizó correctamente, 1 si no se encontró
	 */
	@Override
	public int updateById(Long id, TemaDTO newData) {
		Optional<Tema> opt = temaRepo.findById(id);
		if (opt.isPresent()) {
			Tema entity = opt.get();
			entity.setTema(newData.getTema());
	        entity.setTipo(newData.getTipo());
	        entity.setContenido(newData.getContenido());
			temaRepo.save(entity);
			return 0;
		}
		return 1;
	}

	/**
	 * Cuenta la cantidad total de temas registrados.
	 * 
	 * @return número total de temas
	 */
	@Override
	public Long count() {
		Long tamano = (long) getAll().size();
		return tamano;
	}

	/**
	 * Verifica si existe un tema por su ID.
	 * 
	 * @param id identificador del tema
	 * @return {@code false} (implementación pendiente)
	 */
	@Override
	public boolean exist(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
}
