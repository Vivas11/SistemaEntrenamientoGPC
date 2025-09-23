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

import co.edu.unbosque.sistemaentrenamientoGPC_back.dto.ProfesorDTO;
import co.edu.unbosque.sistemaentrenamientoGPC_back.service.ProfesorService;

/**
 * Controlador REST para gestionar operaciones relacionadas con profesores.
 * Permite crear, actualizar, eliminar y consultar profesores mediante diferentes formatos de entrada.
 * 
 * Endpoint base: /profesor
 */
@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/profesor" })
public class ProfesorController {

	@Autowired
	private ProfesorService profesorService;

	/**
	 * Crea un nuevo profesor usando parámetros de solicitud.
	 * 
	 * @param nombre       Nombre del profesor
	 * @param correo       Correo electrónico
	 * @param edad         Edad del profesor
	 * @param contrasena   Contraseña
	 * @param cargo        Cargo del profesor
	 * @param esEntrenador Indica si el profesor es entrenador
	 * @return ResponseEntity con mensaje de éxito o error
	 */
	@PostMapping(path = "/crear")
	public ResponseEntity<String> crear(@RequestParam String nombre, String correo, int edad, String contrasena, String cargo, boolean esEntrenador) {
		ProfesorDTO nuevo = new ProfesorDTO(nombre, correo, edad, contrasena, cargo, esEntrenador);
		int status = profesorService.create(nuevo);
		if (status == 0) {
			return new ResponseEntity<>("Profesor creado", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Error al crear", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * Crea un nuevo profesor usando un objeto JSON.
	 * 
	 * @param newUser Objeto ProfesorDTO recibido en el cuerpo de la solicitud
	 * @return ResponseEntity con mensaje de éxito o error
	 */
	@PostMapping(path = "/createjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<String> createNewWithJSON(@RequestBody ProfesorDTO newUser) {
		int status = profesorService.create(newUser);

		if (status == 0) {
			return new ResponseEntity<>("{Profesor create successfully}", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("{Error on created profesor}", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * Elimina un profesor por su ID.
	 * 
	 * @param id Identificador del profesor
	 * @return ResponseEntity con mensaje de éxito o error
	 */
	@DeleteMapping(path = "/eliminar")
	public ResponseEntity<String> eliminar(@RequestParam Long id) {
		int status = profesorService.deleteById(id);
		if (status == 0) {
			return new ResponseEntity<>("Eliminado con exito", HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>("Profesor no encontrado", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Actualiza los datos de un profesor por su ID.
	 * 
	 * @param id           Identificador del profesor
	 * @param nombre       Nuevo nombre
	 * @param correo       Nuevo correo
	 * @param edad         Nueva edad
	 * @param contrasena   Nueva contraseña
	 * @param cargo        Nuevo cargo
	 * @param esEntrenador Nuevo estado de entrenador
	 * @return ResponseEntity con mensaje de éxito o error
	 */
	@PutMapping(path = "/actualizar")
	public ResponseEntity<String> actualizar(@RequestParam Long id, String nombre, String correo, int edad, String contrasena, String cargo, boolean esEntrenador) {
		ProfesorDTO data = new ProfesorDTO(nombre, correo, edad, contrasena, cargo, esEntrenador);
		int status = profesorService.updateById(id, data);
		if (status == 0) {
			return new ResponseEntity<>("Profesor actualizado", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Error al actualizar", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * Muestra todos los profesores en formato de texto.
	 * 
	 * @return ResponseEntity con lista de profesores o mensaje de ausencia
	 */
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

	/**
	 * Obtiene todos los profesores en formato JSON.
	 * 
	 * @return ResponseEntity con lista de objetos ProfesorDTO
	 */
	@GetMapping("/getall")
	ResponseEntity<List<ProfesorDTO>> getAll() {
		List<ProfesorDTO> users = profesorService.getAll();
		if (users.isEmpty()) {
			return new ResponseEntity<>(users, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
		}
	}

}

