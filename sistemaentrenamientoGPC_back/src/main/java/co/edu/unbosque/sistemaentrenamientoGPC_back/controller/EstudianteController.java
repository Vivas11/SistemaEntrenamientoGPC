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



/**
 * Controlador REST para gestionar operaciones relacionadas con estudiantes.
 * Expone endpoints para crear, actualizar, eliminar y consultar estudiantes.
 * Utiliza {@link EstudianteService} para delegar la lógica de negocio.
 */
@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/estudiante" })
public class EstudianteController {

	/** Servicio que gestiona la lógica de negocio para estudiantes. */
	@Autowired
	private EstudianteService estudianteService;

	/**
	 * Crea un nuevo estudiante a partir de parámetros individuales.
	 *
	 * @param nombre      nombre del estudiante
	 * @param correo      correo electrónico
	 * @param edad        edad del estudiante
	 * @param contrasena  contraseña del estudiante
	 * @param nivelCompe  nivel de competencia
	 * @param semestre    semestre actual
	 * @return respuesta HTTP con mensaje de éxito o error
	 */
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

	/**
	 * Crea un nuevo estudiante a partir de un objeto JSON.
	 *
	 * @param newUser DTO con los datos del nuevo estudiante
	 * @return respuesta HTTP con mensaje de éxito o error
	 */
	@PostMapping(path = "/createjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<String> createNewWithJSON(@RequestBody EstudianteDTO newUser) {
		int status = estudianteService.create(newUser);
		if (status == 0) {
			return new ResponseEntity<>("{User create successfully}", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("{Error on created user, maybe username already in use}", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * Elimina un estudiante por su ID.
	 *
	 * @param id identificador del estudiante
	 * @return respuesta HTTP con mensaje de éxito o error
	 */
	@DeleteMapping(path = "/eliminar")
	public ResponseEntity<String> eliminar(@RequestParam Long id) {
		int status = estudianteService.deleteById(id);
		if (status == 0) {
			return new ResponseEntity<>("Eliminado con exito", HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>("Estudiante no encontrado", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Actualiza los datos de un estudiante por su ID.
	 *
	 * @param id          identificador del estudiante
	 * @param nombre      nuevo nombre
	 * @param correo      nuevo correo
	 * @param edad        nueva edad
	 * @param contrasena  nueva contraseña
	 * @param nivelCompe  nuevo nivel de competencia
	 * @param semestre    nuevo semestre
	 * @return respuesta HTTP con mensaje de éxito o error
	 */
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

	/**
	 * Muestra todos los estudiantes en formato de texto plano.
	 *
	 * @return respuesta HTTP con listado de estudiantes o mensaje de vacío
	 */
	@GetMapping(path = "/mostrar")
	public ResponseEntity<String> mostrar() {
		List<EstudianteDTO> lista = estudianteService.getAll();
		if (lista.isEmpty()) {
			return new ResponseEntity<>("Aun no existe ningun estudiante", HttpStatus.NOT_FOUND);
		} else {
			StringBuilder sb = new StringBuilder();
			lista.forEach(dto -> sb.append(dto.toString()).append("\n"));
			return new ResponseEntity<>("Estudiantes:\n" + sb.toString(), HttpStatus.ACCEPTED);
		}
	}

	/**
	 * Obtiene todos los estudiantes en formato JSON.
	 *
	 * @return respuesta HTTP con lista de estudiantes o estado vacío
	 */
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
