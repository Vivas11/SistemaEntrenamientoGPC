package co.edu.unbosque.sistemaentrenamientoGPC_back.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.unbosque.sistemaentrenamientoGPC_back.dto.EstudianteDTO;
import co.edu.unbosque.sistemaentrenamientoGPC_back.entity.Estudiante;
import co.edu.unbosque.sistemaentrenamientoGPC_back.repository.EstudianteRepository;

public class EstudianteService implements CRUDOperation<EstudianteDTO>{
	@Autowired 
	private EstudianteRepository estudianteRepo;
	@Autowired
	private ModelMapper modelMapper;
	public EstudianteService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int create(EstudianteDTO newData) {
		Estudiante entity = modelMapper.map(newData, Estudiante.class);
		estudianteRepo.save(entity);
		return 0;
	}

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

	@Override
	public int deleteById(Long id) {
		if(estudianteRepo.existsById(id)) {
			estudianteRepo.deleteById(id);
			return 0;
		}
		return 1;
	}

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

	@Override
	public Long count() {
		Long tamano =  (long) getAll().size();
		return tamano;
	}

	@Override
	public boolean exist(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	

}
