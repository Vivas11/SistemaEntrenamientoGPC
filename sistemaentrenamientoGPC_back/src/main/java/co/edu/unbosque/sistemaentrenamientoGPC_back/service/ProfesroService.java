package co.edu.unbosque.sistemaentrenamientoGPC_back.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.unbosque.sistemaentrenamientoGPC_back.dto.ProfesorDTO;
import co.edu.unbosque.sistemaentrenamientoGPC_back.repository.AdminRepository;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ProfesorDTO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteById(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateById(Long id, ProfesorDTO newData) {
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
