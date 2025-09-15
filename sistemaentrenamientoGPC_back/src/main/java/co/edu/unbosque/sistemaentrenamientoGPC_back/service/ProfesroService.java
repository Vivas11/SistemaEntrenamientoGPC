package co.edu.unbosque.sistemaentrenamientoGPC_back.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.unbosque.sistemaentrenamientoGPC_back.dto.ProfesorDTO;
import co.edu.unbosque.sistemaentrenamientoGPC_back.entity.Profesor;
import co.edu.unbosque.sistemaentrenamientoGPC_back.repository.ProfesorRepository;

public class ProfesroService implements CRUDOperation<ProfesorDTO>{
	
	
	
	@Autowired //el AUTOINICIALIZA LA ESTRUCTURA DEL PROYECTO como los atributops  
	private ProfesorRepository profesroRepo;
	@Autowired
	private ModelMapper modelMapper;
	public ProfesroService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int create(ProfesorDTO newData) {
		Profesor entity = modelMapper.map(newData, Profesor.class);
		profesroRepo.save(entity);
	
		return 0;
	}

	@Override
	public List<ProfesorDTO> getAll() {
		List<Profesor> entityList = profesroRepo.findAll();
	    List<ProfesorDTO> dtoList = new ArrayList<>();
	    entityList.forEach((entity)->{
	        ProfesorDTO dto = modelMapper.map(entity, ProfesorDTO.class);
	        dtoList.add(dto);
	    });
	    return dtoList;
	}

	@Override
	public int deleteById(Long id) {
		if(profesroRepo.existsById(id)) {
			profesroRepo.deleteById(id);
			return 0;
		}
		return 1;
	}

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
