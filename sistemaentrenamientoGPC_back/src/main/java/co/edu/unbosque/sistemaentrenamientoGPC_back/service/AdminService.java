package co.edu.unbosque.sistemaentrenamientoGPC_back.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.unbosque.sistemaentrenamientoGPC_back.dto.AdminDTO;
import co.edu.unbosque.sistemaentrenamientoGPC_back.repository.AdminRepository;



public class AdminService  implements CRUDOperation<AdminDTO>{
	

	@Autowired //el AUTOINICIALIZA LA ESTRUCTURA DEL PROYECTO como los atributops  
	private AdminRepository adminRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public AdminService() {
	
	}

	@Override
	public int create(AdminDTO newData) {
		
		return 0;
	}

	@Override
	public List<AdminDTO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteById(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateById(Long id, AdminDTO newData) {
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
