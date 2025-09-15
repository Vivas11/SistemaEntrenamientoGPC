package co.edu.unbosque.sistemaentrenamientoGPC_back.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.unbosque.sistemaentrenamientoGPC_back.dto.AdminDTO;
import co.edu.unbosque.sistemaentrenamientoGPC_back.entity.Admin;
import co.edu.unbosque.sistemaentrenamientoGPC_back.repository.AdminRepository;



public class AdminService  implements CRUDOperation<AdminDTO>{
	

	@Autowired   
	private AdminRepository adminRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public AdminService() {
	
	}

	@Override
	public int create(AdminDTO newData) {
		Admin entity = modelMapper.map(newData, Admin.class);
		adminRepo.save(entity);
	
		return 0;
	}

	@Override
	public List<AdminDTO> getAll() {
		List<Admin> entityList = adminRepo.findAll();
	    List<AdminDTO> dtoList = new ArrayList<>();
	    entityList.forEach((entity)->{
	        AdminDTO dto = modelMapper.map(entity, AdminDTO.class);
	        dtoList.add(dto);
	    });
	    return dtoList;
	}

	@Override
	public int deleteById(Long id) {
		if(adminRepo.existsById(id)) {
			adminRepo.deleteById(id);
			return 0;
		}
		return 1;
	}

	@Override
	public int updateById(Long id, AdminDTO newData) {
		Optional<Admin> opt = adminRepo.findById(id);
		if (opt.isPresent()) {
			Admin entity = opt.get();
			entity.setNombre(newData.getNombre());
	        entity.setCorreo(newData.getCorreo());
	        entity.setEdad(newData.getEdad());
	        entity.setContrasena(newData.getContrasena());
			
			
			
			adminRepo.save(entity);
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
