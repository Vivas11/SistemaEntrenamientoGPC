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

import co.edu.unbosque.sistemaentrenamientoGPC_back.dto.TemaDTO;
import co.edu.unbosque.sistemaentrenamientoGPC_back.service.TemaService;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/tema" })
public class TemaController {
	
	
	@Autowired
	private TemaService temaService;
	
	
	
	@PostMapping(path = "/crear")
	public ResponseEntity<String> crear(@RequestParam String tema, String tipo,String contenido ) {
		TemaDTO nuevo = new TemaDTO(tema,tipo, contenido);
		int status = temaService.create(nuevo);
		if (status == 0) {
			return new ResponseEntity<>("Tema creado con exito", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Error creando su tema", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	
	@PostMapping(path = "/createjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<String> createNewWithJSON(@RequestBody TemaDTO newUser) {
		int status = temaService.create(newUser);

		if (status == 0) {
			return new ResponseEntity<>("Tema creado con exito", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Error creando su tema",
					HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@DeleteMapping(path = "/eliminar")
	public ResponseEntity<String> eliminar(@RequestParam Long id) {
		int status = temaService.deleteById(id);
		if (status == 0) {
			return new ResponseEntity<>("Eliminado con exito", HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>("Tema no encontrado", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(path = "/actualizar")
	public ResponseEntity<String> actualizar(@RequestParam Long id,String tema, String tipo,String contenido) {
		TemaDTO data = new TemaDTO(tema,tipo, contenido);
		int status = temaService.updateById(id, data);
		if (status == 0) {
			return new ResponseEntity<>("Tema actualizado", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Error al actualizar", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@GetMapping(path = "/mostrar")
	public ResponseEntity<String> mostrar() {
		List<TemaDTO> lista = temaService.getAll();
		if (lista.isEmpty()) {
			return new ResponseEntity<>("Aun no existe ningun tema", HttpStatus.NOT_FOUND);
		} else {
			StringBuilder sb = new StringBuilder();
			lista.forEach(dto -> sb.append(dto.toString()).append("\n"));
			return new ResponseEntity<>("Temas:\n" + sb.toString(), HttpStatus.ACCEPTED);
		}
	}
	
	
	

}
