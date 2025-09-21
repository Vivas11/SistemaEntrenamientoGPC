package co.edu.unbosque.sistemaentrenamientoGPC_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import co.edu.unbosque.sistemaentrenamientoGPC_back.dto.DocPDFDTO;
import co.edu.unbosque.sistemaentrenamientoGPC_back.service.DocPDFService;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(path = { "/doc/pdf" })
public class DocPDFController {

	@Autowired
	private DocPDFService docPDFSer;

	@PostMapping(path = "/crear", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> crear(
	        @RequestParam("nombre") String nombre,
	        @RequestParam("descripcion") String descripcion,
	        @RequestPart("imagen") MultipartFile imagen,
	        @RequestPart("archivoPdf") MultipartFile archivoPdf) {
	    try {
	        int status = docPDFSer.create(nombre, descripcion, imagen, archivoPdf);
	        if (status == 1) {
	            return new ResponseEntity<>("Documento PDF leído con éxito", HttpStatus.CREATED);
	        } else {
	            return new ResponseEntity<>("Error al leer el documento PDF", HttpStatus.NOT_ACCEPTABLE);
	        }
	    } catch (IOException e) {
	        return new ResponseEntity<>("Error al procesar los archivos", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@GetMapping(path = "/descargar-imagen/{id}")
	public ResponseEntity<byte[]> descargarImagen(@PathVariable Long id) {
		byte[] imagen = docPDFSer.getImagenById(id);
		return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=\"imagen_" + id + ".jpg\"")
				.body(imagen);
	}

	@GetMapping(path = "/descargar-pdf/{id}")
	public ResponseEntity<byte[]> descargarPdf(@PathVariable Long id) {
		byte[] pdfContent = docPDFSer.getPdfContentById(id);
		return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=\"libro_" + id + ".pdf\"")
				.body(pdfContent);
	}
	
	@GetMapping("/getall")
	ResponseEntity<List<DocPDFDTO>> getAll() {
		List<DocPDFDTO> users = docPDFSer.getAll();
		if (users.isEmpty()) {
			return new ResponseEntity<>(users, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
		}
	}
}