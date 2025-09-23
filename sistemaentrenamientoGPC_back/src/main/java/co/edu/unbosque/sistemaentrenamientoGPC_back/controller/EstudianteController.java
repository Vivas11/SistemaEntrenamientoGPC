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

import co.edu.unbosque.sistemaentrenamientoGPC_back.dto.EstudianteDTO;
import co.edu.unbosque.sistemaentrenamientoGPC_back.service.EstudianteService;



@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/estudiante" })
public class EstudianteController {
	@Autowired
	private EstudianteService estudianteService;
	
	
	
	@PostMapping(path = "/crear")
	public ResponseEntity<String> crear(@RequestParam String nombre, String correo, int edad, String contrasena, String nivelCompe, int semestre) {
		EstudianteDTO nuevo = new EstudianteDTO(nombre, correo, edad, contrasena, nivelCompe, semestre);
		int status = estudianteService.create(nuevo);
		if (status == 0) {
			return new ResponseEntity<>("Estudiante creado", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Error al crear", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	
	@PostMapping(path = "/createjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<String> createNewWithJSON(@RequestBody EstudianteDTO newUser) {
		int status = estudianteService.create(newUser);

		if (status == 0) {
			return new ResponseEntity<>("{User create successfully}", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("{Error on created user, maybe username already in use}",
					HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@DeleteMapping(path = "/eliminar")
	public ResponseEntity<String> eliminar(@RequestParam Long id) {
		int status = estudianteService.deleteById(id);
		if (status == 0) {
			return new ResponseEntity<>("Eliminado con exito", HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>("Estudiante no encontrado", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(path = "/actualizar")
	public ResponseEntity<String> actualizar(@RequestParam Long id, String nombre, String correo, int edad, String contrasena, String nivelCompe, int semestre) {
		EstudianteDTO data = new EstudianteDTO(nombre, correo, edad, contrasena, nivelCompe, semestre);
		int status = estudianteService.updateById(id, data);
		if (status == 0) {
			return new ResponseEntity<>("Estudiante actualizado", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Error al actualizar", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@GetMapping(path = "/mostrar")
	public ResponseEntity<String> mostrar() {
		List<EstudianteDTO> lista = estudianteService.getAll();
		if (lista.isEmpty()) {
			return new ResponseEntity<>("Aun no existe ningun profesor", HttpStatus.NOT_FOUND);
		} else {
			StringBuilder sb = new StringBuilder();
			lista.forEach(dto -> sb.append(dto.toString()).append("\n"));
			return new ResponseEntity<>("Profesores:\n" + sb.toString(), HttpStatus.ACCEPTED);
		}
	}

	@GetMapping("/getall")
	ResponseEntity<List<EstudianteDTO>> getAll() {
		List<EstudianteDTO> users = estudianteService.getAll();
		if (users.isEmpty()) {
			return new ResponseEntity<>(users, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
		}
	}
	
	

}
