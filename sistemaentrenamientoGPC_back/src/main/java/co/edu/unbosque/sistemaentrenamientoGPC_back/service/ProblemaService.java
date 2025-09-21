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
@Service
public class ProblemaService implements CRUDOperation<ProblemaDTO>{
	
	@Autowired   
	private ProblemaRepository problemaRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
      public ProblemaService() {
		
	}

	  @Override
	  public int create(ProblemaDTO newData) {
		  Problema entity = modelMapper.map(newData, Problema.class);
			problemaRepo.save(entity);
		
			return 0;
	  }

	  @Override
	  public List<ProblemaDTO> getAll() {
		  List<Problema> entityList = problemaRepo.findAll();
		    List<ProblemaDTO> dtoList = new ArrayList<>();
		    entityList.forEach((entity)->{
		        ProblemaDTO dto = modelMapper.map(entity, ProblemaDTO.class);
		        dtoList.add(dto);
		    });
		    return dtoList;
	  }

	  @Override
	  public int deleteById(Long id) {
		  if(problemaRepo.existsById(id)) {
				problemaRepo.deleteById(id);
				return 0;
			}
			return 1;
	  }

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
