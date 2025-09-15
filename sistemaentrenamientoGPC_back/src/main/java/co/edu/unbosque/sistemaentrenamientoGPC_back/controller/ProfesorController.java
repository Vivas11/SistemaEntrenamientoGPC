package co.edu.unbosque.sistemaentrenamientoGPC_back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.sistemaentrenamientoGPC_back.dto.ProfesorDTO;
import co.edu.unbosque.sistemaentrenamientoGPC_back.service.ProfesroService;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/profesor" })
public class ProfesorController {

	@Autowired
	private ProfesroService profesorService;

	@PostMapping(path = "/crear")
	public ResponseEntity<String> crear(@RequestParam Long id, @RequestParam String nombre,@RequestParam String correo, @RequestParam int edad,@RequestParam String contrasena,@RequestParam String cargo,@RequestParam boolean esEntrenador ) {
		ProfesorDTO nuevo = new ProfesorDTO(nombre, correo, edad, contrasena, cargo, esEntrenador);
		int status = profesorService.create(nuevo);
		if (status == 0) {
			return new ResponseEntity<>("Profesor creado", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Error al crear", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@DeleteMapping(path = "/eliminar")
	public ResponseEntity<String> eliminar(@RequestParam Long id) {
		int status = profesorService.deleteById(id);
		if (status == 0) {
			return new ResponseEntity<>("Eliminado con exito", HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>("Profesor no encontrado", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(path = "/actualizar")
	public ResponseEntity<String> actualizar(@RequestParam Long id, @RequestParam String nombre,@RequestParam String correo, @RequestParam int edad,@RequestParam String contrasena,@RequestParam String cargo,@RequestParam boolean esEntrenador) {
		ProfesorDTO data = new ProfesorDTO(nombre, correo, edad, contrasena, cargo, esEntrenador);
		int status = profesorService.updateById(id, data);
		if (status == 0) {
			return new ResponseEntity<>("Profesor actualizado", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Error al actualizar", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@GetMapping(path = "/mostrar")
	public ResponseEntity<String> mostrar() {
		List<ProfesorDTO> lista = profesorService.getAll();
		if (lista.isEmpty()) {
			return new ResponseEntity<>("Aun no existe ningun profesor", HttpStatus.NOT_FOUND);
		} else {
			StringBuilder sb = new StringBuilder();
			lista.forEach(dto -> sb.append(dto.toString()).append("\n"));
			return new ResponseEntity<>("Profesores:\n" + sb.toString(), HttpStatus.ACCEPTED);
		}
	}

	@GetMapping(path = "/contar")
	public ResponseEntity<String> contar() {
		Long total = profesorService.count();
		return new ResponseEntity<>("Total de profesores: " + total, HttpStatus.OK);
	}
}

