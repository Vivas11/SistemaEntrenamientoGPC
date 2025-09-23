package co.edu.unbosque.sistemaentrenamientoGPC_back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.sistemaentrenamientoGPC_back.dto.ProblemaDTO;
import co.edu.unbosque.sistemaentrenamientoGPC_back.service.ProblemaService;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/problema" })
public class ProblemaController {
	
	@Autowired
	private ProblemaService problemaService;
	
	
	@PostMapping(path = "/crear")
	public ResponseEntity<String> crear(@RequestParam String nombre, String dificultad,String tema, String juez ) {
		ProblemaDTO nuevo = new ProblemaDTO(nombre, dificultad, tema, juez);
		int status = problemaService.create(nuevo);
		if (status == 0) {
			return new ResponseEntity<>("Problema creado con exito", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Error creando su problema", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PostMapping(path = "/createjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<String> createNewWithJSON(@RequestBody ProblemaDTO newUser) {
		int status = problemaService.create(newUser);

		if (status == 0) {
			return new ResponseEntity<>("Problema creado con exito", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Error creando su problema ",
					HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@DeleteMapping(path = "/eliminar")
	public ResponseEntity<String> eliminar(@RequestParam Long id) {
		int status = problemaService.deleteById(id);
		if (status == 0) {
			return new ResponseEntity<>("Eliminado con exito", HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>("Problema no encontrado", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(path = "/actualizar")
	public ResponseEntity<String> actualizar(@RequestParam Long id,String nombre, String dificultad,String tema, String juez) {
			ProblemaDTO data = new ProblemaDTO(nombre, dificultad, tema, juez);
		int status = problemaService.updateById(id, data);
		if (status == 0) {
			return new ResponseEntity<>("Problema actualizado", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Error al actualizar", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@GetMapping(path = "/mostrar")
	public ResponseEntity<String> mostrar() {
		List<ProblemaDTO> lista = problemaService.getAll();
		if (lista.isEmpty()) {
			return new ResponseEntity<>("Aun no existe ningun problema", HttpStatus.NOT_FOUND);
		} else {
			StringBuilder sb = new StringBuilder();
			lista.forEach(dto -> sb.append(dto.toString()).append("\n"));
			return new ResponseEntity<>("Problemas:\n" + sb.toString(), HttpStatus.ACCEPTED);
		}
	}
	
	
	@GetMapping("/getall")
	ResponseEntity<List<ProblemaDTO>> getAll() {
		List<ProblemaDTO> users = problemaService.getAll();
		if (users.isEmpty()) {
			return new ResponseEntity<>(users, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
		}
	}

}
