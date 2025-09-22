package co.edu.unbosque.sistemaentrenamientoGPC_back.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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

import co.edu.unbosque.sistemaentrenamientoGPC_back.dto.EventoDTO;
import co.edu.unbosque.sistemaentrenamientoGPC_back.service.EventoService;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/evento" })
public class EventoController {

	@Autowired
	private EventoService eventoService;

	@PostMapping(path = "/crear")
	public ResponseEntity<String> crear(@RequestParam String nombre, int dia, int mes, int ano, String descripcion) {
		LocalDate fecha = LocalDate.of(ano, mes, dia);
		EventoDTO nuevo = new EventoDTO(nombre, fecha, descripcion);
		int status = eventoService.create(nuevo);
		if (status == 0) {
			return new ResponseEntity<>("Evento creado con exito", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Error creando su evento", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping(path = "/actualizar")
	public ResponseEntity<String> actualizar(@RequestParam Long id, String nombre, int dia, int mes, int ano,
			String descripcion) {
		LocalDate fecha = LocalDate.of(ano, mes, dia);
		EventoDTO data = new EventoDTO(nombre, fecha, descripcion);
		int status = eventoService.updateById(id, data);
		if (status == 0) {
			return new ResponseEntity<>("Problema actualizado", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Error al actualizar", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PostMapping(path = "/createjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<String> createNewWithJSON(@RequestBody EventoDTO newUser) {
		int status = eventoService.create(newUser);

		if (status == 0) {
			return new ResponseEntity<>("Evento creado con exito", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Error creando su Evento.", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@DeleteMapping(path = "/eliminar")
	public ResponseEntity<String> eliminar(@RequestParam Long id) {
		int status = eventoService.deleteById(id);
		if (status == 0) {
			return new ResponseEntity<>("Eliminado con exito", HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>("Evento no encontrado", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/mostrar")
	public ResponseEntity<String> mostrar() {
		List<EventoDTO> lista = eventoService.getAll();
		if (lista.isEmpty()) {
			return new ResponseEntity<>("Aun no existe ningun evento", HttpStatus.NOT_FOUND);
		} else {
			StringBuilder sb = new StringBuilder();
			lista.forEach(dto -> sb.append(dto.toString()).append("\n"));
			return new ResponseEntity<>("Evento:\n" + sb.toString(), HttpStatus.ACCEPTED);
		}
	}

	@GetMapping("/getall")
	ResponseEntity<List<EventoDTO>> getAll() {
		List<EventoDTO> users = eventoService.getAll();
		if (users.isEmpty()) {
			return new ResponseEntity<>(users, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
		}
	}

}
