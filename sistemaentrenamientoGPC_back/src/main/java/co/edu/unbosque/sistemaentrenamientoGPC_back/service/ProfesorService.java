package co.edu.unbosque.sistemaentrenamientoGPC_back.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.sistemaentrenamientoGPC_back.dto.ProfesorDTO;
import co.edu.unbosque.sistemaentrenamientoGPC_back.entity.Profesor;
import co.edu.unbosque.sistemaentrenamientoGPC_back.repository.ProfesorRepository;

/**
 * Servicio encargado de gestionar operaciones CRUD para la entidad {@code Profesor} usando {@code ProfesorDTO}.
 * Implementa la interfaz {@code CRUDOperation<ProfesorDTO>} y utiliza {@link ModelMapper} para la conversión entre entidades y DTOs.
 */
@Service
public class ProfesorService implements CRUDOperation<ProfesorDTO> {

	/** Repositorio JPA para acceder a datos de profesores. */
	@Autowired // el AUTOINICIALIZA LA ESTRUCTURA DEL PROYECTO como los atributos  
	private ProfesorRepository profesroRepo;

	/** Utilidad para mapear entre entidades y DTOs. */
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Constructor vacío requerido por Spring.
	 */
	public ProfesorService() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Crea un nuevo profesor en la base de datos.
	 * 
	 * @param newData DTO con los datos del nuevo profesor
	 * @return 0 si la operación fue exitosa
	 */
	@Override
	public int create(ProfesorDTO newData) {
		Profesor entity = modelMapper.map(newData, Profesor.class);
		profesroRepo.save(entity);
		return 0;
	}

	/**
	 * Obtiene todos los profesores registrados.
	 * 
	 * @return lista de {@code ProfesorDTO}
	 */
	@Override
	public List<ProfesorDTO> getAll() {
		List<Profesor> entityList = profesroRepo.findAll();
	    List<ProfesorDTO> dtoList = new ArrayList<>();
	    entityList.forEach((entity) -> {
	        ProfesorDTO dto = modelMapper.map(entity, ProfesorDTO.class);
	        dtoList.add(dto);
	    });
	    return dtoList;
	}

	/**
	 * Elimina un profesor por su ID.
	 * 
	 * @param id identificador del profesor
	 * @return 0 si se eliminó correctamente, 1 si no existe
	 */
	@Override
	public int deleteById(Long id) {
		if (profesroRepo.existsById(id)) {
			profesroRepo.deleteById(id);
			return 0;
		}
		return 1;
	}

	/**
	 * Actualiza los datos de un profesor por su ID.
	 * 
	 * @param id identificador del profesor
	 * @param newData DTO con los nuevos datos
	 * @return 0 si se actualizó correctamente, 1 si no se encontró
	 */
	@Override
	public int updateById(Long id, ProfesorDTO newData) {
	    Optional<Profesor> opt = profesroRepo.findById(id);
	    if (opt.isPresent()) {
	        Profesor entity = opt.get();
	        entity.setNombre(newData.getNombre());
	        entity.setCorreo(newData.getCorreo());
	        entity.setEdad(newData.getEdad());
	        entity.setContrasena(newData.getContrasena());
	        entity.setCargo(newData.getCargo());
	        entity.setEsEntrenador(newData.isEsEntrenador());
	        profesroRepo.save(entity);
	        return 0;
	    }
	    return 1;
	}

	/**
	 * Cuenta la cantidad total de profesores registrados.
	 * 
	 * @return número total de profesores
	 */
	@Override
	public Long count() {
		Long tamano = (long) getAll().size();
		return tamano;
	}

	/**
	 * Verifica si existe un profesor por su ID.
	 * 
	 * @param id identificador del profesor
	 * @return {@code false} (implementación pendiente)
	 */
	@Override
	public boolean exist(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
}
