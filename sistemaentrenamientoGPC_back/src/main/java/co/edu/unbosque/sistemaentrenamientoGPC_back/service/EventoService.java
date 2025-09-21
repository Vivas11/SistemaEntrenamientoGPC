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
@Service
public class EventoService implements CRUDOperation<EventoDTO> {

	@Autowired   
	private EventoRepository eventoRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public EventoService() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public int create(EventoDTO newData) {
		Evento entity = modelMapper.map(newData, Evento.class);
		eventoRepo.save(entity);
	
		return 0;
	}


	@Override
	public List<EventoDTO> getAll() {
		List<Evento> entityList = eventoRepo.findAll();
	    List<EventoDTO> dtoList = new ArrayList<>();
	    entityList.forEach((entity)->{
	        EventoDTO dto = modelMapper.map(entity, EventoDTO.class);
	        dtoList.add(dto);
	    });
	    return dtoList;
	}


	@Override
	public int deleteById(Long id) {
		if(eventoRepo.existsById(id)) {
			eventoRepo.deleteById(id);
			return 0;
		}
		return 1;
	}


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
