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

/**
 * Controlador REST para gestionar operaciones relacionadas con los temas.
 * Permite crear, actualizar, eliminar y consultar temas mediante distintos formatos de entrada.
 * 
 * Endpoint base: /tema
 */
@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/tema" })
public class TemaController {

	@Autowired
	private TemaService temaService;

	/**
	 * Crea un nuevo tema usando parámetros de solicitud.
	 * 
	 * @param tema      Título o nombre del tema
	 * @param tipo      Tipo o categoría del tema
	 * @param contenido Contenido asociado al tema
	 * @return ResponseEntity con mensaje de éxito o error
	 */
	@PostMapping(path = "/crear")
	public ResponseEntity<String> crear(@RequestParam String tema, String tipo, String contenido) {
		TemaDTO nuevo = new TemaDTO(tema, tipo, contenido);
		int status = temaService.create(nuevo);
		if (status == 0) {
			return new ResponseEntity<>("Tema creado con exito", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Error creando su tema", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * Crea un nuevo tema usando un objeto JSON.
	 * 
	 * @param newUser Objeto TemaDTO recibido en el cuerpo de la solicitud
	 * @return ResponseEntity con mensaje de éxito o error
	 */
	@PostMapping(path = "/createjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<String> createNewWithJSON(@RequestBody TemaDTO newUser) {
		int status = temaService.create(newUser);

		if (status == 0) {
			return new ResponseEntity<>("Tema creado con exito", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Error creando su tema", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * Elimina un tema por su ID.
	 * 
	 * @param id Identificador del tema
	 * @return ResponseEntity con mensaje de éxito o error
	 */
	@DeleteMapping(path = "/eliminar")
	public ResponseEntity<String> eliminar(@RequestParam Long id) {
		int status = temaService.deleteById(id);
		if (status == 0) {
			return new ResponseEntity<>("Eliminado con exito", HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>("Tema no encontrado", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Actualiza los datos de un tema por su ID.
	 * 
	 * @param id        Identificador del tema
	 * @param tema      Nuevo nombre del tema
	 * @param tipo      Nuevo tipo o categoría
	 * @param contenido Nuevo contenido
	 * @return ResponseEntity con mensaje de éxito o error
	 */
	@PutMapping(path = "/actualizar")
	public ResponseEntity<String> actualizar(@RequestParam Long id, String tema, String tipo, String contenido) {
		TemaDTO data = new TemaDTO(tema, tipo, contenido);
		int status = temaService.updateById(id, data);
		if (status == 0) {
			return new ResponseEntity<>("Tema actualizado", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Error al actualizar", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * Muestra todos los temas en formato de texto.
	 * 
	 * @return ResponseEntity con lista de temas o mensaje de ausencia
	 */
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

	/**
	 * Obtiene todos los temas en formato JSON.
	 * 
	 * @return ResponseEntity con lista de objetos TemaDTO
	 */
	@GetMapping("/getall")
	ResponseEntity<List<TemaDTO>> getAll() {
		List<TemaDTO> users = temaService.getAll();
		if (users.isEmpty()) {
			return new ResponseEntity<>(users, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
		}
	}

}
