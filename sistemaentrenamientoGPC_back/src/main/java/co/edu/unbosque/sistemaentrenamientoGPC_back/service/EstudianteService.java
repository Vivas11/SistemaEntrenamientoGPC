package co.edu.unbosque.sistemaentrenamientoGPC_back.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.sistemaentrenamientoGPC_back.dto.EstudianteDTO;
import co.edu.unbosque.sistemaentrenamientoGPC_back.entity.Estudiante;
import co.edu.unbosque.sistemaentrenamientoGPC_back.repository.EstudianteRepository;

/**
 * Servicio encargado de gestionar operaciones CRUD para la entidad {@code Estudiante} usando {@code EstudianteDTO}.
 * Implementa la interfaz {@code CRUDOperation<EstudianteDTO>} y utiliza {@code ModelMapper} para la conversión entre entidades y DTOs.
 */
@Service
public class EstudianteService implements CRUDOperation<EstudianteDTO> {

	/** Repositorio JPA para acceder a datos de estudiantes. */
	@Autowired 
	private EstudianteRepository estudianteRepo;

	/** Utilidad para mapear entre entidades y DTOs. */
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Constructor vacío requerido por Spring.
	 */
	public EstudianteService() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Crea un nuevo estudiante en la base de datos.
	 * 
	 * @param newData DTO con los datos del nuevo estudiante
	 * @return 0 si la operación fue exitosa
	 */
	@Override
	public int create(EstudianteDTO newData) {
		Estudiante entity = modelMapper.map(newData, Estudiante.class);
		estudianteRepo.save(entity);
		return 0;
	}

	/**
	 * Obtiene todos los estudiantes registrados.
	 * 
	 * @return lista de {@code EstudianteDTO}
	 */
	@Override
	public List<EstudianteDTO> getAll() {
		List<Estudiante> entityList = estudianteRepo.findAll();
	    List<EstudianteDTO> dtoList = new ArrayList<>();
	    entityList.forEach((entity)->{
	        EstudianteDTO dto = modelMapper.map(entity, EstudianteDTO.class);
	        dtoList.add(dto);
	    });
	    return dtoList;
	}

	/**
	 * Elimina un estudiante por su ID.
	 * 
	 * @param id identificador del estudiante
	 * @return 0 si se eliminó correctamente, 1 si no existe
	 */
	@Override
	public int deleteById(Long id) {
		if(estudianteRepo.existsById(id)) {
			estudianteRepo.deleteById(id);
			return 0;
		}
		return 1;
	}

	/**
	 * Actualiza los datos de un estudiante por su ID.
	 * 
	 * @param id identificador del estudiante
	 * @param newData DTO con los nuevos datos
	 * @return 0 si se actualizó correctamente, 1 si no se encontró
	 */
	@Override
	public int updateById(Long id, EstudianteDTO newData) {
		Optional<Estudiante> opt = estudianteRepo.findById(id);
		if (opt.isPresent()) {
			Estudiante entity = opt.get();
			entity.setNombre(newData.getNombre());
	        entity.setCorreo(newData.getCorreo());
	        entity.setEdad(newData.getEdad());
	        entity.setContrasena(newData.getContrasena());
	        entity.setNivelCompe(newData.getNivelCompe());
	        entity.setSemestre(newData.getSemestre());

	        estudianteRepo.save(entity);
			estudianteRepo.save(entity);
			return 0;
		}
		return 1;
	}

	/**
	 * Cuenta la cantidad total de estudiantes registrados.
	 * 
	 * @return número total de estudiantes
	 */
	@Override
	public Long count() {
		Long tamano =  (long) getAll().size();
		return tamano;
	}

	/**
	 * Verifica si existe un estudiante por su ID.
	 * 
	 * @param id identificador del estudiante
	 * @return {@code false} (implementación pendiente)
	 */
	@Override
	public boolean exist(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
}