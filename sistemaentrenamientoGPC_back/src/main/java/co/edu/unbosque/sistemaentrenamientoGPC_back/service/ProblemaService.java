package co.edu.unbosque.sistemaentrenamientoGPC_back.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.sistemaentrenamientoGPC_back.dto.ProblemaDTO;
import co.edu.unbosque.sistemaentrenamientoGPC_back.entity.Problema;
import co.edu.unbosque.sistemaentrenamientoGPC_back.repository.ProblemaRepository;
/**
 * Servicio encargado de gestionar operaciones CRUD para la entidad {@code Problema} usando {@code ProblemaDTO}.
 * Implementa la interfaz {@code CRUDOperation<ProblemaDTO>} y utiliza {@link ModelMapper} para la conversión entre entidades y DTOs.
 */
@Service
public class ProblemaService implements CRUDOperation<ProblemaDTO> {

	/** Repositorio JPA para acceder a datos de problemas. */
	@Autowired   
	private ProblemaRepository problemaRepo;

	/** Utilidad para mapear entre entidades y DTOs. */
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Constructor vacío requerido por Spring.
	 */
	public ProblemaService() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Crea un nuevo problema en la base de datos.
	 * 
	 * @param newData DTO con los datos del nuevo problema
	 * @return 0 si la operación fue exitosa
	 */
	@Override
	public int create(ProblemaDTO newData) {
		Problema entity = modelMapper.map(newData, Problema.class);
		problemaRepo.save(entity);
		return 0;
	}

	/**
	 * Obtiene todos los problemas registrados.
	 * 
	 * @return lista de {@code ProblemaDTO}
	 */
	@Override
	public List<ProblemaDTO> getAll() {
		List<Problema> entityList = problemaRepo.findAll();
		List<ProblemaDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			ProblemaDTO dto = modelMapper.map(entity, ProblemaDTO.class);
			dtoList.add(dto);
		});
		return dtoList;
	}

	/**
	 * Elimina un problema por su ID.
	 * 
	 * @param id identificador del problema
	 * @return 0 si se eliminó correctamente, 1 si no existe
	 */
	@Override
	public int deleteById(Long id) {
		if (problemaRepo.existsById(id)) {
			problemaRepo.deleteById(id);
			return 0;
		}
		return 1;
	}

	/**
	 * Actualiza los datos de un problema por su ID.
	 * 
	 * @param id identificador del problema
	 * @param newData DTO con los nuevos datos
	 * @return 0 si se actualizó correctamente, 1 si no se encontró
	 */
	@Override
	public int updateById(Long id, ProblemaDTO newData) {
		Optional<Problema> opt = problemaRepo.findById(id);
		if (opt.isPresent()) {
			Problema entity = opt.get();
			entity.setNombre(newData.getNombre());
			entity.setDificultad(newData.getDificultad());
			entity.setTema(newData.getTema());
			entity.setJuez(newData.getJuez());
			problemaRepo.save(entity);
			return 0;
		}
		return 1;
	}

	/**
	 * Cuenta la cantidad total de problemas registrados.
	 * 
	 * @return número total de problemas
	 */
	@Override
	public Long count() {
		Long tamano = (long) getAll().size();
		return tamano;
	}

	/**
	 * Verifica si existe un problema por su ID.
	 * 
	 * @param id identificador del problema
	 * @return {@code false} (implementación pendiente)
	 */
	@Override
	public boolean exist(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
}
