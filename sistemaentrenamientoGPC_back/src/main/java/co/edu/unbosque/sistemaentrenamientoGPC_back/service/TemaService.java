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
@Service
public class TemaService  implements CRUDOperation<TemaDTO> {

	@Autowired 
	private TemaRepository temaRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public TemaService() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public int create(TemaDTO newData) {
		Tema entity = modelMapper.map(newData, Tema.class);
		temaRepo.save(entity);
	
		return 0;
	}

	@Override
	public List<TemaDTO> getAll() {
		List<Tema> entityList = temaRepo.findAll();
	    List<TemaDTO> dtoList = new ArrayList<>();
	    entityList.forEach((entity)->{
	        TemaDTO dto = modelMapper.map(entity, TemaDTO.class);
	        dtoList.add(dto);
	    });
	    return dtoList;
	}

	@Override
	public int deleteById(Long id) {
		if(temaRepo.existsById(id)) {
			temaRepo.deleteById(id);
			return 0;
		}
		return 1;
	}

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
