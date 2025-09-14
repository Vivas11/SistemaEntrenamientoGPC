package co.edu.unbosque.sistemaentrenamientoGPC_back.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.unbosque.sistemaentrenamientoGPC_back.dto.AdminDTO;
import co.edu.unbosque.sistemaentrenamientoGPC_back.dto.EstudianteDTO;

public class EstudianteService implements CRUDOperation<EstudianteDTO>{
	
	@Autowired
	private ModelMapper modelMapper;
	public EstudianteService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int create(EstudianteDTO newData) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<EstudianteDTO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteById(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateById(Long id, EstudianteDTO newData) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exist(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	

}
